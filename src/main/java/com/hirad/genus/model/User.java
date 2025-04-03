package com.hirad.genus.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User extends Account
{
    private List<Artist> followedArtists;
    private List<LyricEditRequest> editRequests;
    private List<Song> viewedHistory;
    public User(String name, int age, String email, String username, String password)
    {
        super(name, age, email, username, password);
        this.followedArtists = new ArrayList<>();
        this.editRequests = new ArrayList<>();
        this.viewedHistory = new ArrayList<>();
    }

    @Override
    public String getRole()
    {
        return "User";
    }
    public List<Artist> getFollowedArtists()
    {
        return followedArtists;
    }
    public void followArtist(Artist artist)
    {
        if (!followedArtists.contains(artist))
        {
            followedArtists.add(artist);
        }
    }
    public void unfollowArtist(Artist artist)
    {
        followedArtists.remove(artist);
    }
    public boolean isFollowing(Artist artist)
    {
        return followedArtists.contains(artist);
    }
    public List<LyricEditRequest> getEditRequests()
    {
        return editRequests;
    }
    public void addEditRequest(LyricEditRequest request)
    {
        editRequests.add(request);
    }
    public List<Song> getViewedHistory()
    {
        return viewedHistory;
    }
    public void addToViewedHistory(Song song)
    {
        viewedHistory.add(song);
    }
    public List<Song> getRecentViews(int count)
    {
        int size = viewedHistory.size();
        if (size == 0) return Collections.emptyList();
        return viewedHistory.subList(Math.max(size - count, 0), size);
    }
    public void commentOnSong(Song song, String content)
    {
        Comment comment = new Comment(this, content);
        song.addComment(comment);
    }
    public String summary()
    {
        return String.format("User: %s | Following: %d | Viewed: %d",
                getUsername(), followedArtists.size(), viewedHistory.size());
    }
    public void showNotifications()
    {
        System.out.println("🔔Notifications🔔");
        for (Artist artist : followedArtists)
        {
            List<Song> songs = artist.getSongs();
            if (!songs.isEmpty())
            {
                Song last = songs.get(songs.size() - 1);
                System.out.println("- " + artist.getUsername() + " released: " + last.getTitle());
            }
        }
    }

}
