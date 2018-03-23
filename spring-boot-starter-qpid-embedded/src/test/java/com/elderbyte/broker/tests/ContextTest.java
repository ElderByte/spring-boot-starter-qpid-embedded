package com.elderbyte.broker.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = SpringBootTestApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ContextTest {


    @Test
    public void testContextLoads(){
        Assert.assertTrue(true);
    }
}
