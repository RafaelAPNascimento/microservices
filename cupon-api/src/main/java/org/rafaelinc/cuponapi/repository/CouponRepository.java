package org.rafaelinc.cuponapi.repository;

import org.rafaelinc.cuponapi.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

    public Coupon findByCode(String code);
}
