package ci.overnetflow.tp.application;

import ci.overnetflow.tp.domain.Departement;

public class PersonneVO {
    private Long id;
    private String nom;
    private String prenom;
    private Long age;
    private DepartementVO departement;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public DepartementVO getDepartement() {
        return departement;
    }

    public void setDepartement(DepartementVO departement) {
        this.departement = departement;
    }
}
