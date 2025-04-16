package com.hirad.genus.utils;

public class ConsoleUtils
{
    /*public static void clearScreen()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }*/
    public static void clearScreen()
    {
        for (int i = 0; i < 50; ++i)
        {
            System.out.println();
        }
    }
    public static void showLoadingAnimation(String message, int repeatCount, int delayMillis)
    {
        String[] frames = {"⣾", "⣽", "⣻", "⢿", "⡿", "⣟", "⣯", "⣷"};
        for (int i = 0; i < repeatCount; i++) {
            for (String frame : frames) {
                System.out.print("\r" + message + " " + frame);
                try {
                    Thread.sleep(delayMillis);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        clearScreen();
    }
    public static void showWinkingFace(int repeatCount) {
        String openEye = "o";
        String closedEye = "-";

        for (int i = 0; i < repeatCount; i++) {
            clearScreen();

            String eye = (i % 2 == 0) ? openEye : closedEye;

            System.out.println("   _______");
            System.out.println("  /       \\");
            System.out.println(" |  " + eye + "   o  |");
            System.out.println(" |  \\___/  |");
            System.out.println("  \\_______/");

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}


/*package com.hirad.genus.utils;

public class ConsoleUtils {

    public static void clearScreen() {
        try {
            if (System.console() != null) {
                // ترمینال واقعی
                System.out.print("\033[H\033[2J");
                System.out.flush();
            } else {
                // IDE مثل IntelliJ یا VS Code
                for (int i = 0; i < 50; ++i) {
                    System.out.println();
                }
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; ++i) {
                System.out.println();
            }
        }
    }
}*/

