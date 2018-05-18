package com.myharbour.pojo;

public class User {

    //用户分3种，操作员(operator)，货主(shipper)，管理员(admin)
    public static final int TYPE_OPERATOR = 1;
    public static final int TYPE_SHIPPER = 2;
    public static final int TYPE_ADMIN = 3;

    //判断是否失效
    public static final boolean STATUS_VALID = true;
    public static final boolean STATUS_INVALID = false;


    private Integer userId;

    private String password;

    private String email;

    private Boolean valid;

    private Integer type;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
