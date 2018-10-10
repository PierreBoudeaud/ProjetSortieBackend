package fr.eni.projetsortie.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity()
@Table(name = "INSCRIPTIONS")
public class Inscription {

    @EmbeddedId
    private InscriptionId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("sortieId")
    private Sortie sortie;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("participantId")
    private Participant participant;

    @Column(name = "date_inscription", nullable = false)
    private Date dateInscription = new Date();

    private Inscription(){}

    public Inscription(Sortie sortie, Participant participant) {
        this();
        this.participant = participant;
        this.sortie = sortie;
        this.id = new InscriptionId(sortie.getId(), participant.getId());
        this.dateInscription = new Date();
    }

    public InscriptionId getId() {
        return id;
    }

    public void setId(InscriptionId id) {
        this.id = id;
    }

    public Sortie getSortie() {
        return sortie;
    }

    public void setSortie(Sortie sortie) {
        this.sortie = sortie;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Inscription that = (Inscription) o;
        return Objects.equals(sortie, that.sortie) &&
                Objects.equals(participant, that.participant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sortie, participant);
    }
}
