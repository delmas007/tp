package ci.overnetflow.tp.interfaces;

import ci.overnetflow.tp.application.DepartementVO;
import ci.overnetflow.tp.application.PersonneVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/departements")
public class DepartementControleur {
    private final DepartementFacade departementFacade;

    public DepartementControleur(DepartementFacade departementFacade) {
        this.departementFacade = departementFacade;
    }

    @GetMapping("/toutes")
    @ResponseBody
    public java.util.List<DepartementVO> recupererDepartements() {
        return departementFacade.listeDepartements();
    }

    @GetMapping("/recuperer/{id}")
    @ResponseBody
    public DepartementVO recupererUnDepartementParSonId(@PathVariable("id") Long id) {
        return departementFacade.recupererUnDepartementParSonId(id);
    }
}
