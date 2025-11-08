package es.zenith.levelestapi.domain;


import jakarta.persistence.*;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.util.Map;

@Entity
public class Image {

    private static final Map<String, String> EXTENSIONS = Map.of("image/jpeg", ".jpg", "image/png", ".png", "image/gif", ".gif");

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Lob
    private Blob imageFile;

    private String contentType;

    public Image(MultipartFile rawImage) throws IOException {
        this.contentType = EXTENSIONS.getOrDefault(rawImage.getContentType(), ".jpg");
        byte[] imageData = rawImage.getBytes();
        imageFile = BlobProxy.generateProxy(new ByteArrayInputStream(imageData), imageData.length);
    }

    public Image() {
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Blob getimageFile() { return imageFile; }
    public void setImageFile(Blob imageFile) { this.imageFile = imageFile; }

    public String getContentType() { return contentType; }
    public void setContentType(String contentType){ this.contentType = EXTENSIONS.getOrDefault(contentType, ".jpg"); }
}