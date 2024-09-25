package com.example.smart_paper.service.AdminMaster;

import com.example.smart_paper.models.Chapter;
import com.example.smart_paper.repository.AdminMaster.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

    public List<Chapter> getAllChapters(){
        return chapterRepository.findAll();
    }
    // Save new chapter
    public Chapter save(Chapter chapter, String username) {
        chapter.setUsername(username);
        return chapterRepository.save(chapter);
    }

    // Update existing chapter
    public Chapter update(Long id, Chapter updatedChapter, String username) {
        Optional<Chapter> existingChapterOpt = chapterRepository.findById(id);
        if (existingChapterOpt.isPresent()) {
            Chapter existingChapter = existingChapterOpt.get();

            // Update only non-null fields
            if (updatedChapter.getName() != null) existingChapter.setName(updatedChapter.getName());
            if (updatedChapter.getSortOrder() != 0) existingChapter.setSortOrder(updatedChapter.getSortOrder());

            existingChapter.setUsername(username);

            return chapterRepository.save(existingChapter);
        }
        return null;  // Return null if the chapter is not found
    }

    // Get chapter by id
    public Optional<Chapter> getById(Long id) {
        return chapterRepository.findById(id);
    }

    // Delete chapter
    public void delete(Long id) {
        chapterRepository.deleteById(id);
    }
}
