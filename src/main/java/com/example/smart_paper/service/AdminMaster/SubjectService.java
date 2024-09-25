package com.example.smart_paper.service.AdminMaster;
import com.example.smart_paper.models.Subject;
import com.example.smart_paper.repository.AdminMaster.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    // Save a new Subject
    public Subject saveSubject(Subject subject) {
        subject.setCreationDate(new Date());  // Set creation date
        return subjectRepository.save(subject);
    }

    // Get all Subjects
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    // Get a Subject by ID
    public Optional<Subject> getSubjectById(Long id) {
        return subjectRepository.findById(id);
    }

    // Update a Subject by ID
    public Subject updateSubject(Long id, Subject updatedSubject) {
        Optional<Subject> existingSubjectOptional = subjectRepository.findById(id);
        if (existingSubjectOptional.isPresent()) {
            Subject existingSubject = existingSubjectOptional.get();

            if (updatedSubject.getName() != null) {
                existingSubject.setName(updatedSubject.getName());
            }

            if (updatedSubject.getDisplayName() != null) {
                existingSubject.setDisplayName(updatedSubject.getDisplayName());
            }

            if (updatedSubject.isActive() != existingSubject.isActive()) {
                existingSubject.setActive(updatedSubject.isActive());
            }
            existingSubject.setUpdationDate(new Date());  // Update the date

            return subjectRepository.save(existingSubject);  // Save the updated subject
        }
        return null;  // Return null if not found
    }

    // Delete a Subject by ID
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }
}

