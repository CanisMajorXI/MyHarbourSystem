package com.myharbour.pojo;

public class User {

    //判断是否失效
    public static final boolean STATUS_VALID = true;
    public static final boolean STATUS_INVALID = false;


    private Integer userId;

    private String password;

    private String email;

    private boolean valid;

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

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
