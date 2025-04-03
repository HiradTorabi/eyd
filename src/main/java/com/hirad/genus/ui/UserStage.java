package com.hirad.genus.ui;

import com.hirad.genus.model.*;
import com.hirad.genus.seed.SeedData;

import java.util.Scanner;

public class UserStage
{
    private final User user;
    private final Scanner scanner;
    public UserStage(User user)
    {
        this.user = user;
        this.scanner = new Scanner(System.in);
    }
    public void run()
    {
        while (true)
        {
            System.out.println("\n-*-*-*-User Menu-*-*-*-");
            System.out.println("1. View Followed Artists");
            System.out.println("2. View All Songs");
            System.out.println("3. Comment on a Song");
            System.out.println("4. Suggest Lyric Edit");
            System.out.println("0. Logout");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();
            switch (choice)
            {
                case "1" -> viewFollowedArtists();
                case "2" -> viewAllSongs();
                case "3" -> commentOnSong();
                case "4" -> suggestLyricEdit();
                case "0" ->
                {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
    private void viewFollowedArtists()
    {
        System.out.println("-*-*-Followed Artists-*-*-");
        if (user.getFollowedArtists().isEmpty())
        {
            System.out.println("You are not following any artists.");
            return;
        }
        for (Artist artist : user.getFollowedArtists())
        {
            System.out.println("- " + artist.getUsername());
        }
    }
    private void viewAllSongs()
    {
        System.out.println("-*-*-All Songs-*-*-");
        for (int i = 0; i < SeedData.songs.size(); i++)
        {
            Song song = SeedData.songs.get(i);
            System.out.printf("%d. %s (%d views)\n", i + 1, song.getTitle(), song.getViewCount());
        }
    }
    private void commentOnSong()
    {
        viewAllSongs();
        System.out.print("Enter song number to comment on: ");
        int idx = Integer.parseInt(scanner.nextLine()) - 1;
        if (idx < 0 || idx >= SeedData.songs.size())
        {
            System.out.println("Invalid index.");
            return;
        }
        Song song = SeedData.songs.get(idx);
        System.out.print("Your comment: ");
        String content = scanner.nextLine();
        user.commentOnSong(song, content);
        System.out.println("Comment added!");
    }
    private void suggestLyricEdit()
    {
        viewAllSongs();
        System.out.print("Enter song number to suggest edit: ");
        int idx = Integer.parseInt(scanner.nextLine()) - 1;
        if (idx < 0 || idx >= SeedData.songs.size())
        {
            System.out.println("Invalid index.");
            return;
        }
        Song song = SeedData.songs.get(idx);
        System.out.println("Current lyrics:\n" + song.getLyrics());
        System.out.print("Suggest new lyrics: ");
        String newLyrics = scanner.nextLine();
        LyricEditRequest request = new LyricEditRequest(user, song, newLyrics);
        user.addEditRequest(request);
        System.out.println("Your lyric edit request has been submitted.");
    }
}
