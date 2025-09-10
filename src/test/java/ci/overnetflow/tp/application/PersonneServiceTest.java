package ci.overnetflow.tp.application;

import ci.overnetflow.tp.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PersonneServiceTest {

    @InjectMocks
    private PersonneService personneService;

    @Mock
    private PersonneRepository personneRepository;

    private Personne personne;
    private Departement departement;
    private Long personneId;

    @BeforeEach
    void setUp() {
        departement = new DepartementMockBuilder()
                .setId(1L)
                .setCode(123L)
                .setDesignation("Informatique")
                .build();

        personneId = 1L;
        personne= new PersonneMockBuilder()
                .setId(personneId)
                .setAge(25L)
                .setNom("Dupont")
                .setPrenom("Jean")
                .setDepartement(departement)
                .build();
    }

    @Test
    void recuperer() {
        when(personneRepository.findById(personneId))
                .thenReturn(Optional.of(personne));

        Personne resultat = personneService.recupererParId(personneId);

        assertNotNull(resultat, "La personne ne devrait pas être null");
        assertEquals(personneId, resultat.getId(), "L'ID devrait correspondre");
        assertEquals("Dupont", resultat.getNom(), "Le nom devrait correspondre");
        assertEquals("Jean", resultat.getPrenom(), "Le prénom devrait correspondre");
        assertEquals(25, resultat.getAge(), "L'âge devrait correspondre");

        assertNotNull(resultat.getDepartement(), "Le département ne devrait pas être null");
        assertEquals(123L, resultat.getDepartement().getCode(), "Le code du département devrait correspondre");

        verify(personneRepository, times(1)).findById(personneId);
    }

    @Test
    void enregistrer() {
        // Given

        when(personneRepository.save(personne))
                .thenReturn(personne);
        // When
        Personne resultat = personneService.enregistrer(personne);

        // Then
        assertNotNull(resultat, "La personne sauvegardée ne devrait pas être null");
        assertEquals(personne.getNom(), resultat.getNom(), "Le nom devrait être conservé");
        assertEquals(personne.getPrenom(), resultat.getPrenom(), "Le prénom devrait être conservé");
        assertEquals(personne.getAge(), resultat.getAge(), "L'âge devrait être conservé");
        assertEquals(personne.getDepartement(), resultat.getDepartement(),
                "Le département devrait être conservé");

        verify(personneRepository, times(1)).save(personne);
    }

    @Test
    void supprimerUnePersonne_AvecIdExistant_DoitSupprimerLaPersonne() {

        when(personneRepository.findById(personneId))
                .thenReturn(Optional.of(personne));
        doNothing().when(personneRepository).delete(any(Personne.class));

        assertDoesNotThrow(() -> personneService.supprimerUnePersonne(personneId));

        verify(personneRepository, times(1)).findById(personneId);
        verify(personneRepository, times(1)).delete(personne);
    }

    @Test
    void recupererPersonnes_AvecDesPersonnes_DoitRetournerLaListe() {

        Personne personne2= new PersonneMockBuilder()
                .setAge(50L)
                .setNom("ludo")
                .setPrenom("fred")
                .build();

        List<Personne> personnes = Arrays.asList(personne, personne2);

        when(personneRepository.findAll()).thenReturn(personnes);

        List<Personne> resultat = personneService.recupererPersonnes();

        assertNotNull(resultat, "La liste ne devrait pas être null");
        assertEquals(2, resultat.size(), "La liste devrait contenir 2 personnes");

        assertTrue(resultat.contains(personne), "La liste devrait contenir la première personne");
        assertTrue(resultat.contains(personne2), "La liste devrait contenir la seconde personne");

        Personne premierePersonne = resultat.get(0);
        assertEquals("Dupont", premierePersonne.getNom());
        assertEquals("Jean", premierePersonne.getPrenom());

        verify(personneRepository, times(1)).findAll();
    }
}