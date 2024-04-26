package com.grayseal.microfictionapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Comment {
    @Id
    private Long id;

    private String content;

    private Long userId;

    private Long storyId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStoryId() {
        return storyId;
    }

    public void setStoryId(Long storyId) {
        this.storyId = storyId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}