package com.example.trade_site.service;

import com.example.trade_site.dto.BoardDto;
import com.example.trade_site.entity.BoardEntity;
import com.example.trade_site.entity.BoardFileEntity;
import com.example.trade_site.repository.BoardRepository;
import com.example.trade_site.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;
    private final FileRepository fileRepository;

    @Override
    public void save(BoardDto boardDto) throws IOException {
        List<MultipartFile> boardFileList = boardDto.getBoardFile();
        if(boardFileList.isEmpty()){
            BoardEntity saveEntity = BoardEntity.toSaveEntity(boardDto);

            BoardEntity save = boardRepository.save(saveEntity);

        }

        BoardEntity saveFileEntity = BoardEntity.toSaveFileEntity(boardDto);
        BoardEntity boardEntity = boardRepository.save(saveFileEntity);

        for(MultipartFile boardFile : boardFileList){
            String originalFileName = boardFile.getOriginalFilename();
            String storedFileName = UUID.randomUUID().toString() + "_" + originalFileName;

            String savePath = "C:/springboot_img/" + storedFileName;

            boardFile.transferTo(new File(savePath));

            BoardFileEntity boardFileEntity = BoardFileEntity.saveToEntity(boardEntity, originalFileName, storedFileName);
            fileRepository.save(boardFileEntity);
        }
    }

    @Override
    public Page<BoardDto> getAllBoard(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 3;
        Page<BoardEntity> all =
                boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC,"id")));
        Page<BoardDto> boardDtoList = all.map(boardEntity -> BoardDto.entityToResponse(boardEntity));
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

    @Override
    public void deleteBoard(Long id) {
        BoardEntity boardEntity = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalStateException("게시글이 없습니다.");
        });

        boardRepository.delete(boardEntity);

    }


}
