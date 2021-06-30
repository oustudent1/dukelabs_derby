package duke.labs.rs.service;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

@ApplicationScoped
@ApplicationPath("/labs")
public class DukeLabsApp extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(ExperimentResource.class);
    }

}
