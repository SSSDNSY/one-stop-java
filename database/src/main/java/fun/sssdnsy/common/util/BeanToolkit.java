package fun.sssdnsy.common.util;

import fun.sssdnsy.common.util.orika.*;
import ma.glasnost.orika.Converter;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class BeanToolkit {
    private static final Logger LOG = LoggerFactory.getLogger(BeanToolkit.class);
    private MapperFactory mapperFactory = (new DefaultMapperFactory.Builder()).build();

    public static BeanToolkit instance() {
        return BeanToolkit.BeanToolkitHolder.INSTANCE;
    }

    public static BeanToolkit newInstance() {
        return BeanToolkit.BeanToolkitHolder.INSTANCE;
    }

    public BeanToolkit registerConverter(String id, Converter converter) {
        this.mapperFactory.getConverterFactory().registerConverter(id, converter);
        return this;
    }

    public BeanToolkit registerConverter(Converter converter) {
        this.mapperFactory.getConverterFactory().registerConverter(converter);
        return this;
    }

    public <S, D> D copy(S source, Class<D> destinationClass) {
        return this.mapperFactory.getMapperFacade().map(source, destinationClass);
    }

    public <S, D> D copy(S source, Type<S> from, Type<D> to) {
        return this.mapperFactory.getMapperFacade().map(source, from, to);
    }

/*    public <S, D> D parseObject(S source, Class<S> collectionClazz, Class<D> elementClazzes) {
        Type<S> fromType = (new TypeBuilder<S>(this) {
        }).build();
        Type<D> toType = (new TypeBuilder<D>(this) {
        }).build();
        return this.mapperFactory.getMapperFacade().map(source, fromType, toType);
    }*/

    public <S, D> List<D> copyList(Iterable<S> sourceList, Class<D> destinationClass) {
        return this.mapperFactory.getMapperFacade().mapAsList(sourceList, destinationClass);
    }

    public MapperFactory getMapperFactory() {
        return this.mapperFactory;
    }

    private BeanToolkit(boolean defaultRegisterConverter) {
        if (defaultRegisterConverter) {
            this.registerConverter(new LocalDateTimeConverter());
            this.registerConverter(new LocalDateTime2TimeMillisConverter());
            this.registerConverter(new LocalDateTime2StringConverter());
            this.registerConverter(new Object2LocalDateTimeConverter());
            this.registerConverter(new LocalDateTime2DateConverter());
            this.registerConverter(new LocalDateConverter());
            this.registerConverter(new LocalDate2TimeMillisConverter());
            this.registerConverter(new LocalDate2DateConverter());
            this.registerConverter(new LocalTimeConverter());
        }

    }

    public <S, D> D reflectCopy(S source, D destination) {
        BeanUtils.copyProperties(source, destination);
        return destination;
    }

    private static class BeanToolkitHolder {
        private static final BeanToolkit INSTANCE = new BeanToolkit(true);

        private BeanToolkitHolder() {
        }
    }

}
