package com.ac.swagger.controller;

import com.ac.swagger.ao.PersonAo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Hello World 单元测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("===before class===");
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("===before method===");
    }

    /**
     * 说明:若参数是实体：则需要添加.content(JSONObject.toJSONString(personAo)),若是单个参数则打开注释去掉.content(JSONObject.toJSONString(personAo))
     *
     */
    @Test
    public void hello() throws Exception {
        PersonAo personAo = new PersonAo();
        personAo.setUserName("张三");
        MvcResult result = mvc.perform(post("/").content(JSONObject.toJSONString(personAo))
                /*.param("name", "IMOOC")*/
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println("==="+result.getResponse().getContentAsString()+"===");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("===after method===");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("===after class===");
    }
}