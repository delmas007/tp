package ci.overnetflow.tp.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "personne")
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String prenom;

    private Long age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departement")
    private Departement departement;

    public Personne() {
    }

    public Personne(String nom, String prenom, Long age, Departement departement) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.departement = departement;
    }

    public void mettreAJour(String nom, String prenom, Long age, Departement departement) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.departement = departement;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Long getAge() {
        return age;
    }

    public Departement getDepartement() {
        return departement;
    }
}
