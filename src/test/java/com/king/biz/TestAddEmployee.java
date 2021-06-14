package com.king.biz;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TestAddEmployee {
    @Autowired
    FacadeService facadeService;

    @Test
    public void testAddHourlyEmployee() {
        facadeService.addHourlyEmployee(1, "t1", "a1");
    }

}
