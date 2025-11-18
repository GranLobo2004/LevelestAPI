package es.zenith.levelestapi.service;

import es.zenith.levelestapi.Enumeration.Subject;
import es.zenith.levelestapi.domain.Insignia;
import es.zenith.levelestapi.domain.Level;
import es.zenith.levelestapi.dto.InsigniaDTO;
import es.zenith.levelestapi.dto.LevelDTO;
import es.zenith.levelestapi.dto.LevelSimpleDTO;
import es.zenith.levelestapi.mapper.LevelMapper;
import es.zenith.levelestapi.repository.LevelRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

    @PostConstruct
    public void init() {
        if (levelRepository.count() > 0) return;
        List<Level> levels = List.of(
                new Level(
                        Subject.HISTORY,
                        "¿En qué año comenzó la Guerra Civil Española?",
                        "1936",
                        List.of("1936", "1939", "1929", "1945")
                ),

                new Level(
                        Subject.HISTORY,
                        "¿Quién fue el primer presidente de los Estados Unidos?",
                        "George Washington",
                        List.of("Thomas Jefferson", "Abraham Lincoln", "George Washington", "John Adams")
                ),

                new Level(
                        Subject.HISTORY,
                        "¿Qué imperio construyó la ciudad de Petra?",
                        "Imperio Nabateo",
                        List.of("Imperio Romano", "Imperio Persa", "Imperio Nabateo", "Imperio Egipcio")
                ),

                new Level(
                        Subject.HISTORY,
                        "¿Qué tratado puso fin a la Primera Guerra Mundial?",
                        "Tratado de Versalles",
                        List.of("Tratado de Versalles", "Tratado de Utrecht", "Paz de Westfalia", "Tratado de París")
                ),

                new Level(
                        Subject.HISTORY,
                        "¿Qué evento inició la Revolución Francesa?",
                        "Toma de la Bastilla",
                        List.of("Toma de la Bastilla", "Guillotina", "Declaración de los Derechos del Hombre", "Golpe de Napoleón")
                ),

                new Level(
                        Subject.HISTORY,
                        "¿Quién fue el líder de la Unión Soviética durante la Segunda Guerra Mundial?",
                        "Stalin",
                        List.of("Vladimir Lenin", "Stalin", "Trotsky", "Khrushchev")
                ),

                new Level(
                        Subject.HISTORY,
                        "¿Cuál fue la principal causa de la caída del Imperio Romano de Occidente?",
                        "Todas las anteriores",
                        List.of("Invasiones bárbaras", "Crisis económica", "Decadencia moral", "Todas las anteriores")
                ),

                new Level(
                        Subject.HISTORY,
                        "¿En qué siglo se produjo la Revolución Industrial en Gran Bretaña?",
                        "Siglo XVIII",
                        List.of("Siglo XVII", "Siglo XVIII", "Siglo XIX", "Siglo XX")
                ),

                new Level(
                        Subject.HISTORY,
                        "¿Qué sistema político instauró Napoleón Bonaparte en Francia?",
                        "Consulado y luego Imperio",
                        List.of("Monarquía absoluta", "República democrática", "Consulado y luego Imperio", "Feudalismo")
                ),

                new Level(
                        Subject.HISTORY,
                        "¿Qué acontecimiento marcó el inicio de la Segunda Guerra Mundial?",
                        "Invasión de Polonia",
                        List.of("Invasión de Polonia", "Bombardeo de Pearl Harbor", "Tratado de Versalles", "Caída de Berlín")
                ),
                new Level(
                        Subject.ENGLISH,
                        "Choose the correct past simple form: 'I ____ to the store yesterday.'",
                        "went",
                        List.of("go", "went", "gone", "going")
                ),

                new Level(
                        Subject.ENGLISH,
                        "Which word is a synonym of 'happy'?",
                        "joyful",
                        List.of("sad", "angry", "joyful", "tired")
                ),

                new Level(
                        Subject.ENGLISH,
                        "Complete the sentence: 'She has been working here ____ five years.'",
                        "for",
                        List.of("since", "for", "from", "at")
                ),

                new Level(
                        Subject.ENGLISH,
                        "Which sentence is grammatically correct?",
                        "He doesn't like pizza.",
                        List.of(
                                "He don't like pizza.",
                                "He doesn't likes pizza.",
                                "He doesn't like pizza.",
                                "He not like pizza."
                        )
                ),

                new Level(
                        Subject.ENGLISH,
                        "Choose the correct form of the verb: 'If I ____ you, I would apologize.'",
                        "were",
                        List.of("am", "was", "were", "be")
                ),

                new Level(
                        Subject.ENGLISH,
                        "Which word completes the sentence: 'I have never ____ to Japan.'",
                        "been",
                        List.of("be", "been", "was", "being")
                ),

                new Level(
                        Subject.ENGLISH,
                        "What is the opposite of 'increase'?",
                        "decrease",
                        List.of("rise", "expand", "decrease", "grow")
                ),

                new Level(
                        Subject.ENGLISH,
                        "Choose the correct preposition: 'She is good ____ mathematics.'",
                        "at",
                        List.of("in", "at", "on", "for")
                ),

                new Level(
                        Subject.ENGLISH,
                        "Which word is a phrasal verb meaning 'to cancel'?",
                        "put off",
                        List.of("put off", "turn on", "take up", "bring in")
                ),

                new Level(
                        Subject.ENGLISH,
                        "Select the correct reported speech: 'She said: \"I am tired.\"'",
                        "She said she was tired.",
                        List.of(
                                "She said she is tired.",
                                "She said she was tired.",
                                "She said she had been tired.",
                                "She said she will be tired."
                        )
                ),
                new Level(
                        Subject.LITERATURE,
                        "¿Quién es el autor de 'La Celestina'?",
                        "Fernando de Rojas",
                        List.of("Miguel de Cervantes", "Fernando de Rojas", "Lope de Vega", "Góngora")
                ),

                new Level(
                        Subject.LITERATURE,
                        "¿En qué siglo se desarrolla principalmente el Siglo de Oro español?",
                        "XVI",
                        List.of("XVII", "XVI", "XV", "XVIII")
                ),

                new Level(
                        Subject.LITERATURE,
                        "¿Cuál de los siguientes autores es representativo del Barroco español?",
                        "Góngora",
                        List.of("Góngora", "Bécquer", "Juan Ramón Jiménez", "Miguel Hernández")
                ),

                new Level(
                        Subject.LITERATURE,
                        "¿Qué género literario predominaba en la Edad Media?",
                        "Lírica trovadoresca",
                        List.of("Novela", "Lírica trovadoresca", "Teatro clásico", "Ensayo")
                ),

                new Level(
                        Subject.LITERATURE,
                        "¿Cuál de estos recursos literarios consiste en la exageración de la realidad?",
                        "Hipérbole",
                        List.of("Hipérbole", "Metáfora", "Ironía", "Aliteración")
                ),

                new Level(
                        Subject.LITERATURE,
                        "¿Qué obra es considerada la primera novela moderna de la literatura española?",
                        "Don Quijote de la Mancha",
                        List.of("Don Quijote de la Mancha", "La Celestina", "Lazarillo de Tormes", "El Cantar de Mio Cid")
                ),

                new Level(
                        Subject.LITERATURE,
                        "¿Qué autor es conocido por su poesía romántica y melancólica?",
                        "Gustavo Adolfo Bécquer",
                        List.of("Gustavo Adolfo Bécquer", "Calderón de la Barca", "Lope de Vega", "Miguel de Cervantes")
                ),

                new Level(
                        Subject.LITERATURE,
                        "¿Qué obra pertenece al teatro del Siglo de Oro español?",
                        "Fuenteovejuna",
                        List.of("Fuenteovejuna", "La Regenta", "Cien años de soledad", "Luces de Bohemia")
                ),

                new Level(
                        Subject.LITERATURE,
                        "¿Qué es un verso endecasílabo?",
                        "Verso de 11 sílabas",
                        List.of("Verso de 8 sílabas", "Verso de 11 sílabas", "Verso de 14 sílabas", "Verso de 7 sílabas")
                ),

                new Level(
                        Subject.LITERATURE,
                        "¿Quién escribió 'Rimas y Leyendas'?",
                        "Gustavo Adolfo Bécquer",
                        List.of("Gustavo Adolfo Bécquer", "Juan Ramón Jiménez", "Antonio Machado", "Federico García Lorca")
                ),

                new Level(
                        Subject.LITERATURE,
                        "Identifica el sujeto en la oración: 'El perro de Juan corre rápido.'",
                        "El perro de Juan",
                        List.of("El perro de Juan", "corre rápido", "Juan", "perro")
                ),

                new Level(
                        Subject.LITERATURE,
                        "¿Cuál es el tiempo verbal de: 'Había cantado antes de salir'?",
                        "Pretérito pluscuamperfecto",
                        List.of("Pretérito perfecto", "Pretérito pluscuamperfecto", "Futuro perfecto", "Pretérito imperfecto")
                ),

                new Level(
                        Subject.LITERATURE,
                        "Selecciona la oración subordinada: 'No fui a clase porque estaba enfermo.'",
                        "porque estaba enfermo",
                        List.of("No fui a clase", "porque estaba enfermo", "No fui", "estaba enfermo")
                ),

                new Level(
                        Subject.LITERATURE,
                        "¿Qué recurso literario consiste en la comparación usando 'como' o 'cual'?",
                        "Símil",
                        List.of("Metáfora", "Símil", "Hipérbole", "Personificación")
                ),

                new Level(
                        Subject.LITERATURE,
                        "¿Quién escribió 'Soledades'?",
                        "Góngora",
                        List.of("Góngora", "Quevedo", "Bécquer", "Lope de Vega")
                ),

                new Level(
                        Subject.LITERATURE,
                        "¿Qué obra pertenece al Romanticismo español?",
                        "Rimas y Leyendas",
                        List.of("Rimas y Leyendas", "La Regenta", "Fuenteovejuna", "Don Quijote de la Mancha")
                ),

                new Level(
                        Subject.LITERATURE,
                        "¿Qué es un adjetivo calificativo?",
                        "Palabra que describe al sustantivo",
                        List.of("Palabra que describe al sustantivo", "Palabra que sustituye al sustantivo", "Palabra que une oraciones", "Palabra que indica acción")
                ),

                new Level(
                        Subject.LITERATURE,
                        "Identifica el tipo de oración: '¡Qué hermoso día!'",
                        "Exclamativa",
                        List.of("Enunciativa", "Exclamativa", "Imperativa", "Interrogativa")
                ),

                new Level(
                        Subject.LITERATURE,
                        "¿Cuál de los siguientes autores pertenece a la Generación del 98?",
                        "Unamuno",
                        List.of("Unamuno", "Góngora", "Bécquer", "Lope de Vega")
                ),

                new Level(
                        Subject.LITERATURE,
                        "¿Qué es la anáfora?",
                        "Repetición de palabras al inicio de versos u oraciones",
                        List.of("Repetición de palabras al inicio de versos u oraciones", "Exageración de la realidad", "Comparación usando 'como'", "Sustitución de un sustantivo")
                ),

                new Level(
                        Subject.LITERATURE,
                        "¿Quién escribió 'Campos de Castilla'?",
                        "Antonio Machado",
                        List.of("Antonio Machado", "Juan Ramón Jiménez", "Federico García Lorca", "Miguel Hernández")
                ),

                new Level(
                        Subject.LITERATURE,
                        "Selecciona el sujeto en: 'Llueve mucho en otoño.'",
                        "Sujeto implícito",
                        List.of("Llueve", "mucho", "en otoño", "Sujeto implícito")
                ),

                new Level(
                        Subject.LITERATURE,
                        "¿Qué tipo de rima tiene: 'Caminante, son tus huellas / el camino y nada más.'?",
                        "No tiene rima",
                        List.of("Consonante", "Asonante", "Libre", "No tiene rima")
                ),

                new Level(
                        Subject.LITERATURE,
                        "¿Qué autor es representativo del Modernismo español?",
                        "Rubén Darío",
                        List.of("Rubén Darío", "Bécquer", "Góngora", "Unamuno")
                ),

                new Level(
                        Subject.LITERATURE,
                        "¿Qué es un determinante?",
                        "Palabra que acompaña al sustantivo indicando cantidad o posesión",
                        List.of("Palabra que acompaña al sustantivo indicando cantidad o posesión", "Palabra que describe al sustantivo", "Palabra que indica acción", "Palabra que une frases")
                ),

                new Level(
                        Subject.LITERATURE,
                        "¿Quién escribió 'La Regenta'?",
                        "Leopoldo Alas Clarín",
                        List.of("Leopoldo Alas Clarín", "Bécquer", "Antonio Machado", "Galdós")
                ),

                new Level(
                        Subject.LITERATURE,
                        "¿Qué tipo de sujeto es: 'Se vive bien en este país'?",
                        "Sujeto impersonal",
                        List.of("Sujeto explícito", "Sujeto tácito", "Sujeto impersonal", "Sujeto compuesto")
                ),

                new Level(
                        Subject.LITERATURE,
                        "Identifica el recurso: 'La luna me sonreía mientras caminaba.'",
                        "Personificación",
                        List.of("Metáfora", "Personificación", "Hipérbole", "Símil")
                ),

                new Level(
                        Subject.LITERATURE,
                        "¿Qué tipo de verso es un alejandrino?",
                        "Verso de 14 sílabas",
                        List.of("Verso de 11 sílabas", "Verso de 14 sílabas", "Verso de 8 sílabas", "Verso de 7 sílabas")
                ),

                new Level(
                        Subject.LITERATURE,
                        "¿Quién escribió 'Luces de Bohemia'?",
                        "Ramón del Valle-Inclán",
                        List.of("Ramón del Valle-Inclán", "Federico García Lorca", "Miguel de Unamuno", "Antonio Machado")
                ),

                new Level(
                        Subject.LITERATURE,
                        "Selecciona la oración subordinada adjetiva: 'El libro que leíste es interesante.'",
                        "que leíste",
                        List.of("El libro", "que leíste", "es interesante", "El libro es interesante")
                ),
                new Level(
                        Subject.MATH,
                        "¿Cuál es la derivada de f(x) = x² + 3x + 2?",
                        "2x + 3",
                        List.of("2x + 3", "x² + 3", "2x + 2", "x² + 2x")
                ),

                new Level(
                        Subject.MATH,
                        "Resuelve la ecuación: 2x - 5 = 9",
                        "x = 7",
                        List.of("x = 2", "x = 7", "x = -2", "x = 5")
                ),

                new Level(
                        Subject.MATH,
                        "¿Cuál es la integral de ∫ 3x² dx?",
                        "x³ + C",
                        List.of("x³ + C", "x³", "3x + C", "x² + C")
                ),

                new Level(
                        Subject.MATH,
                        "Si f(x) = 2x + 1, ¿cuál es f⁻¹(x)?",
                        "(x - 1)/2",
                        List.of("(x - 1)/2", "2x - 1", "1/(2x+1)", "x + 2")
                ),

                new Level(
                        Subject.MATH,
                        "¿Cuál es la solución de la ecuación cuadrática x² - 4x + 3 = 0?",
                        "x = 1 y x = 3",
                        List.of("x = 1 y x = 3", "x = -1 y x = -3", "x = 1 y x = -3", "x = 3 y x = -1")
                ),

                new Level(
                        Subject.MATH,
                        "¿Cuál es el límite? lim (x→0) (sin x)/x",
                        "1",
                        List.of("0", "1", "∞", "-1")
                ),

                new Level(
                        Subject.MATH,
                        "¿Cuál es el valor de log₂ 8?",
                        "3",
                        List.of("2", "3", "8", "1")
                ),

                new Level(
                        Subject.MATH,
                        "Resuelve el sistema: x + y = 5, x - y = 1",
                        "x = 3, y = 2",
                        List.of("x = 3, y = 2", "x = 2, y = 3", "x = 4, y = 1", "x = 1, y = 4")
                ),

                new Level(
                        Subject.MATH,
                        "¿Cuál es la pendiente de la recta que pasa por los puntos (1,2) y (3,6)?",
                        "2",
                        List.of("2", "1", "3", "4")
                ),

                new Level(
                        Subject.MATH,
                        "Si cos²θ + sin²θ = ?",
                        "1",
                        List.of("0", "1", "2", "No existe")
                )
        );
        for(Level level: levels) {
            this.levelRepository.save(level);
        }
    }

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
