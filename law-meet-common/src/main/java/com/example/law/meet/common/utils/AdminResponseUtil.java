package com.example.law.meet.common.utils;


/**
 * 管理后台接口枚举信息的响应
 *
 * @author CHENBO
 * @since 1.0.0
 * @QQ 623659388
 */
public class AdminResponseUtil extends ResponseUtil {

	/**
	 * 按枚举返回错误响应结果
	 *
	 * @param orderUnknown
	 * @return
	 */
	public static Object fail(AdminResponseCode responseCode) {
		return fail(responseCode.code(), responseCode.desc());
	}
}
