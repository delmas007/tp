package ci.overnetflow.tp.domain;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

public class PersonneMockBuilder {
    private Long id;
    private String nom;
    private String prenom;
    private Long age;
    private Departement departement;

    public Personne build() {
        Personne mock = mock(Personne.class);

        // Utilisation de lenient() pour éviter les "unnecessary stubbing" warnings
        lenient().when(mock.getId()).thenReturn(id);
        lenient().when(mock.getNom()).thenReturn(nom);
        lenient().when(mock.getPrenom()).thenReturn(prenom);
        lenient().when(mock.getAge()).thenReturn(age);
        lenient().when(mock.getDepartement()).thenReturn(departement);

        return mock;
    }

    public PersonneMockBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public PersonneMockBuilder setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public PersonneMockBuilder setPrenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public PersonneMockBuilder setDepartement(Departement departement) {
        this.departement = departement;
        return this;
    }

    public PersonneMockBuilder setAge(Long age) {
        this.age = age;
        return this;
    }
}