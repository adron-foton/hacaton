package org.data_base.dataSets;

public class UsersDataSet {
    private long id;
    private String first_name;
    private String last_name;

    private String login;
    private String password;

    public String getEmail_contact() {
        return email_contact;
    }

    private String email_contact;


    public UsersDataSet(long id, String first_name, String login, String password, String email_contact) {
        this.id = id;
        this.first_name = first_name;
        this.login = login;
        this.password = password;
        this.email_contact = email_contact;
    }

    public UsersDataSet() {

    }

    public String getFirst_name() {
        return first_name;
    }

    public long getID() {
        return id;
    }


    public String getLast_name() {
        return last_name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
