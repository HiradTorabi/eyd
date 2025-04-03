package com.hirad.genus.model;
import java.util.UUID;
import com.hirad.genus.utils.PasswordUtils;


public abstract class Account
{
    protected String id;
    protected String name;
    protected int age;
    protected String email;
    protected String username;
    protected String password;
    public Account(String name, int age, String email, String username, String password)
    {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.age = age;
        this.email = email;
        this.username = username;
        this.password = PasswordUtils.hash(password);

    }
    public String getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }
    public String getEmail()
    {
        return email;
    }
    public String getUsername()
    {
        return username;
    }
    public String getPassword()
    {
        return password;
    }
    public abstract String getRole();
    @Override
    public String toString()
    {
        return String.format("[%s] %s (%s)", getRole(), name, username);
    }
    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Account))
        {
            return false;
        }
        Account other = (Account) obj;
        return this.username.equals(other.username);
    }
}