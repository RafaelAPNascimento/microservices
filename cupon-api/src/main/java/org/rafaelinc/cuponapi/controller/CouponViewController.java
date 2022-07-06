package org.rafaelinc.cuponapi.controller;

import org.rafaelinc.cuponapi.model.Coupon;
import org.rafaelinc.cuponapi.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CouponViewController {

    @Autowired
    private CouponRepository couponRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/showCreateCoupon")
    public String showCreateCoupon() {
        return "createCoupon";
    }

    @PostMapping("/saveCoupon")
    public String saveCoupon(Coupon coupon) {
        couponRepository.save(coupon);
        return "createResponse";
    }

    @GetMapping("/showGetCoupon")
    public String showGetCoupon() {
        return "getCoupon";
    }

    @PostMapping("/getCoupon")
    public ModelAndView getCoupon(String code) {

        ModelAndView mv = new ModelAndView("couponDetails");
        mv.addObject(couponRepository.findByCode(code));
        return mv;
    }
}
