package com.hirad.genus.model;


import java.time.LocalDateTime;

public class Comment
{
    private String id;
    private User commenter;
    private String content;
    private LocalDateTime date;
    public Comment(User commenter, String content)
    {
        this.id = java.util.UUID.randomUUID().toString();
        this.commenter = commenter;
        this.content = content;
        this.date = LocalDateTime.now();
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
    @Override
    public String toString()
    {
        return String.format("[%s] %s: %s", date, getUsername(), content);
    }
}
