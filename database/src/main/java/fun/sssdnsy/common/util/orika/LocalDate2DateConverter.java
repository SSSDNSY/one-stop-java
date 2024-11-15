
package fun.sssdnsy.common.util.orika;

import java.time.LocalDate;
import java.util.Date;

import fun.sssdnsy.common.util.LocalDateTimeUtil;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class LocalDate2DateConverter extends BidirectionalConverter<LocalDate, Date> {
    public LocalDate2DateConverter() {
    }

    public Date convertTo(LocalDate source, Type<Date> destinationType, MappingContext mappingContext) {
        return LocalDateTimeUtil.localDate2Date(source);
    }

    public LocalDate convertFrom(Date source, Type<LocalDate> destinationType, MappingContext mappingContext) {
        return LocalDateTimeUtil.date2LocalDate(source);
    }
}