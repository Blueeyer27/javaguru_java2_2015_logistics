package lv.javaguru.java2.domain;

import javax.persistence.*;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", columnDefinition = "int(11)")
    private long vehicleId;

    @Column(name = "user_id", columnDefinition = "int(11)")
    private long userId;

    @Column(name = "name", columnDefinition = "char(50)")
    private String name;

    @Column(name = "type", columnDefinition = "char(30)")
    private String type;

    @Column(name = "plate_number", columnDefinition = "char(30)")
    private String plateNumber;

    @Column(name = "trailer_number", columnDefinition = "char(30)")
    private String trailerNumber;

    @Column(name = "capacity", columnDefinition = "float(8,2)")
    private Double capacity;

    @Column(name = "status", columnDefinition = "char(30)")
    private String status;

    public Vehicle(){

    }

    public Vehicle(long userId, String name, String type, String plateNumber, String trailerNumber, Double capacity, String status) {
        this.userId = userId;
        this.name = name;
        this.type = type;
        this.plateNumber = plateNumber;
        this.trailerNumber = trailerNumber;
        this.capacity = capacity;
        this.status = status;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getplateNumber() {
        return plateNumber;
    }

    public void setplateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String gettrailerNumber() {
        return trailerNumber;
    }

    public void settrailerNumber(String trailerNumber) {
        this.trailerNumber = trailerNumber;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
