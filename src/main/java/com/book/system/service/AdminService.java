package com.book.system.service;

import com.book.system.entity.Admin;


public interface AdminService {

    Admin adminLogin(String username,String password);

    int editPassword(Admin admin);

    int editAdmin(Admin admin);
}
