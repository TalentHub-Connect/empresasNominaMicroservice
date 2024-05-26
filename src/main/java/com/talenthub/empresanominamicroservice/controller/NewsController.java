package com.talenthub.empresanominamicroservice.controller;
/**
 * Developed by: Juan Felipe Arias
 */

import com.talenthub.empresanominamicroservice.model.News;
import com.talenthub.empresanominamicroservice.model.Pay;
import com.talenthub.empresanominamicroservice.service.NewsService;
import com.talenthub.empresanominamicroservice.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller to manage operations related to news.
 */
@RestController
@RequestMapping("/news")
public class NewsController {
    
    @Autowired
    private NewsService newsService;

    @Autowired
    private PayService payService;

    /**
     * @name getAllNews
     * @description Retrieves all existing news.
     *
     * @return An iterable list of news.
     */
    @CrossOrigin
    @GetMapping("/getNews")
    public Iterable<News> getAllNews() {
        return newsService.getAll();
    }

    /**
     * @name getNewsById
     * @description Retrieves news by its ID.
     *
     * @param id the ID of the news.
     * @return An optional containing the news with the specified ID, if exists.
     */
    @CrossOrigin
    @GetMapping("/{id}")
    public Optional<News> getNewsById(@PathVariable Long id) {
        return newsService.getById(id);
    }

    /**
     * @name createNews
     * @description Creates a new news.
     *
     * @param news the details of the news to create.
     * @return The newly created news.
     */
    @CrossOrigin()
    @PostMapping("/createNews/{id}")
    public News createNews(@PathVariable Long id,@RequestBody News news) {

        Pay payEmployee = payService.getPayByEmployeeId(id);

        //It's deprecated because It doesn't makes sense with the business logic.
        //Double moreSalary = news.getMoneybenefit();
        //Double actualSalary = payEmployee.getDiscount();
        //Double finalSalary = actualSalary + moreSalary;
        //payEmployee.setDiscount(finalSalary);

        payEmployee.setStatus("Revisado");

        payService.update(payEmployee);

        return newsService.create(news);
    }

    /**
     * @name updateNews
     * @description Updates an existing news.
     *
     * @param id the ID of the news to update.
     * @param newsDetails  the details of the updated news.
     * @return The updated news.
     */
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
