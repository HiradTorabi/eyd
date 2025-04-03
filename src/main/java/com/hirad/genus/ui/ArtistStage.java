package com.hirad.genus.ui;

import com.hirad.genus.model.*;
import com.hirad.genus.seed.SeedData;

import java.time.LocalDate;
import java.util.Scanner;

public class ArtistStage
{
    private final Artist artist;
    private final Scanner scanner;
    public ArtistStage(Artist artist)
    {
        this.artist = artist;
        this.scanner = new Scanner(System.in);
    }
    public void run()
    {
        while (true)
        {
            System.out.println("\n-*-*-*-Artist Menu-*-*-*-");
            System.out.println("1. View My Songs");
            System.out.println("2. View My Albums");
            System.out.println("3. Create Song");
            System.out.println("4. Create Album");
            System.out.println("5. Review Edit Requests");
            System.out.println("0. Logout");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();
            switch (choice)
            {
                case "1" -> viewSongs();
                case "2" -> viewAlbums();
                case "3" -> createSong();
                case "4" -> createAlbum();
                case "5" -> reviewEdits();
                case "0" ->
                {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
    private void viewSongs()
    {
        System.out.println("-*-*-*-My Songs-*-*-*-");
        for (Song song : artist.getSongs())
        {
            System.out.println("- " + song.getTitle());
        }
    }
    private void viewAlbums()
    {
        System.out.println("-*-*-*-My Albums-*-*-*-");
        for (Album album : artist.getAlbums())
        {
            System.out.println("- " + album.getTitle());
        }
    }
    private void createSong()
    {
        System.out.print("Song Title: ");
        String title = scanner.nextLine();
        System.out.print("Lyrics: ");
        String lyrics = scanner.nextLine();
        LocalDate date = LocalDate.now();
        Song song = new Song(title, lyrics, date);
        song.incrementView();
        artist.addSong(song);
        SeedData.songs.add(song);
        System.out.println("✅ Song created successfully.");
    }
    private void createAlbum()
    {
        System.out.print("Album Title: ");
        String title = scanner.nextLine();
        LocalDate date = LocalDate.now();
        Album album = new Album(title, date, artist);
        artist.addAlbum(album);
        SeedData.albums.add(album);
        System.out.println("✅ Album created. Add songs to it manually for now.");
    }
    private void reviewEdits()
    {
        System.out.println("-*-*-*-Review Edit Requests-*-*-*-");
        for (User user : SeedData.users)
        {
            for (LyricEditRequest req : user.getEditRequests())
            {
                if (req.getSong() != null && artist.getSongs().contains(req.getSong()) && !req.isApproved())
                {
                    System.out.println("- Song: " + req.getSong().getTitle());
                    System.out.println("Suggested by: " + req.getRequester().getUsername());
                    System.out.println("New Lyrics: " + req.getProposedLyrics());
                    System.out.print("Approve this edit? (yes/no): ");
                    String ans = scanner.nextLine();
                    if (ans.equalsIgnoreCase("yes"))
                    {
                        req.approve();
                        System.out.println("✅ Approved.");
                    }
                    else
                    {
                        req.reject();
                        System.out.println("❌Rejected❌");
                    }
                }
            }
        }
    }
}
