package com.yfyld.tracker.controller;


import com.yfyld.tracker.model.Board;

import com.yfyld.tracker.service.board.BoardService;
import com.yfyld.tracker.util.CommonResult;
import com.yfyld.tracker.util.ValidateGroups;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(description = "看板相关")
@RestController
@RequestMapping(value = "/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/")
    @ApiOperation(value = "新增看板")
    public CommonResult addBoard(@Validated(ValidateGroups.Default.class) @RequestBody Board board) {
        int count = boardService.addBoard(board);
        if (count > 0) {
            return new CommonResult().success("添加成功");
        } else {
            return new CommonResult().failed();
        }
    }

    @GetMapping("/")
    @ApiOperation(value = "看板列表")
    public CommonResult getBoardList(@RequestHeader(value="projectId") Long projectId) {
        return new CommonResult().success(
                boardService.getBoardList(projectId)
        );
    }

    @GetMapping("/{boardId}")
    @ApiOperation(value = "看板详情")
    //@ApiResponse()
    public CommonResult getBoardInfo(@PathVariable(value = "boardId", required = true) Long boardId) {
        return new CommonResult().success(boardService.getBoardPaneList(boardId));
    }

    @PutMapping("/{boardId}")
    @ApiOperation(value = "编辑看板")
    public CommonResult updateBoard(@Validated(ValidateGroups.Default.class) @RequestBody Board board) {
        return new CommonResult().success(null);
    }

    @DeleteMapping("/{boardId}")
    @ApiOperation(value = "删除看板")
    public CommonResult deleteBoard(@PathVariable(value = "boardId", required = true) Long boardId) {
        return new CommonResult().success(null);
    }
}
