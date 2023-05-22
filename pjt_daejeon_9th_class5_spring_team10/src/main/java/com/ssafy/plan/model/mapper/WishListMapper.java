package com.ssafy.plan.model.mapper;

import java.sql.SQLException;

import com.ssafy.plan.model.WishListDto;

public interface WishListMapper {
	int insert(WishListDto wish) throws SQLException;

	int delete(WishListDto wish) throws SQLException;
}
