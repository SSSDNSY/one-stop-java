
package fun.sssdnsy.common.util.orika;

import java.time.LocalDate;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class LocalDateConverter extends BidirectionalConverter<LocalDate, LocalDate> {
    public LocalDateConverter() {
    }

    public LocalDate convertTo(LocalDate source, Type<LocalDate> destinationType, MappingContext mappingContext) {
        return LocalDate.from(source);
    }

    public LocalDate convertFrom(LocalDate source, Type<LocalDate> destinationType, MappingContext mappingContext) {
        return LocalDate.from(source);
    }
}
