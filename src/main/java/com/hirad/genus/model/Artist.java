package com.hirad.genus.model;

import java.util.ArrayList;
import java.util.List;

public class Artist extends Account
{
    private List<Song> songs;
    private List<Album> albums;
    private boolean isVerified;
    public Artist(String name, int age, String email, String username, String password)
    {
        super(name, age, email, username, password);
        this.songs = new ArrayList<>();
        this.albums = new ArrayList<>();
        this.isVerified = false;
    }
    @Override
    public String getRole()
    {
        return "Artist";
    }
    public List<Song> getSongs()
    {
        return songs;
    }
    public List<Album> getAlbums()
    {
        return albums;
    }
    public void addSong(Song song)
    {
        songs.add(song);
    }
    public void addAlbum(Album album)
    {
        albums.add(album);
    }
    public boolean isVerified()
    {
        return isVerified;
    }
    public void verify()
    {
        this.isVerified = true;
    }
    public String getPopularSong()
    {
        if (songs.isEmpty()) return "No songs yet.";
        return songs.stream()
                .max((a, b) -> a.getViewCount() - b.getViewCount())
                .get()
                .getTitle();
    }
    public String profileSummary()
    {
        return String.format("Artist: %s | Verified: %b | Songs: %d | Albums: %d | Top Song: %s",
                getUsername(), isVerified, songs.size(), albums.size(), getPopularSong());
    }
}
