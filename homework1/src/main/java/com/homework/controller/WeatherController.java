package com.homework.controller;

import com.homework.service.WeatherProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private WeatherProducer weatherProducer;

    public WeatherController(WeatherProducer weatherProducer) {
        this.weatherProducer = weatherProducer;
    }

    @PostMapping
    public void getWeatherReport(@RequestBody int cityId) {
        weatherProducer.sendWeatherReport(cityId);
    }
}
