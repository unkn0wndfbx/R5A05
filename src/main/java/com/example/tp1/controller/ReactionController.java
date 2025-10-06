package com.example.tp1.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.tp1.model.ReactionType;
import com.example.tp1.service.ReactionService;

@RestController
@RequestMapping("/articles/{articleId}")
public class ReactionController {

    private final ReactionService reactionService;

    public ReactionController(ReactionService reactionService) {
        this.reactionService = reactionService;
    }

    @PostMapping("/like")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void like(@PathVariable("articleId") Long articleId, @RequestParam("userId") Long userId) {
        reactionService.react(userId, articleId, ReactionType.LIKE);
    }

    @PostMapping("/dislike")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void dislike(@PathVariable("articleId") Long articleId, @RequestParam("userId") Long userId) {
        reactionService.react(userId, articleId, ReactionType.DISLIKE);
    }

    @RequestMapping("/reactions")
    public Map<String, Long> getCounts(@PathVariable("articleId") Long articleId) {
        long likes = reactionService.countLikes(articleId);
        long dislikes = reactionService.countDislikes(articleId);
        return Map.of("likes", likes, "dislikes", dislikes);
    }
}
