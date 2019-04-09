package com.yfyld.tracker.core;

import com.yfyld.tracker.mapper.BoardMapper;
import com.yfyld.tracker.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrackerCoreApplicationTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrackerCoreApplicationTests.class);

    @Autowired
    private BoardMapper boardMapper;

    @Test
    public void contextLoads() {
//        Map<String,Object> a = boardMapper.getBoardPaneList(1L);
//        String json = JsonUtil.objectToJson(a);
//        LOGGER.info(json);
    }

}
