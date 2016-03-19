package com.project.board.common;

import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.springframework.web.util.HtmlUtils;

/**
 * 문자열 유틸리티
 * @author sales
 *
 */
public class StringUtil {

	/**
	 * Object Null 체크
	 * @param s
	 * @return
	 */
	public static boolean isNull(Object obj){
		return obj == null;
	}

	/**
	 * int 형 Null 체크
	 * @param i
	 * @return
	 */
	public static boolean isNull(Integer i){
		return i == null || i.intValue() <= 0;
	}

	/**
	 * Null 체크
	 * @param s
	 * @return
	 */
	public static boolean isNull(String s){
		return s == null || s.equals("");
	}

	/*
	 * 빈갑 체크후 초기값 셋팅
	 * @param s
	 * @return
	 */
	public static String nvl(String s){
		return nvl(s, "");
	}

	/**
	 * 빈갑 체크후 초기값 셋팅
	 * @param s
	 * @param d
	 * @return
	 */
	public static String nvl(String s, String d){
		String r = s;
		if(isNull(s)) r = d;
		return r;
	}

	/**
	 * str -> int 변환 하면서 기본값 셋팅
	 * @param s
	 * @param d
	 * @return
	 */
	public static int nvl(String s, int d){

		int r = d;
		try{
			r = Integer.parseInt(s);
		}catch(Exception e){
			r = d;
		}		
		return r;
	}

	/**
	 * 현재 날짜(yyyyMMdd)
	 * @return
	 */
	public static String getCurrentDate(){
		return getCurrentDate("yyyyMMdd");
	}

	public static String getCurrentDate(String format){
		Date date = new Date();
		return getDateString(date, format);
	}

	// 분 계산
	public static String getMin(String format, int min) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		c.add(Calendar.MINUTE, min);
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(c.getTime());
	}
	// 시간 계산
	public static String getHour(String format, int hour) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		c.add(Calendar.HOUR_OF_DAY, hour);
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(c.getTime());
	}
	public static String getHour(String format, Calendar c, int hour) {
		c.add(Calendar.HOUR_OF_DAY, hour);
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(c.getTime());
	}
	// 일 계산
	public static String getDay(int day) {
		return getDay("yyyy-MM-dd HH:mm:ss", day, 0);
	}
	public static String getDay(String format, int day, int hour) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		c.add(Calendar.DATE, day);
		c.add(Calendar.HOUR_OF_DAY, hour);
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(c.getTime());
	}
	// 년 계산
	public static String getYear(int year) {
		return getYear("yyyy-MM-dd HH:mm:ss", year);
	}
	public static String getYear(String format, int year) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		c.add(Calendar.YEAR, year);
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(c.getTime());
	}

	/**
	 * 현재 날짜와 시간(yyyyMMddHHmmss)
	 * @return
	 */
	public static String getCurrentDateTime(){
		Date date = new Date();
		return getDateString(date, "yyyyMMddHHmmss");
	}

	/**
	 * 날짜 문자열 출력
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getDateString(Date date, String format){
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	/**
	 * 날짜포맷.. ( 구분자 -)
	 * @param date
	 * @return
	 */
	public static String date_format(String date){
		return date_format(date, "-");

	}
	public static String date_format(String date, String dim){
		String y = date.substring(0, 4);
		String m = date.substring(4, 6);
		String d = date.substring(6, 8);
		return y + dim + m + dim + d;
	}
	
	public static String[] getToDateBeforeHours(String now_date, int days, String format) {
		int size = Math.abs(days) + 1;
		String[] result = new String[size];
		Calendar c = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat df2 = new SimpleDateFormat(format);
		try {
			c.setTime(df.parse(now_date));
			result[0] = df2.format(c.getTime());
			for(int i = 1; i < result.length; i++) {
				result[i] = getHour(format, c, -1);
				c.setTime(df.parse(result[i]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 두 날짜 차이 계산
	 * @param date
	 * @return
	 */
	public static int getDateDiffDay(String date) {
		// 현재일 설정
		Calendar now = Calendar.getInstance();
		now.setTimeInMillis(System.currentTimeMillis());
		now.set(Calendar.MONTH, now.get(Calendar.MONTH) + 1);
		
		// 특정일 설정
		date = date.replace("-", "");
		int y = Integer.parseInt(date.substring(0, 4));
		int m = Integer.parseInt(date.substring(4, 6));
		int d = Integer.parseInt(date.substring(6, 8));
		
		Calendar own_date = Calendar.getInstance();
		own_date.set(y, m, d);
		
		return (int) ((now.getTime().getTime() - own_date.getTime().getTime()) / (1000 * 60 * 60 * 24)); 
		
	}

	/**
	 * 브라우저가 HTML로 인식하지 않도록 크다 작다의 부등호를 "&lt;" "&gt;" 로 변경한다.
	 * 
	 * @param value 문자열
	 * @return 브라우저가 HTML로 인식하지 않도록 수정된 문자열
	 */
	public static String getAgainstHtmlString(String value) {
		if(value == null) return "";
		String returns = value.replaceAll("<", "&lt;");
		returns = returns.replaceAll(">", "&gt;");
		return returns;
	}

	/**
	 * alert
	 * @param res
	 * @param msg
	 */
	public static void alert(HttpServletResponse res, String msg){
		alert(res, msg, Codes.Alert.NONE, null, -1);	
	}

	/**
	 * alert
	 * @param res
	 * @param msg
	 * @param method
	 */
	public static void alert(HttpServletResponse res, String msg, int method){
		alert(res, msg, method, null, -1);
	}

	/**
	 * alert
	 * @param res
	 * @param msg
	 * @param method
	 * @param url
	 */
	public static void alert(HttpServletResponse res, String msg, int method, String url){

		String[] msgArray = {msg};
		
		alert(res, msgArray, method, url, -1);
	}
	/**
	 * alert
	 * @param res
	 * @param msg
	 * @param method
	 * @param url
	 */
	public static void alert(HttpServletResponse res, String msg, int method, String url, int count){
		
		String[] msgArray = {msg};
		
		alert(res, msgArray, method, url, count);
	}

	public static void alert(HttpServletResponse res, String[] msg, int method, String url, int count){
		StringBuffer sb = new StringBuffer();

		sb.append("<script>");
		
		if(msg != null && msg.length > 0) {
			for(String m : msg){
				sb.append("alert(\"" + m + "\");");
			}
		}

		switch(method){
		case Codes.Alert.NONE:		// 기본
			break;
		case Codes.Alert.GOBACK:		// 뒤로가기
			sb.append("history.back();");
			break;
		case Codes.Alert.MOVE:			// 이동
			sb.append("document.location = \"" + url + "\"; "); 
			break;
		case Codes.Alert.CLOSE:			// 창 종료
			sb.append("self.close();");
			break;
		case Codes.Alert.GO:
			sb.append("history.go(" + count + ")");			
			break;
		}		

		sb.append("</script>");
		try{
			res.setContentType("text/html;charset=UTF-8");	
			res.setHeader("Cache-Control", "no-cache");
			res.getWriter().write(sb.toString());
			sb.setLength(0);
			sb = null;
			res.getWriter().flush();
			res.getWriter().close();
		}catch(Exception e){}
	}
	/**
	 * lpad
	 *
	 * @param str   대상문자열, len 길이, addStr 대체문자
	 * @return      문자열
	 */

	public static String lpad(String str, int len, String addStr) {
		String result = str;
		int templen   = len - result.length();

		for (int i = 0; i < templen; i++){
			result = addStr + result;
		}

		return result;
	}

	public static String korSubstring(String pm_sStr, int pm_iSize, String pm_sAppend) {

		String lm_sRetStr = "";
		int lm_iStrSize = 0;
		char[] charArray = pm_sStr.toCharArray();
		byte[] lm_oByteArray = pm_sStr.getBytes();
		if (lm_oByteArray.length > pm_iSize) {
			for (int i = 0; i < pm_sStr.length(); i++) {
				if (charArray[i] > '\u00FF') {
					lm_iStrSize += 2;
				} else {
					lm_iStrSize++;
				}
				if (lm_iStrSize > pm_iSize) {
					break;
				} else {
					lm_sRetStr += charArray[i];
				}
			}
			lm_sRetStr += pm_sAppend;
			return lm_sRetStr;
		}
		return pm_sStr;
	}

	/**
	 * md5
	 * @param txt
	 * @return
	 */
	public static String md5(String txt) {
		String en_txt = null;
		try {
			byte[] bytesOfMessage = txt.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);
			en_txt = new String(Hex.encodeHex(thedigest));
		} catch(Exception e) {}
		return en_txt;
	}
	
	/**
	 * 유니코드 문자 삭제
	 * 
	 * @param text
	 * @return
	 */
	public static String escapeUnicodeAndHtml(String text){
		
		String return_text = text;
		Pattern unicodeOutliers = Pattern.compile("\\\\u[0-9a-zA-Z]{4}");
        Matcher unicodeOutlierMatcher = unicodeOutliers.matcher(return_text);
        return_text = unicodeOutlierMatcher.replaceAll("");
        
        Pattern HtmlOutliers = Pattern.compile("&#[0-9a-zA-Z]{2};");
        Matcher HtmlOutlierMatcher = HtmlOutliers.matcher(return_text);
        return_text = HtmlOutlierMatcher.replaceAll("");
        
        return return_text;
	}
	
	/**
	 * html 태그 제거
	 * @param s
	 * @return
	 */
	/*public static String RemoveHTML(String content) {
		content = HtmlUtils.htmlUnescape(content);
		Document doc = Jsoup.parse(content);
		doc.select("img").remove();
		return doc.text();
	}*/
}