package com.ssafy.plan.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.plan.controller.PlanBoardController;
import com.ssafy.plan.model.PlanDto;
import com.ssafy.plan.model.WishListDto;
import com.ssafy.plan.model.mapper.PlanMapper;
import com.ssafy.plan.model.mapper.WishListMapper;

@Service
public class WishListService {
	private static final Logger logger = LoggerFactory.getLogger(PlanBoardController.class);

	private WishListMapper wishListMapper;

	public WishListService(WishListMapper wishListMapper) {
		super();
		this.wishListMapper = wishListMapper;
	}

	public int insert(WishListDto wish) throws SQLException {
		return wishListMapper.insert(wish);
	}

	public int delete(WishListDto wish) throws SQLException {
		return wishListMapper.delete(wish);
	}

}
