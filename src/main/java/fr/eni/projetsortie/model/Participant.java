package fr.eni.projetsortie.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity()
@Table(name = "Participants")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no_participant")
    private int id;

    @Size(max = 50)
    @Column(name = "pseudo", nullable = false, unique = true)
    private String pseudo;

    @Size(max = 100)
    @Column(name = "nom", nullable = false)
    private String nom;

    @Size(max = 100)
    @Column(name = "prenom", nullable = false)
    private String prenom;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password_hash")
    private String password;

    @Size(max = 15)
    @Column(name = "telephone")
    private String telephone;

    @Size(max = 100)
    @Column(name = "mail", nullable = false, unique = true)
    private String mail;

    @Column(name = "administrateur", nullable = false)
    private boolean administrateur;

    @Column(name = "actif")
    private boolean actif;

    @ManyToOne(targetEntity = Site.class)
    @JoinColumn(name = "sites_no_site", referencedColumnName = "no_site")
    private Site site;

    public Participant() {
        this.administrateur = false;
        this.actif = false;
        this.telephone = "";
        this.password = "";
    }

    public Participant(@Size(max = 30) String pseudo, @Size(max = 20) String mail, @Size(max = 30) String nom, @Size(max = 30) String prenom, Site site) {
        this();
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.site = site;
    }

    public Participant(@Size(max = 30) String pseudo, @Size(max = 20) String mail, @Size(max = 30) String nom, @Size(max = 30) String prenom, Site site, String password) {
        this(pseudo, mail, nom, prenom, site);
        this.password = password;
    }

    public Participant(@Size(max = 30) String pseudo, @Size(max = 20) String mail, @Size(max = 30) String nom, @Size(max = 30) String prenom, Site site, String password, boolean actif) {
        this(pseudo, mail, nom, prenom, site, password);
        this.actif = actif;
    }

    public Participant(@Size(max = 30) String pseudo, @Size(max = 20) String mail, @Size(max = 30) String nom, @Size(max = 30) String prenom, Site site, String telephone, String password, boolean actif, boolean admin) {
        this(pseudo, mail, nom, prenom, site, password, actif);
        this.administrateur = admin;
        this.telephone = telephone;
    }

    public Participant(@Size(max = 30) String pseudo, @Size(max = 20) String mail, @Size(max = 30) String nom, @Size(max = 30) String prenom, Site site, String telephone, String password, boolean actif, boolean admin, int id) {
        this(pseudo, mail, nom, prenom, site, telephone, password, actif, admin);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Participant setId(int id) {
        this.id = id;
        return this;
    }

    public String getPseudo() {
        return pseudo;
    }

    public Participant setPseudo(String pseudo) {
        this.pseudo = pseudo;
        return this;
    }

    public String getNom() {
        return nom;
    }

    public Participant setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getPrenom() {
        return prenom;
    }

    public Participant setPrenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Participant setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public Participant setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public String getMail() {
        return mail;
    }

    public Participant setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public boolean isAdministrateur() {
        return administrateur;
    }

    public Participant setAdministrateur(boolean administrateur) {
        this.administrateur = administrateur;
        return this;
    }

    public boolean isActif() {
        return actif;
    }

    public Participant setActif(boolean actif) {
        this.actif = actif;
        return this;
    }

    public Site getSite() {
        return site;
    }

    public Participant setSite(Site site) {
        this.site = site;
        return this;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", pseudo='" + pseudo + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", password='" + password + '\'' +
                ", telephone='" + telephone + '\'' +
                ", mail='" + mail + '\'' +
                ", administrateur=" + administrateur +
                ", actif=" + actif +
                ", site=" + site +
                '}';
    }
}
