package duke.labs.jpa.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;


@Access(value = AccessType.FIELD)
@Entity(name = "Experiment")
@Table(name = "EXPERIMENTS")
public class Experiment {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min=2, max=50, message = " {experiment.desc}")
    @Column(name = "TASK")
    protected String task;

    @Basic
    @Column(name = "START_TIME")
    protected LocalDateTime startTime;

    @Basic
    @Column(name = "END_TIME")
    protected LocalDateTime endTime;

    @Column(name = "COMPLETE")
    private boolean complete;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "experiment", orphanRemoval = true)
    protected Collection<Measurement> measurements;

    @Basic
    @Column(name = "OWNER")
    private String owner;

    @Deprecated
    protected Experiment() {
        super();
    }

    public Experiment(int id, String task, LocalDateTime startTime, LocalDateTime endTime,  boolean complete, Collection<Measurement> measurements, String owner) {
        super();
        this.setId(id);
        this.setTask(task);
        this.setStartTime(startTime);
        this.setEndTime(endTime);
        this.setComplete(complete);
        this.setMeasurements(measurements);
        this.setOwner(owner);
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
        sb.append(", \n\t complete: ").append(complete);
        sb.append(", \n\t ");
        sb.append("Measurements [ ");
        getMeasurements().stream().forEach(
                m -> sb.append("\n\t\t").append(m));
        sb.append("\n\t ]");
        sb.append("\n  } ");

        return sb.toString();
    }


}
