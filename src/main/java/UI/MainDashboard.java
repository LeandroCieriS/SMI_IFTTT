package UI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JPanel panel;
    private JComboBox<String> gear;
    private JLabel labelThrottle;
    private JLabel labelLean;
    private JLabel labelWheelie;
    private JTextField gearTF;
    private JTextField gyroXTF;
    private JTextField gyroYTF;
    private JTextField speedTF;
    private JTextField rpmTF;
    private JTextField coolantTF;
    private JTextField speedometerTF;
    private JTextField tachometerTF;
    private JTextField throttleTF;

    public MainDashboard(){
        super("SMI Dashboard");
        setContentPane(panel);

        ButtonGroup bg = new ButtonGroup();
        bg.add(beginnerRadioButton);
        bg.add(intermediateRadioButton);
        bg.add(experiencedRadioButton);

        speedTF.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                speedometerTF.setText(speedTF.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                speedometerTF.setText(speedTF.getText());
            }

            public void changedUpdate(DocumentEvent e) {
                speedometerTF.setText(speedTF.getText());
            }
        });

        rpmTF.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                tachometerTF.setText(rpmTF.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                tachometerTF.setText(rpmTF.getText());
            }

            public void changedUpdate(DocumentEvent e) {
                tachometerTF.setText(rpmTF.getText());
            }
        });

        coolantTF.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                coolantSensorTF.setText(coolantTF.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                coolantSensorTF.setText(coolantTF.getText());
            }

            public void changedUpdate(DocumentEvent e) {
                coolantSensorTF.setText(coolantTF.getText());
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
                if(Integer.parseInt(rpmTF.getText()) > Integer.parseInt(ecuRPMs.getText()))
                    rpmTF.setText(ecuRPMs.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
            }

            public void changedUpdate(DocumentEvent e) {
            }
        });

        gear.addActionListener (e -> gearTF.setText(gear.getSelectedItem().toString()));

        sliderThrottle.addChangeListener(changeEvent -> {
            labelThrottle.setText(String.valueOf(sliderThrottle.getValue()));
            throttleTF.setText(String.valueOf(sliderThrottle.getValue()));
        });

        sliderLean.addChangeListener(changeEvent -> {
            labelLean.setText(String.valueOf(sliderLean.getValue()));
            gyroXTF.setText(String.valueOf(sliderLean.getValue()));

        });

        sliderWheelie.addChangeListener(changeEvent -> {
            labelWheelie.setText(String.valueOf(sliderWheelie.getValue()));
            gyroYTF.setText(String.valueOf(sliderWheelie.getValue()));
        });
    }
}
