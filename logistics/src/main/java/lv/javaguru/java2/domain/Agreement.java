package lv.javaguru.java2.domain;

import javax.persistence.*;

@Entity
@Table(name = "agreement")
public class Agreement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", columnDefinition = "int(11)")
    private long agreementId;

    @Transient
    //@Column(name = "cargo_id", columnDefinition = "int(11)")
    private long cargoId;

    @Transient
    //@Column(name = "vehicle_id", columnDefinition = "int(11)")
    private long vehicleId;

    @Column(name = "status", columnDefinition = "char(30)")
    private String status;


    @ManyToOne (fetch=FetchType.EAGER)
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;


    @ManyToOne (fetch=FetchType.EAGER)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;


    public Agreement() {

    }

    public Agreement(Cargo cargo, Vehicle vehicle, String status) {
        this.cargo = cargo;
        this.vehicle = vehicle;
        this.status = status;
    }

    public long getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(long agreementId) {
        this.agreementId = agreementId;
    }

    public long getCargoId() {
        return cargoId;
    }

    public void setCargoId(long cargoId) {
        this.cargoId = cargoId;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

}
