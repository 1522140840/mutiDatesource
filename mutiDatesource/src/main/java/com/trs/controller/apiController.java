package com.trs.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trs.primary.PrimaryUserDao;
import com.trs.second.SecondUserDao;

@RestController
@RequestMapping("/api/")
public class apiController {
	
	@Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate1;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate2;
    
    @Autowired
    private PrimaryUserDao primaryUserDao;
    
    @Autowired
    private SecondUserDao secondUserDao;
	
	@RequestMapping("user1")
	public String getUserInfo1(){
		List<Map<String,Object>> list = jdbcTemplate1.queryForList("select * from user");
        return Arrays.asList(list).toString();
	}
	
	@RequestMapping("user2")
	public List<Map<String,Object>> getUserInfo2(){
		List<Map<String,Object>> list = jdbcTemplate2.queryForList("select * from user");
        return list;
	}
	
	@RequestMapping("user3")
	public Object getUserInfo3(){
        return primaryUserDao.findOne(1L);
	}
	
	@RequestMapping("user4")
	public Object getUserInfo4(){
		return secondUserDao.findOne(1L);
	}

}
