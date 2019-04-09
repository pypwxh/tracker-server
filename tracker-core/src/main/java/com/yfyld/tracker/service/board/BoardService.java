package com.yfyld.tracker.service.board;


import com.yfyld.tracker.dto.BoardInfo;
import com.yfyld.tracker.model.Analyse;
import com.yfyld.tracker.model.Board;

import java.util.List;
import java.util.Map;

public interface BoardService {
    int addBoard(Board board);
    List<Board> getBoardList(Long projectId);
    BoardInfo getBoardPaneList(Long boardId);
}
