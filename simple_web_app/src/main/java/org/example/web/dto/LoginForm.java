package org.example.web.dto;

public class LoginForm {
    private Integer id;
    private String username;
    private String password;

    public void setUsername(String username) {this.username = username;}

    public void setPassword(String password) {this.password = password;}

    public String getUsername() {return username;}

    public String getPassword() {return password;}

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public LoginForm(Integer id, String username, String password){
        this.id       = id;
        this.username = username;
        this.password = password;
    }

    public LoginForm(){
    }

    @Override
    public String toString() {
        return "LoginForm{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

