
package fun.sssdnsy.common.util.orika;

import java.time.LocalDateTime;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class LocalDateTimeConverter extends BidirectionalConverter<LocalDateTime, LocalDateTime> {
    public LocalDateTimeConverter() {
    }

    public LocalDateTime convertTo(LocalDateTime source, Type<LocalDateTime> destinationType, MappingContext mappingContext) {
        return LocalDateTime.from(source);
    }

    public LocalDateTime convertFrom(LocalDateTime source, Type<LocalDateTime> destinationType, MappingContext mappingContext) {
        return LocalDateTime.from(source);
    }
}
