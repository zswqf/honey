/**
 * @DescriptionTODO
 * @author 张树威
 * @date2015年3月23日
 * @version v1.0.0
 */
package com.honey.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

/**
 * @Description TODO
 * @author 张树威
 * @date 2015年3月23日
 * @version v1.0.0
 */
public class HoneyTemple {
	private final static MConfig config = new MConfig();

	public static SqlSession getSqlSession() {
		return config.getSession();
	}
	public static SqlSession setSqlSession(){
		SqlSession sqlSession = MSFactory.getSqlSession();
		config.setThreadLocalSession(sqlSession);
		return sqlSession;
	}
	public static int insert(String statement) {
		return getSqlSession().insert(statement);
	}

	public static int insert(String statement, Object parameter) {
		return getSqlSession().insert(statement, parameter);
	}

	public static int update(String statement) {
		return getSqlSession().update(statement);
	}

	public static int update(String statement, Object parameter) {
		return getSqlSession().delete(statement, parameter);
	}

	public static int delete(String statement) {
		return getSqlSession().update(statement);
	}

	public static int delete(String statement, Object parameter) {
		return getSqlSession().update(statement, parameter);
	}

	public static <T> T selectOne(String statement) {
		return getSqlSession().selectOne(statement);
	}

	public static <T> T selectOne(String statement, Object parameter) {
		return getSqlSession().selectOne(statement, parameter);
	}

	public static <E> List<E> selectList(String statement) {
		return getSqlSession().selectList(statement);
	}

	public static <E> List<E> selectList(String statement, Object parameter) {
		return getSqlSession().selectList(statement, parameter);
	}
}
