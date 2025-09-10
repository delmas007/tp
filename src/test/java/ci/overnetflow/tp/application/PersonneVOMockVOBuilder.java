package ci.overnetflow.tp.application;

import ci.overnetflow.tp.domain.Departement;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

public class PersonneVOMockVOBuilder {
    private Long id;
    private String nom;
    private String prenom;
    private Long age;
    private DepartementVO departement;

    public PersonneVO build() {
        PersonneVO mock = mock(PersonneVO.class);

        // Utilisation de lenient() pour éviter les "unnecessary stubbing" warnings
        lenient().when(mock.getId()).thenReturn(id);
        lenient().when(mock.getNom()).thenReturn(nom);
        lenient().when(mock.getPrenom()).thenReturn(prenom);
        lenient().when(mock.getAge()).thenReturn(age);
        lenient().when(mock.getDepartement()).thenReturn(departement);

        return mock;
    }

    public PersonneVOMockVOBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public PersonneVOMockVOBuilder setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public PersonneVOMockVOBuilder setPrenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public PersonneVOMockVOBuilder setDepartement(DepartementVO departement) {
        this.departement = departement;
        return this;
    }

    public PersonneVOMockVOBuilder setAge(Long age) {
        this.age = age;
        return this;
    }
}