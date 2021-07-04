package com.imooc.oa.service;

import org.junit.Test;

import java.util.List;
import java.util.Map;

public class LeaveServiceTest{

    private LeaveService leaveService = new LeaveService();
    @Test
    public void testGetLeaveList() {
        List<Map> list = leaveService.getLeaveList(2L, "process");
        System.out.println(list);
    }

    /**
     * 请假3天以上,部门经理审批通过
     */
    @Test
    public void audit1(){
        leaveService.audit(31l,2l,"approved","祝早日康复");
    }

    /**
     * 请假3天以上,部门经理审批驳回
     */
    @Test
    public void audit2(){
        leaveService.audit(32l,2l,"refused","工期紧张,请勿拖延");
    }

    /**
     * 部门经理请假,总经理审批通过
     */
    @Test
    public void audit3(){
        leaveService.audit(33l,1l,"approved","同意");
    }
}