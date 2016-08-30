package com.ws.sishuok.entity;

public class User {
    /**
     *
     */

    private static String userId = "USER_ID";
    private static String userName = "USER_NAME";
    private static String loginName = "LOGIN_NAME";

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        User.userId = userId;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        User.userName = userName;
    }

    public static String getLoginName() {
        return loginName;
    }

    public static void setLoginName(String loginName) {
        User.loginName = loginName;
    }
}
