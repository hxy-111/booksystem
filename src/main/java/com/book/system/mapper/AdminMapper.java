package com.book.system.mapper;

import com.book.system.entity.Admin;

public interface AdminMapper {
    Admin adminLogin(String username,String password);

    int editPassword(Admin admin);

    int editAdmin(Admin admin);
}
