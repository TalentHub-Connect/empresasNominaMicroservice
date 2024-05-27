package com.talenthub.empresanominamicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talenthub.empresanominamicroservice.model.News;
import com.talenthub.empresanominamicroservice.service.EmployeeService;
import com.talenthub.empresanominamicroservice.service.NewsService;
import com.talenthub.empresanominamicroservice.service.PayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(NewsController.class)
public class NewsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NewsService newsService;

    @MockBean
    private PayService payService;

    @MockBean
    private EmployeeService employeeService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetAllNews() throws Exception {
        mockMvc.perform(get("/news/getNews"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetNewsById() throws Exception {
        News news = new News();
        when(newsService.getById(anyLong())).thenReturn(Optional.of(news));

        mockMvc.perform(get("/news/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateNews() throws Exception {
        News news = new News();
        when(newsService.create(any(News.class))).thenReturn(news);

        mockMvc.perform(post("/news/createNews/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(news)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateNews() throws Exception {
        News news = new News();
        when(newsService.getById(anyLong())).thenReturn(Optional.of(news));
        when(newsService.update(any(News.class))).thenReturn(news);

        mockMvc.perform(put("/news/updateNews/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(news)))
                .andExpect(status().isOk());
    }
}
