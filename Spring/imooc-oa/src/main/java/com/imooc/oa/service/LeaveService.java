package com.imooc.oa.service;

import com.imooc.oa.constants.BusinessConstants;
import com.imooc.oa.dao.EmployeeDao;
import com.imooc.oa.dao.LeaveFormDao;
import com.imooc.oa.dao.NoticeDao;
import com.imooc.oa.dao.ProcessFlowDao;
import com.imooc.oa.entity.Employee;
import com.imooc.oa.entity.LeaveForm;
import com.imooc.oa.entity.Notice;
import com.imooc.oa.entity.ProcessFlow;
import com.imooc.oa.service.exception.BusinessException;
import com.imooc.oa.utils.MybatisUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LeaveService {

    public LeaveForm createLeaveFrom(LeaveForm leaveForm) {
        LeaveForm savedForm = (LeaveForm) MybatisUtils.executeQuery(sqlSession -> {
            LeaveFormDao leaveFormDao = sqlSession.getMapper(LeaveFormDao.class);
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            ProcessFlowDao processFlowDao = sqlSession.getMapper(ProcessFlowDao.class);
            NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);
            Long employeeId = leaveForm.getEmployeeId();
            Employee employee = employeeDao.selectEmployeeById(employeeId);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH时");
            Long time = leaveForm.getEndTime().getTime() - leaveForm.getStartTime().getTime();
            Float hours = time / (1000 * 60 * 60 * 1f);
            if (employee.getLevel() == 8) {
                leaveForm.setState("approve");
            } else {
                leaveForm.setState("process");
            }
            leaveFormDao.insert(leaveForm);

            ProcessFlow flow = new ProcessFlow();
            flow.setAction("apply");
            flow.setIsLast(0);
            flow.setFormId(leaveForm.getFormId());
            flow.setOrderNo(1);
            flow.setState("complete");
            flow.setReason(leaveForm.getReason());
            flow.setOperatorId(leaveForm.getEmployeeId());
            flow.setCreateTime(new Date());
            processFlowDao.insert(flow);
            if (employee.getLevel() < 7) {
                Employee manager = employeeDao.selectLeader(employee);
                ProcessFlow flow1 = new ProcessFlow();
                flow1.setOrderNo(2);
                flow1.setOperatorId(manager.getEmployeeId());
                flow1.setAction("audit");
                flow1.setState("process");
                flow1.setCreateTime(new Date());
                flow1.setFormId(leaveForm.getFormId());
                if (hours >= BusinessConstants.MANAGER_AUDIT_HOURS) {
                    flow1.setIsLast(0);
                    processFlowDao.insert(flow1);
                    Employee leader = employeeDao.selectLeader(manager);
                    ProcessFlow flow2 = new ProcessFlow();
                    flow2.setOrderNo(3);
                    flow2.setAction("audit");
                    flow2.setOperatorId(leader.getEmployeeId());
                    flow2.setState("ready");
                    flow2.setCreateTime(new Date());
                    flow2.setFormId(leaveForm.getFormId());
                    flow2.setIsLast(1);
                    processFlowDao.insert(flow2);
                    String noticeMessage = String.format("您的请假审批[%s-%s]已提交，请等待审批~",sdf.format(leaveForm.getStartTime()), sdf.format(leaveForm.getEndTime()));
                    Notice notice = new Notice(leaveForm.getEmployeeId(), noticeMessage);
                    noticeDao.insert(notice);
                    noticeMessage = String.format("%s-%s提交了请假申请[%s-%s]，请尽快审批~", employee.getTitle(), employee.getName(), sdf.format(leaveForm.getStartTime()), sdf.format(leaveForm.getEndTime()));
                    notice = new Notice(manager.getEmployeeId(), noticeMessage);
                    noticeDao.insert(notice);
                } else {
                    flow1.setIsLast(1);
                    processFlowDao.insert(flow1);
                }
            } else if (employee.getLevel() == 7) {
                Employee leader = employeeDao.selectLeader(employee);
                ProcessFlow flow2 = new ProcessFlow();
                flow2.setOrderNo(2);
                flow2.setAction("audit");
                flow2.setOperatorId(leader.getEmployeeId());
                flow2.setState("process");
                flow2.setCreateTime(new Date());
                flow2.setIsLast(1);
                flow2.setFormId(leaveForm.getFormId());
                processFlowDao.insert(flow2);
                String noticeMessage = String.format("您的请假审批[%s-%s]已提交，请等待审批~",sdf.format(leaveForm.getStartTime()), sdf.format(leaveForm.getEndTime()));
                Notice notice = new Notice(leaveForm.getEmployeeId(), noticeMessage);
                noticeDao.insert(notice);
                noticeMessage = String.format("%s-%s提交了请假申请[%s-%s]，请尽快审批~", employee.getTitle(), employee.getName(), sdf.format(leaveForm.getStartTime()), sdf.format(leaveForm.getEndTime()));
                notice = new Notice(leader.getEmployeeId(), noticeMessage);
                noticeDao.insert(notice);
            } else if (employee.getLevel() == 8) {
                ProcessFlow flow2 = new ProcessFlow();
                flow2.setOrderNo(2);
                flow2.setAction("audit");
                flow2.setOperatorId(employee.getEmployeeId());
                flow2.setReason("自动通过");
                flow2.setState("complete");
                flow2.setCreateTime(new Date());
                flow2.setIsLast(1);
                flow2.setFormId(leaveForm.getFormId());
                processFlowDao.insert(flow2);
                String noticeMessage = String.format("您的请假审批[%s-%s]已通过。",sdf.format(leaveForm.getStartTime()), sdf.format(leaveForm.getEndTime()));
                Notice notice = new Notice(leaveForm.getEmployeeId(), noticeMessage);
                noticeDao.insert(notice);
            }
            sqlSession.commit();
            return leaveForm;
        });
        return savedForm;
    }

    public List<Map> getLeaveList(Long userId, String status) {
        return (List<Map>) MybatisUtils.executeQuery(sqlSession -> {
            LeaveFormDao leaveFormDao = sqlSession.getMapper(LeaveFormDao.class);
            return leaveFormDao.selectLeaveFormList(userId, status);
        });
    }

    public void audit(Long formId, Long operatorId, String result, String reason) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH时");
        MybatisUtils.executeQuery(sqlSession -> {
            //1.获取flow并且设置为complete
            ProcessFlowDao processFlowDao = sqlSession.getMapper(ProcessFlowDao.class);
            NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            LeaveFormDao leaveFormDao = sqlSession.getMapper(LeaveFormDao.class);
            LeaveForm leaveForm = leaveFormDao.selectById(formId);
            Employee employee = employeeDao.selectEmployeeById(leaveForm.getEmployeeId());
            List<ProcessFlow> flows = processFlowDao.selectProcessFlowByFormId(formId);
            if (flows == null) {
                throw new BusinessException("PF001", "无效的表单ID");
            }
            List<ProcessFlow> processFlows = flows.stream().filter(p -> "process".equals(p.getState()) && p.getOperatorId() == operatorId).collect(Collectors.toList());
            if (processFlows == null){
                throw new BusinessException("PF002", "该表单没有流程中的flow");
            }
            ProcessFlow processFlow = processFlows.get(0);
            processFlow.setResult(result);
            processFlow.setReason(reason);
            processFlow.setState("complete");
            processFlow.setAuditTime(new Date());
            processFlowDao.update(processFlow);
            //2.如果当前flow是最后一个，就更新请假表单
            if (processFlow.getIsLast() == 1) {
                leaveForm = leaveFormDao.selectById(formId);
                leaveForm.setState(result);
                leaveForm.setReason(reason);
                leaveForm.setEndTime(new Date());
                leaveFormDao.update(leaveForm);
                String resultMessage = null;
                if ("approved".equals(result)){
                    resultMessage = "批准";
                }else if ("refused".equals(result)){
                    resultMessage = "驳回";
                }
                String noticeMessage = String.format("您的请假审批[%s-%s]已%s，审批意见：%s~",sdf.format(leaveForm.getStartTime()), sdf.format(leaveForm.getEndTime()), resultMessage, reason);
                Notice notice = new Notice(leaveForm.getEmployeeId(), noticeMessage);
                noticeDao.insert(notice);
                noticeMessage = String.format("您已批准%s-%s的请假申请[%s-%s]，审批意见：%s~",employee.getTitle(), employee.getName(), sdf.format(leaveForm.getStartTime()), sdf.format(leaveForm.getEndTime()),reason);
                notice = new Notice(processFlow.getOperatorId(), noticeMessage);
                noticeDao.insert(notice);
            } else {
                //3.如果不是最后一个flow,并且同意，就将下一个flow设置为process
                if ("approved".equals(result)) {
                    leaveForm = leaveFormDao.selectById(formId);
                    List<ProcessFlow> readyFlows = flows.stream().filter(p -> "ready".equals(p.getState())).collect(Collectors.toList());
                    ProcessFlow readyFlow = readyFlows.get(0);
                    readyFlow.setState("process");
                    processFlowDao.update(readyFlow);
                    String noticeMessage = String.format("您的请假审批[%s-%s]已批准，审批意见：%s，请等待下级领导审批~",sdf.format(leaveForm.getStartTime()), sdf.format(leaveForm.getEndTime()), reason);
                    Notice notice = new Notice(leaveForm.getEmployeeId(), noticeMessage);
                    noticeDao.insert(notice);
                    noticeMessage = String.format("您已批准%s-%s的请假申请[%s-%s]，审批意见：%s~",employee.getTitle(), employee.getName(), sdf.format(leaveForm.getStartTime()), sdf.format(leaveForm.getEndTime()), reason);
                    notice = new Notice(processFlow.getOperatorId(), noticeMessage);
                    noticeDao.insert(notice);
                    noticeMessage = String.format("%s-%s提交了请假申请[%s-%s]，请尽快审批~", employee.getTitle(), employee.getName(), leaveForm.getStartTime(), leaveForm.getEndTime());
                    notice = new Notice(readyFlow.getOperatorId(), noticeMessage);
                    noticeDao.insert(notice);
                } else if ("refused".equals(result)) {
                    //4.如果不同意，就将其他ready状态的flow取消，并更新form状态
                    List<ProcessFlow> readyFlows = flows.stream().filter(p -> "ready".equals(p.getState())).collect(Collectors.toList());
                    for (ProcessFlow p : readyFlows){
                        p.setState("cancel");
                        processFlowDao.update(p);
                    }
                    leaveForm = leaveFormDao.selectById(formId);
                    leaveForm.setState(result);
                    leaveForm.setReason(reason);
                    leaveForm.setEndTime(new Date());
                    leaveFormDao.update(leaveForm);
                    String noticeMessage = String.format("您的请假审批[%s-%s]已驳回，审批意见：%s~",sdf.format(leaveForm.getStartTime()), sdf.format(leaveForm.getEndTime()), reason);
                    Notice notice = new Notice(leaveForm.getEmployeeId(), noticeMessage);
                    noticeDao.insert(notice);
                    noticeMessage = String.format("您已驳回%s-%s的请假申请[%s-%s]，审批意见：%s~",employee.getTitle(), employee.getName(), sdf.format(leaveForm.getStartTime()), sdf.format(leaveForm.getEndTime()), reason);
                    notice = new Notice(processFlow.getOperatorId(), noticeMessage);
                    noticeDao.insert(notice);
                }
            }
            sqlSession.commit();
            return null;
        });
    }

}














