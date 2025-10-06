package com.example.tp1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tp1.model.Article;
import com.example.tp1.model.Reaction;
import com.example.tp1.model.User;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    long countByArticleAndType(Article article, com.example.tp1.model.ReactionType type);

    Optional<Reaction> findByUserAndArticle(User user, Article article);
}
