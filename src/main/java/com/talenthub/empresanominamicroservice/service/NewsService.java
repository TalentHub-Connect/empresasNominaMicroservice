package com.talenthub.empresanominamicroservice.service;
/**
 * Developed by: Juan Felipe Arias
 */

import com.talenthub.empresanominamicroservice.model.News;
import com.talenthub.empresanominamicroservice.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing news.
 */
@Service
public class NewsService {
    
    @Autowired
    private NewsRepository newsRepository;

    /**
     * @name getAll
     * @description Retrieves all existing news.
     *
     * @return An iterable list of news
     */
    public Iterable<News> getAll(){
        return newsRepository.findAll();
    }

    /**
     * @name getById
     * @description Retrieves news by its ID.
     *
     * @param id the ID of the news
     * @return An optional containing the news with the specified ID, if exists
     */
    public Optional<News> getById(Long id){
        return newsRepository.findById(id);
    }

    /**
     * @name create
     * @description Creates a new news.
     *
     * @param news the details of the news to create
     * @return The newly created news entity.
     */
    public News create(News news){
        return newsRepository.save(news);
    }

    /**
     * @name update
     * @description Updates an existing news.
     *
     * @param news the news to update
     * @return The updated news
     */
    public News update(News news){
        return newsRepository.save(news);
    }
    
}
