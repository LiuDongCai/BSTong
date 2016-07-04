package com.liu.bstong.config;

/**
 * 项目名称：BSTong<br>
 * 类名称：HttpApi<br>
 * 类描述：接口<br>
 * 创建人：这里填开发者自己的姓名<br>
 * 创建时间：2016年7月1日上午9:45:30<br>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注：
 * @version V1.0
 */
public class HttpApi {

	/**身份证查询的接口   From-聚合数据**/
	public final static String IDENTITY_MSG_URL = "http://apis.juhe.cn/idcard/index";
	/**身份证查询的接口   From-聚合数据**/
	public final static String IDENTITY_REVEAL_URL = "http://apis.juhe.cn/idcard/leak";
	/**身份证查询的接口   From-聚合数据**/
	public final static String IDENTITY_LOSE_URL = "http://apis.juhe.cn/idcard/loss";
	
	/**老黃歷查询的接口   From-聚合数据**/
	public final static String IDENTITY_CALENDAR_URL = "http://v.juhe.cn/laohuangli/d";
	
}
