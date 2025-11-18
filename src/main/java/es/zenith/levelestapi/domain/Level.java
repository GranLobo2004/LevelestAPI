package es.zenith.levelestapi.domain;

import es.zenith.levelestapi.Enumeration.Subject;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Subject subject;
    private String question;
    private List<String> possibleAnswers;
    private String answer;

    public Level() {
        this.id = 0;
    }
    public Level(Subject subject, String question, String answer,  List<String> possibleAnswers) {
        this.subject = subject;
        this.question = question;
        this.id = 0;
        this.answer = answer;
        this.possibleAnswers = possibleAnswers;
    }

    public void setId(long id){
        this.id = id;
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

    public List<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<String> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }
}
