package com.example.trade_site.entity;

import com.example.trade_site.dto.BoardDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

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

    @Column
    private int fileAttached; //미첨부 0, 첨부 1

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardFileEntity> boardFileEntityList = new ArrayList<>();

    public static BoardEntity toSaveEntity(BoardDto boardDto) {
        return BoardEntity.builder()
                .boardWriter(boardDto.getBoardWriter())
                .boardPass(boardDto.getBoardPass())
                .boardTitle(boardDto.getBoardTitle())
                .boardContents(boardDto.getBoardContents())
                .fileAttached(0)
                .build();
    }

    public static BoardEntity toSaveFileEntity(BoardDto boardDto) {
        return BoardEntity.builder()
                .boardWriter(boardDto.getBoardWriter())
                .boardPass(boardDto.getBoardPass())
                .boardTitle(boardDto.getBoardTitle())
                .boardContents(boardDto.getBoardContents())
                .fileAttached(1)
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
