package ci.overnetflow.tp.application;

import ci.overnetflow.tp.domain.Departement;
import ci.overnetflow.tp.domain.Personne;
import ci.overnetflow.tp.domain.PersonneRepository;
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

    @Mock
    private PersonneRepository personneRepository;

    @InjectMocks
    private PersonneService personneService;

    private Personne personne;
    private Departement departement;
    private Long personneId;

    @BeforeEach
    void setUp() {
        departement = new Departement();
        departement.setId(1L);
        departement.setCode(123L);
        departement.setDesignation("Informatique");

        personneId = 1L;
        personne = new Personne();
        personne.setId(personneId);
        personne.setNom("Dupont");
        personne.setPrenom("Jean");
        personne.setAge(25L);
        personne.setDepartement(departement);
    }

    @Test
    void recupererUnePersonneParSonId_QuandPersonneExiste_DoitRetournerLaPersonne() {
        when(personneRepository.findById(personneId))
                .thenReturn(Optional.of(personne));

        Personne resultat = personneService.recupererUnePersonneParSonId(personneId);

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
    void enregistrerUnePersonne_AvecPersonneValide_DoitSauvegarderEtRetournerLaPersonne() {

        when(personneRepository.save(any(Personne.class)))
                .thenReturn(personne);

        Personne resultat = personneService.enregistrerUnePersonne(personne);

        assertNotNull(resultat, "La personne sauvegardée ne devrait pas être null");
        assertEquals(personne.getNom(), resultat.getNom(), "Le nom devrait être conservé");
        assertEquals(personne.getPrenom(), resultat.getPrenom(), "Le prénom devrait être conservé");
        assertEquals(personne.getAge(), resultat.getAge(), "L'âge devrait être conservé");
        assertEquals(personne.getDepartement(), resultat.getDepartement(),
                "Le département devrait être conservé");

        verify(personneRepository, times(1)).save(personne);
    }

    @Test
    void modifierUnePersonne_AvecTousLesChamps_DoitModifierTousLesChamps() {

        Departement nouveauDept = new Departement();
        nouveauDept.setId(2L);
        nouveauDept.setCode(1234L);
        nouveauDept.setDesignation("Mathématiques");

        Personne personneModifiee = new Personne();
        personneModifiee.setNom("Martin");
        personneModifiee.setPrenom("Sophie");
        personneModifiee.setAge(28L);
        personneModifiee.setDepartement(nouveauDept);

        when(personneRepository.findById(personneId))
                .thenReturn(Optional.of(personne));
        when(personneRepository.save(any(Personne.class)))
                .thenReturn(personne);

        Personne resultat = personneService.modifierUnePersonne(personneId, personneModifiee);

        assertNotNull(resultat);

        assertEquals("Martin", personne.getNom(), "Le nom devrait être mis à jour");
        assertEquals("Sophie", personne.getPrenom(), "Le prénom devrait être mis à jour");
        assertEquals(28, personne.getAge(), "L'âge devrait être mis à jour");
        assertEquals(nouveauDept, personne.getDepartement(), "Le département devrait être mis à jour");

        verify(personneRepository, times(1)).findById(personneId);
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
    void recupererTouteLesPersonnes_AvecDesPersonnes_DoitRetournerLaListe() {

        Personne personne2 = new Personne();
        personne2.setId(2L);
        personne2.setNom("Martin");
        personne2.setPrenom("Marie");
        personne2.setAge(30L);
        personne2.setDepartement(departement);

        List<Personne> personnes = Arrays.asList(personne, personne2);

        when(personneRepository.findAll()).thenReturn(personnes);

        List<Personne> resultat = personneService.recupererTouteLesPersonnes();

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