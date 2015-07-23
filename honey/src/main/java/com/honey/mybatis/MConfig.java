/**
 * @DescriptionTODO
 * @author 张树威
 * @date2015年3月23日
 * @version v1.0.0
 */
package com.honey.mybatis;

import org.apache.ibatis.session.SqlSession;

/**
 * @Description TODO
 * @author 张树威
 * @date 2015年3月23日
 * @version v1.0.0
 */
public class MConfig {
	private final ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();

	public final void setThreadLocalSession(SqlSession session) {
		threadLocal.set(session);
	}

	final SqlSession getSession() {
		SqlSession session = threadLocal.get();
		return session;
	}
	public final void removeThreadLocalSession() {
		threadLocal.remove();
	}
}
