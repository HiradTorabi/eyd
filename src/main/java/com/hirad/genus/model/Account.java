package com.hirad.genus.model;
import java.util.UUID;
import com.hirad.genus.utils.PasswordUtils;
import java.util.ArrayList;
import java.util.List;

/*protected List<Account> followers = new ArrayList<>();
protected List<Account> followedAccounts = new ArrayList<>();*/


public abstract class Account
{
    protected String id;
    protected String name;
    protected int age;
    protected String email;
    protected String username;
    protected String password;
    protected List<Account> followers = new ArrayList<>();
    protected List<Account> followedAccounts = new ArrayList<>();
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
    public void follow(Account target)
    {
        if (target == this)
        {
            System.out.println("❌ You cannot follow yourself.");
            return;
        }
        if (!followedAccounts.contains(target))
        {
            followedAccounts.add(target);
            target.followers.add(this);
            target.receiveFollowNotification(this);
            /*if (target instanceof User userTarget)
            {
                userTarget.addNotification(new Notification(this.getUsername() + " followed you."));
            }
            else if (target instanceof Artist artistTarget)
            {
                artistTarget.addNotification(new Notification(this.getUsername() + " followed you."));
            }*/

            System.out.println("✅ Followed " + target.getUsername());
        }
        else
        {
            System.out.println("You already follow " + target.getUsername());
        }
    }
    public void unfollow(Account target)
    {
        if (followedAccounts.remove(target))
        {
            target.followers.remove(this);
            System.out.println("❌ Unfollowed " + target.getUsername());
        }
        else
        {
            System.out.println("You don't follow " + target.getUsername());
        }
    }
    public List<Account> getFollowers()
    {
        return followers;
    }
    public List<Account> getFollowedAccounts()
    {
        return followedAccounts;
    }
    public abstract void receiveFollowNotification(Account follower);

}