package com.suvham.ui;

import com.suvham.model.Weather;
import com.suvham.service.WeatherService;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class WeatherFrame extends JFrame {

    private JTextField txtCity;
    private JButton btnSearch;

    private JLabel lblTemperature;
    private JLabel lblHumidity;
    private JLabel lblWindSpeed;
    private JLabel lblCondition;

    private WeatherService service = new WeatherService();

    public WeatherFrame() {

        setTitle("Weather App");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        getContentPane().setBackground(new Color(240, 248, 255));

        // Heading
        JLabel lblHeading = new JLabel("Weather App");
        lblHeading.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblHeading.setBounds(145, 25, 250, 40);
        add(lblHeading);

        // City Label
        JLabel lblCity = new JLabel("City");
        lblCity.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblCity.setBounds(70, 90, 80, 35);
        add(lblCity);

        // TextField
        txtCity = new JTextField();
        txtCity.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtCity.setBounds(150, 90, 220, 35);
        add(txtCity);

        // Search Button
        btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnSearch.setBounds(175, 145, 120, 35);
        add(btnSearch);

        // Temperature
        lblTemperature = new JLabel("Temperature : --");
        lblTemperature.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTemperature.setBounds(70, 230, 350, 35);
        add(lblTemperature);

        // Humidity
        lblHumidity = new JLabel("Humidity : --");
        lblHumidity.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblHumidity.setBounds(70, 280, 350, 35);
        add(lblHumidity);

        // Wind Speed
        lblWindSpeed = new JLabel("Wind Speed : --");
        lblWindSpeed.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblWindSpeed.setBounds(70, 330, 350, 35);
        add(lblWindSpeed);

        // Condition
        lblCondition = new JLabel("Condition : --");
        lblCondition.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblCondition.setBounds(70, 380, 350, 35);
        add(lblCondition);

        // Search Button Action
        btnSearch.addActionListener(e -> searchWeather());

        // Enter Key Action
        txtCity.addActionListener(e -> searchWeather());

        setVisible(true);
    }

    private void searchWeather() {

        String city = txtCity.getText().trim();

        if (city.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter city name.");
            return;
        }

        btnSearch.setEnabled(false);
        btnSearch.setText("Loading...");

        Weather weather = service.getWeather(city);

        btnSearch.setEnabled(true);
        btnSearch.setText("Search");

        if (weather != null) {

            lblTemperature.setText("Temperature : " + weather.getTemperature() + " °C");
            lblHumidity.setText("Humidity : " + weather.getHumidity() + " %");
            lblWindSpeed.setText("Wind Speed : " + weather.getWindSpeed() + " m/s");
            lblCondition.setText("Condition : " + weather.getCondition());

        } else {

            JOptionPane.showMessageDialog(this, "Weather data not found!");

        }
    }
}