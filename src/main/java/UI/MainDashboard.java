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
    private JCheckBox MS06RevsSoftLimiterCheckBox;
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


    public MainDashboard(ArrayList<Sensor> sensors){
        super("SMI Dashboard");
        setContentPane(panel);

        speedTF.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                sensors.get(4).dataSource(getValueFromRider("speed"));
                speedometerTF.setText(String.valueOf(sensors.get(4).getValue()));
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                sensors.get(4).dataSource(getValueFromRider("speed"));
                speedometerTF.setText(String.valueOf(sensors.get(4).getValue()));
            }

            public void changedUpdate(DocumentEvent e) {
                sensors.get(4).dataSource(getValueFromRider("speed"));
                speedometerTF.setText(String.valueOf(sensors.get(4).getValue()));
            }
        });

        coolantTF.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                sensors.get(0).dataSource(getValueFromRider("temp"));
                coolantSensorTF.setText(String.valueOf(sensors.get(0).getValue()));
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                sensors.get(0).dataSource(getValueFromRider("temp"));
                coolantSensorTF.setText(String.valueOf(sensors.get(0).getValue()));
            }

            public void changedUpdate(DocumentEvent e) {
                sensors.get(0).dataSource(getValueFromRider("temp"));
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
            sensors.get(1).dataSource(getValueFromRider("gear"));
            int gear = sensors.get(1).getValue();
            if (gear == 0)
                gearTF.setText("N");
            else
                gearTF.setText(String.valueOf(gear));
        });

        sliderRPMs.addChangeListener(changeEvent -> {
            sensors.get(5).dataSource(getValueFromRider("rpm"));
            labelRPMs.setText(String.valueOf(sensors.get(5).getValue()));
            tachometerTF.setText(String.valueOf(sensors.get(5).getValue()));
        });

        sliderThrottle.addChangeListener(changeEvent -> {
            sensors.get(6).dataSource(getValueFromRider("throttle"));
            labelThrottle.setText(String.valueOf(sensors.get(6).getValue()));
            throttleTF.setText(String.valueOf(sensors.get(6).getValue()));
        });

        sliderLean.addChangeListener(changeEvent -> {
            sensors.get(2).dataSource(getValueFromRider("leanx"));
            labelLean.setText(String.valueOf(sensors.get(2).getValue()));
            gyroXTF.setText(String.valueOf(sensors.get(2).getValue()));
        });

        sliderWheelie.addChangeListener(changeEvent -> {
            sensors.get(3).dataSource(getValueFromRider("leany"));
            labelWheelie.setText(String.valueOf(sensors.get(3).getValue()));
            gyroYTF.setText(String.valueOf(sensors.get(3).getValue()));
        });
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
