package com.example.trade_site.controller;

import com.example.trade_site.dto.CommentDto;
import com.example.trade_site.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private  final CommentService commentService;

    @PostMapping("/save")
    public @ResponseBody List<CommentDto> commentSave(@ModelAttribute CommentDto commentDto) {
        commentService.commentSave(commentDto);
        System.out.println(commentDto.toString());
        // 댓글 목록 보여주기.
        List<CommentDto> all = commentService.findAll(commentDto.getBoardId());
        return all;
    }
}
