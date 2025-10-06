package com.example.tp1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tp1.model.Article;
import com.example.tp1.model.User;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByAuthor(User author);
}
