package fr.eni.projetsortie.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Lieux")
public class Lieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no_lieu")
    private int id;

    @Size(max = 100)
    @Column(name = "nom_lieu", length = 100, nullable = false)
    private String nom;

    @Size(max = 200)
    @Column(name = "rue", length = 200)
    private String adresse;

    @Column(name = "latitude", precision = 6, length = 9)
    private float latitude;

    @Column(name = "longitude", precision = 6, length = 9)
    private float longitude;

    @ManyToOne(targetEntity = Ville.class)
    @JoinColumn(name = "villes_no_ville", referencedColumnName = "no_ville")
    private Ville ville;

    public Lieu() {
        this.id = 0;
        this.nom = "";
        this.adresse = "";
        this.latitude = 0f;
        this.longitude = 0f;
        this.ville = new Ville();
    }

    public Lieu(@Size(max = 100) String nom, @Size(max = 200) String adresse, float latitude, float longitude, Ville ville) {
        this();
        this.nom = nom;
        this.adresse = adresse;
        this.latitude = latitude;
        this.longitude = longitude;
        this.ville = ville;
    }

    public Lieu(@Size(max = 100) String nom, @Size(max = 200) String adresse, float latitude, float longitude, Ville ville, int id) {
        this(nom, adresse, latitude, longitude, ville);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Lieu setId(int id) {
        this.id = id;
        return this;
    }

    public String getNom() {
        return nom;
    }

    public Lieu setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getAdresse() {
        return adresse;
    }

    public Lieu setAdresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public float getLatitude() {
        return latitude;
    }

    public Lieu setLatitude(float latitude) {
        this.latitude = latitude;
        return this;
    }

    public float getLongitude() {
        return longitude;
    }

    public Lieu setLongitude(float longitude) {
        this.longitude = longitude;
        return this;
    }

    public Ville getVille() {
        return ville;
    }

    public Lieu setVille(Ville ville) {
        this.ville = ville;
        return this;
    }
}
