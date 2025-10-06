package com.example.tp1.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tp1.model.Article;
import com.example.tp1.model.Reaction;
import com.example.tp1.model.ReactionType;
import com.example.tp1.model.User;
import com.example.tp1.repository.ArticleRepository;
import com.example.tp1.repository.ReactionRepository;
import com.example.tp1.repository.UserRepository;

@Service
public class ReactionService {

    private final ReactionRepository reactionRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ReactionService(ReactionRepository reactionRepository, ArticleRepository articleRepository,
            UserRepository userRepository) {
        this.reactionRepository = reactionRepository;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void react(Long userId, Long articleId, ReactionType type) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("Article not found: " + articleId));

        Reaction existing = reactionRepository.findByUserAndArticle(user, article).orElse(null);
        if (existing == null) {
            Reaction r = new Reaction();
            r.setUser(user);
            r.setArticle(article);
            r.setType(type);
            r.setCreatedAt(LocalDateTime.now());
            reactionRepository.save(r);
        } else {
            existing.setType(type);
            reactionRepository.save(existing);
        }
    }

    public long countLikes(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("Article not found: " + articleId));
        return reactionRepository.countByArticleAndType(article, ReactionType.LIKE);
    }

    public long countDislikes(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("Article not found: " + articleId));
        return reactionRepository.countByArticleAndType(article, ReactionType.DISLIKE);
    }
}
