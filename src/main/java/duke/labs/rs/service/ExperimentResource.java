package duke.labs.rs.service;
import duke.labs.rs.data.Experiment;
import duke.labs.rs.facade.RestExperimentFacade;

import javax.inject.Inject;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/experiment")
public class ExperimentResource {
    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

    private static final Logger LOGGER = Logger.getLogger(ExperimentResource.class.getName());

    @Inject
    RestExperimentFacade facade;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Experiment getExperiment(@PathParam("id") String id) throws NotFoundException {

        LOGGER.log(Level.FINE,
                "The following experiment has been requested: {0}", id);
        Experiment experiment =
                facade.getExperimentById(Integer.parseInt(id));
        LOGGER.log(Level.INFO, "Retrieved experiment: {0}", experiment);

        return experiment;
    }

    @GET
    @Path("task/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<Experiment> getExperimentsByName(@PathParam("name") String name) {

        LOGGER.log(Level.FINE, "Looking for experiments with name: {0}", name);
        Collection<Experiment> experiments = facade.getExperimentsByName(name);
        LOGGER.log(Level.INFO, "Number of retrieved experiments: {0}", experiments.size());

        return experiments;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Experiment createExperiment(Experiment experiment) {
        //Owner required by DB
        experiment.setOwner("REST-Scientist");
        Experiment e = facade.saveExperiment(experiment);
        LOGGER.log(Level.INFO,
                "The following experiment has been created: {0}", e);
        return e;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeExperiment(@PathParam("id") String id) throws NotFoundException {

        LOGGER.log(Level.FINE,
                "Deletion requested for experiment: {0}", id);
        boolean deleted = facade.deleteExperiment(Integer.parseInt(id));
        Response.ResponseBuilder rb = null;
        if(deleted){
            rb = Response.ok("Deleted experiment: " + id,
                    MediaType.APPLICATION_JSON);
            LOGGER.log(Level.INFO,
                    "Experiment with id {0} was deleted.", id);
        }else{
            throw new NotFoundException("Experiment was not deleted.");
        }

        return rb.build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Experiment> getAllExperiments() {

        LOGGER.log(Level.FINE,
                "All experiments were requested.");
        Collection<Experiment> experiments = facade.getAllExperiments();
        LOGGER.log(Level.INFO,
                "Retrieved experiments: {0}", experiments.size());

        return experiments;
    }

    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getTestResponse() {
             return createResponse();
     //   }
  //      return Response.status(Response.Status.FOUND).build();
    }
//    public Response getTestResponse(){
//        Response.ResponseBuilder rb = null;
//        rb = Response.ok("Service is working",
//                MediaType.APPLICATION_JSON);
//        return rb.build();
//    }
private JsonObject createResponse() {
    String msg =  "Service is Working and Responding!!";

    return JSON.createObjectBuilder()
            .add("message", msg)
            .build();
}

}
