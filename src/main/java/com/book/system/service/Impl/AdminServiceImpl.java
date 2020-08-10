package com.book.system.service.Impl;

import com.book.system.entity.Admin;
import com.book.system.mapper.AdminMapper;
import com.book.system.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin adminLogin(String username, String password) {
        return adminMapper.adminLogin(username,password);
    }

    @Override
    public int editPassword(Admin admin) {
        return adminMapper.editPassword(admin);
    }

    @Override
    public int editAdmin(Admin admin) {
        return adminMapper.editAdmin(admin);
    }


}
