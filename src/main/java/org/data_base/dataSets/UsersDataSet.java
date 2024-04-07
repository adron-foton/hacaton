package org.data_base.dataSets;

public class UsersDataSet {
    private long id;
    private String first_name;
    private String last_name;

    private String login;
    private String password;

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

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "UsersDataSet{" +
                "id=" + id +
                ", name='" + first_name + '\'' +
                '}';
    }
}
