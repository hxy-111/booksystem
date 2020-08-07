package com.book.system.service;

import com.book.system.entity.Admin;


public interface AdminService {

    boolean adminLogin(String adminName,String adminPwd);
}
