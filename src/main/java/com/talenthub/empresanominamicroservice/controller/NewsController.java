package com.talenthub.empresanominamicroservice.controller;

import com.talenthub.empresanominamicroservice.model.News;
import com.talenthub.empresanominamicroservice.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/news")
public class NewsController {
    
    @Autowired
    private NewsService newsService;

    @CrossOrigin
    @GetMapping("/getNews")
    public Iterable<News> getAllNews() {
        return newsService.getAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Optional<News> getNewsById(@PathVariable Long id) {
        return newsService.getById(id);
    }

    @CrossOrigin
    @PostMapping("/createNews")
    public News createNews(@RequestBody News News) {
        return newsService.create(News);
    }

    @CrossOrigin
    @PutMapping("/updateNews/{id}")
    public News updateNews(@PathVariable Long id, @RequestBody News newsDetails){
        Optional<News> optionalNews = newsService.getById(id);

        News news = optionalNews.get();

        news.setName(newsDetails.getName());
        news.setDescription(newsDetails.getDescription());
        news.setMoneybenefit(newsDetails.getMoneybenefit());

        return newsService.update(news);

    }
    
}
