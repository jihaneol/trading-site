package com.example.trade_site.entity;

import com.example.trade_site.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String commentWriter;

    private String commentContents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;


    public static CommentEntity toSaveEntity(CommentDto commentDto, BoardEntity boardEntity) {
        return CommentEntity.builder()
                .commentWriter(commentDto.getCommentWriter())
                .commentContents(commentDto.getCommentContents())
                .boardEntity(boardEntity)
                .build();
    }
}
