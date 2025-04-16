package com.hirad.genus.ui;

import com.hirad.genus.controller.SearchController;
import com.hirad.genus.model.*;
import com.hirad.genus.seed.SeedData;
import com.hirad.genus.controller.SongController;


import java.util.ArrayList;
import java.util.Comparator;
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

    private void viewAllSongs() {
        List<Song> sortedSongs = new ArrayList<>(SeedData.songs);
        sortedSongs.sort(Comparator.comparingInt(Song::getViewCount).reversed());

        System.out.println("\n🎵 All Songs (Sorted by views)");
        for (int i = 0; i < sortedSongs.size(); i++) {
            Song song = sortedSongs.get(i);
            String coStr = song.getCoArtists().isEmpty() ? "" :
                    " | Co: " + String.join(", ",
                            song.getCoArtists().stream().map(Artist::getUsername).toList());

            System.out.printf("%d. %s | 👤 %s%s | 👍 %d 👎 %d | 👁 %d\n",
                    i + 1,
                    song.getTitle(),
                    song.getArtist().getUsername(),
                    coStr,
                    song.getLikeCount(),
                    song.getDislikeCount(),
                    song.getViewCount());
        }

        System.out.print("\nEnter song number to open (0 to cancel): ");
        try {
            int songIndex = Integer.parseInt(scanner.nextLine());
            if (songIndex == 0) return;
            if (songIndex > 0 && songIndex <= sortedSongs.size()) {
                Song song = sortedSongs.get(songIndex - 1);
                SongController.viewSong(song, user);

                while (true) {
                    System.out.println("\nOptions:");
                    System.out.println("1. 👍/👎 Like/Dislike");
                    System.out.println("2. 💬 View comments");
                    System.out.println("3. ➕ Add comment");
                    System.out.println("4. 📝 Suggest lyric edit");
                    System.out.println("5. 📖 View lyrics");
                    System.out.println("0. Back");
                    System.out.print("Choose: ");
                    String input = scanner.nextLine().trim();

                    switch (input) {
                        case "1" -> {
                            System.out.print("L - Like | D - Dislike | R - Remove vote: ");
                            String vote = scanner.nextLine().trim().toUpperCase();
                            switch (vote) {
                                case "L" -> toggleLike(song);
                                case "D" -> toggleDislike(song);
                                case "R" -> {
                                    song.removeVote(user);
                                    System.out.println("✅ Vote removed.");
                                }
                                default -> System.out.println("Invalid input");
                            }
                        }
                        case "2" -> viewCommentsOnSong(song);
                        case "3" -> {
                            System.out.print("Your comment: ");
                            user.commentOnSong(song, scanner.nextLine());
                            System.out.println("✅ Comment added!");
                        }
                        case "4" -> {
                            System.out.println("Current lyrics:");
                            System.out.println(song.getLyrics());
                            System.out.println("\nSuggest new lyrics (type 'END' to finish):");
                            StringBuilder sb = new StringBuilder();
                            String line;
                            while (!(line = scanner.nextLine()).equals("END")) {
                                sb.append(line).append("\n");
                            }
                            String newLyrics = sb.toString();
                            LyricEditRequest req = new LyricEditRequest(user, song, newLyrics);
                            user.addEditRequest(req);
                            System.out.println("📝 Lyric edit suggestion submitted!");
                        }
                        case "5" -> {
                            System.out.println("\nLyrics:\n" + song.getLyrics());
                            System.out.println("👁 Views: " + song.getViewCount());
                        }
                        case "0" -> {
                            System.out.println("Returning...");
                            return;
                        }
                        default -> System.out.println("Invalid option");
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input");
        }
    }

    private void viewCommentsOnSong(Song song)
    {
        List<Comment> comments = song.getComments();
        if (comments.isEmpty()) {
            System.out.println("No comments yet.");
            return;
        }

        System.out.println("\n💬 Comments:");
        for (int i = 0; i < comments.size(); i++) {
            Comment c = comments.get(i);
            System.out.printf("%d. %s (👍%d 👎%d)\n", i + 1, c.getText(), c.getLikeCount(), c.getDislikeCount());
        }

        System.out.print("Do you want to like/dislike a comment? (y/n): ");
        String input = scanner.nextLine().trim().toLowerCase();
        if (input.equals("y")) {
            System.out.print("Enter comment number: ");
            try {
                int index = Integer.parseInt(scanner.nextLine()) - 1;
                if (index >= 0 && index < comments.size()) {
                    Comment c = comments.get(index);
                    System.out.print("L - Like | D - Dislike | R - Remove vote: ");
                    String vote = scanner.nextLine().trim().toUpperCase();
                    switch (vote) {
                        case "L" -> {
                            if (c.hasLiked(user)) {
                                System.out.println("Already liked.");
                            } else {
                                if (c.hasDisliked(user)) c.removeVote(user);
                                SongController.likeComment(song, c, user);
                                System.out.println("✅ Liked comment.");
                            }
                        }
                        case "D" -> {
                            if (c.hasDisliked(user)) {
                                System.out.println("Already disliked.");
                            } else {
                                if (c.hasLiked(user)) c.removeVote(user);
                                SongController.dislikeComment(song, c, user);
                                System.out.println("👎 Disliked comment.");
                            }
                        }
                        case "R" -> {
                            if (c.hasLiked(user) || c.hasDisliked(user)) {
                                c.removeVote(user);
                                System.out.println("🔄 Vote removed.");
                            } else {
                                System.out.println("You haven't voted on this comment.");
                            }
                        }
                        default -> System.out.println("Invalid vote input.");
                    }
                } else {
                    System.out.println("Invalid comment number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
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
        System.out.print("🔍 Enter keyword: ");
        String keyword = scanner.nextLine().trim().toLowerCase();

        List<Song> matchedSongs = SeedData.songs.stream()
                .filter(s -> s.getTitle().toLowerCase().contains(keyword))
                .toList();
        List<Album> matchedAlbums = SeedData.albums.stream()
                .filter(a -> a.getTitle().toLowerCase().contains(keyword))
                .toList();
        List<Artist> matchedArtists = SeedData.artists.stream()
                .filter(a -> a.getUsername().toLowerCase().contains(keyword)
                        || a.getName().toLowerCase().contains(keyword))
                .toList();

        if (matchedSongs.isEmpty() && matchedAlbums.isEmpty() && matchedArtists.isEmpty())
        {
            System.out.println("❌ No results found.");
            return;
        }

        if (!matchedAlbums.isEmpty() && matchedSongs.isEmpty() && matchedArtists.isEmpty())
        {
            searchAlbumMenu(keyword);
        }
        else if (!matchedSongs.isEmpty() && matchedAlbums.isEmpty() && matchedArtists.isEmpty())
        {

            searchSongMenu(keyword);
        }
        else if (!matchedArtists.isEmpty() && matchedAlbums.isEmpty() && matchedSongs.isEmpty())
        {
            searchArtistMenu(keyword);
        }
        else
        {
            System.out.println("\n🔍 Multiple matches found. What would you like to explore?");
            if (!matchedSongs.isEmpty()) System.out.println("1. Songs (" + matchedSongs.size() + ")");
            if (!matchedAlbums.isEmpty()) System.out.println("2. Albums (" + matchedAlbums.size() + ")");
            if (!matchedArtists.isEmpty()) System.out.println("3. Artists (" + matchedArtists.size() + ")");
            System.out.print("> ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> searchSongMenu(keyword);
                case "2" -> searchAlbumMenu(keyword);
                case "3" -> searchArtistMenu(keyword);
                default -> System.out.println("❌ Invalid option.");
            }
        }
    }

    private void searchAlbumMenu(String keyword)
    {
        List<Album> matchedAlbums = SeedData.albums.stream()
                .filter(a -> a.getTitle().toLowerCase().contains(keyword))
                .toList();

        if (matchedAlbums.isEmpty()) {
            System.out.println("❌ No albums found.");
            return;
        }

        System.out.println("\n💿 Matching Albums:");
        for (int i = 0; i < matchedAlbums.size(); i++) {
            Album a = matchedAlbums.get(i);
            System.out.printf("%d. %s (%s)\n", i + 1, a.getTitle(), a.getArtist().getUsername());
        }

        System.out.print("\nSelect an album number to view details (0 to cancel): ");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) return;
            if (choice > 0 && choice <= matchedAlbums.size()) {
                Album selected = matchedAlbums.get(choice - 1);
                System.out.println("\n🎤 Artist: " + selected.getArtist().getUsername());
                System.out.println("📅 Release Date: " + selected.getReleaseDate());
                System.out.println("\n🎵 Songs in this album:");
                List<Song> tracks = selected.getTrackList();
                for (int i = 0; i < tracks.size(); i++) {
                    System.out.printf("%d. %s\n", i + 1, tracks.get(i).getTitle());
                }

                System.out.print("\nEnter song number to view details (0 to cancel): ");
                int songChoice = Integer.parseInt(scanner.nextLine());
                if (songChoice > 0 && songChoice <= tracks.size()) {
                    Song selectedSong = tracks.get(songChoice - 1);
                    SongController.viewSong(selectedSong, user);

                    while (true) {
                        System.out.println("\nOptions:");
                        System.out.println("1. 👍/👎 Like or Dislike this song");
                        System.out.println("2. 💬 View comments");
                        System.out.println("3. ➕ Add a comment");
                        System.out.println("4. 👍/👎 Like/Dislike a comment");
                        System.out.println("0. Back");
                        System.out.print("Choose: ");
                        String option = scanner.nextLine();

                        switch (option) {
                            case "1" -> {
                                System.out.println("Do you want to:");
                                System.out.println("L - 👍 Like");
                                System.out.println("D - 👎 Dislike");
                                System.out.println("R - 🔄 Remove your vote");
                                System.out.print("> ");
                                String voteInput = scanner.nextLine().trim().toUpperCase();

                                switch (voteInput) {
                                    case "L" -> {
                                        if (selectedSong.hasLiked(user)) {
                                            System.out.println("✅ You already liked this song.");
                                        } else {
                                            if (selectedSong.hasDisliked(user)) selectedSong.removeVote(user);
                                            selectedSong.addLike(user);
                                            System.out.println("👍 Liked the song.");
                                        }
                                    }
                                    case "D" -> {
                                        if (selectedSong.hasDisliked(user)) {
                                            System.out.println("✅ You already disliked this song.");
                                        } else {
                                            if (selectedSong.hasLiked(user)) selectedSong.removeVote(user);
                                            selectedSong.addDislike(user);
                                            System.out.println("👎 Disliked the song.");
                                        }
                                    }
                                    case "R" -> {
                                        if (selectedSong.hasLiked(user) || selectedSong.hasDisliked(user)) {
                                            selectedSong.removeVote(user);
                                            System.out.println("✅ Your vote has been removed.");
                                        } else {
                                            System.out.println("ℹ️ You haven't voted yet.");
                                        }
                                    }
                                    default -> System.out.println("❌ Invalid option.");
                                }
                            }
                            case "2" -> {
                                List<Comment> comments = selectedSong.getComments();
                                if (comments.isEmpty()) {
                                    System.out.println("No comments yet.");
                                } else {
                                    System.out.println("\n💬 Comments:");
                                    for (int i = 0; i < comments.size(); i++) {
                                        Comment c = comments.get(i);
                                        System.out.printf("%d. %s (👍%d 👎%d)\n", i + 1, c.getText(), c.getLikeCount(), c.getDislikeCount());
                                    }
                                }
                            }
                            case "3" -> {
                                System.out.print("Write your comment: ");
                                String text = scanner.nextLine();
                                user.commentOnSong(selectedSong, text);
                                System.out.println("✅ Comment added!");
                            }
                            case "4" -> {
                                List<Comment> comments = selectedSong.getComments();
                                if (comments.isEmpty()) {
                                    System.out.println("No comments to vote on.");
                                    break;
                                }
                                System.out.print("Enter comment number: ");
                                try {
                                    int cIdx = Integer.parseInt(scanner.nextLine()) - 1;
                                    if (cIdx >= 0 && cIdx < comments.size()) {
                                        Comment c = comments.get(cIdx);
                                        System.out.println("L - 👍 Like\nD - 👎 Dislike\nR - 🔄 Remove vote");
                                        System.out.print("> ");
                                        String vote = scanner.nextLine().trim().toUpperCase();

                                        switch (vote) {
                                            case "L" -> {
                                                if (c.hasLiked(user)) {
                                                    System.out.println("✅ You already liked this comment.");
                                                } else {
                                                    if (c.hasDisliked(user)) c.removeVote(user);
                                                    SongController.likeComment(selectedSong, c, user);
                                                    System.out.println("👍 Liked comment.");
                                                }
                                            }
                                            case "D" -> {
                                                if (c.hasDisliked(user)) {
                                                    System.out.println("✅ You already disliked this comment.");
                                                } else {
                                                    if (c.hasLiked(user)) c.removeVote(user);
                                                    SongController.dislikeComment(selectedSong, c, user);
                                                    System.out.println("👎 Disliked comment.");
                                                }
                                            }
                                            case "R" -> {
                                                if (c.hasLiked(user) || c.hasDisliked(user)) {
                                                    c.removeVote(user);
                                                    System.out.println("✅ Vote removed.");
                                                } else {
                                                    System.out.println("ℹ️ You haven't voted yet.");
                                                }
                                            }
                                            default -> System.out.println("❌ Invalid option.");
                                        }
                                    }
                                } catch (Exception e) {
                                    System.out.println("Invalid comment number.");
                                }
                            }
                            case "0" -> {
                                System.out.println("Returning to album...");
                                return;
                            }
                            default -> System.out.println("Invalid option.");
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("⚠️ Invalid input");
        }
    }


    private void searchArtistMenu(String keyword) {
        List<Artist> matchedArtists = SeedData.artists.stream()
                .filter(a -> a.getUsername().toLowerCase().contains(keyword) ||
                        a.getName().toLowerCase().contains(keyword))
                .toList();

        if (matchedArtists.isEmpty()) {
            System.out.println("❌ No artist found.");
            return;
        }

        System.out.println("\n🎤 Matching Artists:");
        for (int i = 0; i < matchedArtists.size(); i++) {
            Artist a = matchedArtists.get(i);
            System.out.printf("%d. %s (%s)\n", i + 1, a.getName(), a.getUsername());
        }

        System.out.print("\nSelect artist number (0 to cancel): ");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) return;
            if (choice > 0 && choice <= matchedArtists.size()) {
                Artist artist = matchedArtists.get(choice - 1);
                System.out.println("\n👤 Artist: " + artist.getName());
                System.out.println("Username: " + artist.getUsername());
                System.out.println("Albums: " + artist.getAlbums().size());
                System.out.println("Songs: " + artist.getSongs().size());

                while (true) {
                    System.out.println("\nOptions:");
                    System.out.println("1. 🎵 View Songs");
                    System.out.println("2. 💿 View Albums");
                    System.out.println("0. Back");
                    System.out.print("Choose: ");
                    String opt = scanner.nextLine();

                    switch (opt) {
                        case "1" -> {
                            List<Song> songs = artist.getSongs();
                            if (songs.isEmpty()) {
                                System.out.println("No songs available.");
                            } else {
                                for (int i = 0; i < songs.size(); i++) {
                                    System.out.printf("%d. %s\n", i + 1, songs.get(i).getTitle());
                                }
                                System.out.print("Select song number to view: ");
                                int sIdx = Integer.parseInt(scanner.nextLine()) - 1;
                                if (sIdx >= 0 && sIdx < songs.size()) {
                                    SongController.viewSong(songs.get(sIdx), user);
                                }
                            }
                        }
                        case "2" -> {
                            List<Album> albums = artist.getAlbums();
                            if (albums.isEmpty()) {
                                System.out.println("No albums available.");
                            } else {
                                for (int i = 0; i < albums.size(); i++) {
                                    System.out.printf("%d. %s (%s)\n", i + 1, albums.get(i).getTitle(), albums.get(i).getReleaseDate());
                                }
                                System.out.print("Select album number to view songs: ");
                                int aIdx = Integer.parseInt(scanner.nextLine()) - 1;
                                if (aIdx >= 0 && aIdx < albums.size()) {
                                    Album selectedAlbum = albums.get(aIdx);
                                    List<Song> tracks = selectedAlbum.getTrackList();
                                    for (int j = 0; j < tracks.size(); j++) {
                                        System.out.printf("%d. %s\n", j + 1, tracks.get(j).getTitle());
                                    }
                                    System.out.print("Select song to view: ");
                                    int songNum = Integer.parseInt(scanner.nextLine()) - 1;
                                    if (songNum >= 0 && songNum < tracks.size()) {
                                        SongController.viewSong(tracks.get(songNum), user);
                                    }
                                }
                            }
                        }
                        case "0" -> {
                            System.out.println("Returning to search...");
                            return;
                        }
                        default -> System.out.println("Invalid option.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("⚠️ Invalid input.");
        }
    }

    private void searchSongMenu(String keyword) {
        List<Song> matchedSongs = SeedData.songs.stream()
                .filter(s -> s.getTitle().toLowerCase().contains(keyword))
                .toList();

        if (matchedSongs.isEmpty()) {
            System.out.println("❌ No songs found.");
            return;
        }

        System.out.println("\n🎵 Matching Songs:");
        for (int i = 0; i < matchedSongs.size(); i++) {
            Song s = matchedSongs.get(i);
            System.out.printf("%d. %s (%s)\n", i + 1, s.getTitle(), s.getArtist().getUsername());
        }

        System.out.print("\nSelect a song number to view (0 to cancel): ");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) return;
            if (choice > 0 && choice <= matchedSongs.size()) {
                Song song = matchedSongs.get(choice - 1);
                SongController.viewSong(song, user);

                Album album = null;
                for (Album a : SeedData.albums) {
                    if (a.getTrackList().contains(song)) {
                        album = a;
                        break;
                    }
                }

                System.out.println("\n🎤 Artist: " + song.getArtist().getUsername());
                System.out.println("📅 Release Date: " + song.getLyrics());
                if (album != null) {
                    System.out.println("💿 Album: " + album.getTitle());
                } else {
                    System.out.println("🎧 This is a single.");
                }

                while (true) {
                    System.out.println("\nOptions:");
                    System.out.println("1. 👀 View Lyrics");
                    System.out.println("2. 👍/👎 Like/Dislike Song");
                    System.out.println("3. 💬 View Comments");
                    System.out.println("4. ➕ Add Comment");
                    System.out.println("5. 👍/👎 Like/Dislike Comment");
                    System.out.println("0. Back");
                    System.out.print("Choose: ");
                    String opt = scanner.nextLine();

                    switch (opt) {
                        case "1" -> System.out.println("Lyrics:\n" + song.getLyrics());

                        case "2" -> {
                            System.out.println("L - 👍 Like | D - 👎 Dislike | R - 🔄 Remove vote");
                            System.out.print("> ");
                            String vote = scanner.nextLine().toUpperCase();
                            switch (vote) {
                                case "L" -> {
                                    if (song.hasLiked(user)) System.out.println("✅ Already liked.");
                                    else {
                                        if (song.hasDisliked(user)) song.removeVote(user);
                                        song.addLike(user);
                                        System.out.println("👍 Liked.");
                                    }
                                }
                                case "D" -> {
                                    if (song.hasDisliked(user)) System.out.println("✅ Already disliked.");
                                    else {
                                        if (song.hasLiked(user)) song.removeVote(user);
                                        song.addDislike(user);
                                        System.out.println("👎 Disliked.");
                                    }
                                }
                                case "R" -> {
                                    if (song.hasLiked(user) || song.hasDisliked(user)) {
                                        song.removeVote(user);
                                        System.out.println("🔄 Vote removed.");
                                    } else System.out.println("ℹ️ You haven't voted.");
                                }
                                default -> System.out.println("❌ Invalid input.");
                            }
                        }

                        case "3" -> {
                            List<Comment> comments = song.getComments();
                            if (comments.isEmpty()) {
                                System.out.println("No comments yet.");
                            } else {
                                for (int i = 0; i < comments.size(); i++) {
                                    Comment c = comments.get(i);
                                    System.out.printf("%d. %s (👍%d 👎%d)\n", i + 1, c.getText(), c.getLikeCount(), c.getDislikeCount());
                                }
                            }
                        }

                        case "4" -> {
                            System.out.print("Write your comment: ");
                            String txt = scanner.nextLine();
                            user.commentOnSong(song, txt);
                            System.out.println("✅ Comment added.");
                        }

                        case "5" -> {
                            List<Comment> comments = song.getComments();
                            if (comments.isEmpty()) {
                                System.out.println("No comments to vote.");
                                break;
                            }
                            System.out.print("Enter comment number: ");
                            int cid = Integer.parseInt(scanner.nextLine()) - 1;
                            if (cid >= 0 && cid < comments.size()) {
                                Comment c = comments.get(cid);
                                System.out.print("L - Like | D - Dislike | R - Remove vote: ");
                                String v = scanner.nextLine().toUpperCase();
                                switch (v) {
                                    case "L" -> {
                                        if (c.hasLiked(user)) System.out.println("Already liked.");
                                        else {
                                            if (c.hasDisliked(user)) c.removeVote(user);
                                            SongController.likeComment(song, c, user);
                                            System.out.println("Liked.");
                                        }
                                    }
                                    case "D" -> {
                                        if (c.hasDisliked(user)) System.out.println("Already disliked.");
                                        else {
                                            if (c.hasLiked(user)) c.removeVote(user);
                                            SongController.dislikeComment(song, c, user);
                                            System.out.println("Disliked.");
                                        }
                                    }
                                    case "R" -> {
                                        if (c.hasLiked(user) || c.hasDisliked(user)) {
                                            c.removeVote(user);
                                            System.out.println("Vote removed.");
                                        } else System.out.println("You haven't voted.");
                                    }
                                    default -> System.out.println("Invalid option.");
                                }
                            }
                        }

                        case "0" -> {
                            System.out.println("Returning to search...");
                            return;
                        }
                        default -> System.out.println("Invalid option.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Invalid input");
        }
    }
}







