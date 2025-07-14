package com.homework.service;

import com.homework.service.dto.WeatherReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeatherAnalytics {
    private Map<String, List<WeatherReport>> weatherReports = new HashMap<>();
    private int reportsCount = 0;
    private String report;

    public  WeatherAnalytics() {
    }

    public void addWeatherReport(WeatherReport weatherReport) {
        if (weatherReports.containsKey(weatherReport.getCity())) {
            weatherReports.get(weatherReport.getCity()).add(weatherReport);
        } else {
            List<WeatherReport> list = new ArrayList<>();
            list.add(weatherReport);
            weatherReports.put(weatherReport.getCity(), list);
        }
        reportsCount++;
        if (reportsCount == weatherReports.size()*7) {
            report = makeReport();
        }
    }

    private String makeReport() {
        double max = -40;
        String cityMaxTemp = "";
        double min = 40;
        String cityMinTemp = "";
        int countSunnyDaysMagadan = 0;
        int countRainyDaysMoscow = 0;
        for  (Map.Entry<String, List<WeatherReport>> entry : weatherReports.entrySet()) {
            for (WeatherReport report : entry.getValue()) {
                if (report.getTemperature() > max) {
                    max = report.getTemperature();
                    cityMaxTemp = entry.getKey();
                } else if (report.getTemperature() < min) {
                    min = report.getTemperature();
                    cityMinTemp = entry.getKey();
                }
            }
            if (entry.getValue().get(0).getCity().equals("Magadan")) {
                for (WeatherReport report : entry.getValue()) {
                    if (report.getState().equals("sunny")) {
                        countSunnyDaysMagadan++;
                    }
                }
            }
            if (entry.getValue().get(0).getCity().equals("Moscow")) {
                for (WeatherReport report : entry.getValue()) {
                    if (report.getState().equals("rainy")) {
                        countRainyDaysMoscow++;
                    }
                }
            }
        }
        StringBuilder report = new StringBuilder();
        report.append("Отчет по погоде за последнюю неделю:\n")
                .append("Максимальная температура на этой неделе ")
                .append(max)
                .append(" наблюдалась в городе ")
                .append(cityMaxTemp)
                .append("\nМинимальная температура на этой неделе ")
                .append(min)
                .append(" наблюдалась в городе ")
                .append(cityMinTemp)
                .append("\nКоличество солнечных дней в Магадане: ")
                .append(countSunnyDaysMagadan)
                .append("\nКоличество дождливых дней в Москве: ")
                .append(countRainyDaysMoscow);
        return report.toString();
    }

    public void printReport() {
        if (report != null) {
            System.out.println(report);
            clearWeatherReports();
        }
    }

    private void clearWeatherReports() {
        for (String key : weatherReports.keySet()) {
            weatherReports.put(key, new ArrayList<>());
        }
        reportsCount=0;
        report = null;
    }
}
