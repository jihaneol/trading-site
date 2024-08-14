package com.example.trade_site.service;

import com.example.trade_site.dto.BoardDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    BoardDto save(BoardDto boardDto);

    Page<BoardDto> getAllBoard(Pageable pageable);

    BoardDto getBoard(Long id);

    BoardDto getUpdateBoard(Long id);

    BoardDto updateBoard(BoardDto boardDto);

    void deleteBoard(Long id);
}
