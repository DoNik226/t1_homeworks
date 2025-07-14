package com.homework.service;

import com.homework.service.dto.WeatherReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "app")
public class WeatherProducer {
    private KafkaTemplate<String, WeatherReport> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final Random random = new Random();
    private List<String> states;
    private List<String> cities;


    public WeatherProducer(KafkaTemplate<String, WeatherReport> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendWeatherReport(int cityId) {
        WeatherReport weatherReport = creatWeatherReport(cityId);

        try {
            // Используем асинхронную отправку
            kafkaTemplate.send("weather-topic", weatherReport);
        } catch (Exception e) {
            LOGGER.error("Ошибка при отправке сообщения", e);
        }
    }

    private WeatherReport creatWeatherReport(int cityId) {
        int stateId = random.nextInt(states.size());
        double temperature = random.nextDouble(-35, 35);
        return new WeatherReport(temperature, states.get(stateId), cities.get(cityId), LocalDateTime.now());
    }

    public List<String> getStates() {
        return states;
    }

    public void setStates(List<String> states) {
        this.states = states;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }
}
