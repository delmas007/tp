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

    /**
     * Récupérer un département par son id
     *
     * @param id
     * @return Departement
     */
    public Departement recupererParId(Long id) {
        return departementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Département non trouvé"));
    }

    /**
     * Enregistrer ou modifier un département
     *
     * @param departement
     * @return Departement
     */
    public Departement enregistrer(Departement departement) {
        return departementRepository.save(departement);
    }

    /**
     * Supprimer un département par son id
     *
     * @param id
     */
    public void supprimerParId(Long id) {
        Departement departementASupprimer = recupererParId(id);
        departementRepository.delete(departementASupprimer);
    }

    /**
     * Récupérer la liste des départements
     *
     * @return List<Departement>
     */
    public List<Departement> recupererDepartements() {
        return departementRepository.findAll();
    }
}
