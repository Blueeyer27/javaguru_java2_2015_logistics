package lv.javaguru.java2.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", columnDefinition = "int(11)")
    private long vehicleId;

    @Transient
    //@Column(name = "user_id", columnDefinition = "int(11)")
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


    @ManyToOne (fetch=FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany (fetch=FetchType.EAGER, mappedBy = "vehicle")
    public List<Agreement> agreementList = new ArrayList<Agreement>();



    public Vehicle(){

    }


    public Vehicle(User user, String name, String type, String plateNumber, String trailerNumber, Double capacity, String status) {
        this.user = user;
        this.name = name;

        this.type = type;
        this.plateNumber = plateNumber;
        this.trailerNumber = trailerNumber;
        this.capacity = capacity;
        this.status = status;
    }

    public List<Agreement> getAgreementList() {
        return agreementList;
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


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



}
