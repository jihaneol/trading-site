package com.example.trade_site.entity;

import com.example.trade_site.dto.BoardDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "board")
public class BoardEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String boardWriter;

    @Column
    private String boardPass;

    @Column
    private String boardTitle;

    @Column(length = 500)
    private String boardContents;

    @ColumnDefault("0")
    private int boardHit;

    public static BoardEntity toSaveEntity(BoardDto boardDto){
        return BoardEntity.builder()
                .boardWriter(boardDto.getBoardWriter())
                .boardPass(boardDto.getBoardPass())
                .boardTitle(boardDto.getBoardTitle())
                .boardContents(boardDto.getBoardContents())
                .build();
    }

    public void updateBoardHit() {
        boardHit++;
    }

    public void updateBoard(BoardDto boardDto) {
        boardTitle = boardDto.getBoardTitle();
        boardContents = boardDto.getBoardContents();
    }
}
