package es.zenith.levelestapi.domain;

import es.zenith.levelestapi.Enumeration.InsigniaType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Milestone extends Insignia{

    private int tier;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Image> images = new ArrayList<>();

    public Milestone(String name, String description, String tipo){
        super(name, description, tipo);
        this.tier = 0;
    }

    public Milestone(){
        super();
    }

    @Override
    public Image getImage(){
        if (this.tier == 0) return this.images.getFirst();
        return this.images.get(tier - 1);
    }

    @Override
    public void setImage(Image image){
        if (images.size() < 3){
            this.images.add(image);
        }
    }

    public int getTier(){
        return tier;
    }
    public void setTier(int tier){
        if (tier > 0 && this.tier < 3){
            this.tier = tier;
        }
    }

    public void upgradeTier(){
        if (this.tier <= 3){this.tier++;}
    }

}
