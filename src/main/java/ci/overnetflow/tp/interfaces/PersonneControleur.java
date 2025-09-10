package ci.overnetflow.tp.interfaces;

import ci.overnetflow.tp.application.PersonneVO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personnes")
public class PersonneControleur {

    private final PersonneFacade personneFacade;

    public PersonneControleur(PersonneFacade personneFacade) {
        this.personneFacade = personneFacade;
    }

    /**
     * Enregistrer une personne dans un département.
     *
     * @param personneVO la personne à enregistrer.
     * @param idDepartement l'identifiant du département où enregistrer la personne.
     *
     * @return la personne enregistrée avec son identifiant.
     */
    @PostMapping("/enregistrer/{idDepartement}")
    @ResponseBody
    public PersonneVO enregistrerModifier(
            @RequestBody PersonneVO personneVO,
            @PathVariable("idDepartement")
            Long idDepartement) {
        return personneFacade.enregistrerModifier(personneVO,idDepartement);
    }

    /**
     * Récupérer une personne par son id.
     *
     * @param id l'identifiant de la personne à récupérer.
     *
     * @return la personne correspondante à l'identifiant fourni.
     */
    @GetMapping("/recuperer/{id}")
    @ResponseBody
    public PersonneVO recupererParId(@PathVariable("id") Long id) {
        return personneFacade.recupererParId(id);
    }


    /**
     * Supprimer une personne par son id.
     *
     * @param id l'identifiant de la personne à supprimer.
     */
    @DeleteMapping("/supprimer/{id}")
    public void supprimerParId(@PathVariable("id") Long id) {
        personneFacade.supprimerParId(id);
    }

    /**
     * Récupérer la liste de toutes les personnes.
     *
     * @return une liste contenant toutes les personnes.
     */
    @GetMapping("/toutes")
    @ResponseBody
    public java.util.List<PersonneVO> liste() {
        return personneFacade.liste();
    }
}
