
package fun.sssdnsy.common.util.orika;

import fun.sssdnsy.common.util.DateFormatUtil;
import java.time.LocalDateTime;
import java.util.Date;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class LocalDateTime2DateConverter extends BidirectionalConverter<LocalDateTime, Date> {
    public LocalDateTime2DateConverter() {
    }

    public Date convertTo(LocalDateTime source, Type<Date> destinationType, MappingContext mappingContext) {
        return DateFormatUtil.localDateTime2Date(source);
    }

    public LocalDateTime convertFrom(Date source, Type<LocalDateTime> destinationType, MappingContext mappingContext) {
        return DateFormatUtil.date2LocalDateTime(source);
    }
}
