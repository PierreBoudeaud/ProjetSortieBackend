package fr.eni.projetsortie.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

@Entity
@Table
public class Token {
    @ManyToOne(targetEntity = Participant.class)
    @JoinColumn(name = "participant", referencedColumnName = "no_participant")
    private Participant participant;

    @Id
    private String token;

    private Date creationDate;

    private Date expirationDate;

    /**
     * If false 30 mn expiration else a week
     */
    private boolean remember;

    public Token() {
        this.token = UUID.randomUUID().toString();
        this.creationDate = new Date();
        this.remember = false;
        this.setExpirationDate();
    }

    public Token(Participant participant) {
        this();
        this.participant = participant;
    }

    public Token(Participant participant, boolean remember) {
        this(participant);
        this.remember = remember;
        this.setExpirationDate();
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate() {
        GregorianCalendar date = new GregorianCalendar();
        if(this.isRemember()) {
            date.add(Calendar.DAY_OF_MONTH, 7);
        }
        else {
            date.add(Calendar.MINUTE, 30);
        }
        this.expirationDate = date.getTime();
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Token{" +
                "participant=" + participant +
                ", token='" + token + '\'' +
                ", creationDate=" + creationDate +
                ", expirationDate=" + expirationDate +
                ", remember=" + remember +
                '}';
    }
}
