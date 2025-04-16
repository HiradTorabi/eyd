package com.hirad.genus.model;

import java.util.ArrayList;
import java.util.List;

public class Artist extends Account
{
    private boolean verified = false;
    private List<Album> albums = new ArrayList<>();
    private List<Song> songs = new ArrayList<>();
    private List<Notification> notifications = new ArrayList<>();
    public Artist(String name, int age, String email, String username, String password)
    {
        super(name, age, email, username, password);
    }
    public boolean isVerified()
    {
        return verified;
    }
    public void setVerified(boolean verified)
    {
        this.verified = verified;
    }
    public List<Album> getAlbums()
    {
        return albums;
    }
    public void addAlbum(Album album)
    {
        albums.add(album);
    }
    public List<Song> getSongs()
    {
        return songs;
    }
    public void addSong(Song song)
    {
        songs.add(song);
    }
    public void addNotification(Notification n)
    {
        notifications.add(n);
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
    @Override
    public String getRole()
    {
        return "Artist";
    }
    public void verify()
    {
        this.verified = true;
    }
   /* public String getFullName() {
        return this.name;
    }*/


}
