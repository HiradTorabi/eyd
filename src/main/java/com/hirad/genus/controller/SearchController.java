package com.hirad.genus.controller;

import com.hirad.genus.model.Album;
import com.hirad.genus.model.Artist;
import com.hirad.genus.model.Song;

import java.util.List;

public class SearchController
{

    public static void searchSongs(List<Song> songs, String keyword)
    {
        System.out.println("\n🔍Matching Songs🔍");
        for (Song song : songs)
        {
            if (song.getTitle().toLowerCase().contains(keyword.toLowerCase()))
            {
                System.out.println("- " + song.getTitle());
            }
        }
    }

    public static void searchArtists(List<Artist> artists, String keyword)
    {
        System.out.println("\n🎤 Matching Artists:");
        for (Artist artist : artists)
        {
            if (artist.getUsername().toLowerCase().contains(keyword.toLowerCase()) ||
                    artist.getName().toLowerCase().contains(keyword.toLowerCase()))
            {
                System.out.println("- " + artist.getUsername());
            }
        }
    }

    public static void searchAlbums(List<Album> albums, String keyword)
    {
        System.out.println("\n💿 Matching Albums:");
        for (Album album : albums)
        {
            if (album.getTitle().toLowerCase().contains(keyword.toLowerCase()))
            {
                System.out.println("- " + album.getTitle());
            }
        }
    }
}
