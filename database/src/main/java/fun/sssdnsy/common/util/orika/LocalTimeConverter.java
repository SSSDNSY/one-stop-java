
package fun.sssdnsy.common.util.orika;

import java.time.LocalTime;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class LocalTimeConverter extends BidirectionalConverter<LocalTime, LocalTime> {
    public LocalTimeConverter() {
    }

    public LocalTime convertTo(LocalTime source, Type<LocalTime> destinationType, MappingContext mappingContext) {
        return LocalTime.from(source);
    }

    public LocalTime convertFrom(LocalTime source, Type<LocalTime> destinationType, MappingContext mappingContext) {
        return LocalTime.from(source);
    }
}
