package ci.overnetflow.tp.interfaces;

import ci.overnetflow.tp.application.DepartementService;
import ci.overnetflow.tp.application.DepartementVO;
import ci.overnetflow.tp.domain.Departement;
import ci.overnetflow.tp.domain.DepartementRepository;
import ci.overnetflow.tp.domain.PersonneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementFacade {

    private DepartementService departementService;

    public DepartementFacade(DepartementService departementService) {
        this.departementService = departementService;
    }

    public List<DepartementVO> listeDepartements() {
        List<Departement> departements = departementService.recupererTousLesDepartements();
        return departements.stream().map(departement -> {
            DepartementVO departementVO = new DepartementVO();
            departementVO.setId(departement.getId());
            departementVO.setCode(departement.getCode());
            departementVO.setDesignation(departement.getDesignation());
            return departementVO;
        }).toList();
    }

    public DepartementVO recupererUnDepartementParSonId(Long id) {
        Departement departement = departementService.recupererUnDepartementParSonId(id);
        DepartementVO departementVO = new DepartementVO();
        departementVO.setId(departement.getId());
        departementVO.setCode(departement.getCode());
        departementVO.setDesignation(departement.getDesignation());
        return departementVO;
    }
}
