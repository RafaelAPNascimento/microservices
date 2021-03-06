package org.rafaelinc.cuponapi;

import org.rafaelinc.cuponapi.model.Coupon;
import org.rafaelinc.cuponapi.model.Role;
import org.rafaelinc.cuponapi.model.User;
import org.rafaelinc.cuponapi.repository.CouponRepository;
import org.rafaelinc.cuponapi.repository.RoleRepository;
import org.rafaelinc.cuponapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, RoleRepository roleRepository, CouponRepository couponRepository) {

        return args -> {
            User doug = new User("doug","bailey","doug@bailey.com","$2a$10$U2STWqktwFbvPPsfblVeIuy11vQ1S/0LYLeXQf1ZL0cMXc9HuTEA2");
            User john = new User("john","ferguson","john@ferguson.com","$2a$10$YzcbPL.fnzbWndjEcRkDmO1E4vOvyVYP5kLsJvtZnR1f8nlXjvq/G");

//            User user1 = new User("doug","bailey","doug@bailey.com","123456");
//            User user2 = new User("john","ferguson","john@ferguson.com","123456");

            Role admin = new Role("ROLE_ADMIN");
            Role user = new Role("ROLE_USER");

            Set<Role> adminRole = Set.of(admin);
            Set<Role> userRole = Set.of(user);

            doug.setRoles(adminRole);
            john.setRoles(userRole);

            roleRepository.save(admin);
            roleRepository.save(user);

            userRepository.save(doug);
            userRepository.save(john);

            Coupon coupon = new Coupon("SUPERSALE", 10, "2020-06-25");
            couponRepository.save(coupon);
        };
    }
}
