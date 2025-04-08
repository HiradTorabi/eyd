package com.hirad.genus.model;

import java.util.List;
import java.util.ArrayList;


public class Admin extends Account
{
    public Admin(String name, int age, String email, String username, String password)
    {
        super(name, age, email, username, password);
    }
    @Override
    public String getRole()
    {
        return "Admin";
    }
    public void approveArtist(Artist artist)
    {
        artist.verify();
        System.out.println("✅ Artist approved: " + artist.getUsername());
    }
    public void reviewLyricEdit(LyricEditRequest request, boolean approve)
    {
        if (approve)
        {
            request.approve();
            System.out.println("✅ Edit request approved for: " + request.getSong().getTitle());
        }
        else
        {
            request.reject();
            System.out.println("❌ Edit request rejected for: " + request.getSong().getTitle());
        }
    }
    protected List<Notification> notifications = new ArrayList<>();

    public void addNotification(Notification notification) {
        notifications.add(notification);
    }

    public void checkNotifications()
    {
        System.out.println("🔔 Notifications:");
        if (notifications.isEmpty())
        {
            System.out.println("No notifications.");
        }
        else
        {
            for (Notification n : notifications)
            {
                System.out.println(n);
                n.markAsSeen();
            }
        }
    }
    @Override
    public void receiveFollowNotification(Account follower)
    {
        addNotification(new Notification(follower.getUsername() + " followed you."));
    }

}
