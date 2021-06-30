package duke.labs.jpa.entities;

import duke.labs.jpa.converter.DurationConverter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Duration;

@Access(value = AccessType.FIELD)
@Entity(name = "Measurement")
@Table(name = "MEASUREMENTS")
public class Measurement {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "EXP_ID", nullable = false, updatable=false)
    private Experiment experiment;

    @Basic
    @Column(name = "VALUE")
    protected BigDecimal value;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "UNIT")
    protected UnitOfMeasure unit;

    @Column(name = "TIME", columnDefinition = "BIGINT" )
    @Convert(converter = DurationConverter.class)
    protected Duration time;

    @Deprecated
    protected Measurement() {
        super();
    }

    public Measurement(int id, BigDecimal value, String unit, Duration time) {
        super();
        this.setId(id);
        this.setValue(value);
        this.setUnit(UnitOfMeasure.fromValue(unit));
        this.setTime(time);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public UnitOfMeasure getUnit() {
        if (unit == null) {
            return UnitOfMeasure.S;
        } else {
            return unit;
        }
    }

    public void setUnit(UnitOfMeasure value) {
        this.unit = value;
    }

    public Duration getTime() {
        return time;
    }

    public void setTime(Duration time) {
        this.time = time;
    }

    public Experiment getExperiment() {
        return experiment;
    }

    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    @Override
    public String toString(){
        return "{ id: " + id + ", value: " + (value!=null ? value.floatValue(): null )+ ", unit: " + unit + ", time: "+ time + " }";
    }

}
