package net.serial;

import io.netty.handler.codec.marshalling.*;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

/**
*@Description: jboss marshalling 序列化包
*@Author: pengzh
*@date: 2019/10/20
*/
public class MarshallingCodeFactory {

    public static MarshallingDecoder buildMarshallingDecoder(){
        final MarshallerFactory marshallerFactory  = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration marshallingConfiguration  = new MarshallingConfiguration();
        marshallingConfiguration.setVersion(5);
        UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory,marshallingConfiguration);
        MarshallingDecoder decoder = new MarshallingDecoder(provider,1024*1024*1);
        return decoder;
    }

    public static MarshallingEncoder buildMarshallingEncoder(){
        final MarshallerFactory marshallerFactory  = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration marshallingConfiguration  = new MarshallingConfiguration();
        marshallingConfiguration.setVersion(5);
        MarshallerProvider provider = new DefaultMarshallerProvider(marshallerFactory,marshallingConfiguration);
        MarshallingEncoder encoder = new MarshallingEncoder(provider);
        return encoder;

    }
}
