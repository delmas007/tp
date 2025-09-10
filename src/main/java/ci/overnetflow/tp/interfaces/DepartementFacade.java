package ci.overnetflow.tp.interfaces;

import ci.overnetflow.tp.application.DepartementService;
import ci.overnetflow.tp.application.DepartementVO;
import ci.overnetflow.tp.domain.Departement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementFacade {

    private DepartementService departementService;

    public DepartementFacade(DepartementService departementService) {
        this.departementService = departementService;
    }

    /**
     * Récupérer la liste des départements
     *
     * @return List<DepartementVO>
     */
    public List<DepartementVO> liste() {
        List<Departement> departements = departementService.recupererDepartements();
        return departements.stream()
                .map(DepartementVO::new)
                .toList();
    }

    /**
     * Récupérer un département par son id
     *
     * @param id
     * @return DepartementVO
     */
    public DepartementVO recupererParId(Long id) {
        Departement departement = departementService.recupererParId(id);
        return new DepartementVO(departement);
    }
}
