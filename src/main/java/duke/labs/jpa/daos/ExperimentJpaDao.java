package duke.labs.jpa.daos;

import duke.labs.jpa.entities.Experiment;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Logger;

@Dependent
@Transactional(Transactional.TxType.REQUIRED)
public class ExperimentJpaDao implements Dao<Experiment>{

    private static final Logger LOGGER = Logger.getLogger(ExperimentJpaDao.class.getName());

    @PersistenceContext//(unitName = "duke-derby-pu")
    EntityManager entityManager;

    @Override
    public Collection<Experiment> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Experiment e");
        return query.getResultList();
    }


    @Override
    public Experiment save(Experiment experiment) {

        //Set experiment(parent) to each measurement(child) for JPA relationship
        experiment.getMeasurements().stream().forEach(m -> m.setExperiment(experiment));

        if (experiment.getId() < 1) {
            this.entityManager.persist(experiment);
            return experiment;
        } else {
            return this.entityManager.merge(experiment);
        }
    }


    @Override
    public void delete(Experiment experiment) {

        experiment.getMeasurements().stream().forEach(m -> m.setExperiment(experiment));        
        this.entityManager.remove(experiment);
    }

    @Override
    public Optional<Experiment> findById(int id) {
        return Optional.ofNullable(this.entityManager.find(Experiment.class, id));
    }

    @Override
    public Optional<Collection<Experiment>> findByName(String name) {
        Query query = entityManager
                .createQuery("SELECT e FROM Experiment e WHERE e.task LIKE :taskName")
                .setParameter("taskName", "%" + name + "%");

        return Optional.ofNullable(query.getResultList());
    }

}
