package es.zenith.levelestapi.domain;

import es.zenith.levelestapi.Enumeration.InsigniaType;
import jakarta.persistence.*;


@Entity
public class Insignia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nombre;
    private String description;
    private InsigniaType type;

    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

    public Insignia(String nombre, String description,  String tipo) {
        this.nombre = nombre;
        this.description = description;
        this.type = InsigniaType.valueOf(tipo.toUpperCase());
    }

    public Insignia() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public InsigniaType getType() {
        return type;
    }

    public void setType(String tipo) {
        this.type = InsigniaType.valueOf(tipo.toUpperCase());
    }
}
