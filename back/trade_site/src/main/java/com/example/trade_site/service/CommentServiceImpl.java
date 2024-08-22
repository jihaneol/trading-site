package com.example.trade_site.service;

import com.example.trade_site.dto.CommentDto;
import com.example.trade_site.entity.BoardEntity;
import com.example.trade_site.entity.CommentEntity;
import com.example.trade_site.repository.BoardRepository;
import com.example.trade_site.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Override
    public void commentSave(CommentDto commentDto) {
        BoardEntity boardEntity = boardRepository.findById(commentDto.getBoardId()).orElseThrow(() -> {
            return new IllegalStateException("게시판 없음");
        });
        CommentEntity saveEntity = CommentEntity.toSaveEntity(commentDto, boardEntity);
        commentRepository.save(saveEntity);

    }

    @Override
    public List<CommentDto> findAll(Long boardId) {
        BoardEntity boardEntity = boardRepository.findById(boardId).orElseThrow(() -> {
            return new IllegalStateException("게시판 없음");
        });
        List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);

        List<CommentDto> collect = commentEntityList.stream()
                .map(commentEntity -> CommentDto.toCommentDto(commentEntity, boardId))
                .collect(Collectors.toList());
        return collect;
    }
}
