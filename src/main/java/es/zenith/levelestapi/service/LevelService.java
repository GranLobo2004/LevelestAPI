package es.zenith.levelestapi.service;

import es.zenith.levelestapi.Enumeration.Subject;
import es.zenith.levelestapi.domain.Level;
import es.zenith.levelestapi.dto.LevelDTO;
import es.zenith.levelestapi.dto.LevelSimpleDTO;
import es.zenith.levelestapi.mapper.LevelMapper;
import es.zenith.levelestapi.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class LevelService {

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private LevelMapper levelMapper;

    public List<LevelSimpleDTO> getAll() {
        List<LevelSimpleDTO> list = new ArrayList<>();
        for (Level level : levelRepository.findAll()) {
            list.add(levelMapper.toSimpleDTO(level));
        }
        return list;
    }

    public LevelDTO getLevel(long id) {
        Level level = levelRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Level not found"));
        return levelMapper.toDTO(level);
    }

    @Transactional
    public LevelDTO saveLevel(LevelDTO levelDTO) {
        Level level = levelMapper.toEntity(levelDTO);
        Level savedLevel = levelRepository.save(level);
        return levelMapper.toDTO(savedLevel);
    }

    @Transactional
    public LevelDTO updateLevel(LevelDTO levelDTO, long id) {
        Level level = levelRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Level not found"));
        level.setAnswer(levelDTO.answer());
        level.setQuestion(levelDTO.question());
        level.setSubject(Subject.valueOf(levelDTO.subject()));
        Level savedLevel = levelRepository.save(level);
        return levelMapper.toDTO(savedLevel);
    }

    public LevelDTO deleteLevel(long id) {
        Level level = levelRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Level not found"));
        levelRepository.delete(level);
        return levelMapper.toDTO(level);
    }

    public List<LevelSimpleDTO> filterBySubject(String subject) {
        List<LevelSimpleDTO> list = new ArrayList<>();
        for (Level level : levelRepository.findAll()) {
            if (level.getSubject().equals(Subject.valueOf(subject))) {
                list.add(levelMapper.toSimpleDTO(level));
            }
        }
        return list;
    }
}
