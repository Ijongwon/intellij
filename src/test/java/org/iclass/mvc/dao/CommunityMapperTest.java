package org.iclass.mvc.dao;

import org.iclass.mvc.dto.Community;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CommunityMapperTest {

    @Autowired
    CommunityMapper dao;
    @Test
    @DisplayName("전체 개수 조회입니다.")
    @Disabled
    void count() {

        int count =  0;//dao.count();
    }

    @Test
    @DisplayName("지정된 글번호로 조회된 글이 있어야 합니다.")
    void selectByIdx() {
        Community dto = dao.selectByIdx(133);
        Assertions.assertNotNull(dto);
    }
}