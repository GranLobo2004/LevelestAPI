package es.zenith.levelestapi.domain;

import es.zenith.levelestapi.Enumeration.InsigniaType;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "insignia_type", discriminatorType = DiscriminatorType.STRING)
public class Insignia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 512)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InsigniaType type;

    // Constructor vacío para JPA
    protected Insignia() {
        this.id = 0L;
    }

    public Insignia(String name, String description, String type) {
        this.id = 0L;
        this.name = name;
        this.description = description;
        this.type = InsigniaType.valueOf(type);
    }

    // --- Getters y setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InsigniaType getType() {
        return type;
    }

    public void setType(InsigniaType type) {
        this.type = type;
    }

    public void setImage(Image entity) {
    }

    public Image getImage() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Insignia)) return false;
        Insignia that = (Insignia) o;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}