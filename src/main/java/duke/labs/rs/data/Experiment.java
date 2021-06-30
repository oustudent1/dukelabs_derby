package duke.labs.rs.data;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.annotation.JsonbTransient;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;


@JsonbPropertyOrder({ "id", "task", "startTime", "endTime", "complete", "measurements" })
public class Experiment{

    private int id;
    private String task;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean complete;

    @JsonbTransient
    private String owner;

    private Collection<Measurement> measurements;

    public static Experiment of(int id, String task, LocalDateTime startTime, LocalDateTime endTime, boolean complete, String owner, Collection<Measurement> measurements) {
        Experiment experiment = new Experiment();

        experiment.setId(id);
        experiment.setTask(task);
        experiment.setStartTime(startTime);
        experiment.setEndTime(endTime);
        experiment.setComplete(complete);
        experiment.setOwner(owner);
        experiment.setMeasurements(measurements);

        return experiment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Collection<Measurement> getMeasurements() {
        if(measurements == null){
            measurements = new HashSet<>();
        }
        return measurements;
    }

    public void setMeasurements(Collection<Measurement> measurements) {
        this.measurements = measurements;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n Experiment {");
        sb.append("\n\t id: ").append(id);
        sb.append(", \n\t task: ").append(task);
        sb.append(", \n\t startTime: ").append( startTime);
        sb.append(", \n\t endTime: ").append(endTime);
        sb.append(", \n\t complete: ").append(isComplete());
        sb.append(", \n\t ");
        sb.append("Measurements [ ");
        getMeasurements().stream().forEach(
                m -> sb.append("\n\t\t measurement {  ")
                        .append("\n\t\t\t id: ")
                        .append(m.getId())
                        .append("\n\t\t\t value: ")
                        .append(m.getValue())
                        .append(",\n\t\t\t unit: ")
                        .append(m.getUnit())
                        .append(",\n\t\t\t time: ")
                        .append(m.getTime())
                        .append("\n\t\t } "));
        sb.append("\n\t ]");
        sb.append("\n  } ");

        return sb.toString();
    }



}
