package com.example.smart_paper.service.AdminMaster;

import com.example.smart_paper.models.Publisher;
import com.example.smart_paper.repository.AdminMaster.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    // Save Publisher
    public Publisher savePublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    // Get All Publishers
    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    // Get Publisher by ID
    public Optional<Publisher> getPublisherById(Long id) {
        return publisherRepository.findById(id);
    }

    // Update Publisher
    public Publisher updatePublisher(Long id, Publisher updatedPublisher) {
        Optional<Publisher> existingPublisher = publisherRepository.findById(id);
        if (existingPublisher.isPresent()) {
            Publisher publisher = existingPublisher.get();
            publisher.setName(updatedPublisher.getName());
            return publisherRepository.save(publisher);
        }
        return null; // Handle publisher not found
    }

    // Delete Publisher
    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }
}
