package com.example.trade_site.dto;

import com.example.trade_site.entity.BoardEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {
    private Long id;
    @NotNull(message = "작성자는 필수 입니다.")
    private String boardWriter;
    @NotNull(message = "비밀번호는 필수 입니다.")
    @Size(min = 8, max = 20, message = "비밀 번호는 8~20자 입니다.")
    private String boardPass;

    @Max(value = 50, message = "50자 이하 작성")
    private String boardTitle;
    @Max(value = 500, message = "500자 이하 작성")
    private String boardContents;
    private LocalDateTime boardCreatedTime;
    private int boardHit;

    public static BoardDto entityToResponse(BoardEntity boardEntity){
        return BoardDto.builder()
                .id(boardEntity.getId())
                .boardWriter(boardEntity.getBoardWriter())
                .boardTitle(boardEntity.getBoardTitle())
                .boardCreatedTime(boardEntity.getCreatedTime())
                .boardHit(boardEntity.getBoardHit())
                .build();
    }

    public static BoardDto entityToDetail(BoardEntity boardEntity) {
        return BoardDto.builder()
                .id(boardEntity.getId())
                .boardWriter(boardEntity.getBoardWriter())
                .boardContents(boardEntity.getBoardContents())
                .boardTitle(boardEntity.getBoardTitle())
                .boardCreatedTime(boardEntity.getCreatedTime())
                .boardHit(boardEntity.getBoardHit())
                .build();
    }
    public static BoardDto entityToUpdate(BoardEntity boardEntity) {
        return BoardDto.builder()
                .id(boardEntity.getId())
                .boardWriter(boardEntity.getBoardWriter())
                .boardContents(boardEntity.getBoardContents())
                .boardTitle(boardEntity.getBoardTitle())
                .boardPass(boardEntity.getBoardPass())
                .boardHit(boardEntity.getBoardHit())
                .build();
    }
}
