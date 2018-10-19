package edu.bsuc.utils;

public class StrUtil {
	/**
	 * 判断字符串是否为""、null
	 * @param str 待测字符串
	 * @return str为空串则返回true，非空返回false
	 */
	public static boolean isEmpty(String str){
		return null == str ? true : str.trim().equals("");
	}
	
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}
	
	/**
	 * 判断两字符串是否  相等
	 * @param src 字符串1
	 * @param dest 字符串2
	 * @return 两串相等，返回true
	 */
	public static boolean equals(String src,String dest){
		return src == null ? dest == null : src.equals(dest) ;
	}
	
	public static boolean isNotEquals(String src,String dest){
		return src == null ? dest == null : src.equals(dest) ;
	}
	
	/**
	 * 将字符串首字母小写
	 * @param str
	 * @return Str
	 */
	public static String toLowerTitle(String str){
		if(isEmpty(str))
			return str;
		char ch = str.charAt(0);
		if(ch>='A'&&ch<='Z')
			ch += 32;
		return ch+str.substring(1);
	}
	
}
