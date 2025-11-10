package es.zenith.levelestapi.service;

import es.zenith.levelestapi.domain.Insignia;
import es.zenith.levelestapi.dto.InsigniaDTO;
import es.zenith.levelestapi.mapper.ImageMapper;
import es.zenith.levelestapi.mapper.InsigniaMapper;
import es.zenith.levelestapi.repository.InsigniaRepository;
import jakarta.annotation.PostConstruct;
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

    public static MultipartFile getImageAsMultipart(String imageName) throws IOException {
        // Cargar el archivo desde resources/static/Insignias/Images
        ClassPathResource resource = new ClassPathResource("static/Insignias/Images/" + imageName);

        if (!resource.exists()) {
            throw new IOException("No se encontró la imagen: " + imageName);
        }

        // Detectar tipo MIME según la extensión
        String mimeType = URLConnection.guessContentTypeFromName(imageName);
        if (mimeType == null) {
            mimeType = "application/octet-stream"; // fallback genérico
        }

        // Crear MultipartFile usando el contenido del recurso
        try (InputStream inputStream = resource.getInputStream()) {
            return new MockMultipartFile(
                    "file",         // nombre del parámetro
                    imageName,      // nombre original
                    mimeType,       // tipo MIME
                    inputStream     // contenido binario
            );
        }
    }

    @PostConstruct
    public void init() {
        if (insigniaRepository.count()>0) return;
        List<Insignia> insignias = List.of(
                new Insignia("Knowledge conector", "Comparte conocimiento con el equipo", "medal"),
                new Insignia("Adaptability engine", "Se adapta rápido a cambios y obstáculos", "medal"),
                new Insignia("Bug slayer", "Detecta y elimina bugs constantemente", "medal"),
                new Insignia("Communication champ", "Mantiene una comunicación clara y efectiva", "medal"),
                new Insignia("Fearless leader", "Lidera iniciativas con valentía", "medal"),
                new Insignia("Helping hand", "Apoya compañeros cuando lo necesitan", "medal"),
                new Insignia("Smart thinker", "Propone soluciones inteligentes", "medal"),
                new Insignia("Sprint finisher", "Termina tareas clave del sprint", "medal"),
                new Insignia("Sprint member", "Participa activamente en el sprint", "medal"),
                new Insignia("Tech guru", "Destaca con conocimientos técnicos", "medal"),
                new Insignia("Unlocker", "Desbloquea bloqueos del equipo", "medal"),
                new Insignia("Value booster", "Genera valor en cada entrega", "medal"),
                new Insignia("Bug fixer Bronce", "Corrige un número significativo de bugs", "milestone"),
                new Insignia("Bug fixer Plata", "Corrige muchos bugs en varios sprints", "milestone"),
                new Insignia("Bug fixer Oro", "Corrige bugs críticos consistentemente", "milestone"),

                new Insignia("Code contributor Bronce", "Aporta código útil al proyecto", "milestone"),
                new Insignia("Code contributor Plata", "Contribuye con varias funcionalidades", "milestone"),
                new Insignia("Code contributor Oro", "Contribución sobresaliente al código", "milestone"),

                new Insignia("Feedback loop Bronce", "Da retroalimentación constante", "milestone"),
                new Insignia("Feedback loop Plata", "Retroalimenta ayudando a mejorar resultados", "milestone"),
                new Insignia("Feedback loop Oro", "Feedback preciso que impulsa calidad", "milestone"),

                new Insignia("Sprint synergy Bronce", "Buena colaboración en el sprint", "milestone"),
                new Insignia("Sprint synergy Plata", "Sincronización notable en entregas", "milestone"),
                new Insignia("Sprint synergy Oro", "Sinergia sobresaliente en el sprint", "milestone"),

                new Insignia("Task closer Bronce", "Cierra tareas correctamente", "milestone"),
                new Insignia("Task closer Plata", "Cierra tareas con eficiencia", "milestone"),
                new Insignia("Task closer Oro", "Cierra tareas críticas y complejas", "milestone"),

                new Insignia("Team uplifter Bronce", "Ayuda al equipo a mejorar", "milestone"),
                new Insignia("Team uplifter Plata", "Mejora la moral del equipo", "milestone"),
                new Insignia("Team uplifter Oro", "Eleva al equipo consistentemente", "milestone")
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
        for(int i = 0; i<insignias.size(); i++) {
            Insignia insignia = insigniaRepository.save(insignias.get(i));
            try{
                saveImage(insignia.getId(), getImageAsMultipart(images.get(i)));
            }
            catch(Exception e){
                System.out.println("Couldn't save image" + images.get(i));
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

    public InsigniaDTO saveImage(long id, MultipartFile file) {
        Insignia insignia = insigniaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Insignia not found"));
        insignia.setImage(imageService.saveImage(file));
        return insigniaMapper.toDTO(insigniaRepository.save(insignia));
    }
}
