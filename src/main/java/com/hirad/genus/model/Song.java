package com.hirad.genus.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Song
{
    private String id;
    private String title;
    private String lyrics;
    private List<Artist> artists;
    private List<Comment> comments;
    private int viewCount;
    private LocalDate releaseDate;
    public Song(String title, String lyrics, LocalDate releaseDate)
    {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.lyrics = lyrics;
        this.releaseDate = releaseDate;
        this.artists = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.viewCount = 0;
    }
    public void addComment(Comment comment)
    {
        comments.add(comment);
    }
    public void incrementView()
    {
        viewCount++;
    }
    public List<Comment> getComments()
    {
        return comments;
    }
    public int getViewCount()
    {
        return viewCount;
    }
    public String getTitle()
    {
        return title;
    }

    public String getLyrics()
    {
        return lyrics;
    }
    @Override
    public String toString()
    {
        return String.format("Song: %s | Views: %d", title, viewCount);
    }
    public void setLyrics(String lyrics)
    {
        this.lyrics = lyrics;
    }
}
