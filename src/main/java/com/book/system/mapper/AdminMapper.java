package com.book.system.mapper;

import com.book.system.entity.Admin;

public interface AdminMapper {
    Admin adminLogin(String adminName,String adminPwd);
}
