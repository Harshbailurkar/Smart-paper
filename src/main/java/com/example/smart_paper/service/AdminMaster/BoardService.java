package com.example.smart_paper.service.AdminMaster;

import com.example.smart_paper.models.Board;
import com.example.smart_paper.repository.AdminMaster.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    // Save a new Board
    public Board saveBoard(Board board) {
        return boardRepository.save(board);
    }

    // Get all Boards
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    // Get a Board by ID
    public Optional<Board> getBoardById(Long id) {
        return boardRepository.findById(id);
    }

    // Update a Board by ID
    public Board updateBoard(Long id, Board updatedBoard) {

        Optional<Board> existingBoardOptional = boardRepository.findById(id);
        if (existingBoardOptional.isPresent()) {
            Board existingBoard = existingBoardOptional.get();

            if (updatedBoard.getName() != null) {
                existingBoard.setName(updatedBoard.getName());
            }
            if (updatedBoard.getCode() != null) {
                existingBoard.setCode(updatedBoard.getCode());
            }
            if (updatedBoard.getDescription() != null) {
                existingBoard.setDescription(updatedBoard.getDescription());
            }
            if (updatedBoard.getCountry() != null) {
                existingBoard.setCountry(updatedBoard.getCountry());
            }
            if (updatedBoard.getEstablishmentYear() != null) {
                existingBoard.setEstablishmentYear(updatedBoard.getEstablishmentYear());
            }
            if (updatedBoard.getWebsite() != null) {
                existingBoard.setWebsite(updatedBoard.getWebsite());
            }
            if (updatedBoard.getContactEmail() != null) {
                existingBoard.setContactEmail(updatedBoard.getContactEmail());
            }
            if (updatedBoard.getRegion() != null) {
                existingBoard.setRegion(updatedBoard.getRegion());
            }
            if (updatedBoard.getGradeLevelsCovered() != null) {
                existingBoard.setGradeLevelsCovered(updatedBoard.getGradeLevelsCovered());
            }
            if (updatedBoard.getAffiliatedSchoolsCount() != null) {
                existingBoard.setAffiliatedSchoolsCount(updatedBoard.getAffiliatedSchoolsCount());
            }
            if (updatedBoard.getExaminationPattern() != null) {
                existingBoard.setExaminationPattern(updatedBoard.getExaminationPattern());
            }
            if (updatedBoard.getSyllabusUrl() != null) {
                existingBoard.setSyllabusUrl(updatedBoard.getSyllabusUrl());
            }
            existingBoard.setUpdationDate(new Date());

            return boardRepository.save(existingBoard);
        }
        return null;  // Return null if not found
    }

    // Delete a Board by ID
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
