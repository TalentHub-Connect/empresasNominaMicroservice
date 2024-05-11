package com.talenthub.empresanominamicroservice.service;

import com.talenthub.empresanominamicroservice.model.News;
import com.talenthub.empresanominamicroservice.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NewsService {
    
    @Autowired
    private NewsRepository newsRepository;

    public Iterable<News> getAll(){
        return newsRepository.findAll();
    }

    public Optional<News> getById(Long id){
        return newsRepository.findById(id);
    }

    public News create(News News){
        return newsRepository.save(News);
    }

    public News update(News News){
        return newsRepository.save(News);
    }
    
}
