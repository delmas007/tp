package ci.overnetflow.tp.application;

import ci.overnetflow.tp.domain.Departement;
import ci.overnetflow.tp.domain.DepartementRepository;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartementServiceTest {

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private DepartementService departementService;

    private Departement departement;
    private Long departementId;

    @BeforeEach
    void setUp() {
        departementId = 1L;
        departement = new Departement();
        departement.setId(departementId);
        departement.setCode(123L);
        departement.setDesignation("Informatique");
    }

    @Test
    void recupererUnDepartementParSonId_QuandDepartementExiste_DoitRetournerLeDepartement() {
        when(departementRepository.findById(departementId))
                .thenReturn(Optional.of(departement));

        Departement resultat = departementService.recupererUnDepartementParSonId(departementId);

        assertNotNull(resultat, "Le département ne devrait pas être null");
        assertEquals(departementId, resultat.getId(), "L'ID devrait correspondre");
        assertEquals(123L, resultat.getCode(), "Le code devrait correspondre");
        assertEquals("Informatique", resultat.getDesignation(), "La désignation devrait correspondre");

        verify(departementRepository).findById(departementId);
    }

    @Test
    void enregistrerUnDepartement_AvecDepartemenetValide_DoitSauvegarderEtRetournerLeDepartement() {

        when(departementRepository.save(any(Departement.class)))
                .thenReturn(departement);

        Departement resultat = departementService.enregistrerUnDepartement(departement);

        assertNotNull(resultat, "Le département sauvegardé ne devrait pas être null");
        assertEquals(departement.getId(), resultat.getId());
        assertEquals(departement.getCode(), resultat.getCode());
        assertEquals(departement.getDesignation(), resultat.getDesignation());

        verify(departementRepository).save(departement);
    }

    @Test
    void modifierUnDepartement_AvecTousLesChamps_DoitModifierTousLesChamps() {

        Departement departementModifie = new Departement();
        departementModifie.setCode(1234L);
        departementModifie.setDesignation("Mathématiques");

        when(departementRepository.findById(departementId))
                .thenReturn(Optional.of(departement));

        when(departementRepository.save(any(Departement.class)))
                .thenReturn(departement);

        Departement resultat = departementService.modifierUnDepartement(departementId, departementModifie);

        assertNotNull(resultat);

        assertEquals(1234L, departement.getCode(), "Le code devrait être mis à jour");
        assertEquals("Mathématiques", departement.getDesignation(), "La désignation devrait être mise à jour");

        verify(departementRepository).findById(departementId);
        verify(departementRepository).save(departement);
    }

    @Test
    void modifierUnDepartement_AvecSeulementLeCode_DoitModifierSeulementLeCode() {

        Departement departementModifie = new Departement();
        departementModifie.setCode(12334L);

        String designationOriginale = departement.getDesignation();

        when(departementRepository.findById(departementId))
                .thenReturn(Optional.of(departement));
        when(departementRepository.save(any(Departement.class)))
                .thenReturn(departement);

        departementService.modifierUnDepartement(departementId, departementModifie);

        assertEquals(12334L, departement.getCode(), "Le code devrait être modifié");
        assertEquals(designationOriginale, departement.getDesignation(),
                "La désignation ne devrait pas changer");

        verify(departementRepository).findById(departementId);
        verify(departementRepository).save(departement);
    }

    @Test
    void supprimerUnDepartement_AvecIdExistant_DoitSupprimerLeDepartement() {

        when(departementRepository.findById(departementId))
                .thenReturn(Optional.of(departement));

        doNothing().when(departementRepository).delete(any(Departement.class));

        assertDoesNotThrow(() -> departementService.supprimerUnDepartement(departementId));

        verify(departementRepository).findById(departementId);
        verify(departementRepository).delete(departement);
    }

    @Test
    void recupererTousLesDepartements_AvecDesDepatements_DoitRetournerLaListe() {

        Departement dept2 = new Departement();
        dept2.setId(2L);
        dept2.setCode(123L);
        dept2.setDesignation("Mathématiques");

        List<Departement> departements = Arrays.asList(departement, dept2);

        when(departementRepository.findAll()).thenReturn(departements);

        List<Departement> resultat = departementService.recupererTousLesDepartements();

        assertNotNull(resultat, "La liste ne devrait pas être null");
        assertEquals(2, resultat.size(), "La liste devrait contenir 2 départements");
        assertTrue(resultat.contains(departement), "La liste devrait contenir le premier département");
        assertTrue(resultat.contains(dept2), "La liste devrait contenir le second département");

        verify(departementRepository).findAll();
    }

}