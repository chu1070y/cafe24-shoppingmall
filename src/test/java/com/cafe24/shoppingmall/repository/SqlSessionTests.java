package com.cafe24.shoppingmall.repository;

import static org.junit.Assert.assertNotNull;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cafe24.shoppingmall.config.AppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class})
public class SqlSessionTests {
	
	@Autowired
	private SqlSessionFactory factory;
	
	// DB 연결 테스트
	@Test
	public void test() {
		System.out.println(factory);
		assertNotNull(factory);
	}
	

}
