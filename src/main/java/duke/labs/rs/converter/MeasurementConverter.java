package duke.labs.rs.converter;

import duke.labs.rs.data.Measurement;

import java.util.function.Function;

public class MeasurementConverter extends Converter<Measurement, duke.labs.jpa.entities.Measurement> {
    public MeasurementConverter(Function<Measurement, duke.labs.jpa.entities.Measurement> fromDto, Function<duke.labs.jpa.entities.Measurement, Measurement> fromEntity) {
        super(fromDto, fromEntity);
    }

    public MeasurementConverter() {
        super(MeasurementConverter::convertToEntity, MeasurementConverter::convertToDto);
    }

    private static Measurement convertToDto(duke.labs.jpa.entities.Measurement m) {
        Measurement measurement = Measurement.of(m.getId(), m.getValue(), m.getUnit().value(), m.getTime());
        return measurement;
    }

    private static duke.labs.jpa.entities.Measurement convertToEntity(Measurement dto) {
        duke.labs.jpa.entities.Measurement measurement = new duke.labs.jpa.entities.Measurement(dto.getId(), dto.getValue(), dto.getUnit().getValue().toUpperCase(), dto.getTime());
        return measurement;
    }


}
