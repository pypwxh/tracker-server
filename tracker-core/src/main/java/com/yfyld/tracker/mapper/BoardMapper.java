package com.yfyld.tracker.mapper;
import com.yfyld.tracker.dto.BoardInfo;
import com.yfyld.tracker.model.Analyse;
import com.yfyld.tracker.model.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    int addBoard(Board board);
    List<Board>  getBoardList(Long projectId);
    BoardInfo getBoardPaneList(Long boardId);
}
