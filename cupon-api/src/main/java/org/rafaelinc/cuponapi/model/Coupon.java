package org.rafaelinc.cuponapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Coupon {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String code;
    private BigDecimal discount;
    private String expiryDate;

    public Coupon() {}

    public Coupon(String code, int discount, String expiryDate) {

        this.discount = BigDecimal.valueOf(discount);
        this.code = code;
        this.expiryDate = expiryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", discount=" + discount +
                ", expireDate=" + expiryDate +
                '}';
    }
}
