package es.zenith.levelestapi.domain;

import es.zenith.levelestapi.Enumeration.Subject;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Subject subject;
    private String question;
    private String answer;

    public Level() {
    }
    public Level(Subject subject, String question, String answer) {
        this.subject = subject;
        this.question = question;
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
