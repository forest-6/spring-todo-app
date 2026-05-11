package com.example.todo.dto.post;


import com.example.todo.domain.PostEntity;
import lombok.Getter;

@Getter
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private Long creatorId;
    private String createdAt;
    private String updatedAt;

    public PostResponse(PostEntity entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.creatorId = entity.getCreator_id();
        this.createdAt = entity.getCreated_at();
        this.updatedAt = entity.getUpdated_at();
    }
}
