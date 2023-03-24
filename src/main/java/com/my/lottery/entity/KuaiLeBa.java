package com.my.lottery.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.my.lottery.base.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * okr部门
 */
@TableName(value = "kuaileba", autoResultMap = true)
@Data
public class KuaiLeBa extends BaseEntity {
    private String code;
    private Date date;
    private String week;
    private String red;

}