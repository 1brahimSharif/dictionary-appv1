package com.example.dictionary.controller;

import com.example.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api")
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @GetMapping("/definition")
    public ResponseEntity<?> getDefinition(@RequestParam String word) {
        try {
            String definition = dictionaryService.fetchDefinition(word);
            return ResponseEntity.ok(definition);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching definition");
        }
    }
}
