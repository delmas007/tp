package ci.overnetflow.tp.application;

import ci.overnetflow.tp.domain.Departement;
import ci.overnetflow.tp.domain.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementService {

    private DepartementRepository departementRepository;

    @Autowired
    public DepartementService(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    public Departement recupererUnDepartementParSonId(Long id) {
        return departementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Département non trouvé"));
    }

    public Departement enregistrerUnDepartement(Departement departement) {
        return departementRepository.save(departement);
    }

    public Departement modifierUnDepartement(Long id, Departement departement) {
        Departement departementAModifier = recupererUnDepartementParSonId(id);
        if (departement.getCode() != null) {
            departementAModifier.setCode(departement.getCode());
        }
        if (departement.getDesignation() != null) {
            departementAModifier.setDesignation(departement.getDesignation());
        }
        return departementRepository.save(departementAModifier);
    }

    public void supprimerUnDepartement(Long id) {
        Departement departementASupprimer = recupererUnDepartementParSonId(id);
        departementRepository.delete(departementASupprimer);
    }

    public List<Departement> recupererTousLesDepartements() {
        return departementRepository.findAll();
    }
}
