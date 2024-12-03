
package fun.sssdnsy.common.util.orika;

import fun.sssdnsy.common.util.DateFormatUtil;
import java.time.LocalDate;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class LocalDate2TimeMillisConverter extends BidirectionalConverter<LocalDate, Long> {
    public LocalDate2TimeMillisConverter() {
    }

    public Long convertTo(LocalDate source, Type<Long> destinationType, MappingContext mappingContext) {
        return DateFormatUtil.localDate2TimeMillis(source);
    }

    public LocalDate convertFrom(Long source, Type<LocalDate> destinationType, MappingContext mappingContext) {
        return DateFormatUtil.timeMillis2LocalDate(source);
    }
}
