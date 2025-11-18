package es.zenith.levelestapi.domain;

import es.zenith.levelestapi.Enumeration.InsigniaType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Medal extends Insignia {

    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

    public Medal(String nombre, String description,  String tipo){
        super(nombre,description, tipo);
    }

    public Medal(){
        super();
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
    }
}
