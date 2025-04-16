package com.hirad.genus.ui;

import com.hirad.genus.controller.SearchController;
import com.hirad.genus.model.*;
import com.hirad.genus.seed.SeedData;
import com.hirad.genus.controller.SongController;


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
        // todo add search
        while (true)
        {
            System.out.println("\n-*-*-*-User Menu-*-*-*-");
            System.out.println("1. View Followed Artists");
            System.out.println("2. View All Songs (Like/Dislike)");
            System.out.println("3. Comment on a Song");
            System.out.println("4. Suggest Lyric Edit");
            System.out.println("5. Check Notifications");
            System.out.println("6. View Song Lyrics");
            System.out.println("7. View Comments on a Song");
            System.out.println("8. View Followed Accounts");
            System.out.println("9. View My Followers");
            System.out.println("10. Follow Someone");
            System.out.println("11. Unfollow Someone");
            System.out.println("12. 🔍 Search Songs, Artists, Albums");
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
                case "12" -> searchMenu();
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

        // todo use as song profile
        // todo sort by view
        while (true)
        {
            System.out.println("\n🎵 All Songs (Press 0 to return) 🎵");
            for (int i = 0; i < SeedData.songs.size(); i++) {
                Song song = SeedData.songs.get(i);
                String likeStatus = "";

                if (song.hasLiked(user))
                {
                    likeStatus = " [You 👍]";
                }
                else if (song.hasDisliked(user))
                {
                    likeStatus = " [You 👎]";
                }

                System.out.printf("%d. %s - %s 👍%d 👎%d%s\n",
                        i+1,
                        song.getTitle(),
                        song.getArtist().getUsername(),
                        song.getLikeCount(),
                        song.getDislikeCount(),
                        likeStatus);
            }

            System.out.println("\nOptions:");
            System.out.println("1-99: Select song number");
            System.out.println("L [number]: Like song");
            System.out.println("D [number]: Dislike song");
            System.out.println("0: Back to main menu");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine().trim();

            // برگشت به منوی اصلی
            if (input.equals("0"))
            {
                return;
            }

            // پردازش Like/Dislike
            if (input.toUpperCase().startsWith("L ") || input.toUpperCase().startsWith("D "))
            {
                try
                {
                    int songNumber = Integer.parseInt(input.substring(2).trim());
                    if (songNumber > 0 && songNumber <= SeedData.songs.size())
                    {
                        Song selectedSong = SeedData.songs.get(songNumber-1);

                        if (input.toUpperCase().startsWith("L "))
                        {
                            toggleLike(selectedSong);
                        }
                        else
                        {
                            toggleDislike(selectedSong);
                        }
                    }
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Invalid song number!");
                }
            }
            // مشاهده جزئیات آهنگ
            else
            {
                try
                {
                    int songNumber = Integer.parseInt(input);
                    if (songNumber > 0 && songNumber <= SeedData.songs.size())
                    {
                        SongController.viewSongDetails(SeedData.songs.get(songNumber-1));
                    }
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Invalid input! Use format 'L 1' or 'D 2'");
                }
            }
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

        // todo fix
        viewAllSongs();
        System.out.print("Enter song number to see comments: ");
        int songIdx = Integer.parseInt(scanner.nextLine()) - 1;
        if (songIdx < 0 || songIdx >= SeedData.songs.size())
        {
            System.out.println("Invalid index.");
            return;
        }
        Song song = SeedData.songs.get(songIdx);
        List<Comment> comments = song.getComments();
        if (comments.isEmpty())
        {
            System.out.println("No comments yet.");
            return;
        }
        System.out.println("💬 Comments on " + song.getTitle() + ":");
        for (int i = 0; i < comments.size(); i++)
        {
            Comment c = comments.get(i);
            System.out.printf("%d. %s (👍%d 👎%d)\n",
                    i + 1, c.getText(), c.getLikeCount(), c.getDislikeCount());
        }
        System.out.println("\n1. Like a comment");
        System.out.println("2. Dislike a comment");
        System.out.println("0. Back");
        System.out.print("Choose: ");
        String choice = scanner.nextLine();
        if (choice.equals("1") || choice.equals("2"))
        {
            System.out.print("Enter comment number: ");
            int commentIdx = Integer.parseInt(scanner.nextLine()) - 1;
            if (commentIdx >= 0 && commentIdx < comments.size())
            {
                Comment selectedComment = comments.get(commentIdx);
                if (choice.equals("1"))
                {
                    SongController.likeComment(song, selectedComment, user);
                    System.out.println("👍 Liked comment!");
                }
                else
                {
                    SongController.dislikeComment(song, selectedComment, user);
                    System.out.println("👎 Disliked comment!");
                }
            }
            else
            {
                System.out.println("Invalid comment number.");
            }
        }
    }

    private void viewFollowed()
    {
        System.out.println("You follow👉");
        for (Account acc : user.getFollowedAccounts())
        {
            System.out.println("- " + acc.getUsername());
        }
        //todo
        //ber to profile artist
        // todo show album profile
        // todo show song profile
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
    private void toggleLike(Song song)
    {
        if (song.hasLiked(user))
        {
            song.removeVote(user);
            System.out.println("Removed your like from '" + song.getTitle() + "'");
        }
        else
        {
            song.addLike(user);
            if (song.hasDisliked(user)) song.removeVote(user);
            System.out.println("Liked '" + song.getTitle() + "'");
        }
    }

    private void toggleDislike(Song song)
    {
        if (song.hasDisliked(user))
        {
            song.removeVote(user);
            System.out.println("Removed your dislike from '" + song.getTitle() + "'");
        }
        else
        {
            song.addDislike(user);
            if (song.hasLiked(user)) song.removeVote(user);
            System.out.println("Disliked '" + song.getTitle() + "'");
        }
    }
    private void searchMenu()
    {
        System.out.print("🔎 Enter keyword to search: ");
        String keyword = scanner.nextLine().trim().toLowerCase();

        List<Song> matchingSongs = SeedData.songs.stream()
                .filter(s -> s.getTitle().toLowerCase().contains(keyword))
                .toList();

        List<Artist> matchingArtists = SeedData.artists.stream()
                .filter(a -> a.getUsername().toLowerCase().contains(keyword) ||
                        a.getName().toLowerCase().contains(keyword))
                .toList();

        List<Album> matchingAlbums = SeedData.albums.stream()
                .filter(alb -> alb.getTitle().toLowerCase().contains(keyword))
                .toList();

        if (matchingSongs.isEmpty() && matchingArtists.isEmpty() && matchingAlbums.isEmpty()) {
            System.out.println("❌ No results found.");
            return;
        }

        int songIndex = 1;
        System.out.println("\n🎵 Matching Songs:");
        for (Song song : matchingSongs) {
            System.out.printf("%d. %s - %s\n", songIndex++, song.getTitle(), song.getArtist().getUsername());
        }

        System.out.println("\n🎤 Matching Artists:");
        for (Artist artist : matchingArtists) {
            System.out.printf("- %s (%s)\n", artist.getUsername(), artist.getName());
        }

        System.out.println("\n💿 Matching Albums:");
        for (Album album : matchingAlbums) {
            System.out.printf("- %s (%s)\n", album.getTitle(), album.getArtist().getUsername());
        }

        if (!matchingSongs.isEmpty()) {
            System.out.print("\nEnter song number to view details (0 to cancel): ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice > 0 && choice <= matchingSongs.size()) {
                    SongController.viewSongDetails(matchingSongs.get(choice - 1));
                }
            } catch (NumberFormatException ignored) {
            }
        }
    }


}
