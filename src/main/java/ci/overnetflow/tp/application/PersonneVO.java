package ci.overnetflow.tp.application;

import ci.overnetflow.tp.domain.Departement;
import ci.overnetflow.tp.domain.Personne;

public class PersonneVO {
    private Long id;
    private String nom;
    private String prenom;
    private Long age;
    private DepartementVO departement;

    public PersonneVO() {
    }

    public PersonneVO(Personne personne, Departement departement) {
        this.id = personne.getId();
        this.nom = personne.getNom();
        this.prenom = personne.getPrenom();
        this.age = personne.getAge();
        this.departement = new DepartementVO(departement);
    }

    public PersonneVO(Personne personne) {
        this.id = personne.getId();
        this.nom = personne.getNom();
        this.prenom = personne.getPrenom();
        this.age = personne.getAge();
        this.departement = new DepartementVO(personne.getDepartement());
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

    public DepartementVO getDepartement() {
        return departement;
    }
}
