package com.hirad.genus.model;

import com.hirad.genus.seed.SeedData;

public class LyricEditRequest
{
    private String id;
    private User requester;
    private Song song;
    private String proposedLyrics;
    private boolean isApproved;
    public LyricEditRequest(User requester, Song song, String proposedLyrics)
    {
        this.id = java.util.UUID.randomUUID().toString();
        this.requester = requester;
        this.song = song;
        this.proposedLyrics = proposedLyrics;
        this.isApproved = false;
        for (Admin admin : SeedData.admins)
        {
            admin.addNotification(new Notification(
                    requester.getUsername() + " suggested lyric edit for song: " + song.getTitle()
            ));
        }
        Artist mainArtist = song.getArtist();
        if (mainArtist != null)
        {
            mainArtist.addNotification(new Notification(
                    requester.getUsername() + " suggested lyric edit for your song: " + song.getTitle()
            ));
        }
        for (Artist coArtist : song.getCoArtists())
        {
            coArtist.addNotification(new Notification(
                    requester.getUsername() + " suggested lyric edit for your co-song: " + song.getTitle()
            ));
        }

    }
    public User getRequester()
    {
        return requester;
    }
    public Song getSong()
    {
        return song;
    }
    public String getProposedLyrics()
    {
        return proposedLyrics;
    }
    public boolean isApproved()
    {
        return isApproved;
    }
    public void approve()
    {
        this.isApproved = true;
        song.setLyrics(proposedLyrics);
    }
    public void reject()
    {
        this.isApproved = false;
    }
    @Override
    public String toString()
    {
        return String.format("Edit Request for [%s] by %s: %s",
                song.getTitle(), requester.getUsername(), isApproved ? "Approved" : "Pending");
    }
}