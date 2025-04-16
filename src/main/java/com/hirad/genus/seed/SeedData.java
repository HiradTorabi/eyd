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
        Song db1 = new Song("Changes", "Still don't know what I was waiting for\n" +
                "And my time was running wild, a million dead-end streets and\n" +
                "Every time I thought I'd got it made\n" +
                "It seemed the taste was not so sweet\n" +
                "So I turned myself to face me\n" +
                "But I've never caught a glimpse\n" +
                "Of how the others must see the faker\n" +
                "I'm much too fast to take that test\n" +
                "Ch-ch-changes\n" +
                "(Turn and face the strain)\n" +
                "Ch-ch-changes\n" +
                "(Don't want to be a richer man)\n" +
                "Ch-ch-changes\n" +
                "(Turn and face the strain)\n" +
                "Ch-ch-changes\n" +
                "(Just gonna have to be a different man)\n" +
                "Time may change me\n" +
                "But I can't trace time\n" +
                "I watch the ripples change their size\n" +
                "But never leave the stream of warm impermanence and\n" +
                "So the days float through my eyes\n" +
                "But still the days seem the same\n" +
                "And these children that you spit on\n" +
                "As they try to change their worlds\n" +
                "Are immune to your consultations\n" +
                "They're quite aware of what they're going through\n" +
                "Ch-ch-changes\n" +
                "(Turn and face the strain)\n" +
                "Ch-ch-changes\n" +
                "(Don't tell them to grow up and out of it)\n" +
                "Ch-ch-changes\n" +
                "(Turn and face the strain)\n" +
                "Ch-ch-changes\n" +
                "(Where's your shame, you've left us up to our necks in it)\n" +
                "Time may change me\n" +
                "But you can't trace time\n" +
                "Strange fascination, fascinating me\n" +
                "Ah changes are taking the pace I'm going through\n" +
                "Ch-ch-changes\n" +
                "(Turn and face the strain)\n" +
                "Ch-ch-changes\n" +
                "(Oh, look out you rock 'n rollers)\n" +
                "Ch-ch-changes\n" +
                "(Turn and face the strain)\n" +
                "Ch-ch-changes\n" +
                "(Pretty soon now you're gonna get older)\n" +
                "Time may change me\n" +
                "But I can't trace time\n" +
                "I said that time may change me\n" +
                "But I can't trace time", LocalDate.of(1971, 12, 17), bowie);
        Song db2 = new Song("Life on Mars?", "It's a God awful small affair\n" +
                "To the girl with the mousey hair,\n" +
                "But her mummy is yelling, \"No!\"\n" +
                "And her daddy has told her to go,\n" +
                "But her friend is no where to be seen.\n" +
                "Now she walks through her sunken dream\n" +
                "To the seats with the clearest view\n" +
                "And she's hooked to the silver screen,\n" +
                "But the film is sadd'ning bore\n" +
                "For she's lived it ten times or more.\n" +
                "She could spit in the eyes of fools\n" +
                "As they ask her to focus on\n" +
                "Sailors\n" +
                "Fighting in the dance hall.\n" +
                "Oh man!\n" +
                "Look at those cavemen go.\n" +
                "It's the freakiest show.\n" +
                "Take a look at the lawman\n" +
                "Beating up the wrong guy.\n" +
                "Oh man!\n" +
                "Wonder if he'll ever know\n" +
                "He's in the best selling show.\n" +
                "Is there Life On Mars?\n" +
                "It's on America's tortured brow\n" +
                "That Mickey Mouse has grown up a cow.\n" +
                "Now the workers have struck for fame\n" +
                "'Cause Lennon's on sale again.\n" +
                "See the mice in their million hordes\n" +
                "From Ibeza to the Norfolk Broads.\n" +
                "Rule Britannia is out of bounds\n" +
                "To my mother, my dog, and clowns,\n" +
                "But the film is a sadd'ning bore\n" +
                "'Cause I wrote it ten times or more.\n" +
                "It's about to be writ again\n" +
                "As I ask you to focus on\n" +
                "Sailors\n" +
                "Fighting in the dance hall.\n" +
                "Oh man!\n" +
                "Look at those cavemen go.\n" +
                "It's the freakiest show.\n" +
                "Take a look at the lawman\n" +
                "Beating up the wrong guy.\n" +
                "Oh man!\n" +
                "Wonder if he'll ever know\n" +
                "He's in the best selling show.\n" +
                "Is there life on Mars", LocalDate.of(1971, 12, 17), bowie);
        dbAlbum1.addSong(db1);
        dbAlbum1.addSong(db2);

        Album dbAlbum2 = new Album("Heroes", LocalDate.of(1977, 9, 23), bowie);
        Song db3 = new Song("Heroes", "I, I will be king\n" +
                "And you, you will be queen\n" +
                "Though nothing will drive them away\n" +
                "We can beat them, just for one day\n" +
                "We can be heroes, just for one day\n" +
                "And you, you can be mean\n" +
                "And I, I'll drink all the time\n" +
                "'Cause we're lovers, and that is a fact\n" +
                "Yes we're lovers, and that is that\n" +
                "Though nothing will keep us together\n" +
                "We could steal time just for one day\n" +
                "We can be heroes for ever and ever\n" +
                "What d'you say?\n" +
                "I, I wish you could swim\n" +
                "Like the dolphins, like dolphins can swim\n" +
                "Though nothing, nothing will keep us together\n" +
                "We can beat them, for ever and ever\n" +
                "Oh we can be Heroes, just for one day\n" +
                "I, I will be king\n" +
                "And you, you will be queen\n" +
                "Though nothing will drive them away\n" +
                "We can be Heroes, just for one day\n" +
                "We can be us, just for one day\n" +
                "I, I can remember (I remember)\n" +
                "Standing, by the wall (by the wall)\n" +
                "And the guns, shot above our heads (over our heads)\n" +
                "And we kissed, as though nothing could fall (nothing could fall)\n" +
                "And the shame, was on the other side\n" +
                "Oh we can beat them, for ever and ever\n" +
                "Then we could be Heroes, just for one day\n" +
                "We can be Heroes\n" +
                "We can be Heroes\n" +
                "We can be Heroes\n" +
                "Just for one day\n" +
                "We can be Heroes\n" +
                "We're nothing, and nothing will help us\n" +
                "Maybe we're lying, then you better not stay\n" +
                "But we could be safer, just for one day\n" +
                "Oh-oh-oh-ohh, oh-oh-oh-ohh, just for one day", LocalDate.of(1977, 9, 23), bowie);
        dbAlbum2.addSong(db3);

        Song dbSingle = new Song("Starman", "Didn't know what time it was\n" +
                "The lights were low\n" +
                "I leaned back on my radio\n" +
                "Some cat was layin' down\n" +
                "Some rock 'n' roll\n" +
                "'Lotta soul', he said\n" +
                "Then the loud sound did seem to fade\n" +
                "Came back like a slow voice on a wave of phase\n" +
                "That weren't no D.J. that was hazy cosmic jive\n" +
                "There's a starman waiting in the sky\n" +
                "He'd like to come and meet us\n" +
                "But he thinks he'd blow our minds\n" +
                "There's a starman waiting in the sky\n" +
                "He's told us not to blow it\n" +
                "'Cause he knows it's all worthwhile\n" +
                "He told me:\n" +
                "Let the children lose it\n" +
                "Let the children use it\n" +
                "Let all the children boogie\n" +
                "I had to phone someone so I picked on you\n" +
                "Hey, that's far out so you heard him too!\n" +
                "Switch on the TV we may pick him up on channel two\n" +
                "Look out your window I can see his light\n" +
                "If we can sparkle he may land tonight\n" +
                "Don't tell your poppa or he'll get us locked up\n" +
                "In fright\n" +
                "There's a starman waiting in the sky\n" +
                "He'd like to come and meet us\n" +
                "But he thinks he'd blow our minds\n" +
                "There's a starman waiting in the sky\n" +
                "He's told us not to blow it\n" +
                "'Cause he knows it's all worthwhile\n" +
                "He told me:\n" +
                "Let the children lose it\n" +
                "Let the children use it\n" +
                "Let all the children boogie\n" +
                "Starman waiting in the sky\n" +
                "He'd like to come and meet us\n" +
                "But he thinks he'd blow our minds\n" +
                "There's a starman waiting in the sky\n" +
                "He's told us not to blow it\n" +
                "'Cause he knows it's all worthwhile\n" +
                "He told me:\n" +
                "Let the children lose it\n" +
                "Let the children use it\n" +
                "Let all the children boogie", LocalDate.of(1972, 4, 28), bowie);

        bowie.getSongs().addAll(List.of(db1, db2, db3, dbSingle));
        bowie.getAlbums().addAll(List.of(dbAlbum1, dbAlbum2));

        //Eminem
        Artist eminem = new Artist("Eminem", 50, "eminem@rap.com", "eminem", "rapgod");
        eminem.setVerified(true);
        Album emAlbum1 = new Album("The Marshall Mathers LP", LocalDate.of(2000, 5, 23), eminem);
        Song em1 = new Song("Stan", "My tea's gone cold, I'm wonderin why i got out of bed at all.\n" +
                "the morning rain clouds up my window and i can't see at all.\n" +
                "And even if i could it'd all be grey. with your picture on my wall.\n" +
                "It reminds me that it's not so bad, it's not so bad.\n" +
                "\n" +
                "My tea's gone cold, I'm wonderin why i got out of bed at all.\n" +
                "the morning rain clouds up my window and i can't see at all.\n" +
                "And even if i could it'd all be grey. with your picture on my wall.\n" +
                "It reminds me that it's not so bad, it's not so bad...\n" +
                "\n" +
                "Dear Slim, I wrote you but you still ain't callin'\n" +
                "I left my cell, my pager, and my home phone at the bottom\n" +
                "I sent two letters back in autumn You must not have got them\n" +
                "There probably was a problem at the post office or somethin'\n" +
                "\n" +
                "Sometimes I scribble addresses too sloppy when I jot 'em\n" +
                "But anyways, fuck it, what's been up man, how's your daughter?\n" +
                "My girlfriend's pregnant too, I'm 'bout to be a father\n" +
                "If I have a daughter, guess what I'm-a call her? I'ma\n" +
                "name her Bonnie.\n" +
                "\n" +
                "I read about your uncle Ronnie too, I'm sorry\n" +
                "I had a friend kill himself over some bitch who didn't want him.\n" +
                "I know you probably hear this everyday, but I'm your biggest fan.\n" +
                "I even got the underground shit that you did with Scam.\n" +
                "\n" +
                "I got a room full of your posters and your pictures, man.\n" +
                "I like the shit you did with Ruckus too, that shit was phat.\n" +
                "Anyways, I hope you get this man, hit me back, just to chat\n" +
                "Truly yours, your biggest fan, this is Stan.\n" +
                "\n" +
                "My tea's gone cold, I'm wonderin why i got out of bed at all.\n" +
                "the morning rain clouds up my window and i can't see at all.\n" +
                "And even if i could it'd all be grey. with your picture on my wall.\n" +
                "It reminds me that it's not so bad, it's not so bad.\n" +
                "\n" +
                "Dear Slim, you still ain't called or wrote,\n" +
                "I hope you have the chance.\n" +
                "I ain't mad, I just think it's fucked up you don't answer fans.\n" +
                "If you didn't want to talk to me outside the concert\n" +
                "you didn't have to, but you could have signed an autograph\n" +
                "for Matthew.\n" +
                "That's my little brother, man. He's only 6 years old.\n" +
                "We waited in the blistering cold for you for 4 hours\n" +
                "and you just said no.\n" +
                "That's pretty shitty man, you're like his fuckin' idol\n" +
                "He wants to be just like you man, he likes you more\n" +
                "than I do.\n" +
                "\n" +
                "\n" +
                "I ain't that mad, though I just don't like bein' lied to.\n" +
                "Remember when we met in Denver? You said if I write you\n" +
                "You would write back. See, I'm just like you in a way.\n" +
                "I never knew my father neither,\n" +
                "He used to always cheat on my mom and beat her.\n" +
                "\n" +
                "I can relate to what you're sayin' in your songs.\n" +
                "So when I have a shitty day, I drift away and put 'em on.\n" +
                "Cause I don't really got shit else, so that shit helps\n" +
                "when I'm depressed.\n" +
                "I even got a tattoo with your name across the chest.\n" +
                "\n" +
                "Sometimes I even cut myself to see how much it bleeds.\n" +
                "It's like adrenaline. The Pain is such a sudden rush for me.\n" +
                "See, everything you say is real, and I respect you\n" +
                "'cause you tell it.\n" +
                "My girlfriend's jealous 'cause I talk about you 24/7.\n" +
                "But she don't know you like I know you, Slim, no one does.\n" +
                "She don't know what it was like for people like us growing up.\n" +
                "You've gotta call me man. I'll be the biggest fan\n" +
                "you'll ever lose.\n" +
                "Sincerely yours, Stan. PS: We should be together too.\n" +
                "\n" +
                "My tea's gone cold, I'm wonderin why i got out of bed at all.\n" +
                "the morning rain clouds up my window and i can't see at all.\n" +
                "And even if i could it'd all be grey. with your picture on my wall.\n" +
                "It reminds me that it's not so bad, it's not so bad.\n" +
                "\n" +
                "Dear Mr. I'm too good to call or write my fans\n" +
                "This'll be the last package I ever send your ass.\n" +
                "It's been six months and still no word. I don't\n" +
                "deserve it.\n" +
                "I know you got my last two letters, I wrote the\n" +
                "addresses on 'em perfect.\n" +
                "\n" +
                "So this is my cassette I'm sending you. I hope you hear it.\n" +
                "I'm in the car right now. I'm doing 90 on the freeway. hey slim i drank\n" +
                "a 5th a vodca dare me to drive? you know that song by phil collins in\n" +
                "the air in the night about that guy who could've saved that other guy\n" +
                "from drowning but didn't and phil saw it all and at his show he found\n" +
                "him thats kinda how this is you could've rescued me from drowning\n" +
                "but its too late i'm on a thousand downers now i'm drowsy and all i\n" +
                "wanted was a lousy letter or a call i hope you know i ripped all your\n" +
                "pictures off the wall I loved you Slim, we could have been together.\n" +
                "Think about it.You ruined it now, I hope you can't sleep and you dream about it.\n" +
                "And when you dream, I hope you can't sleep and you scream about it.\n" +
                "I hope your conscious eats at you and you can't breathe without me.\n" +
                "See Slim, {screaming} shut up bitch, I'm trying to talk\n" +
                "Hey Slim, that's my girlfriend screaming in the trunk.\n" +
                "But I didn't slit her throat,\n" +
                "I just tied her up, see I ain't like you.\n" +
                "'Cause if she suffocates, she'll suffer more,\n" +
                "then she'll die too.\n" +
                "Well, gotta go, I'm almost at the bridge now.\n" +
                "Oh shit, I forgot, how am I supposed to send this shit out?\n" +
                "\n" +
                "My tea's gone cold, I'm wonderin why i got out of bed at all.\n" +
                "the morning rain clouds up my window and i can't see at all.\n" +
                "And even if i could it'd all be grey. with your picture on my wall.\n" +
                "It reminds me that it's not so bad, it's not so bad.\n" +
                "\n" +
                "Dear Stan, I meant to write you sooner, but I've just been busy.\n" +
                "You said your girlfriend's pregnant now, how far along is she?\n" +
                "Look, I'm really flatterd you'd call your daughter that.\n" +
                "And here's an autograph for your brother: I wrote it\n" +
                "on the Starter cap.\n" +
                "\n" +
                "I'm sorry I didn't see you at the show, I must have missed you.\n" +
                "Don't think I did that shit intentionally, just to diss you.\n" +
                "but what's this shit you said about you\n" +
                "like to cut your wrists too?\n" +
                "I say that shit just clownin' dog,\n" +
                "c'mon, how fucked up is you?\n" +
                "You got some issues, Stan, I think you need some counselin'\n" +
                "To help your ass from bouncin' off the\n" +
                "walls when you get down some.\n" +
                "\n" +
                "And what's this shit about us meant to be together?\n" +
                "That type of shit'll make me not want us to meet each other.\n" +
                "I really think you and your girlfriend need each other.\n" +
                "Or maybe you just need to treat her better.\n" +
                "I hope you get to read this letter.\n" +
                "I just hope it reaches you in time.\n" +
                "Before you hurt yourself, I think that you'd be doin'\n" +
                "just fine\n" +
                "If you'd relax a little. I'm glad that I inspire you, but Stan\n" +
                "Why are you so mad? Try to understand that I do want\n" +
                "you as a fan.\n" +
                "I just don't want you to do some crazy shit.\n" +
                "I seen this one shit on the news a couple weeks ago\n" +
                "that made me sick.\n" +
                "Some dude was drunk and drove his car over a bridge\n" +
                "And had his girlfriend in the trunk and she was\n" +
                "pregnant with his kid\n" +
                "And in the car they found a tape but it didn't say who\n" +
                "it was to\n" +
                "Come to think about it...his name was...it was you.\n" +
                "Damn.", LocalDate.of(2000, 5, 23), eminem);
        Song em2 = new Song("The Way I Am", "Intro]\n" +
                "Man, whatever\n" +
                "Dre, just let it run\n" +
                "Ayo, turn the beat up a little bit\n" +
                "Ayo\n" +
                "This song is for anyone\n" +
                "Fuck it, just shut up and listen\n" +
                "Ayo\n" +
                "\n" +
                "[Verse 1]\n" +
                "I sit back with this pack of Zig-Zags and this bag of this weed\n" +
                "It gives me the shit needed to be the most meanest MC on this—\n" +
                "On this Earth, and since birth, I've been cursed with this curse to just curse\n" +
                "And just blurt this berserk and bizarre shit that works\n" +
                "And it sells and it helps in itself to relieve\n" +
                "All this tension, dispensin' these sentences\n" +
                "Gettin' this stress that's been eatin' me recently\n" +
                "Off of this chest, and I rest again peacefully\n" +
                "But at least have the decency in you\n" +
                "To leave me alone, when you freaks see me out\n" +
                "In the streets when I'm eatin' or feedin' my daughter\n" +
                "Do not come and speak to me\n" +
                "I don't know you, and, no, I don't owe you a motherfuckin' thing\n" +
                "I'm not Mr. *NSYNC, I'm not what your friends think\n" +
                "I'm not Mr. Friendly, I can be a prick\n" +
                "If you tempt me, my tank is on empty\n" +
                "No patience is in me, and if you offend me\n" +
                "I'm liftin' you ten feet in the air\n" +
                "I don't care who was there and who saw me, just jaw you\n" +
                "Go call you a lawyer, file you a lawsuit\n" +
                "I'll smile in the courtroom, and buy you a wardrobe\n" +
                "I'm tired of all you\n" +
                "I don't mean to be mean\n" +
                "But that's all I can be, it's just me\n" +
                "You might also like\n" +
                "Antichrist\n" +
                "Eminem\n" +
                "Renaissance\n" +
                "Eminem\n" +
                "Big Foot\n" +
                "Nicki Minaj\n" +
                "[Chorus]\n" +
                "And I am whatever you say I am\n" +
                "If I wasn't, then why would I say I am?\n" +
                "In the paper, the news, everyday I am\n" +
                "(Ha) Radio won't even play my jam\n" +
                "'Cause I am whatever you say I am\n" +
                "If I wasn't, then why would I say I am?\n" +
                "In the paper, the news, everyday I am\n" +
                "(Ha) I don't know, that's just the way I am\n" +
                "\n" +
                "[Verse 2]\n" +
                "Sometimes I just feel like my father, I hate to be bothered\n" +
                "With all of this nonsense, it's constant\n" +
                "And, \"Oh, it's his lyrical content\n" +
                "The song Guilty Conscience has gotten such rotten responses\"\n" +
                "And all of this controversy circles me\n" +
                "And it seems like the media immediately points a finger at me\n" +
                "So I point one back at 'em, but not the index or pinkie\n" +
                "Or the ring or the thumb, it's the one you put up\n" +
                "When you don't give a fuck, when you won't just put up\n" +
                "With the bullshit they pull, 'cause they full of shit too\n" +
                "When a dude's gettin' bullied and shoots up his school\n" +
                "And they blame it on Marilyn and the heroin\n" +
                "Where were the parents at?\n" +
                "And look where it's at\n" +
                "Middle America, now it's a tragedy\n" +
                "Now it's so sad to see\n" +
                "An upper-class city havin' this happenin'\n" +
                "Then attack Eminem 'cause I rap this way, but I'm glad\n" +
                "'Cause they feed me the fuel that I need for the fire to burn\n" +
                "And it's burnin', and I have returned\n" +
                "[Chorus]\n" +
                "And I am whatever you say I am\n" +
                "If I wasn't, then why would I say I am?\n" +
                "In the paper, the news, everyday I am\n" +
                "Radio won't even play my jam\n" +
                "'Cause I am whatever you say I am\n" +
                "If I wasn't, then why would I say I am?\n" +
                "In the paper, the news, everyday I am\n" +
                "(Ha) I don't know, that's just the way I am\n" +
                "\n" +
                "[Verse 3]\n" +
                "I'm so sick and tired of bein' admired that I wish that I would just die or get fired\n" +
                "And dropped from my label, let's stop with the fables\n" +
                "I'm not gonna be able to top a My Name Is\n" +
                "And pigeon-holed into some poppy sensation\n" +
                "To cop me rotation at rock 'n' roll stations\n" +
                "And I just do not got the patience\n" +
                "To deal with these cocky Caucasians who think\n" +
                "I'm some wigga who just tries to be black\n" +
                "'Cause I talk with an accent and grab on my balls\n" +
                "So they always keep askin' the same fuckin' questions\n" +
                "What school did I go to, what hood I grew up in\n" +
                "The why, the who, what, when, the where, and the how\n" +
                "'Til I'm grabbin' my hair and I'm tearin' it out (Argh)\n" +
                "'Cause they drivin' me crazy, I can't take it\n" +
                "I'm racin', I'm pacin', I stand, and I sit\n" +
                "And I'm thankful for every fan that I get\n" +
                "But I can't take a shit in the bathroom without someone standin' by it\n" +
                "No, I won't sign your autograph\n" +
                "You can call me an asshole, I'm glad\n" +
                "[Chorus]\n" +
                "'Cause I am whatever you say I am\n" +
                "If I wasn't, then why would I say I am?\n" +
                "In the paper, the news, everyday I am\n" +
                "Radio won't even play my jam\n" +
                "'Cause I am whatever you say I am\n" +
                "If I wasn't, then why would I say I am?\n" +
                "In the paper, the news, everyday I am\n" +
                "(Ha) I don't know, that's just the way I am", LocalDate.of(2000, 5, 23), eminem);
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
        Song nv2 = new Song("Come As You Are", "[Verse 1]\n" +
                "Come as you are, as you were\n" +
                "As I want you to be\n" +
                "As a friend, as a friend\n" +
                "As an old enemy\n" +
                "Take your time, hurry up\n" +
                "Choice is yours, don't be late\n" +
                "Take a rest as a friend\n" +
                "As an old memoria\n" +
                "\n" +
                "[Refrain]\n" +
                "Memoria\n" +
                "Memoria\n" +
                "Memoria\n" +
                "\n" +
                "[Verse 2]\n" +
                "Come doused in mud, soaked in bleach\n" +
                "As I want you to be\n" +
                "As a trend, as a friend\n" +
                "As an old memoria\n" +
                "\n" +
                "[Refrain]\n" +
                "Memoria\n" +
                "Memoria\n" +
                "Memoria\n" +
                "You might also like\n" +
                "THE HEART PART 6\n" +
                "Drake\n" +
                "St. Chroma\n" +
                "Tyler, The Creator\n" +
                "LUNCH\n" +
                "Billie Eilish\n" +
                "[Chorus]\n" +
                "And I swear that I don't have a gun\n" +
                "No, I don't have a gun\n" +
                "No, I don't have a gun\n" +
                "\n" +
                "[Guitar Solo]\n" +
                "\n" +
                "[Refrain]\n" +
                "'Ria\n" +
                "Memoria\n" +
                "Memoria\n" +
                "Memoria (No, I don't have a gun)\n" +
                "\n" +
                "[Chorus]\n" +
                "And I swear that I don't have a gun\n" +
                "No, I don't have a gun\n" +
                "No, I don't have a gun\n" +
                "No, I don't have a gun\n" +
                "No, I don't have a gun\n" +
                "\n" +
                "[Outro]\n" +
                "(Memoria)\n" +
                "(Memoria)", LocalDate.of(1991, 9, 24), nirvana);
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
