package com.newsproject.fisher.web.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newsproject.fisher.domain.Article;
import com.newsproject.fisher.repository.ArticleRepository;
import com.newsproject.fisher.service.ApiService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {

    private ApiService apiService;

    private ArticleRepository articleRepository;

    @Autowired
    public ApiController(ApiService apiService, ArticleRepository articleRepository) {
        this.apiService = apiService;
        this.articleRepository = articleRepository;
    }

    //constructor injection bc of early failure detection, fail at startup, not runtime

    //@Scheduled(initialDelay = 60000, fixedRate = Long.MAX_VALUE)
    //@Scheduled(cron = "0 0 6 * * *", zone = "America/New_York")
    public void testing() {
        List<Article> articles = apiService.fetchDataFromExternalApi();
        //assign articles List to list of java objects from api
        List<Article> filteredArticles = apiService.filteringArticles(articles);
        //save filtered articles after filtering them
        List<Article> savedArticles = articleRepository.saveAll(filteredArticles);
        //saving it, but saving it to savedArticles
    }

    @PostMapping("/api/posting/multiple")
    public ResponseEntity<List<Article>> fetchAndSaveArticles() {
        List<Article> articles = apiService.fetchDataFromExternalApi();
        //assign articles List to list of java objects from api
        List<Article> savedArticles = articleRepository.saveAll(articles);
        //saving it, but saving it to savedArticles
        return ResponseEntity.ok(savedArticles);
        //return 200 status ok if articles are saved successfully
    }
}
