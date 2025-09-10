package ci.overnetflow.tp.interfaces;

import ci.overnetflow.tp.application.DepartementVO;
import ci.overnetflow.tp.application.DepartementVOMockBuilder;
import ci.overnetflow.tp.domain.DepartementMockBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartementControleur.class)
class DepartementControleurTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartementFacade departementFacade;

    private DepartementVO departementVO;

    @BeforeEach
    void setUp() {
        departementVO = new DepartementVOMockBuilder()
                .setId(1L)
                .setCode(123L)
                .setDesignation("Informatique")
                .build();
    }

    @Test
    void liste_shouldReturnListOfDepartements() throws Exception {
        List<DepartementVO> departements = Arrays.asList(departementVO);
        when(departementFacade.liste()).thenReturn(departements);

        mockMvc.perform(get("/departements/toutes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].designation").value("Informatique"));
    }

    @Test
    void recupererParId_shouldReturnDepartement() throws Exception {
        when(departementFacade.recupererParId(1L)).thenReturn(departementVO);

        mockMvc.perform(get("/departements/recuperer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.designation").value("Informatique"));
    }
}