package com.honey.result;

/**
 * @author zsw
 * 
 *         返回值状态码 mmnnkk  项目名称代号两位mm
 *                  接口类型代码两位 nn 错误状态类型两位 kk
 */
public interface ResultCode {
	/**
	 * 成功返回码
	 */
	String SUCCESS_CODE = "1";
	/**
	 * 查询没有结果
	 */
	String NO_VALUE_CODE = "0";
	/**
	 * 更新或插入失败
	 */
	String INSERT_UPDATE_FIAL_CODE = "-1";
//	/**
//	 * 插入门店失败
//	 */
//	String SHOP_INSERT_FAIL_CODE = "01001";
//	/**
//	 * 更新门店失败
//	 */
//	String SHOP_UPDATE_FAIL_CODE = "01002";
//	/**
//	 * 查询门店异常
//	 */
//	String SHOP_GET_FAIL_CODE = "01003";
//	/**
//	 * 批量查询门店异常
//	 */
//	String SHOP_FIND_FAIL_CODE = "01004";
//	/**
//	 * 通过经纬度查询门店异常
//	 */
//	String SHOP_LATLON_FAIL_CODE = "01005";
}
