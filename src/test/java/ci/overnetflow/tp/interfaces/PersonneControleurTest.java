package ci.overnetflow.tp.interfaces;

import ci.overnetflow.tp.application.PersonneVOMockVOBuilder;
import ci.overnetflow.tp.domain.PersonneMockBuilder;
import org.junit.jupiter.api.Test;

import ci.overnetflow.tp.application.PersonneVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PersonneControleur.class)
class PersonneControleurTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonneFacade personneFacade;

    private PersonneVO personneVO;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        personneVO = new PersonneVOMockVOBuilder()
                .setId(1L)
                .setAge(25L)
                .setNom("Dupont")
                .setPrenom("Jean")
                .build();
    }

    @Test
    void enregistrerModifier() throws Exception {
        when(personneFacade.enregistrerModifier(any(PersonneVO.class), eq(2L))).thenReturn(personneVO);

        mockMvc.perform(post("/personnes/enregistrer/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personneVO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nom").value("Dupont"))
                .andExpect(jsonPath("$.prenom").value("Jean"));
    }

    @Test
    void recupererParId() throws Exception {
        when(personneFacade.recupererParId(1L)).thenReturn(personneVO);

        mockMvc.perform(get("/personnes/recuperer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nom").value("Dupont"));
    }

    @Test
    void supprimerParId() throws Exception {
        doNothing().when(personneFacade).supprimerParId(1L);

        mockMvc.perform(delete("/personnes/supprimer/1"))
                .andExpect(status().isOk());
        verify(personneFacade).supprimerParId(1L);
    }

    @Test
    void liste() throws Exception {

        PersonneVO personne2 = new PersonneVOMockVOBuilder()
                .setId(2L)
                .setAge(30L)
                .setNom("Martin")
                .setPrenom("Paul")
                .build();

        List<PersonneVO> personnes = Arrays.asList(personneVO, personne2);
        when(personneFacade.liste()).thenReturn(personnes);

        mockMvc.perform(get("/personnes/toutes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
    }
}