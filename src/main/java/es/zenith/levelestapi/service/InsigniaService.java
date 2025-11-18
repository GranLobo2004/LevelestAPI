package es.zenith.levelestapi.service;

import es.zenith.levelestapi.Enumeration.InsigniaType;
import es.zenith.levelestapi.domain.Insignia;
import es.zenith.levelestapi.domain.Medal;
import es.zenith.levelestapi.domain.Milestone;
import es.zenith.levelestapi.dto.InsigniaDTO;
import es.zenith.levelestapi.mapper.ImageMapper;
import es.zenith.levelestapi.mapper.InsigniaMapper;
import es.zenith.levelestapi.repository.InsigniaRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
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

    @PostConstruct
    public void init() {
        if (insigniaRepository.count()>0) return;
        List<Insignia> insignias = List.of(
                new Medal("Knowledge conector", "Comparte conocimiento con el equipo", "MEDAL"),
                new Medal("Adaptability engine", "Se adapta rápido a cambios y obstáculos", "MEDAL"),
                new Medal("Bug slayer", "Detecta y elimina bugs constantemente", "MEDAL"),
                new Medal("Communication champ", "Mantiene una comunicación clara y efectiva", "MEDAL"),
                new Medal("Fearless leader", "Lidera iniciativas con valentía", "MEDAL"),
                new Medal("Helping hand", "Apoya compañeros cuando lo necesitan", "MEDAL"),
                new Medal("Smart thinker", "Propone soluciones inteligentes", "MEDAL"),
                new Medal("Sprint finisher", "Termina tareas clave del sprint", "MEDAL"),
                new Medal("Sprint member", "Participa activamente en el sprint", "MEDAL"),
                new Medal("Tech guru", "Destaca con conocimientos técnicos", "MEDAL"),
                new Medal("Unlocker", "Desbloquea bloqueos del equipo", "MEDAL"),
                new Medal("Value booster", "Genera valor en cada entrega", "MEDAL"),

                new Milestone("Bug fixer", "Corrige un número significativo de bugs", "MILESTONE"),
                new Milestone("Code contributor", "Aporta código útil al proyecto", "MILESTONE"),
                new Milestone("Feedback loop", "Da retroalimentación constante", "MILESTONE"),
                new Milestone("Sprint synergy", "Buena colaboración en el sprint", "MILESTONE"),
                new Milestone("Task closer", "Cierra tareas correctamente", "MILESTONE"),
                new Milestone("Team uplifter", "Ayuda al equipo a mejorar", "MILESTONE")
        );
        List<String> images = List.of(
                "Knowledge_Conector.png",
                "Adaptability_Engine.png",
                "Bug_Slayer.png",
                "Communication_Champ.png",
                "Fearless_Leader.png",
                "Helping_Hand.png",
                "Smart_Thinker.png",
                "Sprint_Finisher.png",
                "Sprint_Member.png",
                "Tech_Guru.png",
                "Unlocker.png",
                "Value_Booster.png",
                "Bug_Fixer_bronce.png",
                "Bug_Fixer_plata.png",
                "Bug_Fixer_oro.png",
                "Code_Contributor_bronce.png",
                "Code_Contributor_plata.png",
                "Code_Contributor_oro.png",
                "Feedback_Loop_bronce.png",
                "Feedback_Loop_plata.png",
                "Feedback_Loop_oro.png",
                "Sprint_Synergy_bronce.png",
                "Sprint_Synergy_plata.png",
                "Sprint_Synergy_oro.png",
                "Task_Closer_bronce.png",
                "Task_Closer_plata.png",
                "Task_Closer_oro.png",
                "Team_Uplifter_bronce.png",
                "Team_Uplifter_plata.png",
                "Team_Uplifter_oro.png"
        );
        int i = 0;
        int limit;
        for(Insignia insignia : insignias) {
            insignia = insigniaRepository.save(insignia);
            switch (insignia.getType()) {
                case MEDAL -> limit = 1;
                case MILESTONE -> limit = 3;
                default -> limit = 1;
            }
            for (int j = 0; j < limit; j++){
                try {
                    saveImage(insignia.getId(), imageService.getImageAsMultipart(images.get(i)));
                } catch (Exception e) {
                    System.out.println("Couldn't save image" + images.get(i) + e.getMessage());
                }
                i++;
            }
        }
    }

    public List<InsigniaDTO> getAll() {
        List<InsigniaDTO> insignias = new ArrayList<>();
        for(Insignia insignia : insigniaRepository.findAll()) {
            insignias.add(insigniaMapper.toDTO(insignia));
        }
        return insignias;
    }

    public InsigniaDTO saveInsignia(InsigniaDTO insigniaDTO) {
        Insignia insignia = null;
        switch (insigniaDTO.type()){
            case InsigniaType.MEDAL -> insignia = new Medal();
            case InsigniaType.MILESTONE ->  insignia = new Milestone();
            default -> {
                return null;
            }
        }
        insignia.setDescription(insigniaDTO.description());
        insignia.setName(insigniaDTO.name());
        insignia.setImage(imageMapper.toEntity(insigniaDTO.image()));
        return insigniaMapper.toDTO(insigniaRepository.save(insignia));
    }

    public InsigniaDTO getInsignia(long id) {
        Insignia insignia = insigniaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Insignia not found"));
        return insigniaMapper.toDTO(insignia);
    }

    public InsigniaDTO updateInsignia(long id, InsigniaDTO insigniaDTO) {
        Insignia insignia = insigniaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Insignia not found"));
        insignia.setName(insigniaDTO.name());
        insignia.setImage(imageMapper.toEntity(insigniaDTO.image()));
        insignia.setDescription(insigniaDTO.description());
        return insigniaMapper.toDTO(insigniaRepository.save(insignia));
    }

    public InsigniaDTO deleteInsignia(long id) {
        Insignia insignia = insigniaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Insignia not found"));
        insigniaRepository.delete(insignia);
        return insigniaMapper.toDTO(insignia);
    }

    @Transactional
    public InsigniaDTO saveImage(long id, MultipartFile file) {
        Insignia insignia = insigniaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Insignia not found"));
        insignia.setImage(imageService.saveImage(file));
        return insigniaMapper.toDTO(insigniaRepository.save(insignia));
    }

    public Insignia findInsignia(long id) {
        return insigniaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Insignia not found"));
    }
}
