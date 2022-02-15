package lang.features.jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.ManagementFactory;

/**
 * @author pengzh
 * @desc
 * @class SimpleRmiAgent
 * @since 2022-02-14
 */
public class SimpleRmiAgent {

    private MBeanServer mbs = null;

    public SimpleRmiAgent() {

        //Get the platform MBeanServer
        mbs = ManagementFactory.getPlatformMBeanServer();

        //Unique identification of MBeans
        Hello helloBean = new Hello();
        ObjectName helloName = null;

        try {
            //Uniquely identify the MBeans and register them with the MBeanServer
            helloName = new ObjectName("SimpleAgent:name=hellothere");
            mbs.registerMBean(helloBean, helloName);


            //   Create an RMI connector and start it

            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://10.135.24.90:9999/server");

            JMXConnectorServer cs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);
            cs.start();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String argv[]) {
        SimpleRmiAgent agent = new SimpleRmiAgent();
        System.out.println("SimpleRmiAgent is running...");
    }
}
