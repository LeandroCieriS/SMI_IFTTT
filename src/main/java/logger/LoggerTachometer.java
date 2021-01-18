package logger;

import enviroment.Tachometer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoggerTachometer implements InvocationHandler {
    private Tachometer tachometer;

    public LoggerTachometer(Tachometer tachometer) {
        this.tachometer = tachometer;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        int dataSensor = (int) method.invoke(tachometer, objects);

        System.out.println("tachometer called [RPMs = " + dataSensor + "]");

        return dataSensor;
    }
}
