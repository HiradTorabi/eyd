package com.hirad.genus.controller;

import com.hirad.genus.model.*;
import com.hirad.genus.seed.SeedData;
import com.hirad.genus.ui.UserStage;
import com.hirad.genus.ui.ArtistStage;
import com.hirad.genus.ui.AdminStage;

import java.util.Scanner;


public class AuthController
{
    private static Account currentUser = null;
    private static Scanner scanner = new Scanner(System.in);
    public static void signUp()
    {
        System.out.println("=== Sign Up ===");
        System.out.print("Full Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Are you an Artist? (yes/no): ");
        String roleInput = scanner.nextLine();
        if (roleInput.equalsIgnoreCase("yes"))
        {
            Artist artist = new Artist(name, age, email, username, password);
            SeedData.artists.add(artist);
            System.out.println("Your artist account is registered and pending approval.");
        }
        else
        {
            User user = new User(name, age, email, username, password);
            SeedData.users.add(user);
            System.out.println("User account created successfully.");
        }
    }
    public static void login()
    {
        System.out.println("=== Login ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        for (Admin admin : SeedData.admins)
        {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password))
            {
                currentUser = admin;
                System.out.println("Welcome admin: " + admin.getUsername());
                new AdminStage(admin).run();
                return;
            }
        }
        for (Artist artist : SeedData.artists)
        {
            if (artist.getUsername().equals(username) && artist.getPassword().equals(password))
            {
                if (!artist.isVerified())
                {
                    System.out.println("❌ Artist not verified yet. Please wait for admin approval.");
                    return;
                }
                currentUser = artist;
                System.out.println("Welcome artist: " + artist.getUsername());
                new ArtistStage(artist).run();
                return;
            }
        }
        for (User user : SeedData.users)
        {
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
            {
                currentUser = user;
                System.out.println("Welcome user: " + user.getUsername());
                new UserStage(user).run();
                return;
            }
        }
        System.out.println("❌ Invalid username or password.");
    }
    public static Account getCurrentUser()
    {
        return currentUser;
    }
    public static void logout()
    {
        currentUser = null;
        System.out.println("Logged out successfully.");
    }
}