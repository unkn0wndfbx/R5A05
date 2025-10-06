package com.example.tp1.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tp1.model.Article;
import com.example.tp1.model.User;
import com.example.tp1.repository.ArticleRepository;
import com.example.tp1.repository.UserRepository;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Article create(Long authorId, String content) {
        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Author not found: " + authorId));
        Article article = new Article();
        article.setAuthor(author);
        article.setContent(content);
        article.setPublishedAt(LocalDateTime.now());
        return articleRepository.save(article);
    }

    public List<Article> listAll() {
        return articleRepository.findAll();
    }

    public List<Article> listByAuthor(Long authorId) {
        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Author not found: " + authorId));
        return articleRepository.findByAuthor(author);
    }

    public Article get(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Article not found: " + id));
    }

    @Transactional
    public Article update(Long id, String content) {
        Article article = get(id);
        article.setContent(content);
        return articleRepository.save(article);
    }

    @Transactional
    public void delete(Long id) {
        if (!articleRepository.existsById(id)) {
            throw new IllegalArgumentException("Article not found: " + id);
        }
        articleRepository.deleteById(id);
    }
}
