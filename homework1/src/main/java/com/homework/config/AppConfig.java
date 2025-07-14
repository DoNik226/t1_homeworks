package com.homework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppConfig {
    private List<String> states;
    private List<String> cities;

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

    // Добавляем геттеры для размеров списков
    public int getStatesSize() {
        return states != null ? states.size() : 0;
    }

    public int getCitiesSize() {
        return cities != null ? cities.size() : 0;
    }
}