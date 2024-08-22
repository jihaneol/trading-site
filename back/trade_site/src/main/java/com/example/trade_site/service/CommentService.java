package com.example.trade_site.service;

import com.example.trade_site.dto.CommentDto;

import java.util.List;

public interface CommentService {

    void commentSave(CommentDto commentDto);

    List<CommentDto> findAll(Long boardId);
}
