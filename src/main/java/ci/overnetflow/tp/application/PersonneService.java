package ci.overnetflow.tp.application;

import ci.overnetflow.tp.domain.Personne;
import ci.overnetflow.tp.domain.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonneService {

    public PersonneService(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    private PersonneRepository personneRepository;

    public Personne recupererUnePersonneParSonId(Long id) {
        return personneRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Personne non trouver") );
    }

    public Personne enregistrerUnePersonne(Personne personne) {
        return personneRepository.save(personne);
    }

    public Personne modifierUnePersonne(Long id ,Personne personne) {
        Personne personneAModifier = recupererUnePersonneParSonId(id);
        if (personne.getNom() != null) {
            personneAModifier.setNom(personne.getNom());
        }
        if (personne.getPrenom() != null) {
            personneAModifier.setPrenom(personne.getPrenom());
        }
        if (personne.getAge() != null) {
            personneAModifier.setAge(personne.getAge());
        }
        if (personne.getDepartement() != null) {
            personneAModifier.setDepartement(personne.getDepartement());
        }
        return personneRepository.save(personneAModifier);
    }

    public void supprimerUnePersonne(Long id) {
        Personne personneASupprimer = recupererUnePersonneParSonId(id);
        personneRepository.delete(personneASupprimer);
    }

    public List<Personne> recupererTouteLesPersonnes() {
        return personneRepository.findAll();
    };
}
