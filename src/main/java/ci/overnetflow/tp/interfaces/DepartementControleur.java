package ci.overnetflow.tp.interfaces;

import ci.overnetflow.tp.application.DepartementVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departements")
public class DepartementControleur {
    private final DepartementFacade departementFacade;

    public DepartementControleur(DepartementFacade departementFacade) {
        this.departementFacade = departementFacade;
    }

    /**
     * Récupérer la liste des départements
     *
     * @return List<DepartementVO>
     */
    @GetMapping("/toutes")
    @ResponseBody
    public java.util.List<DepartementVO> liste() {
        return departementFacade.liste();
    }

    /**
     * Récupérer un département par son id
     *
     * @param id l'identifiant du département à récupérer.
     *
     * @return le département correspondant à l'identifiant fourni.
     */
    @GetMapping("/recuperer/{id}")
    @ResponseBody
    public DepartementVO recupererParId(@PathVariable("id") Long id) {
        return departementFacade.recupererParId(id);
    }
}
