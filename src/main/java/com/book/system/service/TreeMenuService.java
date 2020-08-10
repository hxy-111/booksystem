package com.book.system.service;

import com.book.system.entity.TreeMenu;

import java.util.List;

/**
 * @Classname TreeMenuService
 * @Description None
 */
public interface TreeMenuService {

    List<TreeMenu> selectByAdminId();

    List<TreeMenu> selectByUserId();

}
