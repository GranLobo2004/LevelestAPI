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

    @Lob
    private Blob image;

    public Insignia(String nombre, String description, Blob image) {
        this.nombre = nombre;
        this.description = description;
        this.image = image;
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

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }
}
