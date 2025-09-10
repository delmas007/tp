package ci.overnetflow.tp.interfaces;


import ci.overnetflow.tp.application.DepartementService;
import ci.overnetflow.tp.application.DepartementVO;
import ci.overnetflow.tp.domain.Departement;
import ci.overnetflow.tp.domain.DepartementMockBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartementFacadeTest {

    @Mock
    private DepartementService departementService;

    @InjectMocks
    private DepartementFacade departementFacade;

    private Departement departement;
    private Long departementId;

    @BeforeEach
    void setUp() {
        departementId = 1L;
        departement = new DepartementMockBuilder()
                .setId(departementId)
                .setCode(123L)
                .setDesignation("Informatique")
                .build();
    }

    @Test
    void liste() {
        Departement dept2 = new DepartementMockBuilder()
                .setId(2L)
                .setCode(456L)
                .setDesignation("Mathématiques")
                .build();

        List<Departement> departements = Arrays.asList(departement, dept2);
        when(departementService.recupererDepartements()).thenReturn(departements);

        List<DepartementVO> resultat = departementFacade.liste();

        assertNotNull(resultat, "La liste ne doit pas être null");
        assertEquals(2, resultat.size(), "La liste doit contenir 2 départements");
        assertEquals("Informatique", resultat.get(0).getDesignation());
        assertEquals("Mathématiques", resultat.get(1).getDesignation());

        verify(departementService, times(1)).recupererDepartements();
    }

    @Test
    void recupererParId() {
        when(departementService.recupererParId(departementId)).thenReturn(departement);

        DepartementVO resultat = departementFacade.recupererParId(departementId);

        assertNotNull(resultat, "Le département ne doit pas être null");
        assertEquals(departementId, resultat.getId());
        assertEquals("Informatique", resultat.getDesignation());

        verify(departementService, times(1)).recupererParId(departementId);
    }
}