package com.zz.region.methods.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	/**
	 * 判断是否为空或是空字符串,如果为null或为“”返回true
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(Object str) {
		if (null == str) {
			return true;
		}
		String s = str.toString();
		if("".equalsIgnoreCase(s)){
			return true;
		}
        return "null".equalsIgnoreCase(s);
    }

	/**
	 * 截取字符串
	 * 
	 * @param str
	 * @param size
	 * @return
	 */
	public synchronized static String splitString(String str, int size) {
		if (StringUtil.isNullOrEmpty(str)) {
			return "";
		}
		if (str.length() > size) {
			return str.substring(0, size) + "...";
		}
		return str;
	}

	/**
	 * 从ISO8859-1转换为utf-8
	 * 
	 * @param str
	 * @return
	 */
	public synchronized static String convert2UTF8FromIOS8859_1(String str) {
		if (isNullOrEmpty(str)) {
			return str;
		}
		try {
			return new String(str.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	/**
	 * 从utf-8转换为ISO8859-1
	 * 
	 * @param str
	 * @return
	 */
	public synchronized static String convert2ISO8859_1FromUTF8(String str) {
		if (isNullOrEmpty(str)) {
			return str;
		}
		try {
			return new String(str.getBytes("UTF-8"), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	/**
	 * 判断字符串是否是正整数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isPositiveInteger(String str) {
		if(isNullOrEmpty(str)){
			return false;
		}
		return str.matches("[0-9]*");
	}
	/**
	 * 得到一个字符串,在另一个字符串中重复出现的次数,区分大小写
	 * 
	 * @param str
	 *            原字符串
	 * @param temp
	 *            计算重复出现的字符串
	 * @return
	 */
	public static int stringRepeatNum(String str, String temp) {
		Pattern p = Pattern.compile(temp);
		Matcher m = p.matcher(str);
		int i = 0;
		while (m.find()) {
			i++;
		}
		return i;
	}
    /**
     * 根据values值依次替换字符串中的temp选项,区分大小写
     * @param str 原字符串
     * @param temp 被替换的参数
     * @param values 替换值
     * @return 返回替换后的字符串
     */
	public static String replaceStringByValues(String str,String temp,String...values){
		if(values==null)
			return str;
		Pattern p = Pattern.compile(temp);
		Matcher m = p.matcher(str);
		int i = 0;
		while (m.find()) {
			if(StringUtil.isNullOrEmpty(values[i]))
				str = str.replaceFirst(temp,"");
			else{
				str = str.replaceFirst(temp,values[i]);
				i++;
				if(i>=values.length)
					break;	
			}
		}
		return str;
	}
	/**
	 * 判断一个字符串数组里是否包含某个字符串
	 * @param temps
	 * @param str
	 * @return 包含返回true,不包含返回false
	 */
	public static boolean isContainsStr(String [] temps,String str){
		if(StringUtil.isNullOrEmpty(str))
			return false;
		if(temps==null)
			return false;
		for (String string : temps) {
			if(str.equals(string))
				return true;
		}
		return false;
	}
	/**
	 * 得到UUID
	 * @return
	 * @author gql 2013-7-5 下午4:17:54
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	/**
	 * 验证是否是数字
	 * @param str
	 * @return
	 * @author gql 2013-7-3 下午6:43:52
	 */
	public static boolean isNum(String str){
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
	
	public static int parseInt(String str){
		if(isNum(str)){
			return Integer.parseInt(str);
		}else{
			return 0;
		}
	}
	
	public static double parseDouble(String str){
		if(isNum(str)){
			return Double.parseDouble(str);
		}else{
			return 0;
		}
	}
	
	/**
	 * 字符串转字符串集合
	 * @param src
	 * @param splitStr
	 * @return
	 * @author gql 2014-12-19 下午4:07:14
	 */
	public static List<String> string2List(String src,String splitStr){
		if(StringUtil.isNullOrEmpty(src) || StringUtil.isNullOrEmpty(splitStr)){
			return null;
		}
		String[] s = src.split(splitStr);
		List<String> list = new ArrayList<String>();
		for (String string : s) {
			if(StringUtil.isNullOrEmpty(string))
				continue;
			list.add(string);
		}
		return list;
	}
	
	/** 
     * @Title:string2HexString 
     * @Description:字符串转16进制字符串 
     * @param strPart 
     *            字符串 
     * @return 16进制字符串 
     * @throws 
     */  
    public static String string2HexString(String strPart) {  
        StringBuffer hexString = new StringBuffer();  
        for (int i = 0; i < strPart.length(); i++) {  
            int ch = (int) strPart.charAt(i);  
            String strHex = Integer.toHexString(ch);  
            hexString.append(strHex);  
        }  
        return hexString.toString();  
    }  
  
    /** 
     * @Title:hexString2String 
     * @Description:16进制字符串转字符串 
     * @param src 
     *            16进制字符串 
     * @return 字节数组 
     * @throws 
     */  
    public static String hexString2String(String src) {  
        String temp = "";  
        for (int i = 0; i < src.length() / 2; i++) {  
            temp = temp  
                    + (char) Integer.valueOf(src.substring(i * 2, i * 2 + 2),  
                            16).byteValue();  
        }  
        return temp;  
    } 
	
    /**
     * 字符数组转字符串
     * @param str
     * @param splitStr
     * @return
     * @author gql 2017年6月19日 上午10:30:08
     */
    public static String array2String(String[] arrays,String splitStr){
    	StringBuffer sb = new StringBuffer();
    	if(null == arrays || arrays.length==0){
    		return null;
    	}
    	for (String string : arrays) {
			sb.append(string + splitStr);
		}
    	if(sb.length()>splitStr.length()){
    		return sb.substring(0,sb.length()-splitStr.length());
    	}
    	return sb.toString();
    }
    /**
	 * 格式化列字符数组长度,数组长度不够自动补充空字符串
	 * @param list
	 * @param colLength 
	 * @return
	 * @author gql 2017年8月8日 下午2:34:15
	 */
	public static List<String[]> formatList(List<String[]> list,int colLength){
		List<String[]> newList = null;
		if(null != list){
			newList = new ArrayList<String[]>();
			for (String[] strings : list) {
				String[] news = new String[colLength];
				for (int i=0;i<colLength;i++) {
					if(i<strings.length){
						news[i] = strings[i];
					}else{
						news[i] = "";
					}
				}
				newList.add(news);
			}
		}
		return newList;
	}
	
	/**
	 * 格式化串号
	 * @param imei
	 * @return
	 * @author gql 2018年9月23日 下午6:04:48
	 */
	public static String formatImei(int yysType,String imei){
		return "#_#" + (yysType == 0?"cm":yysType == 1?"cu":yysType == 2?"ct":"") + ":" + imei + "#_#";
	}
	public static String[] parseImei(String imei){
		if(StringUtil.isNullOrEmpty(imei)){
			return new String[]{"","",""};
		}
		String strs[] = imei.split(";");
		String[] returnStr = new String[3];
		returnStr[0] = strs[0].replaceAll("#_#cm:", "").replaceAll("#_#", "").trim();
		returnStr[1] = strs[1].replaceAll("#_#cu:", "").replaceAll("#_#", "").trim();
		returnStr[2] = strs[2].replaceAll("#_#ct:", "").replaceAll("#_#", "").trim();
		return returnStr;
	}

	/**
	 * 数字转成4位字符串，0001
	 * @param count
	 * @return
	 */
	public static String paraeString4(Integer count){

		String c = count+"";
		int num = c.length();
		for (int i = 0;i<4-num;i++){
			c = "0"+c;
		}

		return c;
	}
}
