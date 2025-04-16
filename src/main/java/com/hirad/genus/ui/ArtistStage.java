package com.hirad.genus.ui;

import com.hirad.genus.model.*;
import com.hirad.genus.seed.SeedData;

import java.time.LocalDate;
import java.util.List;
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
            System.out.println("6. Check Notifications");
            System.out.println("7. View Song Lyrics");
            System.out.println("8. View Comments on a Song");
            System.out.println("9. View Followed Accounts");
            System.out.println("10. View My Followers");
            System.out.println("11. Follow Someone");
            System.out.println("12. Unfollow Someone");
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
                case "6" -> artist.checkNotifications();
                case "7" -> viewLyrics();
                case "8" -> viewComments();
                case "9" -> viewFollowed();
                case "10" -> viewFollowers();
                case "11" -> followSomeone();
                case "12" -> unfollowSomeone();
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
        System.out.println("Enter lyrics (type 'END' in a new line to finish):");
        StringBuilder lyricsBuilder = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).equals("END"))
        {
            lyricsBuilder.append(line).append("\n");
        }
        String lyrics = lyricsBuilder.toString();

        LocalDate date = LocalDate.now();
        Song song = new Song(title, lyrics, date, artist);
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
                if (req.getSong() != null && (artist.getSongs().contains(req.getSong()) /*   || todo coArtist in song*/  ) && !req.isApproved())
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
                    String msg = "Your lyric edit for \"" + req.getSong().getTitle() + "\" was " +
                            (req.isApproved() ? "approved ✅" : "rejected ❌");
                    req.getRequester().addNotification(new Notification(msg));
                }
            }
        }
    }
    private void viewLyrics()
    {
        viewSongs();
        System.out.print("Enter song number to view lyrics: ");
        int idx = Integer.parseInt(scanner.nextLine()) - 1;
        List<Song> songs = artist.getSongs();
        if (idx < 0 || idx >= songs.size())
        {
            System.out.println("Invalid index.");
            return;
        }
        Song song = songs.get(idx);
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
    private void viewAllSongs()
    {
        List<Song> songs = artist.getSongs();
        if (songs.isEmpty())
        {
            System.out.println("You have no songs.");
            return;
        }
        System.out.println("🎵Your Songs:");
        for (int i = 0; i < songs.size(); i++)
        {
            System.out.println((i + 1) + ". " + songs.get(i).getTitle());
        }
    }
    private void viewFollowed() {
        System.out.println("You follow👉");
        for (Account acc : artist.getFollowedAccounts())
        {
            System.out.println("- " + acc.getUsername());
        }
    }

    private void viewFollowers() {
        System.out.println("👥Your followers:");
        for (Account acc : artist.getFollowers()) {
            System.out.println("- " + acc.getUsername());
        }
    }

    private void followSomeone()
    {
        System.out.print("Enter username to follow: ");
        String uname = scanner.nextLine();
        Account target = findAccountByUsername(uname);
        if (target == null)
        {
            System.out.println("❌No such account❌");
            return;
        }
        artist.follow(target);
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
        artist.unfollow(target);
    }
    private Account findAccountByUsername(String username) {
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
