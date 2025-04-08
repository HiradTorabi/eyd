package com.hirad.genus.seed;

import com.hirad.genus.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SeedData
{
    public static List<User> users = new ArrayList<>();
    public static List<Artist> artists = new ArrayList<>();
    public static List<Admin> admins = new ArrayList<>();
    public static List<Song> songs = new ArrayList<>();
    public static List<Album> albums = new ArrayList<>();

    public static void generate()
    {
        // Admin
        Admin admin = new Admin("Main Admin", 35, "admin@genus.com", "admin", "adminpass");
        admins.add(admin);

        // Users
        User reza = new User("Reza", 22, "reza@gmail.com", "reza", "pass1");
        User sara = new User("Sara", 21, "sara@gmail.com", "sara", "pass2");
        User nima = new User("Nima", 23, "nima@gmail.com", "nima", "pass3");
        users.addAll(List.of(reza, sara, nima));

        //David Bowie
        Artist bowie = new Artist("David Bowie", 70, "bowie@rock.com", "Davidbowie", "ziggy");
        bowie.setVerified(true);
        Album dbAlbum1 = new Album("Hunky Dory", LocalDate.of(1971, 12, 17), bowie);
        Song db1 = new Song("Changes", "Still don't know what I was waiting for...", LocalDate.of(1971, 12, 17), bowie);
        Song db2 = new Song("Life on Mars?", "It's a god-awful small affair...", LocalDate.of(1971, 12, 17), bowie);
        dbAlbum1.addSong(db1);
        dbAlbum1.addSong(db2);

        Album dbAlbum2 = new Album("Heroes", LocalDate.of(1977, 9, 23), bowie);
        Song db3 = new Song("Heroes", "We could be heroes, just for one day", LocalDate.of(1977, 9, 23), bowie);
        dbAlbum2.addSong(db3);

        Song dbSingle = new Song("Starman", "There's a starman waiting in the sky...", LocalDate.of(1972, 4, 28), bowie);

        bowie.getSongs().addAll(List.of(db1, db2, db3, dbSingle));
        bowie.getAlbums().addAll(List.of(dbAlbum1, dbAlbum2));

        //Eminem
        Artist eminem = new Artist("Eminem", 50, "eminem@rap.com", "eminem", "rapgod");
        eminem.setVerified(true);
        Album emAlbum1 = new Album("The Marshall Mathers LP", LocalDate.of(2000, 5, 23), eminem);
        Song em1 = new Song("Stan", "My tea's gone cold...", LocalDate.of(2000, 5, 23), eminem);
        Song em2 = new Song("The Way I Am", "...", LocalDate.of(2000, 5, 23), eminem);
        emAlbum1.addSong(em1);
        emAlbum1.addSong(em2);

        Album emAlbum2 = new Album("Encore", LocalDate.of(2004, 11, 12), eminem);
        Song em3 = new Song("Mockingbird", "...", LocalDate.of(2004, 11, 12), eminem);
        emAlbum2.addSong(em3);

        Song emSingle = new Song("Lose Yourself", "You better lose yourself...", LocalDate.of(2002, 10, 28), eminem);

        eminem.getSongs().addAll(List.of(em1, em2, em3, emSingle));
        eminem.getAlbums().addAll(List.of(emAlbum1, emAlbum2));


        //Tupac
        Artist tupac = new Artist("Tupac", 47, "tupac@hiphop.com", "tupac", "thuglife");
        tupac.setVerified(true);
        Album tpAlbum1 = new Album("All Eyez on Me", LocalDate.of(1996, 2, 13), tupac);
        Song tp1 = new Song("Ambitionz Az a Ridah", "...", LocalDate.of(1996, 2, 13), tupac);
        Song tp2 = new Song("All Eyez on Me", "...", LocalDate.of(1996, 2, 13), tupac);
        tpAlbum1.addSong(tp1);
        tpAlbum1.addSong(tp2);

        Album tpAlbum2 = new Album("Me Against the World", LocalDate.of(1995, 3, 14), tupac);
        Song tp3 = new Song("Dear Mama", "...", LocalDate.of(1995, 3, 14), tupac);
        tpAlbum2.addSong(tp3);

        Song tpSingle = new Song("Changes", "...", LocalDate.of(1998, 10, 13), tupac);

        tupac.getSongs().addAll(List.of(tp1, tp2, tp3, tpSingle));
        tupac.getAlbums().addAll(List.of(tpAlbum1, tpAlbum2));


        //Linkin Park
        Artist lp = new Artist("Linkin Park", 40, "lp@rock.com", "lp", "numbpass");
        lp.setVerified(true);
        Album lpAlbum1 = new Album("Hybrid Theory", LocalDate.of(2000, 10, 24), lp);
        Song lp1 = new Song("In the End", "...", LocalDate.of(2000, 10, 24), lp);
        Song lp2 = new Song("Crawling", "...", LocalDate.of(2000, 10, 24), lp);
        lpAlbum1.addSong(lp1);
        lpAlbum1.addSong(lp2);

        Album lpAlbum2 = new Album("Meteora", LocalDate.of(2003, 3, 25), lp);
        Song lp3 = new Song("Numb", "...", LocalDate.of(2003, 3, 25), lp);
        lpAlbum2.addSong(lp3);

        Song lpSingle = new Song("Bleed It Out", "...", LocalDate.of(2007, 8, 14), lp);

        lp.getSongs().addAll(List.of(lp1, lp2, lp3, lpSingle));
        lp.getAlbums().addAll(List.of(lpAlbum1, lpAlbum2));


        //Nirvana
        Artist nirvana = new Artist("Nirvana", 35, "nirvana@grunge.com", "nirvana", "smells");
        nirvana.setVerified(true);
        Album nvAlbum1 = new Album("Nevermind", LocalDate.of(1991, 9, 24), nirvana);
        Song nv1 = new Song("Smells Like Teen Spirit", "Load up on guns, bring your friends\n" +
                "It's fun to lose and to pretend\n" +
                "She's over-bored and self-assured\n" +
                "Oh no, I know a dirty word\n" +
                "Hello, hello, hello, how low\n" +
                "Hello, hello, hello, how low\n" +
                "Hello, hello, hello, how low\n" +
                "Hello, hello, hello\n" +
                "With the lights out, it's less dangerous\n" +
                "Here we are now, entertain us\n" +
                "I feel stupid and contagious\n" +
                "Here we are now, entertain us\n" +
                "A mulatto, an albino\n" +
                "A mosquito, my libido\n" +
                "Yeah\n" +
                "Hey\n" +
                "Yay\n" +
                "I'm worse at what I do best\n" +
                "And for this gift I feel blessed\n" +
                "Our little group has always been\n" +
                "And always will until the end\n" +
                "Hello, hello, hello, how low\n" +
                "Hello, hello, hello, how low\n" +
                "Hello, hello, hello, how low\n" +
                "Hello, hello, hello\n" +
                "With the lights out, it's less dangerous\n" +
                "Here we are now, entertain us\n" +
                "I feel stupid and contagious\n" +
                "Here we are now, entertain us\n" +
                "A mulatto, an albino\n" +
                "A mosquito, my libido\n" +
                "Yeah\n" +
                "Hey\n" +
                "Yay\n" +
                "And I forget just why I taste\n" +
                "Oh yeah, I guess it makes me smile\n" +
                "I found it hard, it's hard to find\n" +
                "Ooh well, whatever, nevermind\n" +
                "Hello, hello, hello, how low\n" +
                "Hello, hello, hello, how low\n" +
                "Hello, hello, hello, how low\n" +
                "Hello, hello, hello\n" +
                "With the lights out, it's less dangerous\n" +
                "Here we are now, entertain us\n" +
                "I feel stupid and contagious\n" +
                "Here we are now, entertain us\n" +
                "A mulatto, an albino\n" +
                "A mosquito, my libido\n" +
                "A denial, a denial\n" +
                "A denial, a denial\n" +
                "A denial, a denial\n" +
                "A denial, a denial\n" +
                "A denial", LocalDate.of(1991, 9, 24), nirvana);
        Song nv2 = new Song("Come As You Are", "...", LocalDate.of(1991, 9, 24), nirvana);
        nvAlbum1.addSong(nv1);
        nvAlbum1.addSong(nv2);

        Album nvAlbum2 = new Album("In Utero", LocalDate.of(1993, 9, 21), nirvana);
        Song nv3 = new Song("Heart-Shaped Box", "She eyes me like a Pisces when I am weak\n" +
                "I've been locked inside your heart-shaped box for weeks\n" +
                "I've been drawn into your magnet tar pit trap\n" +
                "I wish I could eat your cancer when you turn black\n" +
                "Hey\n" +
                "Wait\n" +
                "I got a new complaint\n" +
                "Forever in debt to your priceless advice\n" +
                "Hey\n" +
                "Wait\n" +
                "I got a new complaint\n" +
                "Forever in debt to your priceless advice\n" +
                "Hey\n" +
                "Wait\n" +
                "I got a new complaint\n" +
                "Forever in debt to your priceless advice\n" +
                "Your advice\n" +
                "Meat-eating orchids forgive no one just yet\n" +
                "Cut myself on angel hair and baby's breath\n" +
                "Broken hymen of Your Highness, I'm left black\n" +
                "Throw down your umbilical noose so I can climb right back\n" +
                "Hey\n" +
                "Wait\n" +
                "I got a new complaint\n" +
                "Forever in debt to your priceless advice\n" +
                "Hey\n" +
                "Wait\n" +
                "I got a new complaint\n" +
                "Forever in debt to your priceless advice\n" +
                "Hey\n" +
                "Wait\n" +
                "I got a new complaint\n" +
                "Forever in debt to your priceless advice\n" +
                "Your advice\n" +
                "She eyes me like a Pisces when I am weak\n" +
                "I've been locked inside your heart-shaped box for weeks\n" +
                "I've been drawn into your magnet tar pit trap\n" +
                "I wish I could eat your cancer when you turn black\n" +
                "Hey\n" +
                "Wait\n" +
                "I got a new complaint\n" +
                "Forever in debt to your priceless advice\n" +
                "Hey\n" +
                "Wait\n" +
                "I got a new complaint\n" +
                "Forever in debt to your priceless advice\n" +
                "Hey\n" +
                "Wait\n" +
                "I got a new complaint\n" +
                "Forever in debt to your priceless advice\n" +
                "Your advice\n" +
                "Your advice\n" +
                "Your advice", LocalDate.of(1993, 9, 21), nirvana);
        nvAlbum2.addSong(nv3);

        Song nvSingle = new Song("About a Girl", "...", LocalDate.of(1989, 6, 1), nirvana);

        nirvana.getSongs().addAll(List.of(nv1, nv2, nv3, nvSingle));
        nirvana.getAlbums().addAll(List.of(nvAlbum1, nvAlbum2));

        artists.addAll(List.of(bowie, eminem, tupac, lp, nirvana));
        albums.addAll(List.of(dbAlbum1, dbAlbum2, emAlbum1, emAlbum2, tpAlbum1, tpAlbum2, lpAlbum1, lpAlbum2, nvAlbum1, nvAlbum2));
        songs.addAll(List.of(db1, db2, db3, dbSingle, em1, em2, em3, emSingle, tp1, tp2, tp3, tpSingle, lp1, lp2, lp3, lpSingle, nv1, nv2, nv3, nvSingle));
    }
}
