package lv.javaguru.java2.domain;

import javax.persistence.*;

//@Entity
//@Table(name = "agreement")
public class Agreement {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name="id", columnDefinition = "int(11)")
    private long agreementId;

//    @Column(name = "cargoId", columnDefinition = "int(11)")
    private long cargoId;

//    @Column(name = "vehicleId", columnDefinition = "int(11)")
    private long vehicleId;

//    @Column(name = "status", columnDefinition = "char(30)")
    private String status;


    public Agreement() {

    }

    public Agreement(long cargoId, long vehicleId, String status) {
        this.cargoId = cargoId;
        this.vehicleId = vehicleId;
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

}
