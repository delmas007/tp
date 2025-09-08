package ci.overnetflow.tp.interfaces;

import ci.overnetflow.tp.application.DepartementVO;
import ci.overnetflow.tp.application.PersonneService;
import ci.overnetflow.tp.application.PersonneVO;
import ci.overnetflow.tp.domain.Departement;
import ci.overnetflow.tp.domain.Personne;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonneFacade {

    private PersonneService personneService;
    private DepartementFacade departementFacade;

    public PersonneFacade(DepartementFacade departementFacade, PersonneService personneService) {
        this.departementFacade = departementFacade;
        this.personneService = personneService;
    }



    public PersonneVO recupererUnePersonneParSonId(Long id) {
        Personne personne = personneService.recupererUnePersonneParSonId(id);
        DepartementVO departementVO = new DepartementVO();
        departementVO.setId(personne.getDepartement().getId());
        departementVO.setCode(personne.getDepartement().getCode());
        departementVO.setDesignation(personne.getDepartement().getDesignation());
        PersonneVO personneVO = new PersonneVO();
        personneVO.setId(personne.getId());
        personneVO.setNom(personne.getNom());
        personneVO.setPrenom(personne.getPrenom());
        personneVO.setAge(personne.getAge());
        personneVO.setDepartement(departementVO);
        return personneVO;
    }

    public PersonneVO enregistrerUnePersonne(PersonneVO personneVO, Long idDepartement) {
        DepartementVO departementVO = departementFacade.recupererUnDepartementParSonId(idDepartement);
        Departement departement = new Departement();
        departement.setId(departementVO.getId());
        departement.setCode(departementVO.getCode());
        departement.setDesignation(departementVO.getDesignation());
        Personne personne = new Personne();
        personne.setNom(personneVO.getNom());
        personne.setPrenom(personneVO.getPrenom());
        personne.setAge(personneVO.getAge());
        personne.setDepartement(departement);
        Personne personneEnregistree = personneService.enregistrerUnePersonne(personne);
        departementVO.setId(personneEnregistree.getDepartement().getId());
        departementVO.setCode(personneEnregistree.getDepartement().getCode());
        departementVO.setDesignation(personneEnregistree.getDepartement().getDesignation());
        PersonneVO personneVOEnregistree = new PersonneVO();
        personneVOEnregistree.setId(personneEnregistree.getId());
        personneVOEnregistree.setNom(personneEnregistree.getNom());
        personneVOEnregistree.setPrenom(personneEnregistree.getPrenom());
        personneVOEnregistree.setAge(personneEnregistree.getAge());
        personneVOEnregistree.setDepartement(departementVO);
        return personneVOEnregistree;
    }

    public PersonneVO modifierUnePersonne(Long idPersonne, PersonneVO personneVO, Long idDepartement) {
        DepartementVO departementVO = departementFacade.recupererUnDepartementParSonId(idDepartement);
        Departement departement = new Departement();
        departement.setId(departementVO.getId());
        departement.setCode(departementVO.getCode());
        departement.setDesignation(departementVO.getDesignation());
        Personne personne = new Personne();
        personne.setNom(personneVO.getNom());
        personne.setPrenom(personneVO.getPrenom());
        personne.setAge(personneVO.getAge());
        personne.setDepartement(departement);
        Personne personneModifiee = personneService.modifierUnePersonne(idPersonne, personne);
        departementVO.setId(personneModifiee.getDepartement().getId());
        departementVO.setCode(personneModifiee.getDepartement().getCode());
        departementVO.setDesignation(personneModifiee.getDepartement().getDesignation());
        PersonneVO personneVOModifiee = new PersonneVO();
        personneVOModifiee.setId(personneModifiee.getId());
        personneVOModifiee.setNom(personneModifiee.getNom());
        personneVOModifiee.setPrenom(personneModifiee.getPrenom());
        personneVOModifiee.setAge(personneModifiee.getAge());
        personneVOModifiee.setDepartement(departementVO);
        return personneVOModifiee;
    }

    public void supprimerUnePersonne(Long id) {
        personneService.supprimerUnePersonne(id);
    }

    public List<PersonneVO> recupererTouteLesPersonnes() {
        List<Personne> personnes = personneService.recupererTouteLesPersonnes();
        return personnes.stream().map(personne -> {
            DepartementVO departementVO = new DepartementVO();
            departementVO.setId(personne.getDepartement().getId());
            departementVO.setCode(personne.getDepartement().getCode());
            departementVO.setDesignation(personne.getDepartement().getDesignation());
            PersonneVO personneVO = new PersonneVO();
            personneVO.setId(personne.getId());
            personneVO.setNom(personne.getNom());
            personneVO.setPrenom(personne.getPrenom());
            personneVO.setAge(personne.getAge());
            personneVO.setDepartement(departementVO);
            return personneVO;
        }).toList();
    };
}
