package com.hirad.genus.seed;

import com.hirad.genus.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SeedData
{
    public static List<User> users = new ArrayList<>();
    public static List<Artist> artists = new ArrayList<>();
    public static List<Admin> admins = new ArrayList<>();
    public static List<Song> songs = new ArrayList<>();
    public static List<Album> albums = new ArrayList<>();
    public static void generate()
    {
        // Admin
        Admin admin = new Admin("Admin One", 40, "admin@example.com", "admin1", "adminpass");
        admins.add(admin);
        // Artists
        Artist artist1 = new Artist("Ali Music", 30, "ali@music.com", "alimusic", "pass123");
        Artist artist2 = new Artist("Sara Beats", 25, "sara@beats.com", "sarabeats", "beatme");
        artists.add(artist1);
        artists.add(artist2);
        // Users
        User user1 = new User("Reza", 20, "reza@gmail.com", "rezalistener", "pass1");
        User user2 = new User("Niloofar", 22, "nilo@domain.com", "nilo", "pass2");
        users.add(user1);
        users.add(user2);
        // Songs
        Song song1 = new Song("Rainy Days", "Raindrops keep falling...", LocalDate.of(2023, 5, 1));
        song1.incrementView(); song1.incrementView();
        song1.addComment(new Comment(user1, "Awesome lyrics!"));
        artist1.addSong(song1);
        songs.add(song1);
        Song song2 = new Song("Golden Sky", "Shine on me...", LocalDate.of(2022, 10, 15));
        song2.incrementView();
        artist2.addSong(song2);
        songs.add(song2);
        // Albums
        Album album1 = new Album("Ali's Vibes", LocalDate.of(2023, 6, 10), artist1);
        album1.addSong(song1);
        artist1.addAlbum(album1);
        albums.add(album1);
        // Follow
        user1.followArtist(artist1);
        user2.followArtist(artist2);
        // Edit request
        LyricEditRequest edit1 = new LyricEditRequest(user1, song1, "Raindrops keep falling on my head...");
        user1.addEditRequest(edit1);
        // Admin approves artist and lyric
        admin.approveArtist(artist1);
        admin.reviewLyricEdit(edit1, true);
        System.out.println("✅ Seed data generated successfully.");
    }
}
