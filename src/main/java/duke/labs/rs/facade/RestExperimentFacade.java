package duke.labs.rs.facade;

import duke.labs.jpa.daos.Dao;
import duke.labs.rs.converter.ExperimentConverter;
import duke.labs.rs.data.Experiment;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Dependent
public class RestExperimentFacade implements ExperimentFacade<Experiment> {

    private static final ExperimentConverter CONVERTER = new ExperimentConverter();
    private static final Logger LOGGER = Logger.getLogger(RestExperimentFacade.class.getName());

    @Inject
    private Dao<duke.labs.jpa.entities.Experiment> experimentDao;

    @Override
    public Experiment getExperimentById(int id) {
        Experiment experiment = null;
        try{
            Optional<duke.labs.jpa.entities.Experiment> entity = experimentDao.findById(id);
            if (entity.isEmpty()) {
                LOGGER.log(Level.WARNING, "Experiment with id {0}, was not found.", id);
                return null;
            }
            experiment = CONVERTER.convertFromEntity(entity.get());
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "Problem when retrieving Experiment with id:{0}  \n Error message: {1}" , new Object[]{id, ex.getMessage()} );
        }
        return experiment;

    }

    @Override
    public Collection<Experiment> getExperimentsByName(String name) {
        Collection<Experiment> experiments = new ArrayList<>();
        try {
            Optional<Collection<duke.labs.jpa.entities.Experiment>> entity = experimentDao.findByName(name);
            if (entity.isEmpty()) {
                LOGGER.log(Level.WARNING, "Experiments with name {0}, were not found.", name);
                return experiments;
            }
            experiments = CONVERTER.createFromEntities(entity.get());
        }catch (Exception ex){
            LOGGER.log(Level.SEVERE, "Problem when retrieving Experiment with task name:{0}  \n Error message: {1}" , new Object[]{name, ex.getMessage()} );
        }
        return experiments;
    }

    @Override
    public Collection<Experiment> getAllExperiments() {
        Collection<Experiment> experiments = new ArrayList<>();
        try {
            experiments = CONVERTER.createFromEntities(experimentDao.getAll());
        }catch (Exception ex){
            LOGGER.log(Level.SEVERE, "Problem when retrieving all Experiments. \n Error message: {0}", ex.getMessage());
        }
        return experiments;
    }

    @Override
    public Experiment saveExperiment(Experiment e) {
        Experiment experiment = null;
        try {
            duke.labs.jpa.entities.Experiment entity = experimentDao.save(CONVERTER.convertFromDto(e));
            experiment = CONVERTER
                    .convertFromEntity(entity);
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "Problem when saving Experiment:{0}  \n Error message: {1}" , new Object[]{e, ex.getMessage()} );
        }
        return experiment;
    }

    @Override
    @Transactional
    public boolean deleteExperiment(int id) {
        try {
            Optional<duke.labs.jpa.entities.Experiment> entity =
                    experimentDao.findById(id);
            if (entity.isEmpty()) {
                LOGGER.log(Level.WARNING,
                        "Deletion not executed, experiment with id {0} was not found.", id);
                return false;
            }
            experimentDao.delete(entity.get());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred while deleting Experiment with id " + id, e);
            return false;
        }
        return true;
    }
}
