package com.homework.handler;

import com.homework.service.WeatherAnalytics;
import com.homework.service.dto.WeatherReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "weather-topic")
public class WeatherConsumer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final WeatherAnalytics weatherAnalytics;

    public WeatherConsumer(WeatherAnalytics weatherAnalytics) {
        this.weatherAnalytics = weatherAnalytics;
    }

    @KafkaHandler
    public void handle(WeatherReport weatherReport) {
        weatherAnalytics.addWeatherReport(weatherReport);
        logger.info("Received WeatherReport: {}", weatherReport);
        weatherAnalytics.printReport();
    }
}
