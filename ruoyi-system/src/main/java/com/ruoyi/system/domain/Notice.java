package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import javax.persistence.Table;

/**
 * 通知公告表 sys_notice
 *
 * @author ruoyi
 */
@Data
@Table(name = "sys_notice")
public class Notice extends BaseEntity<BaseEntity> {
    private static final long serialVersionUID = 1L;

    /**
     * 公告ID
     */
    private Long noticeId;

    /**
     * 公告标题
     */
    private String noticeTitle;

    /**
     * 公告类型（1通知 2公告）
     */
    private String noticeType;

    /**
     * 公告内容
     */
    private String noticeContent;

    /**
     * 公告状态（0正常 1关闭）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

}
