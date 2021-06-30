package duke.labs.jpa.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.math.BigInteger;
import java.time.Duration;

@Converter(autoApply = true)
public class DurationConverter implements AttributeConverter<Duration, BigInteger> {

    @Override
    public BigInteger convertToDatabaseColumn(Duration duration) {
        if(duration == null){
            return BigInteger.valueOf(0);
        }

        return BigInteger.valueOf(duration.getSeconds());
    }

    @Override
    public Duration convertToEntityAttribute(BigInteger b) {
        if(b == null){
            return Duration.ofSeconds(0);
        }
        return Duration.ofSeconds(b.longValue());
    }
}
