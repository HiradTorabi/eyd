package com.hirad.genus.ui;

import com.hirad.genus.model.*;
import com.hirad.genus.seed.SeedData;

import java.util.Scanner;

public class AdminStage
{
    private final Admin admin;
    private final Scanner scanner;
    public AdminStage(Admin admin)
    {
        this.admin = admin;
        this.scanner = new Scanner(System.in);
    }
    public void run()
    {
        while (true)
        {
            System.out.println("\n-*-*-*-Admin Menu-*-*-*-");
            System.out.println("1. Approve Artists");
            System.out.println("2. Review Lyric Edit Requests");
            System.out.println("0. Logout");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();
            switch (choice)
            {
                case "1" -> approveArtists();
                case "2" -> reviewEdits();
                case "0" ->
                {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void approveArtists()
    {
        for (Artist artist : SeedData.artists)
        {
            if (!artist.isVerified())
            {
                System.out.println("- " + artist.getUsername());
                System.out.print("Approve this artist? (yes/no): ");
                String ans = scanner.nextLine();
                if (ans.equalsIgnoreCase("yes"))
                {
                    admin.approveArtist(artist);
                }
            }
        }
    }
    private void reviewEdits()
    {
        for (User user : SeedData.users)
        {
            for (LyricEditRequest req : user.getEditRequests())
            {
                if (!req.isApproved())
                {
                    System.out.println("- Song: " + req.getSong().getTitle());
                    System.out.println("Suggested by: " + req.getRequester().getUsername());
                    System.out.println("New Lyrics: " + req.getProposedLyrics());
                    System.out.print("Approve this edit? (yes/no): ");
                    String ans = scanner.nextLine();
                    admin.reviewLyricEdit(req, ans.equalsIgnoreCase("yes"));
                }
            }
        }
    }
}
