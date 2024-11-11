
package fun.sssdnsy.common.util.orika;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class LocalDateTime2StringConverter extends BidirectionalConverter<LocalDateTime, String> {
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd[['T'HH][:mm][:ss][.SSS]]");

    public LocalDateTime2StringConverter() {
    }

    public String convertTo(LocalDateTime source, Type<String> destinationType, MappingContext mappingContext) {
        return source.format(this.dateTimeFormatter);
    }

    public LocalDateTime convertFrom(String source, Type<LocalDateTime> destinationType, MappingContext mappingContext) {
        return LocalDateTime.parse(source, this.dateTimeFormatter);
    }
}
