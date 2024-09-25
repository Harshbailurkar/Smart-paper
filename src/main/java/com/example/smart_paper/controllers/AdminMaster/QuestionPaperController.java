package com.example.smart_paper.controllers.AdminMaster;

import com.example.smart_paper.models.QuestionPaper;
import com.example.smart_paper.service.AdminMaster.QuestionPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/question-papers")
public class QuestionPaperController {

    @Autowired
    private QuestionPaperService questionPaperService;

    @PostMapping
    public ResponseEntity<QuestionPaper> createQuestionPaper(@RequestBody QuestionPaper questionPaper,
                                                             @RequestHeader("username") String username) {
        QuestionPaper savedQuestionPaper = questionPaperService.save(questionPaper, username);
        return ResponseEntity.ok(savedQuestionPaper);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionPaper> updateQuestionPaper(@PathVariable Long id,
                                                             @RequestBody QuestionPaper updatedQuestionPaper,
                                                             @RequestHeader("username") String username) {
        QuestionPaper updated = questionPaperService.update(id, updatedQuestionPaper, username);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionPaper> getQuestionPaper(@PathVariable Long id) {
        Optional<QuestionPaper> questionPaper = questionPaperService.getById(id);
        return questionPaper.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestionPaper(@PathVariable Long id) {
        questionPaperService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
