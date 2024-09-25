package com.example.smart_paper.controllers.AdminMaster;

import com.example.smart_paper.models.QuestionTemplate;
import com.example.smart_paper.service.AdminMaster.QuestionTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/question-templates")
public class QuestionTemplateController {

    @Autowired
    private QuestionTemplateService questionTemplateService;

    @PostMapping
    public ResponseEntity<QuestionTemplate> createQuestionTemplate(@RequestBody QuestionTemplate questionTemplate,
                                                                   @RequestHeader("username") String username) {
        QuestionTemplate savedTemplate = questionTemplateService.save(questionTemplate, username);
        return ResponseEntity.ok(savedTemplate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionTemplate> updateQuestionTemplate(@PathVariable Long id,
                                                                   @RequestBody QuestionTemplate updatedQuestionTemplate,
                                                                   @RequestHeader("username") String username) {
        QuestionTemplate updated = questionTemplateService.update(id, updatedQuestionTemplate, username);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionTemplate> getQuestionTemplate(@PathVariable Long id) {
        Optional<QuestionTemplate> questionTemplate = questionTemplateService.getById(id);
        return questionTemplate.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestionTemplate(@PathVariable Long id) {
        questionTemplateService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
