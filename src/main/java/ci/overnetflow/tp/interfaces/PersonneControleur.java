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

    @PostMapping("/enregistrer")
    @ResponseBody
    public PersonneVO enregistrerUnePersonne(@RequestBody PersonneVO personneVO) {
        return personneFacade.enregistrerUnePersonne(personneVO);
    }

    @GetMapping("/recuperer/{id}")
    @ResponseBody
    public PersonneVO recupererUnePersonneParSonId(@PathVariable("id") Long id) {
        return personneFacade.recupererUnePersonneParSonId(id);
    }

    @PutMapping("/modifier/{id}")
    @ResponseBody
    public PersonneVO modifierUnePersonne(@PathVariable("id") Long id,@RequestBody PersonneVO personneVO) {
        return personneFacade.modifierUnePersonne(id, personneVO);
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
