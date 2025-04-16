package com.hirad.genus.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.*;
import java.util.HashMap;
import java.util.Map;


public class Song
{
    private String id;
    private String title;
    private String lyrics;
    private Artist artist;
    private List<Comment> comments;
    private List<Artist> coArtists = new ArrayList<>();
    private int viewCount;
    private LocalDate releaseDate;
    private Map<User, Boolean> likesDislikes;
    public Song(String title, String lyrics, LocalDate releaseDate, Artist artist)
    {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.lyrics = lyrics;
        this.releaseDate = releaseDate;
        //this.artists = new ArrayList<>();
        this.artist = artist;
        this.coArtists = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.viewCount = 0;
        this.likesDislikes = new HashMap<>();
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
    public void addLike(User user) {
        likesDislikes.put(user, true);
    }

    public void addDislike(User user) {
        likesDislikes.put(user, false);
    }

    public void removeVote(User user) {
        likesDislikes.remove(user);
    }

    public int getLikeCount() {
        return (int) likesDislikes.values().stream().filter(v -> v).count();
    }

    public int getDislikeCount() {
        return (int) likesDislikes.values().stream().filter(v -> !v).count();
    }

    public boolean hasLiked(User user) {
        return likesDislikes.getOrDefault(user, false);
    }

    public boolean hasDisliked(User user) {
        Boolean vote = likesDislikes.get(user);
        return vote != null && !vote;
    }
    @Override
    public String toString() {
        return String.format("🎵 %s | 👤 %s | 👍 %d 👎 %d | Views: %d",
                title, artist.getUsername(), getLikeCount(), getDislikeCount(), viewCount);
    }
    public void setLyrics(String lyrics)
    {
        this.lyrics = lyrics;
    }
    public Artist getArtist()
    {
        return artist;
    }
    public List<Artist> getCoArtists()
    {
        return coArtists;
    }

    public void addCoArtist(Artist artist)
    {
        if (!coArtists.contains(artist))
        {
            coArtists.add(artist);
        }
    }

    public void removeCoArtist(Artist artist)
    {
        coArtists.remove(artist);
    }

    public boolean isCoArtist(Artist artist)
    {
        return coArtists.contains(artist);
    }


}
