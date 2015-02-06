package lv.javaguru.java2.domain;

import java.util.Date;

public class Cargo {

    private long cargoId;
    private long userId;
    private String vehicleType;
    private Double weight;
    private String loadAddress;
    private String unloadAddress;
    private Date loadDate;
    private Date unloaDate;
    private String status;

    public long getCargoId() {
        return cargoId;
    }

    public void setCargoId(long cargoId) {
        this.cargoId = cargoId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getLoadAddress() {
        return loadAddress;
    }

    public void setLoadAddress(String loadAddress) {
        this.loadAddress = loadAddress;
    }

    public String getUnloadAddress() {
        return unloadAddress;
    }

    public void setUnloadAddress(String unloadAddress) {
        this.unloadAddress = unloadAddress;
    }

    public Date getLoadDate() {
        return loadDate;
    }

    public void setLoadDate(Date loadDate) {
        this.loadDate = loadDate;
    }

    public Date getUnloaDate() {
        return unloaDate;
    }

    public void setUnloaDate(Date unloaDate) {
        this.unloaDate = unloaDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}