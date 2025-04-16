package com.hirad.genus;

import com.hirad.genus.seed.SeedData;
import com.hirad.genus.controller.AuthController;
import com.hirad.genus.utils.ConsoleUtils;
import com.hirad.genus.utils.TextFileManager;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        SeedData.generate();
        TextFileManager.loadUsers();
        TextFileManager.loadArtists();


        if (SeedData.users.isEmpty() && SeedData.artists.isEmpty()) {
            SeedData.generate();
        }
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            ConsoleUtils.clearScreen();
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║           Welcome to GENIUS:)        ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║  1. 📝 Sign Up                       ║");
            System.out.println("║  2. 🔐 Log In                        ║");
            System.out.println("║  0. ❌ Exit                          ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.print("Choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice)
            {
                case "1" ->
                {
                    ConsoleUtils.showLoadingAnimation("Loading", 2, 100);
                    ConsoleUtils.clearScreen();
                    AuthController.signUp();
                }
                case "2" ->
                {
                    ConsoleUtils.showLoadingAnimation("Loading", 2, 100);
                    ConsoleUtils.clearScreen();
                    AuthController.login();
                }
                case "0" ->
                {
                    ConsoleUtils.showLoadingAnimation("Loading", 2, 100);
                    ConsoleUtils.clearScreen();
                    System.out.println("👋 Bye!");
                    return;
                }
                default ->
                {
                    System.out.println("❗ Invalid option. Press Enter to try again...");
                    scanner.nextLine();
                }
            }
        }
    }
}
