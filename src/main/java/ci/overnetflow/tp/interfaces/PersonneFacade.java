package ci.overnetflow.tp.interfaces;

import ci.overnetflow.tp.application.PersonneService;
import ci.overnetflow.tp.application.PersonneVO;
import ci.overnetflow.tp.domain.Personne;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonneFacade {
    public PersonneFacade(PersonneService personneService) {
        this.personneService = personneService;
    }

    private PersonneService personneService;

    public PersonneVO recupererUnePersonneParSonId(Long id) {
        Personne personne = personneService.recupererUnePersonneParSonId(id);
        PersonneVO personneVO = new PersonneVO();
        personneVO.setId(personne.getId());
        personneVO.setNom(personne.getNom());
        personneVO.setPrenom(personne.getPrenom());
        personneVO.setAge(personne.getAge());
        return personneVO;
    }

    public PersonneVO enregistrerUnePersonne(PersonneVO personneVO) {
        Personne personne = new Personne();
        personne.setNom(personneVO.getNom());
        personne.setPrenom(personneVO.getPrenom());
        personne.setAge(personneVO.getAge());
        Personne personneEnregistree = personneService.enregistrerUnePersonne(personne);
        PersonneVO personneVOEnregistree = new PersonneVO();
        personneVOEnregistree.setId(personneEnregistree.getId());
        personneVOEnregistree.setNom(personneEnregistree.getNom());
        personneVOEnregistree.setPrenom(personneEnregistree.getPrenom());
        personneVOEnregistree.setAge(personneEnregistree.getAge());
        return personneVOEnregistree;
    }

    public PersonneVO modifierUnePersonne(Long id, PersonneVO personneVO) {
        Personne personne = new Personne();
        personne.setNom(personneVO.getNom());
        personne.setPrenom(personneVO.getPrenom());
        personne.setAge(personneVO.getAge());
        Personne personneModifiee = personneService.modifierUnePersonne(id, personne);
        PersonneVO personneVOModifiee = new PersonneVO();
        personneVOModifiee.setId(personneModifiee.getId());
        personneVOModifiee.setNom(personneModifiee.getNom());
        personneVOModifiee.setPrenom(personneModifiee.getPrenom());
        personneVOModifiee.setAge(personneModifiee.getAge());
        return personneVOModifiee;
    }

    public void supprimerUnePersonne(Long id) {
        personneService.supprimerUnePersonne(id);
    }

    public List<PersonneVO> recupererTouteLesPersonnes() {
        List<Personne> personnes = personneService.recupererTouteLesPersonnes();
        return personnes.stream().map(personne -> {
            PersonneVO personneVO = new PersonneVO();
            personneVO.setId(personne.getId());
            personneVO.setNom(personne.getNom());
            personneVO.setPrenom(personne.getPrenom());
            personneVO.setAge(personne.getAge());
            return personneVO;
        }).toList();
    };
}
