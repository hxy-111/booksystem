package com.book.system.service.Impl;

import com.book.system.entity.Admin;
import com.book.system.mapper.AdminMapper;
import com.book.system.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean adminLogin(String adminName,String adminPwd) {
        if (adminMapper.adminLogin(adminName,adminPwd)!=null){
            return true;
        }
        return false;
    }
}
