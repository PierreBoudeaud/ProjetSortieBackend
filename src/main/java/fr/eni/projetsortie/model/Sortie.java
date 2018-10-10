package fr.eni.projetsortie.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Entity()
@Table(name = "Sorties")
public class Sortie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no_sortie")
    private int id;

    @Size(max=50)
    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "datedebut", nullable = false)
    private Date dateDebut;

    @Column(name = "duree")
    private int duree;

    @Column(name = "datecloture", nullable = false)
    private Date dateCloture;

    @Column(name = "nbinscriptionsmax", nullable = false)
    private int nbInscriptions;

    @Size(max = 500)
    @Column(name = "desciptionsinfos")
    private String description;

    @ManyToOne(targetEntity = Participant.class)
    @JoinColumn(name = "organisateur", referencedColumnName = "no_participant")
    private Participant organisateur;

    @ManyToOne(targetEntity = Lieu.class)
    @JoinColumn(name = "lieux_no_lieu", referencedColumnName = "no_lieu")
    private Lieu lieu;

    @Enumerated(EnumType.ORDINAL)
    private Etat etat;

    @JsonIgnore
    @OneToMany(
            mappedBy = "sortie",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Inscription> participants;

    public Sortie(){
        this.etat = Etat.CREATED;
        this.participants = new ArrayList<>();
    }

    public Sortie(@Size(max = 50) String nom, Date dateDebut, int duree,
                  Date dateCloture, int nbInscriptions, @Size(max = 500) String description,
                  Participant organisateur, Lieu lieu) {
        this();
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.duree = duree;
        this.dateCloture = dateCloture;
        this.nbInscriptions = nbInscriptions;
        this.description = description;
        this.organisateur = organisateur;
        this.lieu = lieu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Date getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(Date dateCloture) {
        this.dateCloture = dateCloture;
    }

    public int getNbInscriptions() {
        return nbInscriptions;
    }

    public void setNbInscriptions(int nbInscriptions) {
        this.nbInscriptions = nbInscriptions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Participant getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(Participant organisateur) {
        this.organisateur = organisateur;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public List<Inscription> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Inscription> participants) {
        this.participants = participants;
    }

    public void addParticipant(Participant participant) {
        Inscription inscription = new Inscription(this, participant);
        participants.add(inscription);
        participant.getSorties().add(inscription);
    }

    public void removeParticipant(Participant participant) {
        for (Iterator<Inscription> iterator = participants.iterator();
             iterator.hasNext(); ) {
            Inscription inscription = iterator.next();

            if (inscription.getParticipant().equals(participant)) {
                iterator.remove();
                inscription.getParticipant().getSorties().remove(inscription);
            }
        }
    }
}
