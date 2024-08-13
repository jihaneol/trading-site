package com.example.trade_site.service;

import com.example.trade_site.dto.BoardDto;
import com.example.trade_site.entity.BoardEntity;
import com.example.trade_site.repository.BoardRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;

    @Override
    public BoardDto save(@Valid  BoardDto boardDto) {

        BoardEntity saveEntity = BoardEntity.toSaveEntity(boardDto);

        BoardEntity save = boardRepository.save(saveEntity);

        return BoardDto.entityToResponse(save);
    }

    @Override
    public List<BoardDto> getAllBoard() {
        List<BoardEntity> all = boardRepository.findAll();

        List<BoardDto> boardDtoList = all.stream()
                .map(boardEntity -> BoardDto.entityToResponse(boardEntity))
                .collect(Collectors.toList());

        return boardDtoList;
    }

    @Override
    public BoardDto getBoard(Long id) {
        BoardEntity boardEntity = boardRepository.findById(id).orElseThrow(() -> new IllegalStateException("해당 게시글이 없습니다."));
        boardEntity.updateBoardHit();

        return  BoardDto.entityToDetail(boardEntity);
    }

    @Override
    public BoardDto getUpdateBoard(Long id) {
        BoardEntity boardEntity = boardRepository.findById(id).orElseThrow(() -> new IllegalStateException("해당 게시글이 없습니다."));

        return  BoardDto.entityToUpdate(boardEntity);
    }

    @Override
    public BoardDto updateBoard(BoardDto boardDto) {
        BoardEntity boardEntity = boardRepository.findById(boardDto.getId()).orElseThrow(() -> {
            return new IllegalStateException("게시글이 없습니다.");
        });
        boardEntity.updateBoard(boardDto);
        boardRepository.save(boardEntity);

        return BoardDto.entityToDetail(boardEntity);
    }


}
