package com.imooc.oa.entity;

import java.util.Date;

public class Notice {
    private Long noticeId;
    private Long receiverId;
    private String content;
    private Date createTime = new Date();

    public Notice() {
    }

    public Notice(Long receiverId, String content) {
        this.receiverId = receiverId;
        this.content = content;
    }

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
