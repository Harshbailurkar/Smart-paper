package com.example.smart_paper.controllers.AdminMaster;

import com.example.smart_paper.models.Topic;
import com.example.smart_paper.service.AdminMaster.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    // Create a new topic
    @PostMapping("/add")
    public ResponseEntity<Topic> createTopic(@RequestBody Topic topic
                                             ) {
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        Topic savedTopic = topicService.save(topic, username);
        return ResponseEntity.ok(savedTopic);
    }

    // Update an existing topic
    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable Long id,
                                             @RequestBody Topic updatedTopic
                                            ) {
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        Topic updated = topicService.update(id, updatedTopic, username);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // Get a topic by id
    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopic(@PathVariable Long id) {
        Optional<Topic> topic = topicService.getById(id);
        return topic.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a topic by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        topicService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
