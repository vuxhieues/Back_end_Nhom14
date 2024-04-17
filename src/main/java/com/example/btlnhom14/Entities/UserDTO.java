package com.example.btlnhom14.Entities;

import lombok.Data;

// Class này không liên quan đến csdl. 
//Nó giúp mình chỉ lấy ra những thứ mình cần từ class user để phục vụ việc đăng nhập thôi
// Cái @Data là cái thư viện lombok tự gene ra constructor đấy k liên quan đến csdl.
@Data
public class UserDTO {
    private Long userId;
    private String username;
    private String role;
}
