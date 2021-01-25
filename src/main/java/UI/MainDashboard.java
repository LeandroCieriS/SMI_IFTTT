package UI;

import enviroment.Sensor;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.ArrayList;
import java.util.Objects;
import static java.lang.Math.abs;

public class MainDashboard extends JFrame{
    private JTextField coolantSensorTF;
    private JRadioButton beginnerRadioButton;
    private JRadioButton intermediateRadioButton;
    private JRadioButton experiencedRadioButton;
    private JCheckBox MS01LaunchControlCheckBox;
    private JCheckBox MS02WheelieControlCheckBox;
    private JCheckBox MS03LeanInformationCheckBox;
    private JCheckBox MS04CornerExitControlCheckBox;
    private JCheckBox MS05ColdEngineProtectionCheckBox;
    private JTextField displayTF;
    private JTextField ecuThrottle;
    private JTextField ecuRPMs;
    private JProgressBar fallBar;
    private JSlider sliderThrottle;
    private JSlider sliderLean;
    private JSlider sliderWheelie;
    private JSlider sliderRPMs;
    private JPanel panel;
    private JComboBox<String> gear;
    private JLabel labelThrottle;
    private JLabel labelLean;
    private JLabel labelWheelie;
    private JTextField gearTF;
    private JTextField gyroXTF;
    private JTextField gyroYTF;
    private JTextField speedTF;
    private JTextField coolantTF;
    private JTextField speedometerTF;
    private JTextField tachometerTF;
    private JTextField throttleTF;
    private JLabel labelRPMs;
    private final ArrayList<Sensor> sensors;

    public MainDashboard(ArrayList<Sensor> sensors){
        super("SMI Dashboard");
        setContentPane(panel);
        this.sensors = sensors;

        speedTF.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                connectSensor(4,"speed");
                speedometerTF.setText(String.valueOf(sensors.get(4).getValue()));
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                connectSensor(4,"speed");
                speedometerTF.setText(String.valueOf(sensors.get(4).getValue()));
            }

            public void changedUpdate(DocumentEvent e) {
                connectSensor(4,"speed");
                speedometerTF.setText(String.valueOf(sensors.get(4).getValue()));
            }
        });

        coolantTF.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                connectSensor(0,"temp");
                coolantSensorTF.setText(String.valueOf(sensors.get(0).getValue()));
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                connectSensor(0,"temp");
                coolantSensorTF.setText(String.valueOf(sensors.get(0).getValue()));
            }

            public void changedUpdate(DocumentEvent e) {
                connectSensor(0,"temp");
                coolantSensorTF.setText(String.valueOf(sensors.get(0).getValue()));
            }
        });

        ecuThrottle.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                sliderThrottle.setMaximum(Integer.parseInt(ecuThrottle.getText()));
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
            }

            public void changedUpdate(DocumentEvent e) {
            }
        });

        ecuRPMs.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                sliderRPMs.setMaximum(Integer.parseInt(ecuRPMs.getText()));
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
            }

            public void changedUpdate(DocumentEvent e) {
            }
        });

        gear.addActionListener (e -> {
            connectSensor(1,"gear");
            int gear = sensors.get(1).getValue();
            if (gear == 0)
                gearTF.setText("N");
            else
                gearTF.setText(String.valueOf(gear));
        });

        sliderRPMs.addChangeListener(changeEvent -> {
            connectSensor(5,"rpm");
            labelRPMs.setText(getStringValueFromSensor(5));
            tachometerTF.setText(getStringValueFromSensor(5));
        });

        sliderThrottle.addChangeListener(changeEvent -> {
            connectSensor(6,"throttle");
            labelThrottle.setText(getStringValueFromSensor(6));
            throttleTF.setText(getStringValueFromSensor(6));
        });

        sliderLean.addChangeListener(changeEvent -> {
            connectSensor(2,"leanx");
            labelLean.setText(getStringValueFromSensor(2));
            gyroXTF.setText(getStringValueFromSensor(2));
        });

        sliderWheelie.addChangeListener(changeEvent -> {
            connectSensor(3,"leany");
            labelWheelie.setText(getStringValueFromSensor(3));
            gyroYTF.setText(getStringValueFromSensor(3));
        });
    }

    private String getStringValueFromSensor(int sensor){
        return String.valueOf(sensors.get(sensor).getValue());
    }

    private void connectSensor(int sensorID, String valueName) {
        sensors.get(sensorID).dataSource(getValueFromRider(valueName));
    }

    public int getValueFromRider(String value){
        switch (value) {

            case "speed":
                int speed = 0;
                if (!speedTF.getText().equals(""))
                    speed = Integer.parseInt(speedTF.getText());
                return speed;

            case "rpm":
                return sliderRPMs.getValue();

            case "leanx":
                return abs(sliderLean.getValue());

            case "leany":
                return sliderWheelie.getValue();

            case "temp":
                int temp = 0;
                if (!coolantTF.getText().equals(""))
                    temp = Integer.parseInt(coolantTF.getText());
                return temp;

            case "gear":
                if (Objects.requireNonNull(gear.getSelectedItem()).toString().equals("N"))
                    return 0;
                else
                    return Integer.parseInt(gear.getSelectedItem().toString());

            case "throttle":
                return sliderThrottle.getValue();
        }
        return -1;
    }

    public void setValueFromActuator(String actuator, int value){
        switch (actuator){
            case "Display":
                displayTF.setText(String.valueOf(value));
                break;
            case "ECUManagerLimitRPM":
                ecuRPMs.setText(String.valueOf(value));
                break;
            case "ECUManagerCloseThrottle":
                ecuThrottle.setText(String.valueOf(value));
                break;
            case "FallAlert":
                fallBar.setValue(value);
                break;
        }
    }
}
