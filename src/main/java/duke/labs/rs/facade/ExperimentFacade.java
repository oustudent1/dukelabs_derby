package duke.labs.rs.facade;

import java.util.Collection;

public interface ExperimentFacade<T> {

    T getExperimentById(int id) throws Exception;

    Collection<T> getExperimentsByName(String name);

    Collection<T> getAllExperiments();

    T saveExperiment(T e);

    boolean deleteExperiment(int id);

}
