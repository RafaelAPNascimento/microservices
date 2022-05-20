package org.rafaelinc.cuponapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.rafaelinc.cuponapi.model.Coupon;
import org.rafaelinc.cuponapi.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coupon")
@Slf4j
public class CouponController {

    @Autowired
    private CouponRepository couponRepository;

    @PostMapping
    public Coupon create(@RequestBody Coupon coupon) {
        log.info("req: {}", coupon);
        return couponRepository.save(coupon);
    }

    @GetMapping("/{code}")
    public Coupon getCoupon(@PathVariable String code) {
        return couponRepository.findByCode(code);
    }
}