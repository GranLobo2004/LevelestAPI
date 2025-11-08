package es.zenith.levelestapi.service;

import es.zenith.levelestapi.domain.Insignia;
import es.zenith.levelestapi.dto.InsigniaDTO;
import es.zenith.levelestapi.mapper.ImageMapper;
import es.zenith.levelestapi.mapper.InsigniaMapper;
import es.zenith.levelestapi.repository.InsigniaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class InsigniaService {

    @Autowired
    private InsigniaRepository insigniaRepository;

    @Autowired
    private InsigniaMapper insigniaMapper;

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private ImageService imageService;

    public List<InsigniaDTO> getAll() {
        List<InsigniaDTO> insignias = new ArrayList<>();
        for(Insignia insignia : insigniaRepository.findAll()) {
            insignias.add(insigniaMapper.toDTO(insignia));
        }
        return insignias;
    }

    public InsigniaDTO saveInsignia(InsigniaDTO insigniaDTO) {
        Insignia insignia = new  Insignia();
        insignia.setDescription(insigniaDTO.description());
        insignia.setNombre(insigniaDTO.nombre());
        insignia.setImage(imageMapper.toEntity(insigniaDTO.image()));
        return insigniaMapper.toDTO(insigniaRepository.save(insignia));
    }

    public InsigniaDTO getInsignia(long id) {
        Insignia insignia = insigniaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Insignia not found"));
        return insigniaMapper.toDTO(insignia);
    }

    public InsigniaDTO updateInsignia(long id, InsigniaDTO insigniaDTO) {
        Insignia insignia = insigniaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Insignia not found"));
        insignia.setNombre(insigniaDTO.nombre());
        insignia.setImage(imageMapper.toEntity(insigniaDTO.image()));
        insignia.setDescription(insigniaDTO.description());
        return insigniaMapper.toDTO(insigniaRepository.save(insignia));
    }

    public InsigniaDTO deleteInsignia(long id) {
        Insignia insignia = insigniaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Insignia not found"));
        insigniaRepository.delete(insignia);
        return insigniaMapper.toDTO(insignia);
    }

    public Resource loadImage(Blob image) {
        try {
            return new InputStreamResource(image.getBinaryStream());
        } catch (SQLException e) {
            throw new NoSuchElementException();
        }
    }

    public InsigniaDTO saveImage(long id, MultipartFile file) {
        Insignia insignia = insigniaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Insignia not found"));
        insignia.setImage(imageService.saveImage(file));
        return insigniaMapper.toDTO(insigniaRepository.save(insignia));
    }
}
