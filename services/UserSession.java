/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author yassi
 */
 public final class UserSession {

    public static UserSession instance;

    private String userName;
    private String privileges;

    private UserSession(String userName, String privileges) {
        this.userName = userName;
        this.privileges = privileges;
    }

    public static UserSession getInstace(String userName, String privileges) {
            instance = new UserSession(userName, privileges);
        
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public String getPrivileges() {
        return privileges;
    }

    public void cleanUserSession() {
        userName = "";// or null
        privileges = "";// or null
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + userName + '\'' +
                ", privileges=" + privileges +
                '}';
    }
}