package ci.overnetflow.tp.interfaces;

import ci.overnetflow.tp.application.DepartementService;
import ci.overnetflow.tp.application.PersonneService;
import ci.overnetflow.tp.application.PersonneVO;
import ci.overnetflow.tp.domain.Departement;
import ci.overnetflow.tp.domain.Personne;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonneFacade {

    private final DepartementService departementService;
    private final PersonneService personneService ;

    public PersonneFacade(DepartementFacade departementFacade, PersonneService personneService, DepartementService departementService) {
        this.personneService = personneService;
        this.departementService = departementService;
    }



    /**
     * Récupérer une personne par son id
     *
     * @param id
     * @return PersonneVO
     */
    public PersonneVO recupererParId(Long id) {
        Personne personne = personneService.recupererParId(id);
        return new  PersonneVO(personne);
    }

    /**
     * Enregistrer ou modifier une personne
     *
     * @param personneVO
     * @param idDepartement
     * @return PersonneVO
     */
    public PersonneVO enregistrerModifier(PersonneVO personneVO, Long idDepartement) {
        Departement departement = departementService.recupererParId(idDepartement);
        Personne personne;
        if (personneVO.getId() != null) {
            Personne personne1 = personneService.recupererParId(personneVO.getId());
            personne1.mettreAJour(personneVO.getNom(),  personneVO.getPrenom(), personneVO.getAge(), departement);
            personne = personneService.enregistrer(personne1);
            return new PersonneVO(personne, departement);
        }
        Personne personne2 = new Personne(personneVO.getNom(),  personneVO.getPrenom(), personneVO.getAge(), departement);
        personne = personneService.enregistrer(personne2);
        return new PersonneVO(personne, departement);
    }

    /**
     * Supprimer une personne par son id
     *
     * @param id
     */
    public void supprimerParId(Long id) {
        personneService.supprimerUnePersonne(id);
    }

    /**
     * Récupérer la liste des personnes
     *
     * @return List<PersonneVO>
     */
    public List<PersonneVO> liste() {
        List<Personne> personnes = personneService.recupererPersonnes();
        return personnes.stream()
                .map(PersonneVO::new).toList();
    };
}
