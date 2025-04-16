package com.hirad.genus.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Album
{
    private String id;
    private String title;
    private LocalDate releaseDate;
    private Artist artist;
    private List<Artist> coArtists = new ArrayList<>();
    private List<Song> trackList;
    public Album(String title, LocalDate releaseDate, Artist artist)
    {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.releaseDate = releaseDate;
        this.artist = artist;
        this.coArtists = new ArrayList<>();
        this.trackList = new ArrayList<>();
    }
    public void addSong(Song song)
    {
        trackList.add(song);
    }
    public List<Song> getTrackList()
    {
        return trackList;
    }
    public String getTitle()
    {
        return title;
    }
    public LocalDate getReleaseDate()
    {
        return releaseDate;
    }
    public Artist getArtist()
    {
        return artist;
    }
    @Override
    public String toString()
    {
        return String.format("Album: %s | Artist: %s | Tracks: %d",
                title, artist.getUsername(), trackList.size());
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
}
