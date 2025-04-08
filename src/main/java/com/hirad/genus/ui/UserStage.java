package com.hirad.genus.ui;

import com.hirad.genus.model.*;
import com.hirad.genus.seed.SeedData;

import java.util.List;
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
        user.showNotifications();
        while (true)
        {
            System.out.println("\n-*-*-*-User Menu-*-*-*-");
            System.out.println("1. View Followed Artists");
            System.out.println("2. View All Songs");
            System.out.println("3. Comment on a Song");
            System.out.println("4. Suggest Lyric Edit");
            System.out.println("5. Check Notifications");
            System.out.println("6. View Song Lyrics");
            System.out.println("7. View Comments on a Song");
            System.out.println("8. View Followed Accounts");
            System.out.println("9. View My Followers");
            System.out.println("10. Follow Someone");
            System.out.println("11. Unfollow Someone");
            System.out.println("0. Logout");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();
            switch (choice)
            {
                case "1" -> viewFollowedArtists();
                case "2" -> viewAllSongs();
                case "3" -> commentOnSong();
                case "4" -> suggestLyricEdit();
                case "5" -> user.checkNotifications();
                case "6" -> viewLyrics();
                case "7" -> viewComments();
                case "8" -> viewFollowed();
                case "9" -> viewFollowers();
                case "10" -> followSomeone();
                case "11" -> unfollowSomeone();
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
        System.out.println("Enter new lyrics suggestion (type 'END' to finish):");
        StringBuilder lyricsBuilder = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).equals("END"))
        {
            lyricsBuilder.append(line).append("\n");
        }
        String newLyrics = lyricsBuilder.toString();
        LyricEditRequest request = new LyricEditRequest(user, song, newLyrics);
        user.addEditRequest(request);
        System.out.println("Your lyric edit request has been submitted.");
    }
    private void viewLyrics() {
        viewAllSongs();
        System.out.print("Enter song number to view lyrics: ");
        int idx = Integer.parseInt(scanner.nextLine()) - 1;
        if (idx < 0 || idx >= SeedData.songs.size()) {
            System.out.println("Invalid index.");
            return;
        }
        Song song = SeedData.songs.get(idx);
        System.out.println("\n🎵Title: " + song.getTitle());
        System.out.println("Lyrics:");
        System.out.println("----------------------------");
        System.out.println(song.getLyrics());
        System.out.println("----------------------------");
        System.out.println("Views: " + song.getViewCount());
    }
    private void viewComments()
    {
        viewAllSongs();
        System.out.print("Enter song number to see comments: ");
        int idx = Integer.parseInt(scanner.nextLine()) - 1;
        if (idx < 0 || idx >= SeedData.songs.size())
        {
            System.out.println("Invalid index.");
            return;
        }
        Song song = SeedData.songs.get(idx);
        List<Comment> comments = song.getComments();
        if (comments.isEmpty())
        {
            System.out.println("No comments yet.");
        }
        else
        {
            System.out.println("💬 Comments on " + song.getTitle() + ":");
            for (Comment c : comments)
            {
                System.out.println("- " + c.getText() + " (by " + c.getAuthor().getUsername() + ")");
            }
        }
    }
    private void viewFollowed() {
        System.out.println("You follow👉");
        for (Account acc : user.getFollowedAccounts())
        {
            System.out.println("- " + acc.getUsername());
        }
    }

    private void viewFollowers()
    {
        System.out.println("👥Your followers:");
        for (Account acc : user.getFollowers())
        {
            System.out.println("- " + acc.getUsername());
        }
    }
    private void followSomeone() {
        System.out.print("Enter username to follow: ");
        String uname = scanner.nextLine();
        Account target = findAccountByUsername(uname);
        if (target == null)
        {
            System.out.println("❌No such account❌");
            return;
        }
        user.follow(target);
    }

    private void unfollowSomeone()
    {
        System.out.print("Enter username to unfollow: ");
        String uname = scanner.nextLine();
        Account target = findAccountByUsername(uname);
        if (target == null)
        {
            System.out.println("❌No such account❌");
            return;
        }
        user.unfollow(target);
    }

    private Account findAccountByUsername(String username)
    {
        for (User u : SeedData.users)
        {
            if (u.getUsername().equals(username))
            {
                return u;
            }
        }
        for (Artist a : SeedData.artists)
        {
            if (a.getUsername().equals(username))
            {
                return a;
            }
        }
        for (Admin a : SeedData.admins)
        {
            if (a.getUsername().equals(username))
            {
                return a;
            }
        }
        return null;
    }

}
