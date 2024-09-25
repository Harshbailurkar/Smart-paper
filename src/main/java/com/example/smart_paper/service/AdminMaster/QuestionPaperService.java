package com.example.smart_paper.service.AdminMaster;

import com.example.smart_paper.models.QuestionPaper;
import com.example.smart_paper.repository.AdminMaster.QuestionPaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionPaperService {

    @Autowired
    private QuestionPaperRepository questionPaperRepository;

    public QuestionPaper save(QuestionPaper questionPaper, String username) {
        questionPaper.setUsername(username);
        return questionPaperRepository.save(questionPaper);
    }

    public QuestionPaper update(Long id, QuestionPaper updatedQuestionPaper, String username) {
        Optional<QuestionPaper> existingQuestionPaperOpt = questionPaperRepository.findById(id);
        if (existingQuestionPaperOpt.isPresent()) {
            QuestionPaper existingQuestionPaper = existingQuestionPaperOpt.get();

            existingQuestionPaper.setTitle(updatedQuestionPaper.getTitle());
            existingQuestionPaper.setDescription(updatedQuestionPaper.getDescription());
            existingQuestionPaper.setQuestions(updatedQuestionPaper.getQuestions());
            existingQuestionPaper.setUsername(username);

            return questionPaperRepository.save(existingQuestionPaper);
        }
        return null;
    }

    public Optional<QuestionPaper> getById(Long id) {
        return questionPaperRepository.findById(id);
    }

    public void deleteById(Long id) {
        questionPaperRepository.deleteById(id);
    }
}
