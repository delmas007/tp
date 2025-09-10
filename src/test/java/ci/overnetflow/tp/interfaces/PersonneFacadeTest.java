package ci.overnetflow.tp.interfaces;


import ci.overnetflow.tp.application.DepartementService;
import ci.overnetflow.tp.application.PersonneService;
import ci.overnetflow.tp.application.PersonneVO;
import ci.overnetflow.tp.application.PersonneVOMockVOBuilder;
import ci.overnetflow.tp.domain.Departement;
import ci.overnetflow.tp.domain.DepartementMockBuilder;
import ci.overnetflow.tp.domain.Personne;
import ci.overnetflow.tp.domain.PersonneMockBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonneFacadeTest {

    @Mock
    private PersonneService personneService;

    @Mock
    private DepartementService departementService;

    @InjectMocks
    private PersonneFacade personneFacade;

    private Personne personne;
    private Departement departement;
    private Long personneId;
    private Long departementId;

    @BeforeEach
    void setUp() {
        departementId = 1L;
        departement = new DepartementMockBuilder()
                .setId(departementId)
                .setCode(123L)
                .setDesignation("Informatique")
                .build();

        personneId = 1L;
        personne = new PersonneMockBuilder()
                .setId(personneId)
                .setAge(25L)
                .setNom("Dupont")
                .setPrenom("Jean")
                .setDepartement(departement)
                .build();
    }

    @Test
    void recupererParId() {
        when(personneService.recupererParId(personneId)).thenReturn(personne);

        PersonneVO resultat = personneFacade.recupererParId(personneId);

        assertNotNull(resultat);
        assertEquals(personneId, resultat.getId());
        assertEquals("Dupont", resultat.getNom());
        assertEquals("Jean", resultat.getPrenom());
        verify(personneService, times(1)).recupererParId(personneId);
    }

    @Test
    void enregistrerModifier_Creation() {

        PersonneVO personneVO = new PersonneVOMockVOBuilder()
                .setNom("Dupont")
                .setPrenom("Jean")
                .setAge(25L)
                .build();

        when(departementService.recupererParId(departementId)).thenReturn(departement);
        when(personneService.enregistrer(any(Personne.class))).thenReturn(personne);

        PersonneVO resultat = personneFacade.enregistrerModifier(personneVO, departementId);

        assertNotNull(resultat);
        assertEquals("Dupont", resultat.getNom());
        assertEquals("Jean", resultat.getPrenom());
        verify(personneService).enregistrer(any(Personne.class));
        verify(departementService).recupererParId(departementId);
    }

    @Test
    void enregistrerModifier_Modification() {

        PersonneVO personneVO = new PersonneVOMockVOBuilder()
                .setId(personneId)
                .setNom("Dupont")
                .setPrenom("Jean")
                .setAge(30L)
                .build();


        when(departementService.recupererParId(departementId)).thenReturn(departement);
        when(personneService.recupererParId(personneId)).thenReturn(personne);
        when(personneService.enregistrer(any(Personne.class))).thenReturn(personne);

        PersonneVO resultat = personneFacade.enregistrerModifier(personneVO, departementId);

        assertNotNull(resultat);
        assertEquals("Dupont", resultat.getNom());
        assertEquals("Jean", resultat.getPrenom());
        verify(personneService).recupererParId(personneId);
        verify(personneService).enregistrer(any(Personne.class));
        verify(departementService).recupererParId(departementId);
    }

    @Test
    void supprimerParId() {
        doNothing().when(personneService).supprimerUnePersonne(personneId);

        assertDoesNotThrow(() -> personneFacade.supprimerParId(personneId));
        verify(personneService).supprimerUnePersonne(personneId);
    }

    @Test
    void liste() {
        Personne personne2 = new PersonneMockBuilder()
                .setId(2L)
                .setNom("Martin")
                .setPrenom("Paul")
                .setAge(40L)
                .setDepartement(departement)
                .build();

        List<Personne> personnes = Arrays.asList(personne, personne2);
        when(personneService.recupererPersonnes()).thenReturn(personnes);

        List<PersonneVO> resultat = personneFacade.liste();

        assertNotNull(resultat);
        assertEquals(2, resultat.size());
        assertEquals("Dupont", resultat.get(0).getNom());
        assertEquals("Martin", resultat.get(1).getNom());
        verify(personneService).recupererPersonnes();
    }
}