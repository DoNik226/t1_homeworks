package com.homework;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class WeatherClient {

    private RestTemplate restTemplate = new RestTemplate();

    public void sendWeatherRequest(int cityId) {
        String url = "http://localhost:8080/weather";

        try {
            ResponseEntity<Void> response = restTemplate.postForEntity(
                    url,
                    cityId,
                    Void.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Запрос успешно отправлен");
            } else {
                System.err.println("Ошибка при отправке запроса: " + response.getStatusCode());
            }
        } catch (Exception e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
        }
    }
}