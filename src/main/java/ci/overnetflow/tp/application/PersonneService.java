package ci.overnetflow.tp.application;

import ci.overnetflow.tp.domain.Personne;
import ci.overnetflow.tp.domain.PersonneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonneService {

    public PersonneService(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    private PersonneRepository personneRepository;

    /**
     * Récupérer une personne par son id
     *
     * @param id
     * @return Personne
     */
    public Personne recupererParId(Long id) {
        return personneRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Personne non trouver") );
    }

    /**
     * Enregistrer ou modifier une personne
     *
     * @param personne
     * @return Personne
     */
    public Personne enregistrer(Personne personne) {
        return personneRepository.save(personne);
    }

    /**
     * Supprimer une personne par son id
     *
     * @param id
     */
    public void supprimerUnePersonne(Long id) {
        Personne personneASupprimer = recupererParId(id);
        personneRepository.delete(personneASupprimer);
    }

    /**
     * Récupérer la liste des personnes
     *
     * @return List<Personne>
     */
    public List<Personne> recupererPersonnes() {
        return personneRepository.findAll();
    };
}
