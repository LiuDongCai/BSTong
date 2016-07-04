package com.liu.bstong.bean;

public class IdentityBean {
	public String reason;
	public Result result;
	public String resultcode;
	
	public class Result{
		//身份证信息查询
		public String birthday;
		public String sex;
		public String area;
		//身份证泄露和挂失查询
		public String cardno;
		public String res;
		public String tips;
		
		//老黄历查询
		public String id;
		public String yangli;
		public String yinli;
		public String wuxing;
		public String chongsha;
		public String baiji;
		public String jishen;
		public String yi;
		public String xiongshen;
		public String ji;
	}
}
