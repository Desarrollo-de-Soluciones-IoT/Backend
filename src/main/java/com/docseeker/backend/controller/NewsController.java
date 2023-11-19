package com.docseeker.backend.controller;

import com.docseeker.backend.model.News;
import com.docseeker.backend.repository.NewsRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/news")
@CrossOrigin()
public class NewsController {

    private final NewsRepository repository;
    public static final String ARTICLE_NOT_FOUND = "Article not found";
    public NewsController(NewsRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    private void init() {
        News newsArticle = new News();
        newsArticle.setTitle("New COVID-19 vaccine");
        newsArticle.setDescription("A new vaccine has been developed to fight COVID-19");
        newsArticle.setImageUrl("https://www.fda.gov/files/COVID%20testing%20policy_1600x900_0.png");
        repository.save(newsArticle);
    }

    @GetMapping("")
    public List<News> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public News findById(@PathVariable int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ARTICLE_NOT_FOUND));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody News newsArticle) {
        repository.save(newsArticle);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody News newsArticle, @PathVariable int id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ARTICLE_NOT_FOUND);
        }
        newsArticle.setId(id);
        repository.save(newsArticle);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ARTICLE_NOT_FOUND);
        }
        repository.deleteById(id);
    }
}
