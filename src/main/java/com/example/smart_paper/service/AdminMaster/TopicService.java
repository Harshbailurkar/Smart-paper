package com.example.smart_paper.service.AdminMaster;


import com.example.smart_paper.models.Topic;
import com.example.smart_paper.repository.AdminMaster.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    // Save a new topic
    public Topic save(Topic topic, String username) {
        topic.setUsername(username);
        return topicRepository.save(topic);
    }

    // Update an existing topic
    public Topic update(Long id, Topic updatedTopic, String username) {
        Optional<Topic> existingTopicOpt = topicRepository.findById(id);
        if (existingTopicOpt.isPresent()) {
            Topic existingTopic = existingTopicOpt.get();

            if (updatedTopic.getName() != null) existingTopic.setName(updatedTopic.getName());
            existingTopic.setSortOrder(updatedTopic.getSortOrder());

            // Update metadata
            existingTopic.setUsername(username);

            return topicRepository.save(existingTopic);
        }
        return null;
    }

    // Get a topic by id
    public Optional<Topic> getById(Long id) {
        return topicRepository.findById(id);
    }

    // Delete a topic (soft delete logic can be added if needed)
    public void deleteById(Long id) {
        topicRepository.deleteById(id);
    }
}
