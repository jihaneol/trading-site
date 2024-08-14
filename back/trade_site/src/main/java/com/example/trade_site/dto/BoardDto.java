package com.example.trade_site.dto;

import com.example.trade_site.entity.BoardEntity;
import com.example.trade_site.entity.BoardFileEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    private List<MultipartFile> boardFile;
    private List<String> originalFileName;
    private List<String> storedFileName;
    private int fileAttached;

    public static BoardDto entityToResponse(BoardEntity boardEntity){
        if(boardEntity.getFileAttached()==1){

            List<String> origin = new ArrayList<>();
            List<String> stored = new ArrayList<>();

           for(BoardFileEntity file : boardEntity.getBoardFileEntityList())  {
               origin.add(file.getOriginalFileName());
               stored.add(file.getStoredFileName());
           }

            return BoardDto.builder()
                    .id(boardEntity.getId())
                    .boardWriter(boardEntity.getBoardWriter())
                    .boardTitle(boardEntity.getBoardTitle())
                    .boardCreatedTime(boardEntity.getCreatedTime())
                    .boardHit(boardEntity.getBoardHit())
                    .storedFileName(stored)
                    .originalFileName(origin)
                    .fileAttached(1)
                    .build();
        }
        return BoardDto.builder()
                .id(boardEntity.getId())
                .boardWriter(boardEntity.getBoardWriter())
                .boardTitle(boardEntity.getBoardTitle())
                .boardCreatedTime(boardEntity.getCreatedTime())
                .boardHit(boardEntity.getBoardHit())
                .fileAttached(0)
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
