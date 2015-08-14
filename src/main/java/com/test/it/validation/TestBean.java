package com.test.it.validation;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * Author: caizh
 * CreateTime: 2015/5/19 16:29
 * Version: 1.0
 */
public class TestBean {
    @NotBlank(message = "名称非空")
    @Pattern(regexp = "[a-zA-z0-9_]*", message = "字母数字下划线")
    private String name;

    @NotBlank(message = "code非空")
    @Pattern(regexp = "[a-zA-z0-9_]*", message = "字母数字下划线")
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
