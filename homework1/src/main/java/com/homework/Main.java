package com.homework;


import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        try {
            // Читаем файл конфигурации
            Yaml yaml = new Yaml();
            Map<String, Object> config = yaml.load(new FileInputStream("src/main/resources/application.yml"));

            // Получаем список городов
            Map<String, Object> appConfig = (Map<String, Object>) config.get("app");
            @SuppressWarnings("unchecked")
            List<String> cities = (List<String>) appConfig.get("cities");

            System.out.println("Размер списка городов: " + cities.size());

            WeatherClient weatherClient = new WeatherClient();
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < cities.size(); j++) {
                    weatherClient.sendWeatherRequest(j);
                }
                TimeUnit.SECONDS.sleep(2);
            }
        } catch (Exception e) {
            System.out.println("Ошибка при чтении файла");
        }


    }
}


