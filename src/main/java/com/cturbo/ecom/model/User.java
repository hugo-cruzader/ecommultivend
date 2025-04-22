package com.cturbo.ecom.model;

import com.cturbo.ecom.domain.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // NEVER RETRIEVE IT. ONLY STORE IT ON WRITE
        private String password;

        private String email;

        private String fullName;

        private String mobile;

        private UserRole role = UserRole.CUSTOMER;

        @OneToMany
        private Set<Address> addresses =  new HashSet<>();

        @ManyToMany
        @JsonIgnore // DO NOT NEED IN FRONTEND
        private Set<Coupon> usedCoupons = new HashSet<>();
}
