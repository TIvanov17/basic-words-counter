package com.springuni.wordscounter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springuni.wordscounter.result.WordsCounterResult;
import com.springuni.wordscounter.service.WordsCounterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/count")
public class WordsCounterController {

    private final WordsCounterService wordsCounterService;

    public WordsCounterController(WordsCounterService wordsCounterService){
        this.wordsCounterService = wordsCounterService;
    }

    @PostMapping("/text")
    public ResponseEntity<WordsCounterResult> countTextWords
            (@RequestBody WordsCounterResult wordsCounterResult){

        return ResponseEntity.ok(this.wordsCounterService.countWords(wordsCounterResult));
    }

    @PostMapping("/url")
    public ResponseEntity<WordsCounterResult> countUrlWords
            (@RequestBody WordsCounterResult wordsCounterResult){

        try{
            new URL(wordsCounterResult.getContent()).toURI();
        }
        catch (MalformedURLException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The input is not a valid url!");
        }

        return ResponseEntity.ok(this.wordsCounterService.countUrlWords(wordsCounterResult));
    }

}
