package ci.overnetflow.tp.interfaces;

import ci.overnetflow.tp.application.PersonneVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/personnes")
public class PersonneControleur {

    public PersonneControleur(PersonneFacade personneFacade) {
        this.personneFacade = personneFacade;
    }

    private final PersonneFacade personneFacade;

    @PostMapping("/enregistrer/{idDepartement}")
    @ResponseBody
    public PersonneVO enregistrerUnePersonne(@RequestBody PersonneVO personneVO,@PathVariable("idDepartement") Long idDepartement) {
        return personneFacade.enregistrerUnePersonne(personneVO,idDepartement);
    }

    @GetMapping("/recuperer/{id}")
    @ResponseBody
    public PersonneVO recupererUnePersonneParSonId(@PathVariable("id") Long id) {
        return personneFacade.recupererUnePersonneParSonId(id);
    }

    @PutMapping("/modifier/{idPersonne}/{idDepartement}")
    @ResponseBody
    public PersonneVO modifierUnePersonne(
            @PathVariable("idPersonne") Long idPersonne,
            @PathVariable("idDepartement") Long idDepartement,
            @RequestBody PersonneVO personneVO) {
        return personneFacade.modifierUnePersonne(idPersonne ,personneVO ,idDepartement);
    }

    @DeleteMapping("/supprimer/{id}")
    @ResponseBody
    public void supprimerUnePersonne(@PathVariable("id") Long id) {
        personneFacade.supprimerUnePersonne(id);
    }

    @GetMapping("/toutes")
    @ResponseBody
    public java.util.List<PersonneVO> recupererTouteLesPersonnes() {
        return personneFacade.recupererTouteLesPersonnes();
    }
}
