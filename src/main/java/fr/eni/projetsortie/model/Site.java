package fr.eni.projetsortie.model;

import javax.persistence.*;

@Entity(name = "Sites")
@Table(name = "Sites")
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no_site")
    private Integer id;

    @Column(name = "nom_site", nullable = false)
    private String nom;

    public Site() {
    }

    public Site(String nom) {
        this();
        this.nom = nom;
    }

    public Site(String nom, int id) {
        this(nom);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Site setId(int id) {
        this.id = id;
        return this;
    }

    public String getNom() {
        return nom;
    }

    public Site setNom(String nom) {
        this.nom = nom;
        return this;
    }

    @Override
    public String toString() {
        return "Site{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
