package logger;

import enviroment.Sensor;
import enviroment.Tachometer;


public class LoggerFactory {
    public static Sensor getLoggerTachometer(Object tachometer){
        return (Sensor) java.lang.reflect.Proxy.newProxyInstance(tachometer.getClass().getClassLoader(),
                new Class[] {Sensor.class},
                new LoggerTachometer((Tachometer) tachometer));
    }
}
