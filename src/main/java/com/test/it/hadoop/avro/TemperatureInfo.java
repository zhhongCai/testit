package com.test.it.hadoop.avro;

/**
 * Created by caizh on 16-5-16.
 */
public class TemperatureInfo {
    private Integer year;
    private Integer temperature;
    private String stationId;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    @Override
    public String toString() {
        return "TemperatureInfo{" +
                "year=" + year +
                ", temperature=" + temperature +
                ", stationId='" + stationId + '\'' +
                '}';
    }
}
