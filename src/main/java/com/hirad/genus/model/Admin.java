package com.hirad.genus.model;
import java.util.List;

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
}
