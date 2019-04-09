package com.yfyld.tracker.service.board.impl;

import com.yfyld.tracker.dto.BoardInfo;
import com.yfyld.tracker.mapper.BoardMapper;
import com.yfyld.tracker.model.Analyse;
import com.yfyld.tracker.model.Board;
import com.yfyld.tracker.service.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value = "BoardService")
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public int addBoard(Board board){
        return boardMapper.addBoard(board);
    }

    @Override
    public List<Board> getBoardList(Long projectId){
        return boardMapper.getBoardList(projectId);
    }

    @Override
    public BoardInfo getBoardPaneList(Long boardId){ return boardMapper.getBoardPaneList(boardId);}
}
