package com.example.btlnhom14.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// BẤT KÌ CLASS NÀO CÓ @Entity và @Table SẼ LIÊN QUAN ĐẾN CSDL !!!
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "User")
public class User implements UserDetails {

    // Người dùng
    // GenerationType.IDENTITY khóa tự động tăng
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    //Tài khoản
    private String password;

    // Mật khẩu
    private String username;

    // Chức vụ. Mình có 2 chức vụ là USER và ADMIN
    private String role;

    // Xác định mối quan hệ 1-n với results
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Result> Results;

    // Xác định mối quan hệ 1-n với attempt
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Attempt> Attempts;

    // Mấy hàm dưới này liên quan đến bảo mật k liên quan đến csdl
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + getRole().toUpperCase()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }

}
