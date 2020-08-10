package com.book.system.mapper;

import com.book.system.entity.TreeMenu;

import java.util.List;

/**
 * @Classname TreeMenuMapper
 * @Description None
 */
public interface TreeMenuMapper {
    List<TreeMenu> selectByAdminId();

    List<TreeMenu> selectByUserId();
}
