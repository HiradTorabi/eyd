package com.hirad.genus.controller;

import com.hirad.genus.model.Comment;
import com.hirad.genus.model.Song;
import com.hirad.genus.model.User;

import java.util.List;

public class SongController
{
    public static void viewSongDetails(Song song)
    {
        System.out.println("\n🎵Song Details🎵");
        System.out.println("Title: " + song.getTitle());
        System.out.println("Lyrics:\n" + song.getLyrics());
        System.out.println("Views: " + song.getViewCount());
        System.out.println("\n--- Comments ---");
        for (Comment comment : song.getComments())
        {
            System.out.println(comment);
        }
    }
    public static void showRecentSongs(List<Song> songs, int limit)
    {
        System.out.println("\n🎧 Recently Added Songs:");
        int count = Math.min(songs.size(), limit);
        for (int i = 0; i < count; i++)
        {
            System.out.println("- " + songs.get(i).getTitle());
        }
    }

    public static void showTopSongs(List<Song> songs, int limit)
    {
        System.out.println("\n📈 Top Songs by Views:");
        songs.stream()
                .sorted((a, b) -> b.getViewCount() - a.getViewCount())
                .limit(limit)
                .forEach(song -> System.out.printf("- %s (%d views)\n", song.getTitle(), song.getViewCount()));
    }
    public static void viewSong(Song song, User viewer)
    {
        song.incrementView();
        viewer.addToViewedHistory(song);
        viewSongDetails(song);
    }
    public static void likeComment(Song song, Comment comment, User user)
    {
        if (comment.hasLiked(user))
        {
            comment.removeVote(user);
        }
        else
        {
            comment.addLike(user);
        }
    }
    public static void dislikeComment(Song song, Comment comment, User user)
    {
        if (comment.hasDisliked(user))
        {
            comment.removeVote(user);
        }
        else
        {
            comment.addDislike(user);
        }
    }
    public static void likeSong(Song song, User user)
    {
        if (song.hasLiked(user))
        {
            song.removeVote(user);
        }
        else
        {
            song.addLike(user);
        }
    }

    public static void dislikeSong(Song song, User user)
    {
        if (song.hasDisliked(user))
        {
            song.removeVote(user);
        }
        else
        {
            song.addDislike(user);
        }
    }
}