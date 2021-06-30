package duke.labs.rs.data;

import javax.json.bind.annotation.JsonbPropertyOrder;
import java.math.BigDecimal;
import java.time.Duration;


@JsonbPropertyOrder({ "id","value", "unit", "time" })
public class Measurement {

    private int id;
    private BigDecimal value;
    private UnitOfMeasure unit;
    protected Duration time;

    public static Measurement of(int id, BigDecimal value, String unit, Duration time) {
        Measurement measurement = new Measurement();
        measurement.setId(id);
        measurement.setValue(value);
        measurement.setUnit(UnitOfMeasure.valueOf(unit));
        measurement.setTime(time);

        return measurement;
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

    public void setUnit(UnitOfMeasure unit) {
        this.unit = unit;
    }

    public Duration getTime() {
        return time;
    }

    public void setTime(Duration time) {
        this.time = time;
    }


    @Override
    public String toString(){
        return "{ id: "+ id+", value: " + (value!=null ? value.floatValue() : null) + ", unit: " + unit + ", time: "+ time + " }";
    }


}
