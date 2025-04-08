package com.hirad.genus.model;

import java.time.LocalDateTime;

public class Notification {
    private final String message;
    private final LocalDateTime createdAt;
    private boolean seen;

    public Notification(String message) {
        this.message = message;
        this.createdAt = LocalDateTime.now();
        this.seen = false;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isSeen() {
        return seen;
    }

    public void markAsSeen() {
        this.seen = true;
    }

    @Override
    public String toString() {
        return (seen ? "✓ " : "🔔 ") + message + "  [" + createdAt.toLocalTime() + "]";
    }
}
