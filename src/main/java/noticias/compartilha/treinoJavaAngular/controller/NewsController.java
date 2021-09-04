package noticias.compartilha.treinoJavaAngular.controller;

import noticias.compartilha.treinoJavaAngular.exception.ResourceNotFoundException;
import noticias.compartilha.treinoJavaAngular.model.News;
import noticias.compartilha.treinoJavaAngular.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    @GetMapping("/news")
    public List<News> getAllNews(){
        return newsRepository.findAll();
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<News> getRoomById(@PathVariable(value = "id") long newsId)
        throws ResourceNotFoundException{
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new ResourceNotFoundException("News not found: " + newsId));
        return ResponseEntity.ok().body(news);
    }

    @PostMapping("/news")
    public News createNews(@Valid @RequestBody News news){
        return newsRepository.save(news);
    }

    @PutMapping("/news/{id}")
    public ResponseEntity<News> updateNews(@PathVariable(value = "id") Long newsId,
                                           @Valid @RequestBody News newsDetails) throws ResourceNotFoundException {
        News notice = newsRepository.findById(newsId)
                .orElseThrow(() -> new ResourceNotFoundException("News not found for this id:: " + newsId));
        notice.setTitle(newsDetails.getTitle());
        notice.setName(newsDetails.getName());
        notice.setDate(newsDetails.getDate());
        notice.setNews(newsDetails.getNews());
        final News updateNews = newsRepository.save(notice);
        return ResponseEntity.ok(updateNews);
    }

    @DeleteMapping("/news/{id}")
    public Map<String, Boolean> deleteNew(@PathVariable(value = "id") Long newsId)
        throws ResourceNotFoundException{
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new ResourceNotFoundException("News not found for delete on this id:: " + newsId));
        newsRepository.delete(news);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }

}
