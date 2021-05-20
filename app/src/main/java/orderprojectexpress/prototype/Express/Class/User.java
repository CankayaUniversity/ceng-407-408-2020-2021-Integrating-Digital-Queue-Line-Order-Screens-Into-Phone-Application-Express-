package orderprojectexpress.prototype.Express.Class;

import java.io.Serializable;

public class User implements Serializable
{
    // REGISTER
    private String name;
    private String email;
    private String userName;
    private String password;
    private String phone;

    public User()
    {

    }

    public User(String name, String email, String userName, String password, String phone)
    {
        this.name = name;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

