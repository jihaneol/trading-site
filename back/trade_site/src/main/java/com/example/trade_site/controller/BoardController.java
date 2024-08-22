package com.example.trade_site.controller;

import com.example.trade_site.dto.BoardDto;
import com.example.trade_site.dto.CommentDto;
import com.example.trade_site.service.BoardService;
import com.example.trade_site.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final CommentService commentService;

    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/save")
    public String createBoard(@Valid BoardDto boardDto) throws IOException {
        boardService.save(boardDto);
        return "index";
    }

    @GetMapping
    public String getAllBoard(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<BoardDto> boardList = boardService.getAllBoard(pageable);
        int blockLimit = 3;
        int startPage = ((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit)) - 1) * blockLimit + 1;
        int endPage = (startPage + blockLimit - 1 < boardList.getTotalPages()) ? startPage + blockLimit - 1 : boardList.getTotalPages();

        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "board";
    }

    @GetMapping("/{id}")
    public String getDetailBoard(Model model, @PathVariable Long id,
                                 @PageableDefault(page = 1) Pageable pageable) {
        BoardDto board = boardService.getBoard(id);
        List<CommentDto> commentDtoList = commentService.findAll(id);
        model.addAttribute("commentList", commentDtoList);
        model.addAttribute("board", board);
        model.addAttribute("page",pageable.getPageNumber());
        return "detail";
    }

    @GetMapping("/update/{id}")
    public String updateBoardForm(Model model, @PathVariable Long id) {
        BoardDto board = boardService.getUpdateBoard(id);
        model.addAttribute("board", board);
        return "updateBoard";
    }

    @PostMapping("/update")
    public String updateBoard(@ModelAttribute BoardDto boardDto, Model model) {
        BoardDto updateBoard = boardService.updateBoard(boardDto);
        model.addAttribute("board", updateBoard);
        return "detail";
    }

    @GetMapping("/delete/{id}")
    public String deleteForm(Model model, @PathVariable Long id) {
        boardService.deleteBoard(id);
        return "redirect:/board/";
    }

}
