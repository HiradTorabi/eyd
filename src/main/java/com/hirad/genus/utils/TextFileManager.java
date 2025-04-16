package com.hirad.genus.utils;

import com.hirad.genus.model.Artist;
import com.hirad.genus.model.User;
import com.hirad.genus.seed.SeedData;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextFileManager {
    private static final String DATA_DIR = "data";
    private static final String USERS_FILE = DATA_DIR + "/users.txt";
    private static final String ARTISTS_FILE = DATA_DIR + "/artists.txt";


    static {
        new File(DATA_DIR).mkdirs();
    }

    public static void saveUsers() {
        try (PrintWriter writer = new PrintWriter(USERS_FILE)) {
            for (User user : SeedData.users) {
                writer.println(user.getUsername() + "," +
                        user.getEmail() + "," +
                        user.getPassword());
            }
        } catch (Exception e) {
            System.out.println("❌ Failed to save users: " + e.getMessage());
        }
    }

    public static void saveArtists() {
        try (PrintWriter writer = new PrintWriter(ARTISTS_FILE)) {
            for (Artist artist : SeedData.artists) {
                writer.println(artist.getUsername() + "," +
                        artist.getEmail() + "," +
                        artist.getPassword() + "," +
                        artist.isVerified());
            }
        } catch (Exception e) {
            System.out.println("❌ Failed to save artists: " + e.getMessage());
        }
    }

    public static void loadUsers() {
        try (Scanner scanner = new Scanner(new File(USERS_FILE))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                if (parts.length == 3) {
                    User user = new User(
                            "Unknown",
                            0, // سن موقت
                            parts[1].trim(),
                            parts[0].trim(),
                            parts[2].trim()
                    );
                    SeedData.users.add(user);
                }
            }
        } catch (Exception e) {
            System.out.println("⚠️ No users file found. Skipping user load.");
        }
    }

    public static void loadArtists() {
        try (Scanner scanner = new Scanner(new File(ARTISTS_FILE))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                if (parts.length == 4) {
                    Artist artist = new Artist(
                            "Unknown",
                            0,
                            parts[1].trim(),
                            parts[0].trim(),
                            parts[2].trim()
                    );
                    artist.setVerified(Boolean.parseBoolean(parts[3].trim()));
                    SeedData.artists.add(artist);
                }
            }
        } catch (Exception e) {
            System.out.println("⚠️ No artists file found. Skipping artist load.");
        }
    }
}