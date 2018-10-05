package fr.eni.projetsortie.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Villes")
public class Ville {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no_ville")
    private int id;

    @Size(max = 100)
    @Column(name = "nom_ville")
    private String nom;

    @Size(max = 10)
    @Column(name = "codePostal")
    private String codePostal;

    public Ville() {
        this.id = 0;
        this.nom = "";
        this.codePostal = "";
    }

    public Ville(@Size(max = 100) String nom, @Size(max = 10) String codePostal) {
        this();
        this.nom = nom;
        this.codePostal = codePostal;
    }

    public Ville(@Size(max = 100) String nom, @Size(max = 10) String codePostal, int id) {
        this(nom, codePostal);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Ville setId(int id) {
        this.id = id;
        return this;
    }

    public String getNom() {
        return nom;
    }

    public Ville setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public Ville setCodePostal(String codePostal) {
        this.codePostal = codePostal;
        return this;
    }
}
