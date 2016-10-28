package com.zwj.basedemo.bean;

import android.text.TextUtils;

/**
 * Created by Administrator on 2016/5/31.
 * 参数的实体类，包含真正的值和显示的文本
 */
public class ParamBean {
    // 如品牌，name barnd，value 1, tag 大众
    private String name;        // 参数名称
    private String tag;         // 参数描述
    private String value;       // 参数的值

    public ParamBean() {
    }

    public ParamBean(String tag, String value) {
        this.tag = tag;
        this.value = value;
    }

    public String getTag() {
        return tag;
    }

    public ParamBean setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public String getValue() {
        return value;
    }

    public ParamBean setValue(String value) {
        this.value = value;
        return this;
    }

    public void set(String tag, String value) {
        this.tag = tag;
        this.value = value;
    }

    public void reset() {
        tag = null;
        value = null;
    }

    /**
     * 判断用户是否有选择该筛选条件
     * @return
     */
    public boolean isSelected() {
        if(TextUtils.isEmpty(value) || "false".equalsIgnoreCase(value)) {
               return false;
        }
        return true;
    }

    public boolean isEmpty() {
        if(TextUtils.isEmpty(value) && TextUtils.isEmpty(tag)) {
            return true;
        }

        return false;
    }

    public String getName() {
        return name;
    }

    public ParamBean setName(String name) {
        this.name = name;
        return this;
    }
}
