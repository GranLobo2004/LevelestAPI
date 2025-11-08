package es.zenith.levelestapi.domain;

import jakarta.persistence.*;

import java.sql.Blob;


@Entity
public class Insignia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nombre;
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

    public Insignia(String nombre, String description) {
        this.nombre = nombre;
        this.description = description;
    }

    public Insignia() {

    }

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
}
