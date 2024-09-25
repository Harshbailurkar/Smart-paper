package com.example.smart_paper.controllers.AdminMaster;
import com.example.smart_paper.models.Publisher;
import com.example.smart_paper.service.AdminMaster.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/publisher")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    // Create a new Publisher
    @PostMapping("/add")
    public ResponseEntity<Publisher> createPublisher(@RequestBody Publisher publisher) {
        // Get the currently logged-in username
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        // Automatically set the username of the publisher
        publisher.setUsername(loggedInUsername);

        Publisher createdPublisher = publisherService.savePublisher(publisher);
        return ResponseEntity.ok(createdPublisher);
    }

    // Get all Publishers
    @PostMapping
    public List<Publisher> getAllPublishers() {
        return publisherService.getAllPublishers();
    }


    // Get a Publisher by ID
    @PostMapping("/{id}")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable Long id) {
        Optional<Publisher> publisher = publisherService.getPublisherById(id);
        return publisher.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update a Publisher by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable Long id, @RequestBody Publisher updatedPublisher) {
        // Get the currently logged-in username
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        // Automatically set the username for the update operation
        updatedPublisher.setUsername(loggedInUsername);

        Publisher publisher = publisherService.updatePublisher(id, updatedPublisher);
        if (publisher != null) {
            return ResponseEntity.ok(publisher);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a Publisher by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }
}
