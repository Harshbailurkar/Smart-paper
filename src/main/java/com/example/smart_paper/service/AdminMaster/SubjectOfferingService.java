package com.example.smart_paper.service.AdminMaster;

import com.example.smart_paper.models.SubjectOffering;
import com.example.smart_paper.repository.AdminMaster.SubjectOfferingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubjectOfferingService {

    @Autowired
    private SubjectOfferingRepository subjectOfferingRepository;

    public SubjectOffering save(SubjectOffering subjectOffering, String username) {
        subjectOffering.setUsername(username);
        return subjectOfferingRepository.save(subjectOffering);
    }

    public SubjectOffering update(Long id, SubjectOffering updatedSubjectOffering, String username) {
        Optional<SubjectOffering> existingSubjectOfferingOpt = subjectOfferingRepository.findById(id);
        if (existingSubjectOfferingOpt.isPresent()) {
            SubjectOffering existingSubjectOffering = existingSubjectOfferingOpt.get();

            existingSubjectOffering.setActive(updatedSubjectOffering.isActive());
            existingSubjectOffering.setPublisher(updatedSubjectOffering.getPublisher());
            existingSubjectOffering.setBoard(updatedSubjectOffering.getBoard());
            existingSubjectOffering.setStandard(updatedSubjectOffering.getStandard());
            existingSubjectOffering.setUsername(username);

            return subjectOfferingRepository.save(existingSubjectOffering);
        }
        return null;
    }

    public Optional<SubjectOffering> getById(Long id) {
        return subjectOfferingRepository.findById(id);
    }

    public void deleteById(Long id) {
        subjectOfferingRepository.deleteById(id);
    }
}
