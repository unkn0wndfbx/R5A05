package com.example.tp1.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.tp1.dto.ArticleCreateRequest;
import com.example.tp1.dto.ArticleUpdateRequest;
import com.example.tp1.model.Article;
import com.example.tp1.service.ArticleService;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Article create(@RequestBody ArticleCreateRequest request) {
        return articleService.create(request.getAuthorId(), request.getContent());
    }

    @GetMapping
    public List<Article> listAll() {
        return articleService.listAll();
    }

    @GetMapping("/mine")
    public List<Article> listMine(@RequestParam("authorId") Long authorId) {
        return articleService.listByAuthor(authorId);
    }

    @GetMapping("/{id}")
    public Article get(@PathVariable("id") Long id) {
        return articleService.get(id);
    }

    @PutMapping("/{id}")
    public Article update(@PathVariable("id") Long id, @RequestBody ArticleUpdateRequest request) {
        return articleService.update(id, request.getContent());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        articleService.delete(id);
    }
}
