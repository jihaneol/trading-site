package com.example.trade_site.controller;

import com.example.trade_site.dto.BoardDto;
import com.example.trade_site.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @GetMapping("/save")
    public String saveForm(){
        return "save";
    }

    @PostMapping("/save")
    public String createBoard( BoardDto boardDto){
        BoardDto save = boardService.save(boardDto);
        return "index";
    }

    @GetMapping("/")
    public String getAllBoard(Model model){
        List<BoardDto> allBoard = boardService.getAllBoard();
        model.addAttribute("boardList", allBoard);
        return "board";
    }

    @GetMapping("/{id}")
    public String getDetailBoard(Model model, @PathVariable Long id){
        BoardDto board = boardService.getBoard(id);
        model.addAttribute("board",board);
        return "detail";
    }

    @GetMapping("/update/{id}")
    public String updateBoardForm(Model model, @PathVariable Long id){
        BoardDto board = boardService.getUpdateBoard(id);
        model.addAttribute("board", board);
        return "updateBoard";
    }
    @PostMapping("/update")
    public String updateBoard(@ModelAttribute BoardDto boardDto, Model model){
        BoardDto updateBoard = boardService.updateBoard(boardDto);
        model.addAttribute("board", updateBoard);
        return "detail";
    }
}
