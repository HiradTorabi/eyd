package com.hirad.genus.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Comment
{
    private String id;
    private User commenter;
    private String content;
    private LocalDateTime date;
    private Map<User, Boolean> likesDislikes;
    public Comment(User commenter, String content)
    {
        this.id = java.util.UUID.randomUUID().toString();
        this.commenter = commenter;
        this.content = content;
        this.date = LocalDateTime.now();
        this.likesDislikes = new HashMap<>();
    }
    public String getUsername()
    {
        return commenter.getUsername();
    }
    public String getContent()
    {
        return content;
    }
    public LocalDateTime getDate()
    {
        return date;
    }
    public String getText()
    {
        return content;
    }
    public User getAuthor()
    {
        return commenter;
    }
    public void addLike(User user)
    {
        likesDislikes.put(user, true);
    }
    public void addDislike(User user)
    {
        likesDislikes.put(user, false);
    }

    public void removeVote(User user)
    {
        likesDislikes.remove(user);
    }

    public int getLikeCount()
    {
        return (int) likesDislikes.values().stream().filter(v -> v).count();
    }

    public int getDislikeCount() {

        return (int) likesDislikes.values().stream().filter(v -> !v).count();
    }

    public boolean hasLiked(User user)
    {
        Boolean vote = likesDislikes.get(user);
        return vote != null && vote;
    }

    public boolean hasDisliked(User user)
    {
        Boolean vote = likesDislikes.get(user);
        return vote != null && !vote;
    }
    @Override
    public String toString()
    {
        return String.format("[%s] %s: %s (👍%d 👎%d)",
                date, getUsername(), content, getLikeCount(), getDislikeCount());
    }
}
