package es.zenith.levelestapi.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "UserInfo")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @CreationTimestamp
    private LocalDateTime creationDate;

    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private List<String> roles = new ArrayList<>();;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Insignia> insignias = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Level> completedLevels = new  ArrayList<>();

    public User() {}

    public User(String username, String password, String name, String surname, String email, List<String> roles) {
        this.username = username;
        this.password = password;
        this.creationDate = LocalDateTime.now();
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addCompletedLevels(Level level) {
        this.completedLevels.add(level);
    }

    public void recieveInsignia(Insignia insignia) {
        this.insignias.add(insignia);
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public List<Insignia> getInsignias() {
        return insignias;
    }

    public List<Level> getCompletedLevels() {
        return completedLevels;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void addInsignia(Insignia insignia) {
        this.insignias.add(insignia);
    }

    public void addRole(String role) {
        this.roles.add(role);
    }
}
