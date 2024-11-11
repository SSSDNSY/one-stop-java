
package fun.sssdnsy.common.util.orika;

import fun.sssdnsy.common.util.LocalDateTimeUtil;
import java.time.LocalDateTime;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class LocalDateTime2TimeMillisConverter extends BidirectionalConverter<LocalDateTime, Long> {
    public LocalDateTime2TimeMillisConverter() {
    }

    public Long convertTo(LocalDateTime source, Type<Long> destinationType, MappingContext mappingContext) {
        return LocalDateTimeUtil.localDateTime2TimeMillis(source);
    }

    public LocalDateTime convertFrom(Long source, Type<LocalDateTime> destinationType, MappingContext mappingContext) {
        return LocalDateTimeUtil.timeMillis2LocalDateTime(source);
    }
}
