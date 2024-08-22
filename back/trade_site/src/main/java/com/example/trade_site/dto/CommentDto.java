package com.example.trade_site.dto;


import com.example.trade_site.entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommentDto {
    private Long id;
    private String commentWriter;
    private String commentContents;
    private Long boardId;
    private LocalDateTime commentCreatedTime;

    public static CommentDto toCommentDto(CommentEntity commentEntity, Long boardId) {
        CommentDto commentDto = new CommentDto();
        commentDto.setCommentContents(commentEntity.getCommentContents());
        commentDto.setCommentWriter(commentEntity.getCommentWriter());
        commentDto.setId(commentEntity.getId());
        commentDto.setCommentCreatedTime(commentEntity.getCreatedTime());
        commentDto.setBoardId(boardId);
        return commentDto;
    }
}
