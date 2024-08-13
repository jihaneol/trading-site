package com.example.trade_site.service;

import com.example.trade_site.dto.BoardDto;

import java.util.List;

public interface BoardService {
    BoardDto save(BoardDto boardDto);

    List<BoardDto> getAllBoard();

    BoardDto getBoard(Long id);

    BoardDto getUpdateBoard(Long id);

    BoardDto updateBoard(BoardDto boardDto);
}
