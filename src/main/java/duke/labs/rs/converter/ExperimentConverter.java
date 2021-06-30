package duke.labs.rs.converter;


import duke.labs.jpa.entities.Measurement;
import duke.labs.rs.data.Experiment;

import java.util.Collection;
import java.util.function.Function;

public class ExperimentConverter extends Converter<Experiment, duke.labs.jpa.entities.Experiment> {

    public ExperimentConverter(Function<Experiment, duke.labs.jpa.entities.Experiment> fromDto, Function<duke.labs.jpa.entities.Experiment, Experiment> fromEntity) {
        super(fromDto, fromEntity);
    }

    public ExperimentConverter() {
        super(ExperimentConverter::convertToEntity, ExperimentConverter::convertToDto);
    }

    private static Experiment convertToDto(duke.labs.jpa.entities.Experiment e) {
        MeasurementConverter c = new MeasurementConverter();
        Collection<duke.labs.rs.data.Measurement> measurements = c.createFromEntities(e.getMeasurements());
        Experiment experiment = Experiment.of(e.getId(), e.getTask(), e.getStartTime(), e.getEndTime(), e.isComplete(), e.getOwner(), measurements);
        return experiment;
    }

    private static duke.labs.jpa.entities.Experiment convertToEntity(Experiment dto) {
        MeasurementConverter c = new MeasurementConverter();
        Collection<Measurement> measurementCollection = c.createFromDtos(dto.getMeasurements());
        duke.labs.jpa.entities.Experiment experiment = new duke.labs.jpa.entities.Experiment(dto.getId(), dto.getTask(), dto.getStartTime(), dto.getEndTime(), dto.isComplete(), measurementCollection, dto.getOwner());
        return experiment;
    }

}
