package org.data_base;

public class UserProfile {
    private String login = "";
    private String password = "";
    private long index;


    public UserProfile(String login, String password) {
        this.index = ProfileHandler.getLastIndex();
        this.login = login;
        this.password = password;


    }
    public String getLogin() {
        return login;
    }

}
