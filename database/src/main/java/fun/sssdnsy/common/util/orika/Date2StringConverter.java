package fun.sssdnsy.common.util.orika;

import fun.sssdnsy.common.util.DateFormatUtil;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Date;

public class Date2StringConverter extends BidirectionalConverter<Date, String> {
    private final Logger LOG = LoggerFactory.getLogger(Date2StringConverter.class);

    public Date2StringConverter() {
    }

    public String convertTo(Date source, Type<String> destinationType, MappingContext mappingContext) {
        return DateFormatUtil.DEFAULT_ON_SECOND_FORMAT.format(source);
    }

    public Date convertFrom(String source, Type<Date> destinationType, MappingContext mappingContext) {
        try {
            return DateFormatUtil.ISO_FORMAT.parse(source);
        } catch (ParseException var5) {
            ParseException e = var5;
            this.LOG.error(e.getMessage(), e);
            return null;
        }
    }
}
