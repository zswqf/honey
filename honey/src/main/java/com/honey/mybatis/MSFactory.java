package com.honey.mybatis;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/**
 *  生成SqlSessionFactory工厂
 * @author zsw
 *
 */
 class MSFactory {
	private static SqlSessionFactory sqlSessionFactory;
	private MSFactory(){
	}
	static {
		try {
			Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取session工厂
	 * @return
	 */
	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
	}
	/**
	 * 获取session
	 * @return
	 */
	public static SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}
}
