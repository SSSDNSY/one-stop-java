
package fun.sssdnsy.common.util.orika;

import java.time.LocalDateTime;

import fun.sssdnsy.common.util.LocalDateTimeUtil;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

public class Object2LocalDateTimeConverter extends CustomConverter<Object, LocalDateTime> {
    public Object2LocalDateTimeConverter() {
    }

    public LocalDateTime convert(Object source, Type<? extends LocalDateTime> destinationType, MappingContext mappingContext) {
        if (source instanceof Number) {
            return LocalDateTimeUtil.timeMillis2LocalDateTime(((Number)source).longValue());
        } else {
            return source instanceof String ? LocalDateTimeUtil.timeMillis2LocalDateTime(Long.parseLong((String)source)) : null;
        }
    }

    public boolean canConvert(Type<?> sourceType, Type<?> destinationType) {
        return destinationType.getRawType().equals(LocalDateTime.class) && (Number.class.isAssignableFrom(sourceType.getRawType()) || sourceType.getRawType().equals(String.class) || sourceType.getRawType().equals(Object.class));
    }
}
