package edu.bsuc.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public  class RegCheck {

	/**手机号校验
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数 
     * 此方法中前三位格式有： 
     * 13+任意数 
     * 15+除4的任意数 
     * 18+除1和4的任意数 
     * 17+除9的任意数 
     * 147 
     */  
 public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {  
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";  
        Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher(str);  
        return m.matches();  
    } 
 
 /** 
  * 验证输入的邮箱格式是否符合 
  * @param email 
  * @return 是否合法 
  */  
public static boolean emailFormat(String email)  
 {  
     boolean tag = true;  
     final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";  
     final Pattern pattern = Pattern.compile(pattern1);  
     final Matcher mat = pattern.matcher(email);  
     if (!mat.find()) {  
         tag = false;  
     }  
     return tag;  
 } 
}
