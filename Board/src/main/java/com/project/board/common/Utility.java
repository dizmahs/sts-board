package com.project.board.common;


import javax.servlet.http.*;

import org.springframework.web.servlet.HandlerMapping;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Random;
import java.text.SimpleDateFormat;

import java.text.NumberFormat;
import java.util.Locale;

import java.security.SecureRandom;

import java.lang.reflect.*;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Comparator;
import java.util.List;


public class Utility {
   
	
	/**
	 * <code>WON</code> : "원" 식별자
	 */
	public static final int WON = 1;
	/**
	 * <code>DOLLAR</code> : "달러" 식별자
	 */
	public static final int DOLLAR = 2;

	
	
    private Utility() {
    }
    
    
    
    
    /**
     * 일반적인 페이지 함수
     * @param current_page  현재페이지
     * @param total_page    전체페이지
     * @param list_url      리스트 URL 
     * @param pre_img_url   이전 10페이지 
     * @param back_img_url  이후 10페이지
     * @return
     */
    public static String PagePrintImg(int current_page, int total_page, String list_url, String pre_img_url, String back_img_url) {
            int pagenumber;     // 화면에 보여질 페이지 인덱스 수
            int startpage;      // 화면에 보여질 시작페이지 번호
            int endpage;        // 화면에 보여질 마지막페이지 번호
            int curpage;        // 이동하고자 하는 페이지 번호
            String strList="";  // 리턴될 페이지 인덱스 리스트

            pagenumber = 10;    // 한 화면의 페이지 인덱스 수

            // 시작 페이지번호 구하기
            startpage = ((current_page - 1) / pagenumber) * pagenumber + 1;
            // 마지막 페이지번호 구하기
            endpage = (((startpage - 1) +  pagenumber) / pagenumber) * pagenumber;

            // 총 페이지 수가 계산된 마지막페이지 번호보다 작을경우
            // 총 페이지 수가 마지막페이지 번호가 됨
            if (total_page <= endpage)
            {
                    endpage = total_page;
            }

            // 첫번째 페이지 인덱스 화면이 아닌경우
            if ( current_page > pagenumber) {
                    curpage = startpage - 1;    // 시작페이지 번호보다 1 적은 페이지로 이동
                    strList = strList + "<a href='"+list_url+"&current_page="+curpage+"'>"+pre_img_url+"</a>";
            }else{
                    strList = strList + pre_img_url;
            }
            strList = strList + " ... ";

            // 시작페이지 번호부터 마지막페이지 번호까지 화면에 표시
            curpage = startpage;
            while (curpage <= endpage){
                    if (curpage == current_page) {
                            strList = strList + "["+current_page+"]";
                    } else {
                            strList = strList +"<a href='"+list_url+"&current_page="+curpage+"'>["+curpage+"]</a>";
                    }
                     curpage++;
            }
            strList = strList + " ... ";

            // 뒤에 페이지가 더 있는경우
            if ( total_page > endpage) {
                    curpage = endpage + 1;
                    strList = strList + "<a href='"+list_url+"&current_page="+curpage+"'>"+back_img_url+"</a>";
            }else{
                    strList = strList + back_img_url;
            }

            return strList;
    }
    

                
    
    
    
    
    
    /**
     * 텍스트식 페이징 함수 
     * @param current_page
     * @param total_page
     * @param list_url
     * @return
     */
    public static String PagePrintText(int current_page, int total_page, String list_url) {
            int pagenumber;     // 화면에 보여질 페이지 인덱스 수
            int startpage;      // 화면에 보여질 시작페이지 번호
            int endpage;        // 화면에 보여질 마지막페이지 번호
            int curpage;        // 이동하고자 하는 페이지 번호
            String strList="";  // 리턴될 페이지 인덱스 리스트

            pagenumber = 10;    // 한 화면의 페이지 인덱스 수

            // 시작 페이지번호 구하기
            startpage = ((current_page - 1) / pagenumber) * pagenumber + 1;
            // 마지막 페이지번호 구하기
            endpage = (((startpage - 1) +  pagenumber) / pagenumber) * pagenumber;

            // 총 페이지 수가 계산된 마지막페이지 번호보다 작을경우
            // 총 페이지 수가 마지막페이지 번호가 됨
            if (total_page <= endpage)
            {
                    endpage = total_page;
            }

            // 첫번째 페이지 인덱스 화면이 아닌경우
            if ( current_page > pagenumber) {
                    curpage = startpage - 1;    // 시작페이지 번호보다 1 적은 페이지로 이동
                    strList = strList + "<a href='"+list_url+"&current_page="+curpage+"'>[이전10개]</a>";
            }else{
                    strList = strList + "[이전10개]";
            }
            strList = strList + " ... ";

            // 시작페이지 번호부터 마지막페이지 번호까지 화면에 표시
            curpage = startpage;
            while (curpage <= endpage){
                    if (curpage == current_page) {
                            strList = strList + "["+current_page+"]";
                    } else {
                            strList = strList +"<a href='"+list_url+"&current_page="+curpage+"'>["+curpage+"]</a>";
                    }
                     curpage++;
            }
            strList = strList + " ... ";

            // 뒤에 페이지가 더 있는경우
            if ( total_page > endpage) {
                    curpage = endpage + 1;
                    strList = strList + "<a href='"+list_url+"&current_page="+curpage+"'>[다음10개]</a>";
            }else{
                    strList = strList + "[다음10개]";
            }

            return strList;
    }
    
    
    
    
    
    
    
    /**
     * 페이징 함수
     * @param current_page
     * @param total_page
     * @param list_url
     * @param page_index_cnt
     * @return
     */
    public static String pagePrintSE(int current_page, int total_page, String list_url, int page_index_cnt) {
            int pagenumber;     // 화면에 보여질 페이지 인덱스 수
            int startpage;      // 화면에 보여질 시작페이지 번호
            int endpage;        // 화면에 보여질 마지막페이지 번호
            int curpage;        // 이동하고자 하는 페이지 번호
            String strList="";  // 리턴될 페이지 인덱스 리스트

            pagenumber = page_index_cnt; // 한 화면의 페이지 인덱스 수

            // 시작 페이지번호 구하기
            startpage = ((current_page - 1) / pagenumber) * pagenumber + 1;
            // 마지막 페이지번호 구하기
            endpage = (((startpage - 1) +  pagenumber) / pagenumber) * pagenumber;

            // 총 페이지 수가 계산된 마지막페이지 번호보다 작을경우
            // 총 페이지 수가 마지막페이지 번호가 됨
            if (total_page <= endpage)
            {
                    endpage = total_page;
            }




            // 맨처음 페이지로
            if ( current_page > 1) {
                    strList = strList + "<a href='"+list_url+"&current_page=1'>[처음]</a>";
            }else{
                    strList = strList + "[처음]";
            }



            // 첫번째 페이지 인덱스 화면이 아닌경우
            if ( current_page > pagenumber) {
                    curpage = startpage - 1;    // 시작페이지 번호보다 1 적은 페이지로 이동
                    strList = strList + "<a href='"+list_url+"&current_page="+curpage+"'>[이전10개]</a>";
            }else{
                    strList = strList + "[이전]";
            }
            strList = strList + " ... ";

            // 시작페이지 번호부터 마지막페이지 번호까지 화면에 표시
            curpage = startpage;
            while (curpage <= endpage){
                    if (curpage == current_page) {
                            strList = strList + "<b>["+current_page+"]</b>";
                    } else {
                            strList = strList +"<a href='"+list_url+"&current_page="+curpage+"'>["+curpage+"]</a>";
                    }
                     curpage++;
            }
            strList = strList + " ... ";

            // 뒤에 페이지가 더 있는경우
            if ( total_page > endpage) {
                    curpage = endpage + 1;
                    strList = strList + "<a href='"+list_url+"&current_page="+curpage+"'>[다음]</a>";
            }else{
                    strList = strList + "[다음]";
            }




            // 맨마지막 페이지로
            if (current_page != total_page) {
                 strList = strList + "<a href='"+list_url+"&current_page="+total_page+"'>[끝]</a>";

            }else{
                 strList = strList + "[끝]";
            }

            return strList;
    }






    /**
     * 버튼식 페이징
     * @param current_page 현재페이지
     * @param total_page   전체페이지
     * @param list_url   리스트 URl
     * @param pre_img_url  다음페이지
     * @param back_img_url 이전페이지
     * @return
     */
    public static String PagePrintButton(int current_page, int total_page, String list_url, String pre_img_url, String back_img_url) {
             String strList="";  // 리턴될 페이지 인덱스 리스트

            // 첫번째 페이지 일때
            if ( current_page <= 1) {
                    strList = strList + pre_img_url;

            }else{
                    strList = strList + "<a href='"+list_url+"&current_page="+(current_page-1)+"'>"+pre_img_url+"</a>";
            }

            strList += "&nbsp;";

            // 마지막페이지일때
            if(current_page >= total_page || current_page == total_page) {
                    strList = strList + back_img_url;
            }
            else {
                    strList = strList + "<a href='"+list_url+"&current_page="+(current_page+1)+"'>"+back_img_url+"</a>";
            }


            return strList;
    }





        


    /**
     * 클라이언트 브라우저에 페이지 이동을 위한 페이지번호 리스트를 보여준다.
     * @param total_page
     * @param page
     * @return
     */ 
   public static String PagePrintScript(int total_page, int page) {

            // 게시물 목록 하단의 각 페이지로 직접 이동할 수 있는 페이지링크에 대한 설정을 한다.//

            int page_per_block = 10;
            int member_page=0;
    
            int total_block = 1;
            if((total_page % page_per_block) == 0) {  
                   total_block = total_page / page_per_block;
            }else{
                   total_block = total_page / page_per_block + 1;
            }  

    
    
            int block = 1;
            if((page % page_per_block) == 0) {  
                  block = page / page_per_block;
            }else{
                  block = page / page_per_block + 1;
            } 

            int first_page = (block-1)* page_per_block ;
            int last_page = block*page_per_block;

            if(total_block <= block) {
              last_page = total_page;
            }

            StringBuffer result = new StringBuffer();
    
            // 이전페이지블록에 대한 페이지 링크 //
            if(total_page > 0){
            //if(block > 1) {
                member_page = first_page;
                result.append("<a href='JavaScript:list(1)'><img src='../img/s1/bu_pre2.gif' width='17' height='13' align='absmiddle' border='0'></a>&nbsp;");
                if(member_page == 0) member_page = 1;
                result.append("<a href='JavaScript:list(" + member_page +")'><img src='../img/s1/bu_pre.gif' width='41' height='13' align='absmiddle' border='0'></a>&nbsp;&nbsp;");
            //}

            // 현재의 페이지 블럭범위내에서 각 페이지로 바로 이동할 수 있는 하이퍼링크를 출력한다. //
            for( int direct_page = first_page+1; direct_page <= last_page; direct_page++) {
            	  if(direct_page != first_page+1) result.append("&nbsp;.&nbsp;");
                  if(page == direct_page) {
                       result.append("<span class='t06'><font color='red'><strong>" + direct_page + "</strong></font></span>");
                  }else {
                       result.append("<a href='JavaScript:list(" + direct_page +")'>" + direct_page + "</a>");
                  }
            }

            // 다음페이지블록에 대한 페이지 링크 //
            //if(block < total_block) {
                   member_page = last_page+1;
                   if(member_page > total_page) member_page = total_page;
                   result.append("&nbsp;&nbsp;<a href='JavaScript:list(" + member_page +")'><img src='../img/s1/bu_next.gif' width='41' height='13' align='absmiddle' border='0'></a>");
                   result.append("&nbsp;<a href='JavaScript:list(" + total_page +")'><img src='../img/s1/bu_next2.gif' width='17' height='13' align='absmiddle' border='0'></a>");
            //}
   			}
            return result.toString(); 
    
    }
    







    /**
     * 8859_1을 KSC5601로 변환
     * @param str
     * @return
     */    
   
     public static String toKorean(String str) {
            String convStr = null;
            try {
               if(str == null)
                       return "";

               // 현재문자열을 8859_1형식으로 읽어내어 KSC5601형식으로 변환
               convStr = new String(str.getBytes("8859_1"),"KSC5601");
            } catch (UnsupportedEncodingException e) { System.out.println("toKorean 오류");
            }
            return convStr;
     }
     
     
     /**
      * 8859_1을 KSC5601로 변환
      * @param str
      * @return
      */    
      public static String toKoreanPara(String str) {
             String convStr = null;
             try {
                if(str == null)
                        return "";

                // 현재문자열을 8859_1형식으로 읽어내어 KSC5601형식으로 변환
                convStr = new String(str.getBytes("ISO-8859-1"),"UTF-8");
                
             } catch (UnsupportedEncodingException e) { System.out.println("toKoreanPara 오류");
             }
             return convStr;
      }
     
     
      /**
       * 8859_1을 KSC5601로 변환
       * @param str
       * @return
       */    
       public static String toKoreanPara(String str, String old_Charset, String new_Charset) {
              String convStr = null;
              try {
                 if(str == null)
                         return "";

                 // 현재문자열을 8859_1형식으로 읽어내어 KSC5601형식으로 변환
                 convStr = new String(str.getBytes( old_Charset ), new_Charset );
                 
              } catch (UnsupportedEncodingException e) { System.out.println("toKoreanPara 오류2");
              }
              return convStr;
       }

       
      /**
       * 8859_1을 KSC5601로 변환
       * @param str
       * @return
       */    
       public static String toUTFPara(String str) {
              String convStr = null;
              try {
                 if(str == null)
                         return "";

                 // 현재문자열을 8859_1형식으로 읽어내어 KSC5601형식으로 변환
                 convStr = new String(str.getBytes("UTF-8"),"ISO-8859-1");
                 
              } catch (UnsupportedEncodingException e) { System.out.println("toUTFPara 오류");
              }
              return convStr;
       }
     
     
    /**
     * KSC5601을 8859_1로 변환
     * @param str
     * @return
    */     
    public static String fromKorean(String str) {
            String convStr = null;
            try {
                    if(str == null)
                            return "";

                    // 현재문자열을 KSC5601형식으로 읽어내어 8859_1형식으로 변환
                    convStr = new String(str.getBytes("KSC5601"),"8859_1");
            } catch (UnsupportedEncodingException e) { System.out.println("fromKorean 오류");
            }
            return convStr;
    }

 
 
 
 
 
 
 
    /**
     * Null을 ""로 변환
     * @param str
     * @return
     */
    
    public static String checkNull(String str) {
            String strTmp;
            if (str == null)
                    strTmp = "";
            else
                    strTmp = str;
            return strTmp;
    }
    
 
 
 
 
 
 
    /**
     * Null을 0로 변환
     * @param str
     * @return
     */
    
    
    public static String checkNull2(String str) {
            String strTmp;
            if (str == null)
                    strTmp = "0";
            else
                    strTmp = str;
            return strTmp;
    }
 
 
 
 
 
 
 
    /**
     * 공백을 &nbsp;로 변환
     * @param str
     * @return
     */
    
    public static String null2nbsp(String str) {
            String strTmp;
            if (str == null || str.equals(""))
                    strTmp = "&nbsp;";
            else
                    strTmp = str;
            return strTmp;
    }








    /**
     * TextArea에서 입력받은 캐리지 리턴값을 <BR>태그로 변환
     * @param comment
     * @return
     */
    
     public static String nl2br(String comment) {
             int length = comment.length();
             StringBuffer buffer = new StringBuffer();

             for (int i = 0; i < length; ++i) {
                     String comp = comment.substring(i, i+1);
                     if ("\r".compareTo(comp) == 0) {
                             comp = comment.substring(++i, i+1);
                             if ("\n".compareTo(comp) == 0)
                                     buffer.append("<BR>\r");
                             else
                                     buffer.append("\r");
                     }

                     buffer.append(comp);
             }
             return buffer.toString();
     }
 
 
 
 
 
 
 
 
 
    /**
     * TextArea에서 입력받은 따옴표 앞에 \ 추가
     * @param comment
     * @return
     */
    
    public static String addslash(String comment) {
           if(comment == null){
                     return "";
           }

            int length = comment.length();
            StringBuffer buffer = new StringBuffer();

            for (int i = 0; i < length; ++i) {
                    String comp = comment.substring(i, i+1);
                    if ("'".compareTo(comp) == 0) {
                            buffer.append("\'");
                    }

                    buffer.append(comp);
            }
            return buffer.toString();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * TextArea에서 입력받은 < or > 를 특수문자로 변환
     * @param comment
     * @return
     */
    
    public static String html2spec(String comment) {
           if(comment == null){
                return "";
           }

            int length = comment.length();
            StringBuffer buffer = new StringBuffer();

            for (int i = 0; i < length; ++i) {
                    String comp = comment.substring(i, i+1);
                    if ("<".compareTo(comp) == 0)
                            buffer.append("&lt;");
                                  else if (">".compareTo(comp) == 0)
                                       buffer.append("&gt;");
                                  else
                                        buffer.append(comp);
            }
            return buffer.toString();
    }







    /**
     * 브라우저에 외부 자바스크립트를 연결한다.
     * @param src 자바스크립트 위치 (src)
     * @return
     */
      public static String setScriptSrc(String src) {
                  
              StringBuffer result = new StringBuffer();
          
              result.append("<script language=JavaScript src='" + src + "'>");
              result.append("</script>");

              return result.toString();
                  
      }





    /**
     * 이동위치(url)
     * @param url
     * @return
     */
    public static String redirect(String url){
            String result =  "<META http-equiv='refresh' content='0;url="+url+"'>";
            return result;          
            
    }







    /**
    * 토큰 함수
    * @param inputStr 입력스트링
    * @param token 토근문자
    * @param token_count 나눠질 토근 갯수
    * @return
    */
     public static String[] selectToken(String inputStr, String token, int token_count) {
                    String token_str[] = new String[token_count];
                    StringTokenizer st = new StringTokenizer(inputStr, token);

                    int count = 0;
                    while(st.hasMoreElements()) {
                             if(count >= token_count)
                                    break;
                              token_str[count] = (String)st.nextElement();
                              
                              // System.out.println("token_str["+count+"]:"+ token_str[count]);
                              
                              count++;
                     }

                     return token_str;
      }








    /**
    * 텍스트 파일을 생성한다.
    * @param file 파일이 저장될 경로. 시스템의 절대 경로를 넣어준다. (ex. "/home/hsboy/text.txt")
    * @param contents 저장될 파일의 내용
    */
    public static void writeFile(String file, String contents)throws IOException{
        FileWriter filew = new FileWriter(new File(file));
        filew.write(contents,0,contents.length());
        filew.close();
    }





    /**
    * 디렉토리를 만드는 메소드
    * @param dir 디렉토리 명
    */
    
         
    public static void makeDir(String dir) {
    	    // 특정 디렉토리만 생성 할수 있도록 변경 
            /*
    		File f = new File(dir);

            if(!f.exists()){
                    // 디렉토리 생성
                   f.mkdirs();
            }
			*/
            return;
    }
    
    
    
    
    
    
    
    
    /**
     * 디렉토리삭제 메소드
     * @param dir
     */
    public static void delDir(String dir) {
    	// 특정 디렉토리만 삭제 할수 있도록 변경 
    	/*
	       // HTTP Request 로 입력된 값을 통해 임의파일 접근(생성, 수정, 삭제 등) 방지 
           if(file.contains("..") || file.contains("/")) return false ;

    	    File f = new File(dir);
            // 존재한다면
            if(f.exists()){
                    // 디렉토리면
                    if(f.isDirectory()) {
                            // 파일의 갯수가 0일떄
                            if((f.listFiles().length) == 0) {
                                    // 디렉토리 삭제
                                    f.delete();
                            }
                    }
            }
    	 */
    }









    /**
     * 파일삭제 메소드
     * @param file
     * @return
     */
    public static boolean delFile(String file) {
            boolean chk = false;
  	      // HTTP Request 로 입력된 값을 통해 임의파일 접근(생성, 수정, 삭제 등) 방지 
            if(file.contains("..") || file.contains("/")) return false ;

            File f = new File(file);
            // 존재하면
            if(f.exists()) {
                    // 파일이면
                    if (f.isFile()) {
                            f.delete();
                            chk = true;
                    }
            }
            return chk;
    }








    /**
     * 파일을 지우는 메소드
     * @param path
     */
    public static void deleteFiles(String path) {
    	  
    	  // 지정된 디렉토리 만 삭제 할수 있도록 처리 
    	
    	  /*
	      // HTTP Request 로 입력된 값을 통해 임의파일 접근(생성, 수정, 삭제 등) 방지 
          if(SourceName.contains("..") || SourceName.contains("/")) return 0 ;

          File file = new File(path);
          File[] files = file.listFiles();

          if (files == null) {
                  return;
          }

          if (files.length != 0) {
                  for (int i =  0; i < files.length; i++) {
                          if (files[i].isFile()) {
                                  files[i].delete();
                                  System.out.println(files[i].getName() + " is Delete!");
                          } else {
                                  deleteFiles(files[i].getPath());
                                  System.out.println("Call the Recursive 'deleteFiles' Method..");
                          }
                  }
          }
          */
    }








    /**
     * 파일까지 다지우고 디렉토리 삭제
     * @param path
     */
    public static void deleteDirs(String path) {
        
    	// 지정된 디렉토리만 삭제 처리 
    	
    	/*
    	    // HTTP Request 로 입력된 값을 통해 임의파일 접근(생성, 수정, 삭제 등) 방지 
            if(SourceName.contains("..") || SourceName.contains("/")) return 0 ;

            File dir = new File(path);
            File[] dirs = dir.listFiles();

            if (dirs == null) {
                    return;
            }

            if (dirs.length != 0) {
                    for (int i = 0; i < dirs.length; i++) {
                            if (dirs[i].listFiles().length == 0) {
                                    dirs[i].delete();
                                    System.out.println(dirs[i].getName() + " is Delete!");
                            } else {
                                    deleteDirs(dirs[i].getPath());
                                    System.out.println("Call the Recursive 'deletedirs' Method..");
                            }
                    }
            }
            dir.delete();
            System.out.println(dir.getName() + " is Delete!");
            */
    	
    }





    /**
      * 문자열중 특정문자를 치환한다 
      *@param    str 대상문자열
      *@param    src 치환당할 문자
      *@param    tgt 치환할 문자
      *@return   완성된 문자열
      */
      public static String replace(String str, String src, String tgt) {
            StringBuffer buf = new StringBuffer();
            String ch  = null;
    
            if (str == null || str.length() == 0) return "";
    
            int i=0; 
            int len = src.length();
            while (i<str.length()-len+1) {
    
                    ch = str.substring(i,i+len);
                    if (ch.equals(src)) {
                            buf.append(tgt);
                            i = i + len;
                    }
                    else    {
                            buf.append(str.substring(i,i+1));
                            i++;
               }
            }
    
            if (i < str.length())
                    buf.append(str.substring(i,str.length()));
    
            return buf.toString();
      }








    /**
     * 브라우저에 출력할 수 있도록 [<,>,\",\n ]의 특수문자 변환
     * @param str 문자열
     * @return    브라우저 출력용으로 포맷된 문자열
     **/
    public static String toHTML(String str) {
    
          String res = str;
    
          res = replace(res,"<" ,"&lt;");
          res = replace(res,">" ,"&gt;");
          res = replace(res,"\"","&quot;");
          res = replace(res,"\n","<br>");
    
          return res;
    }
    
    
    
    
    
    
    /**
     * 문자열을 년월일 형태 [yy/mm]로 리턴한다.
     *@param sep 구분자 "-" or "/" 
     */
     public static String toYYMM(String data, String sep ) {
           if(data == null || data.length() < 8 ) return "";
           return data.substring(2,4) + sep + data.substring(4,6);
     }








    /**
     * 문자열을 년월일 형태 [yy-mm-dd]로 리턴한다.
     *@param sep 구분자 "-" or "/" 
     */
     public static String toYYMMDD(String data, String sep ) {
           if(data == null || data.length() < 8 ) return "";
           return data.substring(2,4) + sep + data.substring(4,6) + sep + data.substring(6,8);
     } 










    /**
     * 문자열을 년월일 시간 형태 [yyyy-MM-dd hh:mm:ss]로 리턴한다.
     */
     public static String toYMDHMS(String data ) {
           if(data == null || data.length() < 14 ) return "";
           String dt = data.substring(0,4) + "-" 
                             + data.substring(4,6) + "-" 
                             + data.substring(6,8) + " "
                             + data.substring(8,10) + ":" 
                             + data.substring(10,12) + ":" 
                             + data.substring(12,14);
           return dt;
     }
    






    /**
     * 문자열을 년월일 형태 [yyyy-mm-dd]로 리턴한다.
     */
     public static String toYMD(String data ) {
           if(data == null || data.length() < 8 ) return "";
           return data.substring(0,4) + "-" + data.substring(4,6) + "-" + data.substring(6,8);
     }



     /**
      * 문자열을 년월일 형태 [yyyy-mm-dd]로 리턴한다.
      */
      public static String toYMDUserFormat(String data, String sep1, String sep2 ) {
            if(data == null || data.length() < 8 ) return "";
            return data.substring(0,4) + sep1 + data.substring(4,6) + sep2 + data.substring(6,8);
      }




    /**
     * Object 값을 체크하여 String 으로 리턴한다.
     */
     public static String setString( Object obj ) {
        if(obj == null) return "";    
        String str = "";
        if (obj.getClass().getName().equals("java.lang.String")) {
            str = (String)obj;
        } else if (obj.getClass().getName().equals("java.math.BigDecimal")) {
            str = ((BigDecimal)obj).toString();
        } else if (obj.getClass().getName().equals("java.sql.Timestamp")) {
             str = ((Timestamp)obj).toString();
        }
    
        return str;
      }
      
      
      
      
      
      
      
      
      
    /**
    * Object 값을 체크하여 BigDecimal로 리턴한다.
    */
    public static BigDecimal setBigDecimal( Object obj ) {
            if(obj == null) return null;    
            BigDecimal num = null;
            if (obj.getClass().getName().equals("java.math.BigDecimal")) {
                  num = (BigDecimal)obj;
            } else if (obj.getClass().getName().equals("java.lang.String")) {
                  if (((String)obj).equals("") == false ) num = new BigDecimal( (String)obj );
            }
    
            return num;
    }






    /**
    * Object 값을 체크하여 Timestamp 리턴한다.
    */  
    public static Timestamp setTimestamp( Object obj ) {
            if(obj == null) return null;
            Timestamp ts = null;
            if (obj.getClass().getName().equals("java.sql.Timestamp")) {
                  ts = (Timestamp)obj;
            } else if (obj.getClass().getName().equals("java.lang.String")) {
                  if (((String)obj).length() == 10 )
                    ts = Timestamp.valueOf( (String)obj + " 00:00:00" );
                  if (((String)obj).length() > 10 )
                    ts = Timestamp.valueOf( (String)obj );
            }
    
            return ts;
    }







    /**
     * 16진수 유니코드를 문자열로 변경
     * @param uni 유니코드
     * @return
     */
    public static String uni2str(String uni){
    
           String str = "" ;
    
           StringTokenizer str1 = new StringTokenizer(uni,"\\u") ;
    
           while(str1.hasMoreTokens()) {
              String str2 =  str1.nextToken() ;       
                  int i = Integer.parseInt(str2,16) ;    
                  str += (char)i ;        
           }              
           
           return str ;
    }








    /**
     * 문자열을 유니코드로 변경 
     * @param str 문자열
     * @return 16진수
     */
    
    public static String str2uni(String str) {
    
           String uni = "" ;
    
           for ( int i = 0 ; i < str.length() ; i++)  {    
                   char chr = str.charAt(i) ;
                   String hex = Integer.toHexString(chr) ;
                   uni +=  "\\u"+hex ;    
           }     

           return uni ;    
    }






    /**
     * 브라우저의 캐싱기능을 막는다.
     * @param response
     * @throws UnsupportedEncodingException
     * @throws IOException
     */ 
    public static void setResponse(HttpServletResponse response) throws UnsupportedEncodingException, IOException {
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expires","-1");
            response.setContentType("text/html; charset=euc-kr");
    }
    





    /**
     * 쿠키값을 설정한다
     * @param res
     * @param name 쿠키이름
     * @param value 쿠키값
     */
    public static void setCookie(HttpServletResponse res, String name, String value){                        
              Cookie cookie=new Cookie(name,value);
              cookie.setPath("/");
              res.addCookie(cookie);        
    }








    /**
     * 숫자형식의 문자열을 입력받아 세자리마다 "," 를 셋팅한다.
     * @param str
     * @return
     */
    public static String setComma(String str){
    
            if (str == null || str == "") return "";
    
            String num = str.trim(); 
            int len = 0;            
            int point = num.indexOf( '.' );         
    
            if( point == -1 ) len = num.length();   
            else len = point;
    
            String newnum = num.substring(0,len);
            String numarray[] = new String[ len/3 + 1];
            int index = 0;
            int chk =0;     
    
            for(int i = len ; i > 0 ; i -= 3 ){
                    if((i-3) > 0){
                            numarray[index] = newnum.substring(i - 3, i );
                    }else{
                            numarray[index] = newnum.substring(0, i);
                    }
                    index++;
            }
    
            newnum = "";    
    
            for(int i = index-1; i >= 0 ;i-- ){
                    if( i < (index-1) ) newnum += ","; 
                    newnum += numarray[i];
            }               
    
            if( point > -1 ) newnum += num.substring( point, num.length());
            return newnum;
    }









    /**
     * 문자열 원본에서 해당문자를 삭제한다.
     * @param str 문자열 원본
     * @param deli 삭제할 문자
     * @return
     */
    public static String removeChar(String str, String deli){

            if(str == null || str.equals("")) return "";
            
        String result = "";
            
            StringTokenizer st = new StringTokenizer(str, deli);
            StringBuffer buffer = new StringBuffer();
            for(; st.hasMoreTokens(); buffer.append(st.nextToken()));
            result = buffer.toString();
            
            return result;
    }













    /**
     * 파일 이동기능
     * @param SourceName  옮길 파일명
     * @param TargetName  옮겨질 파일명
     * @return
     */     

    public static long moveFile(String SourceName,String TargetName) {   
              if(SourceName.length() == 0 || TargetName.length() == 0 ||SourceName == TargetName)
     
              return 0 ;
             
              // HTTP Request 로 입력된 값을 통해 임의파일 접근(생성, 수정, 삭제 등) 방지 
              if(SourceName.contains("..") || SourceName.contains("/")) return 0 ;

              // HTTP Request 로 입력된 값을 통해 임의파일 접근(생성, 수정, 삭제 등) 방지 
              if(TargetName.contains("..") || TargetName.contains("/")) return 0 ;
              
              RandomAccessFile sourceFile;
              RandomAccessFile targetFile;
      
              File srcFile = new File(SourceName);  // <- 소스파일 설정
              
              int c,count=0; 
                      
              try {   
                      sourceFile = new RandomAccessFile(SourceName,"r");
                      targetFile = new RandomAccessFile(TargetName,"rw");
                              
                      while( (c = sourceFile.read() )  != -1) {                        
                              targetFile.write(c);
                              count++;
                      }

                      sourceFile.close();
                      targetFile.close();
                      srcFile.delete();  //<- 삭제...

              }
              catch(FileNotFoundException e)
              {
                      return 0;
              }
              catch(IOException e)
              {
                      return 0;
              }
                    
              return count;
      }
      








    /**
     * 파일을 읽어 문자열로 반환한다.
     * @param filename 파일명(filename)
     * @return
     * @throws IOException
     */
    public static String fileRead(String filename) throws IOException {
         // HTTP Request 로 입력된 값을 통해 임의파일 접근(생성, 수정, 삭제 등) 방지 
         if(filename.contains("..") || filename.contains("/")) return "" ;
         FileInputStream Fi = null;
         
        File myFile = new File(filename);
        byte buff[] = new byte[(int) myFile.length()];
        try { 
        	Fi = new FileInputStream(myFile);
        	Fi.read(buff);
        	Fi.close();
        }
		catch( IOException ioe )
		{
			System.out.println("fileRead IOException");
		}
		finally 
		{
			Fi.close();
		}            
        return new String(buff);
    }







    /**
     * 문자열을 파일에 쓴다.
     * @param str 문자열(data)
     * @param filename 파일명(filename)
     * @return
     */
    public static int  fileWrite(String str,String filename)  {
            try{
 	           // HTTP Request 로 입력된 값을 통해 임의파일 접근(생성, 수정, 삭제 등) 방지 
 	           if(filename.contains("..") || filename.contains("/")) return -1 ;
            	
                File myFile = new File(filename);
                FileWriter o = new FileWriter(myFile);
                o.write(str);
                o.close();
                return 0;
            }catch(IOException e){
            
                    return -1;
            }               
    }













    /**
     * 파일 다운로드
     * @param req
     * @param res
     * @param file_url
     * @param file_name
     * @return
     * @throws IOException
     */
    public static int unix_download(HttpServletRequest req, HttpServletResponse res,String file_url,String file_name, String type)
                                                                                                      throws IOException {

            BufferedInputStream fin = null;
            BufferedOutputStream outs = null;

            try {



    	           // HTTP Request 로 입력된 값을 통해 임의파일 접근(생성, 수정, 삭제 등) 방지 
    	           if(file_name.contains("..") || file_name.contains("/")) return -1 ;


                    String strClient = req.getHeader("User-Agent");

                    //String filepath=file_url;
                    String filepath = "/home/tktwas/window_download"; // 환경 파일 등으로 고정 할 것

                    if (filepath==null) return -1;

                    File file=new File(filepath);
                    int filesize = (int)file.length();
                    byte b[]=new byte[(int)file.length()];


                    //strClient.indexOf("MSIE 5.5")>-1
                    if(strClient.indexOf("MSIE 5.5") != -1) {
                            //res.reset();
                            res.setContentType("application/"+type+"; charset=euc-kr");
                            //res.setHeader("Accept-Ranges", "bytes");
                            // res.setHeader("Content-Disposition", "filename=\""+ file_name + "\";");
                            //res.setHeader("Content-Disposition", "filename="+ file_name + ";");
                            
        
                            res.setHeader("Content-Disposition", "attachment; filename="+ file_name + ";");
                            res.setContentLength(filesize);
                            //res.setHeader("Content-Disposition", "inline;filename=\""+ file_name + "\";");
                            //res.setHeader("Content-Transfer-Encoding", "binary;");
                            //res.setHeader("Content-Length", ""+ file.length());
                                //res.setHeader("Pragma", "no-cache;");
                                //res.setHeader("Expires", "0;");
                             res.setHeader("Content-Transfer-Encoding", "binary;");
                             res.setHeader("Pragma", "no-cache;");
                             res.setHeader("Expires", "-1;");


                            //res.setContentType("application/"+type);
                            //res.setHeader("Content-Disposition", "attachment; filename=\""+file_name+"\";");
                            //res.setHeader("Content-Transfer-Encoding", "binary;");
                            //res.setHeader("Content-Length", ""+ file.length());

                    }  else {
                            res.setContentType("application/"+type+"; charset=euc-kr");
                            res.setHeader("Content-Disposition", "attachment; filename=\""+file_name+"\";");
                            res.setHeader("Content-Transfer-Encoding", "binary;");
                            res.setHeader("Content-Length", ""+ file.length());

                    }




                    if(filesize > 0)  {
                	    try{
                		    fin = new BufferedInputStream(new FileInputStream(file));
                            outs = new BufferedOutputStream(res.getOutputStream());

                            int read = 0;
                            //try {
                            while ((read = fin.read(b)) != -1)      {
                                            outs.write(b,0,read);
                            }
                		} catch (IOException e) { 
                			System.out.println("unix_download execption");
                			// e.printStackTrace();
                		} finally{
                            outs.close();
                            fin.close();
                		}

                    }


            } catch(Exception e) {
                    PrintWriter out = new PrintWriter (res.getWriter());
                    System.err.println("file down load fail Exception Occured");
                    out.println("file down load fail");
                    out.close();
                    if(outs!=null) outs.close();
                    if(fin!=null) fin.close();
                    return -1;
            }
            return 0;
    }
    
    
    
    
    
    
    
    
    
    
    /**
     * 파일 다운로드
     * @param req
     * @param res
     * @param file_url
     * @param file_name
     * @return
     * @throws IOException
     */
    public static int window_download(HttpServletRequest req, HttpServletResponse res,String file_url,String file_name, String type)
                                                                                                      throws IOException {

            BufferedInputStream fin = null;
            BufferedOutputStream outs = null;

            try {

                    String strClient = req.getHeader("User-Agent");

                    
                    // String filepath=file_url;
                    String filepath = "/home/tktwas/window_download"; // 환경 파일 등으로 고정 할 것

                    if (filepath==null) return -1;

        	        // HTTP Request 로 입력된 값을 통해 임의파일 접근(생성, 수정, 삭제 등) 방지 
        	        if(file_name.contains("..") || file_name.contains("/")) return -1 ;
                    
                    File file=new File(filepath);
                    int filesize = (int)file.length();
                    byte b[]=new byte[(int)file.length()];


                    //strClient.indexOf("MSIE 5.5")>-1
                    if(strClient.indexOf("MSIE 5.5") != -1) {
                            res.setContentType("application/"+type+"; charset=euc-kr");
                            res.setHeader("Content-Disposition", "attachment; filename="+ file_name + ";");
                            res.setContentLength(filesize);
                            res.setHeader("Content-Transfer-Encoding", "binary;");
                            res.setHeader("Pragma", "no-cache;");
                            res.setHeader("Expires", "-1;");

                    } else {
                            res.setContentType("application/"+type+"; charset=euc-kr");
                            res.setHeader("Content-Disposition", "attachment; filename=\""+file_name+"\";");
                            res.setHeader("Content-Transfer-Encoding", "binary;");
                            res.setHeader("Content-Length", ""+ file.length());

                    }




                    if(filesize > 0)  {
                	    try{
                            fin = new BufferedInputStream(new FileInputStream(file));
                            outs = new BufferedOutputStream(res.getOutputStream());

                            int read = 0;
                            //try {
                            while ((read = fin.read(b)) != -1)      {
                                            outs.write(b,0,read);
                            }
                		} catch (IOException e) { 
                			System.out.println("window_download execption");
                			// e.printStackTrace();
                		} finally{
                            outs.close();
                            fin.close();
                		}
                    }


            } catch(Exception e) {
                    PrintWriter out = new PrintWriter (res.getWriter());
                    System.err.println("file down load fail Exception Occured");
                    out.println("file down load fail");
                    out.close();
                    if(outs!=null) outs.close();
                    if(fin!=null) fin.close();
                    return -1;
            }
            return 0;
    }
    








    /**
     * 오늘 날자를 가져온다. (ex, 2002년 09월 13일)
     */
    public static String getToday(){
                GregorianCalendar calendar = new GregorianCalendar();
                String  sDate = null;  /* 출력 날짜 */ 

                try{
                            DecimalFormat df = new DecimalFormat("00"); 
                            sDate = calendar.get(Calendar.YEAR) + "년 "
                                  + df.format(calendar.get(Calendar.MONTH)+1) + "월 "
                                  + df.format(calendar.get(Calendar.DATE))  + "일 "
                                  + df.format(calendar.get(Calendar.HOUR_OF_DAY)) + "일 ";
                                 
                           return sDate;
              }catch(Exception e){
                            return sDate;
              }
    } 









    /**
       * 특정 URL 의 HTML 내용을 String 으로 받아 온다. <BR>
       * 예) JSP 에서 아래와 같이 사용한다. 
       * <p>
       * <pre>
       * &lt;%<BR>
       *  String html = getUrl("http://aboutjsp.com");<BR>
       *  out.print(html);<BR>
       * %&gt;
       * </pre>
       * <p>
       * 주의) html 내에서 URI 로 링크 혹은 Frame 이 짜여져 있다면 제대로 보이지 않을수 있다.
       * 해당 URL로 접근해 브라우져의 소스보기에서 나타나는 내용을 가져오는것 뿐이다.
       *
       * @param url HTML을 가져올 URL
       * @return String 가져온 HTML
       */
      public static String getUrl(String url) throws Exception {
              int len;
              InputStream input = (new URL(url)).openStream();
              byte b[] = new byte[64000];
              StringBuffer sb = new StringBuffer();
              while ((len = input.read(b, 0, b.length)) > 0)
                      sb.append(new String(b, 0, len));
              input.close();
              return sb.toString();
      }











    /**
     * &lt; 를 &amp;lt; 로 변경, 
     * &gt; 를 &amp;gt; 로 변경, 
     * 공백을 &amp;nbsp; 로 변경
     *
     * @param contents 내용
     * @return String 변환된 내용
     */
    public static String toHtmlTag(String contents) {
      int len = contents.length();
      int linenum = 0, i = 0;

      for (i = 0; i < len; i++)
        if ((contents.charAt(i) == '<') | (contents.charAt(i) == '>') | (contents.charAt(i) == '&') | (contents.charAt(i) == ' '))
          linenum++;

      StringBuffer result = new StringBuffer(len + linenum * 5);

      for (i = 0; i < len; i++) {
        if (contents.charAt(i) == '<') {
            result.append("&lt;");
        } else if (contents.charAt(i) == '>') {
            result.append("&gt;");
        } else if (contents.charAt(i) == '&') {
            result.append("&amp;");
        } else if (contents.charAt(i) == ' ') {
            if (i == 0) {
                result.append("&nbsp;");
            } else {
                if (contents.substring(i - 1, i).equals(" ") | contents.substring(i - 1, i).equals("\n")) {
                    if (contents.substring(i + 1, i + 2).equals(" ")) {
                    result.append("&nbsp;");
                    } else {
                        if (contents.substring(i - 2, i - 1).equals(" ")) {
                            result.append(" ");
                        } else {
                            result.append("&nbsp;");
                        }
                    }
                } else {
                    result.append(" ");
                }
          }
        } else {
            result.append(contents.charAt(i));
        }
      }
      return result.toString();
    }












    /**
     * 지정한 길이 보다 길경우 지정한 길이에서 자른후 "..."을 붙여 준다.
     * 그보다 길지 않을때는 그냥 돌려준다. char 단위로 계산 (한글도 1자)
     * @param str 원본 String
     * @param amount String 의 최대 길이 (이보다 길면 이 길이에서 자른다)
     * @return String 변경된 내용
     */
    public static String crop(String str, int amount) {
            if (str==null) return "";
            String result = str;
            if(result.length()>amount) result=result.substring(0,amount)+"...";
            return result;
    }








    /**
     * 지정한 길이 보다 길경우 지정한 길이에서 자른후 맨뒷부분에 지정한 문자열을 붙여 준다.
     * 그보다 길지 않을때는 그냥 돌려준다. char 단위로 계산 (한글도 1자)
     * @param str 원본 String
     * @param amount String 의 최대 길이 (이보다 길면 이 길이에서 자른다)
     * @param trail amount 보다 str 이 길경우 amount 만큼만 자른다음 trail 을 붙여 준다.
     * @return String 변경된 내용
     */
    public static String crop(String str, int amount, String trail) {
            if (str==null) return "";
            String result = str;
            if(result.length()>amount) result=result.substring(0,amount)+trail;
            return result;
    }








    /**
     * 지정한 길이 보다 길경우 지정한 길이에서 자른후 맨뒷부분에 지정한 문자열을 붙여 준다.
     * 그보다 길지 않을때는 그냥 돌려준다. Byte 단위로 계산 (한글 = 2자)
     * <p>
     * @param str 원본 String
     * @param amount String 의 최대 길이 (이보다 길면 이 길이에서 자른다)
     * @param trail amount 보다 str 이 길경우 amount 만큼만 자른다음 trail 을 붙여 준다.
     * @return String 변경된 내용
     */
    public static String cropByte(String str, int amount, String trail) throws UnsupportedEncodingException {
                    if (str==null) return "";
                    String tmp = str;
                    int slen = 0, blen = 0;
                    char c;
                    if(tmp.getBytes("euc-kr").length>amount) {
                            while (blen+1 < amount) {
                                    c = tmp.charAt(slen);
                                    blen++;
                                    slen++;
                                    if ( c  > 127 ) blen++;  //2-byte character..
                            }
                            tmp=tmp.substring(0,slen)+trail;
                    }
                    return tmp;
    }











    /**
     * 주어진 문자열에 URL(www)이 포함되어 있을 경우 이를 Link 걸어준다.
     * <p>
     * @param str 원본 String
     * @return String 변경된 내용
     */
    public static String wwwLink(String str) {
                  if (str==null) return "";
          
                  String tmp = str;
                  int itmp = 0;
                  int wend = 0;

                  StringBuffer sb = new StringBuffer();
                  sb.append("");
          
                  while(tmp.indexOf("http://")>-1) {
                          itmp = tmp.indexOf("http://");
                          wend = tmp.indexOf(" ",itmp);
                          if (wend>tmp.indexOf(")",itmp) && tmp.indexOf(")",itmp)>-1) wend = tmp.indexOf(")",itmp);
                          if (wend>tmp.indexOf("<",itmp) && tmp.indexOf("<",itmp)>-1) wend = tmp.indexOf("<",itmp);
                          if (wend==-1) wend = tmp.length();
                          sb.append(tmp.substring(0,itmp));
          
                          if(itmp>3 && tmp.substring(itmp-3,itmp).indexOf("=")>-1) {
                                  wend = tmp.indexOf("</a>",itmp)+3;

                                  if (wend==2) wend = tmp.indexOf(">",itmp);
                                          sb.append(tmp.substring(itmp,wend));
                          } else {
                                  sb.append("<a href=\""+tmp.substring(itmp,wend)+"\" target=\"_blank\" >");
                                  sb.append(tmp.substring(itmp,wend));
                                  sb.append("</a>");
                          }
          
                          tmp=tmp.substring(wend);
                  }
                  sb.append(tmp);
          
                  tmp = sb.toString();
                  sb.setLength(0);

                  while(tmp.indexOf("www.")>-1) {
                          itmp = tmp.indexOf("www.");
                          wend = tmp.indexOf(" ",itmp);
                          if (wend>tmp.indexOf(")",itmp) && tmp.indexOf(")",itmp)>-1) wend = tmp.indexOf(")",itmp);
                          if (wend>tmp.indexOf("<",itmp) && tmp.indexOf("<",itmp)>-1) wend = tmp.indexOf("<",itmp);
                          if (wend==-1) wend = tmp.length();
                          sb.append(tmp.substring(0,itmp));
                          if(itmp>10 && tmp.substring(itmp-10,itmp).indexOf("=")>-1) {
                                  wend = tmp.indexOf("</a>",itmp)+3;
                                  if (wend==2) wend = tmp.indexOf(">",itmp);
                                          sb.append(tmp.substring(itmp,wend));
                          } else {
                          sb.append("<a href=\"http://"+tmp.substring(itmp,wend)+"\" target=\"_blank\" >");
                          sb.append(tmp.substring(itmp,wend));
                          sb.append("</a>");

                          }
          
                          tmp=tmp.substring(wend);
                  }
                  sb.append(tmp);

                  return sb.toString();

    }










    /**
      * 시스템 콘솔에서 실행된 명령의 결과를 String 으로 받아 온다
      * @param cmd 실행한 콘솔 명령
      * @return 실행 결과
      */
     public static String getCmd(String cmd) throws IOException{
            Process p = Runtime.getRuntime().exec(cmd);
            String s = "";
            InputStream in = null;
            BufferedReader br = null;
            try{
	            in = p.getInputStream();
	            br = new BufferedReader(new InputStreamReader(in));
	            String temp = "";
	            while ( (temp = br.readLine()) != null) {
	                    s += temp + "\n";
	            }
    		} catch (IOException e) { 
    			System.out.println("getCmd Exception");
    			// e.printStackTrace();
    		} finally{
                br.close();
                in.close();
    		} 
            return s;
     }  


 











    /**
      * 대소문자를 상관하지 않고 str 문자열에 포함된 keyword 를 찾아서 
      * 원래의 문자에 붉은색 폰트태그를 삽입한 문자열 반환 Method markKeyword.
      * <p>by kenu at http://okjsp.pe.kr </p>
      * @param str
      * @param keyword
      * @return String
      */
     public static String markKeyword(String str, String keyword) {
         keyword =
             replace(
                 replace(replace(keyword, "[", "\\["), ")", "\\)"),
                 "(", "\\(");

         Pattern p = Pattern.compile( keyword , Pattern.CASE_INSENSITIVE );
         Matcher m = p.matcher( str );
         int start = 0;
         int lastEnd = 0;
         StringBuffer sbuf = new StringBuffer();
         while( m.find() ) {
             start = m.start();
             sbuf.append( str.substring(lastEnd, start) )
                 .append("<font color='red'>"+m.group()+"</font>" );
             lastEnd = m.end();
         } 
         return sbuf.append(str.substring(lastEnd)).toString() ;
     }














    /**
     * Email 아이디 숨기기  
     * @param email
     * @return
     */
    
    // hsboy@aboutjsp.com 을 넣으면  *****@aboutjsp.com 을 return 합니다.
    public static String hideEmail(String email) {
    
        StringBuffer str = new StringBuffer();
        try {
            int at = email.indexOf("@");
                    
            for ( int i = 0 ; i < at ; i ++ ) {
                    
                str.append("*");
            }
                    
            str.append(email.substring(at,email.length()));
                    
        } catch ( Exception e ) {
            return email;
        }
        
        return str.toString();
    }












    /**
     * 비밀번호 자리수 만큼 '*'표시만들기 
     * @param str
     * @param len
     * @return
     */
    //String password ="123456789";
    //password=replaceStar(password,2);
    //출력 12*******
    public static String replaceStar(String str, int len){  
       String returnStr="";
       for(int i=0; i<str.length();i++){
            if(i<len)returnStr=returnStr+str.substring(i,i+1);
            else returnStr=returnStr+"*";
       }
       return returnStr;
    }










    /**
     * 입력된 자리수 만큼 랜덤 수자를 만들어 문자열러 반환하는 메소드
     * @param arraySize
     * @return
     */
    public static String makeRandom(int arraySize) {
            int value = 0;
            StringBuffer randomStrBuffer = new StringBuffer();
            int[] random = new int[arraySize];
            
            SecureRandom oRandom =  new SecureRandom();
            
            for(int i=0; i<arraySize; i++) {
                    boolean bool = true;  
            while(bool) {  
                value = 1 + (int)( oRandom.nextInt() * 45);  
                for(int x=0; x<i+1; x++) {  
                    if(random[x] == value) break;  
                    if(x==i) bool = false;  
                }  
            } // end of while loop
            
            random[i] = value; 
            randomStrBuffer.append(String.valueOf(random[i]));

            } // end of for loop
            
            return randomStrBuffer.toString();
    }









    /**
     * 해당 토큰으로 문자열을 파싱하는 함수
     * @param str
     * @param token
     * @return
     */
    public static String[] parsingToken(String str, String token) {
                    
          StringTokenizer st = new StringTokenizer(str, token);
                   
          int token_count = st.countTokens();
          String[] keys = new String[token_count];
                    
          int index = 0;
          while(st.hasMoreElements()) {
                 keys[index] = (String)st.nextElement();
                 index++;
          }                       
                    
          return keys;
                    
    }


    /**
     * 해당 토큰으로 문자열을 파싱하는 함수
     * @param str
     * @param token
     * @return 
     */
    public static String parsingString(String str, String token, int idx) {
        StringTokenizer st = new StringTokenizer(str, token);
        
        int token_count = st.countTokens();
    	
        for (int i=0 ; i < idx; i++) { st.nextElement(); }
        
        String value = "";
        if ( st.hasMoreElements() ) {  value = (String)st.nextElement(); }
        
        return value;
    }

    





    //  2진 코드 에서 101010 1의 위치를 받아서 _ _ _ 1_ _ 식을로 스트링을 반환
    // SELECT 문 WHERE  절 LIKE  에 쓰기위함
    
    // bit_position = 1있는 위치
    // non_position_char = 1이 아닌위치에 대신할 문자
    // totalbit = 전체 코드 자리수
    public static String code_Select(int bit_position, String non_position_char, int totalbit) {
             String result = "";
             int i = 0;
             for (i=1 ; i < bit_position; i++) {
                     result += non_position_char;
             }
    
             result += "1";
    
             for(int j=(totalbit-i) ; j > 0 ; j--) {
                     result += non_position_char;
             }
    
             return result;
     }







    //   코드생성 메소드
    // 001, 002형식의
    // number : 입력받는수
    // cipher : 만들고자하는 자리수

    public static String codeGen(int number, int cipher) {
            String code = Integer.toString(number);

            int len = code.length();

            StringBuffer buf = new StringBuffer();

            for(int i=len; i<cipher; i++) {
                    buf.append("0");
            }
            buf.append(code);

            return buf.toString();
    }









    //   001, 002 형식의 코드를 숫자로 바꾸는 메소드
    public static int codeDecode(String code) {

            int length = 0;

            if(code == null || code.equals("")) {
                         return 0;
            }

            length = code.length();


            // 한번이라도 0이 아닌수 뒤에 나온 0은 제거 대상이 아니라 숫자에 포함된다.
            boolean nonzero = false;

            StringBuffer buf = new StringBuffer();

            for (int i = 0; i < length; ++i)
            {
                    String comp = code.substring(i, i+1);

                    if(nonzero) {
                            buf.append(comp);
                    }
                    else {
                            if ("0".compareTo(comp) != 0) {
                                    nonzero= true;
                                    buf.append(comp);
                            }
                    }

            }
            return Integer.parseInt(buf.toString());

    }















    /**
    * 해당 문자열에서 원하는 문자열만 뽑아내는 함수
    * @param str
    * @param keyword
    * @return
    */
    public static String FindString(String str, String keyword) {

           Pattern p = Pattern.compile(keyword , Pattern.CASE_INSENSITIVE );
           Matcher m = p.matcher( str );

           StringBuffer sbuf = new StringBuffer();

            while( m.find() ) {

                 sbuf.append(m.group());
                 //System.out.println(m.group());
            }
            return sbuf.toString();
    }







    /*

    "난수를 생성하기 위해서는 클래스 java.security.SecureRandom 사용을 권고합니다.
    <시큐어코딩>
    importjava.security.SecureRandom;
    class Foo 
    {
      public static void main(String args[]) 																																				
      {
      SecureRandom oRandom = new SecureRandom();

        // 1~10까지의 정수를 랜덤하게 출력
        int i = oRandom.nextInt(10) + 1;
        System.out.println(i);
     }
   }

   <참고>
   http://cwe.mitre.org/data/definitions/330.html"																																				
    */



    /**
     * 자리스 만큼 랜덤한 대문자 영문자를 리턴한다.
     * @param len
     * @return
     */
    public static String getRandomAlpha(int len) {
         //Random random =new Random();
    	
    	 SecureRandom oRandom =  new SecureRandom();
    	
         //char alphaNum[] = {'A','B','C','E','F','0','1','2','3','4','5','6','7','8','9'};
         char alphaNum[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

         String str = "";
         for(int j = 0; j < len ; j++) {
              str += alphaNum[ oRandom.nextInt(25) ];
         }
                 
         return str;
    }




     /**
       * 주어진 문자을 주어진 길이로 나누어서 배열로 리턴해준다.
       * article : 4500자의 문자이고
       * parseLang : 1000자로 나눈다면
       * 5개의 String배열이 리턴 된다.
       * @param article
       * @param parseLang
       * @return  
       */
      public static String[] StringParseToArray(String article, int parseLang) {
          String[] returnArray = new String[StringParseLang(article,parseLang)];
          
          for(int i=0; i < StringParseLang(article,parseLang); i++) {
              if(StringParseLang(article,parseLang) == 1) {
                  returnArray[i] = new String(article);
              } else if(StringParseLang(article,parseLang) == i+1) {
                  returnArray[i] = new String(article.substring(i*parseLang));    
              } else {
                  returnArray[i] = new String(article.substring(i*parseLang,(i+1)*parseLang));
              }
              
          }
          return returnArray;
      }








    /**
     * 
     * @param article
     * @param parseLang
     * @return  
     */
    public static int StringParseLang(String article, int parseLang) {
        if(article.length()%parseLang !=0) {
            return article.length()/parseLang+1;
        } else {
            return article.length()/parseLang;
        }
    }








    /**
     * 현재 날짜를 가져오는 함수 (사용자 정의 형태)
    * @param   type    날짜형 타입
    * @return  날짜
     */
    public static String getTime(String type){
            return sysTime(type);
    }







    /**
    * 주어진 형식에 따른 날짜나 시간을 가져오는 함수
    * @param   type    날짜형 타입
    * @return  날짜
    */
    public static String sysTime(String type){
            java.util.Date date = new java.util.Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat(type);
            return dateFormat.format(date);
    }






    /**
    * 현재 날짜를 기본형으로 가져오는 함수 (YYYY-MM-DD)
    * @return  날짜
    */
    public static String getTime(){
            return sysTime("yyyy-MM-dd");
    }

    
    
    
    

    
    
    
    
	/**
	 * Object[] 의 복제. Object의 Array 를 복제(clone)하여
	 * 새로운 Instance를 만들어 줍니다.
	 *
	 * @param objects java.lang.Object[]
	 * @return java.lang.Object[]
	 *
	 * @see clone(java.lang.Object)
	 * @see clone(java.lang.Vector)
	 */
	public static Object[] clone(Object[] objects)
	{
		int length = objects.length;
		Class c = objects.getClass().getComponentType();
		Object array = Array.newInstance(c, length);

		for(int i=0;i<length;i++){
			Array.set(array, i, Utility.clone(objects[i]));
		}
		return (Object[])array;
	}

	/**
	 * Object 의 복제. 일반적으로 <code>java.lang.Object.clone()</code> 함수를
	 * 를 사용하여 Object를 복제하면 Object내에 있는 Primitive type을 제외한 Object
	 * field들은 복제가 되는 것이 아니라 같은 Object의 reference를
	 * 갖게 된다.<br>
	 * 그러나 이 Method를 사용하면 각 field의 동일한 Object를 새로 복제(clone)하여
	 * 준다.
	 *
	 * @param object java.lang.Object
	 * @return java.lang.Object
	 *
	 * @see clone(java.lang.Object[])
	 * @see clone(java.lang.Vector)
	 */
	public static Object clone(Object object)
	{
		Class c = object.getClass();
		Object newObject = null;
		try {
			newObject = c.newInstance();
		}
		catch(Exception e ){
			return null;
		}

		Field[] field = c.getFields();
		for (int i=0 ; i<field.length; i++) {
			try {
				Object f = field[i].get(object);
				field[i].set(newObject, f);
			}
			catch(Exception e){
				return null;
			}
		}
		return newObject;
	}

	/**
	 * Vector 의 복제. 일반적으로 Vector Object를 clone()을 하면
	 * Vector내의 Element Object는 새로 생성되는 것이 아니라
	 * 동일한 Object에 대한 reference만 새로 생성되기 때문에 같은 Element Object를
	 * reference하는 Vector를 생성하게 된다. 그러나 이 method를 사용하면
	 * Vector내의 모든 Element Object도 새로 복제하여 준다.
	 *
	 * @param objects java.util.Vector
	 * @return java.util.Vector
	 *
	 * @see clone(java.lang.Object)
	 * @see clone(java.lang.Object[])
	 */
	public static Vector clone(Vector objects)
	{
		Vector newObjects = new Vector();
		Enumeration e = objects.elements();
		while(e.hasMoreElements()){
			Object o = e.nextElement();
			newObjects.addElement(Utility.clone(o));
		}
		return newObjects;
	}

	/**
	 * Entity Class의 null string field 초기화.
	 * <p>
	 * Entity class 내에 있는 java.lang.String형의 field는 DB의 Column과
	 * 밀접한 연관이 있는 경우가 많다. 이러한 Entity Field가 특히 GUI의 특정
	 * TextFiled에 표현되어야 하는 경우도 많다. 만약 그 String Filed가 null일
	 * 경우 일일이 검사를 한다는 것은 참으로 답답한 일이 아닐 수 없다.
	 * <p>
	 * 이 method는 여하한의 Object 내에 있는 모든 java.lang.String형의 field 변수 중
	 * null 값으로 된 field를 길이가 0 인 blank string("")으로 초기화 시켜준다.
	 * <p>
	 *
	 * <xmp>
	 * Sample Code:
	 * public java.util.Vector selectAll() throws Exception
	 * {
	 *  java.util.Vector list = new Vector();
	 * 	Statement stmt = null;
	 * 	ResultSet rs =null;
	 * 	try{
	 * 		stmt = conn.createStatement();
	 * 		String query = "select " +
	 * 			"id, " + 
	 * 			"name, " + 
	 * 			"desc " + 
	 * 			"from THE10 " +
	 * 			"order by id ";
	 * 
	 * 		rs = stmt.executeQuery(query);
	 * 
	 * 		while ( rs.next() ) {
	 * 			AdminAuth entity = new AdminAuth();
	 * 			entity.id = rs.getString("id");
	 * 			entity.name = rs.getString("name");
	 * 			entity.desc = rs.getString("desc");
	 * 			Utility.fixNull(entity);
	 * 			list.addElement(entity);
	 * 		}
	 * 	}
	 * 	finally {
	 * 		try{rs.close();}catch(Exception e){}
	 * 		try{stmt.close();}catch(Exception e){}
	 * 	}
	 * 	return list;
	 * }
	 *</xmp>
	 *
	 * @param java.lang.Object Object내의 public java.lang.String 형의 
	 *        member variable에만 영향을 준다.
	 *
	 * @see fixNullAll(java.lang.Object)
	 * @see fixNullAndTrim(java.lang.Object)
	 * @see fixNullAndTrimAll(java.lang.Object)
	 * @author WonYoung Lee, wyounglee@lgeds.lg.co.kr
	 */
	public static void fixNull(Object o)
	{
		if ( o == null ) return;
		
		Class c = o.getClass();
		if ( c.isPrimitive() ) return;
		
		Field[] fields = c.getFields();
		for (int i=0 ; i<fields.length; i++) {
			try {
				Object f = fields[i].get(o);
				Class fc = fields[i].getType();
				
				if ( fc.getName().equals("java.lang.String") ) {
					if ( f == null ) fields[i].set(o, "");
					else	fields[i].set(o, f);
				}
			}
			catch(Exception e){
			}
		}
	}

	/**
	 * Entity Class의 재귀적인 null string field 초기화.
	 * <p>
	 * fixNull() 과 유사한 기능을 하는데, java.lang.String field 뿐만 아니라
	 * Member 변수 중 Array, Object 가 있으면 재귀적으로 쫒아 가서 String형을
	 * blank string("")으로 만들어 준다.<br>
	 * 정상적인 String인 경우 trim()을 시켜준다.<br>
	 * 만약 Array나, Vector가 null일 경우 Instance화는 하지 않는다.
	 *
	 * <p>
	 * 재귀적으로 추적되는 만큼, 부모와 자식간에 서로 양방향 reference를 갖고 있으면
	 * 절대 안된다. Stack Overflow를 내며 JVM을 내릴 것이다.
	 *
	 *
	 * @param java.lang.Object Object내의 public String 형뿐만 아니라, Object[], Vector 등과
	 *        같은 public Object형 Member Variable에 영향을 준다.
	 *
	 * @see fixNull(java.lang.Object)
	 * @see fixNullAndTrim(java.lang.Object)
	 * @see fixNullAndTrimAll(java.lang.Object)
	 *
	 * @author 김형기
	 */
	public static void fixNullAll(Object o)
	{
		if ( o == null ) return;
		
		Class c = o.getClass();
		if ( c.isPrimitive() ) return;
		
		if( c.isArray() ) {
			int length = Array.getLength(o);
			for(int i=0; i<length ;i++){
				Object element = Array.get(o, i);
				Utility.fixNullAll(element);
			}
		} 
		else {
			Field[] fields = c.getFields();
			for (int i=0 ; i<fields.length; i++) {
				try {
					Object f = fields[i].get(o);
					Class fc = fields[i].getType();
					if ( fc.isPrimitive() ) continue;
					if ( fc.getName().equals("java.lang.String") ) {
						if ( f == null ) fields[i].set(o, "");
						else	fields[i].set(o, f);
					} 
					else if ( f != null ) {
						Utility.fixNullAll(f);
					}
					else {} // Some Object, but it's null.
				}
				catch(Exception e) {
				}
			}
		}
	}

	/**
	 * Entity Class의 null string field 초기화 &amp; trim().
	 * <p>
	 * Entity class 내에 있는 java.lang.String형의 field는 DB의 Column과
	 * 밀접한 연관이 있는 경우가 많다. 이러한 Entity Field가 특히 GUI의 특정
	 * TextFiled에 표현되어야 하는 경우도 많다. 만약 그 String Filed가 null일
	 * 경우 일일이 검사를 한다는 것은 참으로 답답한 일이 아닐 수 없다.
	 * <p>
	 * 이 method는 여하한의 Object 내에 있는 모든 java.lang.String형의 field 변수 중
	 * null 값으로 된 field를 길이가 0 인 blank string("")으로 초기화 시켜준다.
	 * 만약 null이 아닌 정상적인 String이 대입되어 있으면 강제적으로 trim()를
	 * 시켜준다.
	 * <p>
	 * 이 때 trim() 함수는 java.lang.String 의 trim()을 사용하지 않았다.
	 *
	 * <xmp>
	 * Sample Code:
	 * public java.util.Vector selectAll() throws Exception
	 * {
	 *  java.util.Vector list = new Vector();
	 * 	Statement stmt = null;
	 * 	ResultSet rs =null;
	 * 	try{
	 * 		stmt = conn.createStatement();
	 * 		String query = "select " +
	 * 			"id, " + 
	 * 			"name, " + 
	 * 			"desc " + 
	 * 			"from THE10 " +
	 * 			"order by id ";
	 * 
	 * 		rs = stmt.executeQuery(query);
	 * 
	 * 		while ( rs.next() ) {
	 * 			AdminAuth entity = new AdminAuth();
	 * 			entity.id = rs.getString("id");
	 * 			entity.name = rs.getString("name");
	 * 			entity.desc = rs.getString("desc");
	 * 			Utility.fixNull(entity);
	 * 			list.addElement(entity);
	 * 		}
	 * 	}
	 * 	finally {
	 * 		try{rs.close();}catch(Exception e){}
	 * 		try{stmt.close();}catch(Exception e){}
	 * 	}
	 * 	return list;
	 * }
	 *</xmp
	 *
	 * @param java.lang.Object Object내의 public java.lang.String 형의 
	 *        member variable에만 영향을 준다.
	 *
	 * @see fixNull(java.lang.Object)
	 * @see fixNullAll(java.lang.Object)
	 * @see fixNullAndTrimAll(java.lang.Object)
	 * @see trim(String)
	 * @author WonYoung Lee, wyounglee@lgeds.lg.co.kr
	 */
	public static void fixNullAndTrim(Object o)
	{
		if ( o == null ) return;
		
		Class c = o.getClass();
		if ( c.isPrimitive() ) return;
		
		Field[] fields = c.getFields();
		for (int i=0 ; i<fields.length; i++) {
			try {
				Object f = fields[i].get(o);
				Class fc = fields[i].getType();
				if ( fc.getName().equals("java.lang.String") ) {
					if ( f == null ) fields[i].set(o, "");
					else {
						String item = Utility.trim( (String)f );
						fields[i].set(o, item);
					}
				}
			}
			catch(Exception e){
			}
		}
	}

	/**
	 * Entity Class의 재귀적인 null string field 초기화  &amp; trim().
	 * <p>
	 * fixNull() 과 유사한 기능을 하는데, java.lang.String field 뿐만 아니라
	 * Member 변수 중 Array, Object 가 있으면 재귀적으로 쫒아 가서 String형을
	 * blank string("")으로 만들어 준다.<br>
	 * 정상적인 String인 경우 trim()을 시켜준다.<br>
	 * 만약 Array나, Vector가 null일 경우 Instance화는 하지 않는다.
	 *
	 * <p>
	 * 재귀적으로 추적되는 만큼, 부모와 자식간에 서로 양방향 reference를 갖고 있으면
	 * 절대 안된다. Stack Overflow를 내며 JVM을 내릴 것이다.
	 *
	 *
	 * @param java.lang.Object Object내의 public String 형뿐만 아니라, Object[], Vector 등과
	 *        같은 public Object형 Member Variable에 영향을 준다.
	 *
	 * @see fixNull(java.lang.Object)
	 * @see fixNullAll(java.lang.Object)
	 * @see fixNullAndTrim(java.lang.Object)
	 * @see trim(String)
	 *
	 * @author 김형기, 이원영
	 */
	public static void fixNullAndTrimAll(Object o)
	{
		if ( o == null ) return;
		
		Class c = o.getClass();
		if ( c.isPrimitive() ) return;
		
		if( c.isArray() ) {
			int length = Array.getLength(o);
			for(int i=0; i<length ;i++){
				Object element = Array.get(o, i);
				Utility.fixNullAndTrimAll(element);
			}
		} 
		else {
			Field[] fields = c.getFields();
			for (int i=0 ; i<fields.length; i++) {
				try {
					Object f = fields[i].get(o);
					Class fc = fields[i].getType();
					if ( fc.isPrimitive() ) continue;
					if ( fc.getName().equals("java.lang.String") ) {
						if ( f == null ) fields[i].set(o, "");
						else {
							String item = Utility.trim( (String)f );
							fields[i].set(o, item);
						}
					} 
					else if ( f != null ) {
						Utility.fixNullAndTrimAll(f);
					}
					else {} // Some Object, but it's null.
				}
				catch(Exception e) {
				}
			}
		}
	}

	
	
	
	
	
	
	/**
	 * 
	 * @param e java.lang.Throwable
	 */
	public static String getStackTrace(Throwable e) {
		java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
		java.io.PrintWriter writer = new java.io.PrintWriter(bos);
		System.out.println("예외 발생"); // e.printStackTrace(writer);
		writer.flush();
		return bos.toString();
	}

	
	
	
	
	
	
	/**
	 * Remove special white space from both ends of this string. 
	 * <p>
	 * All characters that have codes less than or equal to 
	 * <code>'&#92;u0020'</code> (the space character) are considered to be 
	 * white space. 
	 * <p>
	 * java.lang.String의 trim()과 차이점은 일반적인 white space만 짜르는 것이
	 * 아니라 위에서와 같은 특수한 blank도 짤라 준다.<br>
	 * 이 소스는 IBM HOST와 데이타를 주고 받을 때 유용하게 사용했었다.
	 * 일반적으로 많이 쓰이지는 않을 것이다.
	 *
	 * @param  java.lang.String
	 * @return trimed string with white space removed
	 *         from the front and end.
	 * @author WonYoung Lee, wyounglee@lgeds.lg.co.kr
	 */
	public static String trim(String s) {
		int st = 0;
		char[] val = s.toCharArray();
		int count = val.length;
		int len = count;

		while ((st < len) && ((val[st] <= ' ') || (val[st] == ' ') ) )   st++;
		while ((st < len) && ((val[len - 1] <= ' ') || (val[len-1] == ' ')))  len--;
		
		return ((st > 0) || (len < count)) ? s.substring(st, len) : s;
	}

	
	
	

	/** 
    *		벡터를 받아 구분자 구분지어 스트링으로  리턴. 
    *       @param java.util.Vector 
    *       @parma String delemeter                         
    *       @return String
    **/
	public String addToken(java.util.Vector v, String delemeter) throws Exception  {
		StringBuffer strBuffer = new StringBuffer();
		if(v == null) return strBuffer.toString();
		for(int i=0; i<v.size(); i++)  {
			strBuffer.append( v.elementAt(i) );
			strBuffer.append(delemeter);
		}
		return strBuffer.toString();
	}
	
	

	
	
	
	
	/** 
    *		스트링을 받아 구분자로 나누어 벡터에 담아 리턴. 
    *       @param String buffer                            
    *       @parma String delemeter                         
    *       @return Vector                                  
    **/
	public java.util.Vector getToken(String buffer, String delemeter) throws Exception  {
		java.util.Vector v = new java.util.Vector();
		if(buffer == null) return v;
		java.util.StringTokenizer tmp = new java.util.StringTokenizer(buffer, delemeter);
		while(tmp.hasMoreElements())  {
			v.addElement(tmp.nextElement());
		}
		return v;
	}

		
	 /**
	   * 입력받은 String을 원하는 길이만큼 원하는 문자로 오른쪽을 채워주는 함수
	   * @param  str   입력받은 String
	   * @param  len   원하는 길이
	   * @param  pad   오른쪽으로 채워줄 문자
	   * @return String 지정된 문자로 채워진 String
	   **/
	  public  static String RPAD(String str, int len, char pad) throws UnsupportedEncodingException {
		 String result = str;
         int slen = 0, blen = 0;
		 int templen = 0;
         String tmp = str;
         char c;
		 if ( str == null || str.equals("")) {
			 templen = len;
			 result = "";
		 } else if ( len >= result.getBytes("euc-kr").length ) {
			 templen = len - result.getBytes("euc-kr").length;
			 result = str;

			 // System.out.println("RPAD str:"+ str + " len:"+ len +" templen:"+ templen );
			 
		 } else if ( len < result.getBytes("euc-kr").length ) {
			 result = "";
			 templen = 0;
             while (blen < len) {
                 c = tmp.charAt(slen);
                 blen++;
                 slen++;
                 if ( c  > 127 ) blen++;  //2-byte character..
             }

			 // System.out.println("RPAD str:"+ str + " len:"+ len +" templen:"+ templen +" slen:" + slen );
             
             result = str.substring(0,slen);
		 }
		 for (int i = 0; i < templen; i++) {
			  result = result + pad;
		 }
		 return result;
	  }

 
	  public  static String RPAD(int istr, int len, char pad) throws Exception {
		     String str = intToString( istr );
			 String result = str;
	         int slen = 0, blen = 0;
			 int templen = 0;
	         String tmp = str;
	         char c;
			 if ( str == null || str.equals("")) {
				 templen = len;
				 result = "";
			 } else if ( len >= result.getBytes("euc-kr").length ) {
				 templen = len - result.getBytes("euc-kr").length;
				 result = str;

				 // System.out.println("RPAD str:"+ str + " len:"+ len +" templen:"+ templen );
				 
			 } else if ( len < result.getBytes("euc-kr").length ) {
				 result = "";
				 templen = 0;
	             while (blen < len) {
	                 c = tmp.charAt(slen);
	                 blen++;
	                 slen++;
	                 if ( c  > 127 ) blen++;  //2-byte character..
	             }

				 // System.out.println("RPAD str:"+ str + " len:"+ len +" templen:"+ templen +" slen:" + slen );
	             
	             result = str.substring(0,slen);
			 }
			 for (int i = 0; i < templen; i++) {
				  result = result + pad;
			 }
			 return result;
		  }

	    
	  
	  
	  /**
	   * 입력받은 String을 원하는 길이만큼 원하는 문자로 왼쪽을 채워주는 함수
	   * @param  str   입력받은 String
	   * @param  len   원하는 길이
	   * @param  pad   왼쪽으로 채워줄 문자
	   * @return  String 지정된 문자로 채워진 String
	   **/
	  public static String LPAD(String str, int len, char pad) {
		  String result = str;
		  int templen = len - result.getBytes().length;

		  for (int i = 0; i < templen; i++)
			  result = pad + result;

		  return result;
	  }
	  

	   public static String Checkrev(String str){  
		   String returnval = "";
	       int returnSeq = 0;
	       for(int i=0; i<str.length();i++){
	    	   if (str.substring(i,i+1).equals("1")){
	    		   returnSeq++;
	    	   }else{
	    		   break;
	    	   }
	       }
	       returnval = Integer.toString(returnSeq);
	       return returnval;
	    }

	  public static String Round2(int val, int miman) {
		  int half = 0;
		  half = miman/2;
		  val = val + half;
		  val = (val/miman)*miman;
		 
		  return Integer.toString(val);
	  }

	   public static String Checkrev1(String str){  
		   String returnval = "";
	       
	       for(int i=0; i<str.length();i++){
	    	   if (str.substring(i,i+1).equals("Y")){
	    		   returnval = "true";
	    	   }else{
	    		   returnval = "false";
	    		   break;
	    	   }
	       }
	       return returnval;
	    }
	   
	   
	   /**
	    * 문자열을 년월일 형태 [hhmmss->hh:mm:ss or hh:mm:ss->hhmmss]로 리턴한다.
	    */
	    public static String toHMS(String data ) {
	          if(data == null || data.length() < 6 ) return "";
	          String str = "";
	          if( data.length() == 6 ) {
	          	str = data.substring(0,2) + ":" + data.substring(2,4) + ":" + data.substring(4,6);
	          } else if( data.length() == 8 ) {
	          	str = data.substring(0,2) + data.substring(3,2) + data.substring(6,2);
	          }
	          return str;
	    }
	    
	   /**
	    * 문자열을 년월일 형태 [hhmm->hh:mm or hh:mm->hhmm]로 리턴한다.
	    */
	    public static String toHM(String data ) {
	    	  String hm = data.trim();
	    	  
	    	  System.out.println("hm:" + hm );
	    	  
	          if(hm == null || hm.length() < 4 ) return "";
	          String str = "";
	          if( hm.length() == 4 ) {
	        	  str = hm.substring(0,2) + ":" + hm.substring(2,4) ;
	          } else if( hm.length() == 5 ) {
	        	  str = hm.substring(0,2) + hm.substring(3,5);
	          } else if( hm.length() == 6 ) {
	        	  str = hm.substring(0,2) + ":" + hm.substring(2,4);
	          }
	          return str;
	    }
	    
		/**
		  * 문자열을 년월일 형태 [hhmmss->hh:mm or hh:mm:ss->hh:mm]로 리턴한다.
		*/
	    public static String toHMM(String data ) {
	    	  String hm = data.trim();
	          if(hm == null || hm.length() < 4 ) return "";
	          String str = "";
	          if( hm.length() == 4 ) {
	          	str = hm.substring(0,2) + ":" + hm.substring(2,4) ;
	          } else if( hm.length() == 5 ) {
		          	str = hm.substring(0,2) + hm.substring(3,5);
	          } else if( hm.length() == 6 ) {
	        	  str = data.substring(0,2) + ":" + data.substring(2,4);
	          } else if( data.length() == 8 ) {
	        	  str = data.substring(0,2) + ":" + data.substring(3,5); 
	          }
	          return str;
	}
	    
    /**
 	* 입력받은 String에 특수문자가 포함되어 있는지 확인(아이디, 비밀번호)
	* @param  str   입력받은 String
	* @return  boolean 결과(true:특수문자 포함, false:특수문자 미포함)
	**/
	public static boolean checkSpecial(String str) {
		String check = "abcdefghijklmnopqrstuvwxyz1234567890-_";
		for(int i = 0; i < str.length(); i++){
			if(check.indexOf(str.substring(i, i + 1)) < 0){
				return true; 
			}
		}
		return false;
	}
	
	
	
	/**
 	* 입력받은 String에 int에 해당하는 위치의 권한 리턴 
	* @param  str     입력받은 String
	* @param  index   입력받은 int
	* @return  boolean 결과(true:권한, false:미권한)
	**/
	public static boolean checkAuthority(String str, int index) {
		if(str.length() < index){
			System.out.println("Wrong Index : String size = " + str.length() + ", Index = " + index);
			return false;
		}
		
		String check = str.substring(index - 1, index);
		if(check.equals("1")) return true;
		else return false;
    }

    public static String toYmd(String data ) {
          String str = "";       
           
          if ( data.length() >= 8 ){
             str = data.substring(0,4) + "년"  + data.substring(4,6) + "월" + data.substring(6,8) + "일";
          }
          return str;
    }

    public static String toYmdPoint(String data ) {
        String str = ""; 
         
        if ( data.length() >= 8 ){
          str = data.substring(0,4) + "."  + data.substring(4,6) + "." + data.substring(6,8) ;
        }
        return str;
  }


	/**
	 * 해당 토큰으로 문자열을 파싱하여 마지막 데이타만 리턴 하는 함수
	 * @param str
	 * @param token
	 * @return
	 */
	public static String LastparsingToken(String str, String token) {
	                
	      StringTokenizer st = new StringTokenizer(str, token);
	               
	      int token_count = st.countTokens();
	      String keys = "";

	      while(st.hasMoreElements()) {
	             keys  = (String)st.nextElement();
	      }                       
	                
	      return keys;
	                
	}
	
  	/**
  	 * String을 int로 변환
  	 * @param string
  	 * @return
  	 * @throws Exception
  	 */
	
    public static int StringToInt(String Str)
    throws Exception
	{
	    int result;
	    try
	    {
	        result = Integer.parseInt(Str, 10);
	    }
	    catch(Exception e)
	    {
	        result = 0;
	    }
	    return result;
	}
    
    public static int StrToInt(String Str)
	{
	    int result;
	    try
	    {
	        result = Integer.parseInt(Str, 10);
	    }
	    catch(Exception e)
	    {
	        result = 0;
	    }
	    return result;
	}
    
	
	public static boolean StringToBoolean(String Str, String TrueStr)
	    throws Exception
	{
	    boolean result = false;
	    try
	    {
	        if(Str.equals(TrueStr))
	            result = true;
	    }
	    catch(Exception e)
	    {
	    	throw new Exception("Can not convert to Boolean. String");  //throw new Exception((new StringBuilder("Can not convert to Boolean. String : ")).append(Str).append(", True String : ").append(TrueStr).toString());
	    }
	    return result;
	}

	
	
	/**
	 * 숫자형 String을 원화 금액 포맷으로 변환한다.
	 * ( "6480000" --> "6,480,000" )
	 *
	 * @param 	money 금액 문자열
	 * @return 	money	포맷된 금액 문자열
	 * @throws	Exception
	 */
	 
	public static String money(String money) throws Exception {
	    try {
	        return money(money, WON);
	    } catch (NumberFormatException nfe) {
	    	throw new Exception("금액포맷으로 변환하는중 에러가 발생하였습니다."); 
	        //        "[CMM242_01.money] 금액포맷으로 변환하는중 에러가 발생하였습니다. 숫자(" + money +
	        //        ")가 아닙니다. " + nfe.toString());
	    }
	}
	 
	
	
	/**
	 * double 형을 원화 금액 포맷으로 변환한다.
	 * ( 6480000 --> "6,480,000" )
	 *
	 * @param 	money 	금액
	 * @return	money	포맷된 금액 문자열
	 * @throws 	Exception
	 */
	public static String money(double money) throws Exception {
	    try {
	        return money(money, WON);
	    } catch (Exception e) {
	    	throw new Exception("금액포맷으로 변환하는중 에러가 발생하였습니다..");  // throw new Exception("[CMM242_01.money] 금액포맷으로 변환하는중 에러가 발생하였습니다. " +
	                            // e.toString());
	    }
	}

	/**
	 * int 형을 원화 금액 포맷으로 변환한다.
	 *
	 * @param 	money 	금액
	 * @return 	money	포맷된 금액 문자열
	 * @throws Exception
	 */
	public static String money(int money) throws Exception {
	    try {
	        return money((double)money, WON);
	    } catch (Exception e) {
	    	throw new Exception("금액포맷으로 변환하는중 에러가 발생하였습니다..."); // throw new Exception("[CMM242_01.money] 금액포맷으로 변환하는중 에러가 발생하였습니다. " +
	                           //  e.toString());
	    }
	}

	/**
	 * long 형을 원화 금액 포맷으로 변환한다.
	 *
	 * @param 	money 		금액
	 * @return 	money		포맷된 금액 문자열
	 * @throws Exception
	 */
	public static String money(long money) throws Exception {
	    try {
	        return money((double)money, WON);
	    } catch (Exception e) {
	    	throw new Exception("금액포맷으로 변환하는중 에러가 발생하였습니다...."); // throw new Exception("[CMM242_01.money] 금액포맷으로 변환하는중 에러가 발생하였습니다. " +
	                            // e.toString());
	    }
	}

	/**
	 * 숫자형 String을 언어구분에 따라 금액포맷으로 변환한다.
	 * 언어구분 lang은 CMM242_01.WON 또는 CMM242_01.DOLLAR 두가지를 사용할 수 있다.
	 * ( ex. CMM242_01.money("6480000.50", CMM242_01.DOLLAR) --> "6,480,000.50" )
	 *
	 * @param 	money 			금액 문자열
	 * @param 	lang 			언어구분
	 * @return 	dataMoney		포맷된 금액 문자열
	 * @throws 	Exception
	 */
	public static String money(String money, int lang) throws Exception {
	    money = money.trim();
	    if (money == null) {
	        return "0";
	    }
	
	    if (money.equals("")) {
	        return "0";
	    }
	
	    double dataMoney = 0.0;
	
	    try {
	        BigDecimal bd = new BigDecimal(money);
	        dataMoney = bd.doubleValue();
	    } catch (NumberFormatException nfe) {
	    	throw new Exception("금액포맷으로 변환하는중 에러가 발생하였습니다.....");  // throw new Exception(
	                //"[CMM242_01.money] 금액포맷으로 변환하는중 에러가 발생하였습니다. 숫자(" + money +
	                //")가 아닙니다 " + nfe.toString());
	    }
	
	    return money(dataMoney, lang);
	}

	/**
	 * double형을 언어구분에 따라 금액포맷으로 변환한다.
	 * 언어구분 lang은 CMM242_01.WON 또는 CMM242_01.DOLLAR 두가지를 사용할 수 있다.
	 * ( ex. CMM242_01.money(6480000.50, CMM242_01.DOLLAR) --> "6,480,000.50" )
	 *
	 * @param 	money 		금액
	 * @param 	lang 		언어구분
	 * @return 	money		포맷된 금액 문자열
	 * @throws 	Exception
	 */
	public static String money(double money, int lang) throws Exception {
	    String language = null;
	    String country = null;
	    Locale locale = null;
	    DecimalFormat f = null;
	
	    try {
	        switch (lang) {
	        case WON:
	            language = "ko";
	            country = "KR";
	            break;
	        case DOLLAR:
	            language = "en";
	            country = "US";
	        }
	        locale = new Locale(language, country);
	        f = (DecimalFormat) NumberFormat.getCurrencyInstance(locale);
	        f.setPositivePrefix("");
	        f.setNegativePrefix("-");
	        return f.format(money);
	    } catch (Exception e) {
	    	throw new Exception("금액포맷으로 변환하는중 에러가 발생하였습니다....."); // throw new Exception("[CMM242_01.money] 금액포맷으로 변환하는중 에러가 발생하였습니다. " +
	                            // e.toString());
	    }
	}
	
	/**
  	 * byte배열에서 일정부분 Return
  	 * @param bytes
  	 * @param sPoint
  	 * @param length
  	 * @return
  	 * @throws Exception
  	 */
  	public static byte[] getBytes(byte[] bytes, int sPoint, int length) throws Exception{
  		byte[] value = new byte[length];
  		
  		if(bytes.length < sPoint + length){
  			throw new Exception("Length of bytes is less. length : " + bytes.length + " sPoint : " + sPoint + " length : " + length);
  		}
  		
  		for(int i = 0; i < length; i++){
  			value[i] = bytes[sPoint + i];
  		}
	
		return value;
	}
  	
  	/**
  	 * byte배열을 int로 변환
  	 * @param bytes
  	 * @return
  	 * @throws Exception
  	 */
  	public static int byteToInt(byte[] bytes) throws Exception{
  		int result;
  		
  		try{
  			result = Integer.parseInt(new String(bytes).trim());
  		}catch(Exception e){
  			throw new Exception("Can not convert to int. byte : " + new String(bytes));
  		}
  		
  		return result;
  	}

  	
  	/**
  	 * byte배열에서 일정부분을 String으로 Return
  	 * @param bytes
  	 * @param sPoint
  	 * @param length
  	 * @return
  	 * @throws Exception
  	 */
  	public static String getString(byte[] bytes, int sPoint, int length) throws Exception{
  		byte[] value = new byte[length];
  		
  		if(bytes.length < sPoint + length){
  			throw new Exception("Length of bytes is less. length : " + bytes.length + " sPoint : " + sPoint + " length : " + length);
  		}
  		
  		for(int i = 0; i < length; i++){
  			value[i] = bytes[sPoint + i];
  		}
	
		return new String(value).trim();
	}
  	
  	
  	/**
  	 * int를 String으로 변환
  	 * @param integer
  	 * @return
  	 * @throws Exception
  	 */
  	public static String intToString(int integer) throws Exception{
  		String result;
  		
  		try{
  			result = Integer.toString(integer);
  		}catch(Exception e){
  			throw new Exception("Can not convert to String. int : " + integer);
  		}
  		
  		return result;
  	}
  	
  	
  	public static String IntToStr(int integer) {
  		String result;
  		
  		try{
  			result = Integer.toString(integer);
  		}catch(Exception e){
  			return "";
  		}
  		
  		return result;
  	}
 
  	
  	
  	/**
  	 * String을 int로 변환
  	 * @param string
  	 * @return
  	 * @throws Exception
  	 */
  	public static int stringToInt(String string) throws Exception{
  		int result;
  		
  		try{
  			if ( string == null || string.equals("") ){ 
  				System.out.println("stringToInt 입력값이 없습니다. 확인 바랍니다." );
  				string = "0"; 
  			} 
  			result = Integer.parseInt(string, 10);
  		}catch(Exception e){
  			throw new Exception("Can not convert to int. String : " + string);
  		}
  		
  		return result;
  	}
  	
  	
  	/**
  	 * String을 int로 변환
  	 * @param string
  	 * @return
  	 * @throws Exception
  	 */
  	public static double stringTodouble(String string) throws Exception{
  		double result;
  		
  		try{
  			if ( string == null || string.equals("") ){ 
  				System.out.println("stringTodouble 입력값이 없습니다. 확인 바랍니다." );
  				string = "0"; 
  			} 
  			result = Double.parseDouble( string );
  		}catch(Exception e){
  			System.out.println("stringTodouble :"+ string );
  			throw new Exception("Can not convert to int. String : " + string);
  		}
  		
  		return result;
  	}

  	
	public  static ArrayList<HashMap> parsingtData( String StringData, char sep, int Seat_Cnt, int Col_count )
	{
    	//ArrayList<HashMap> SeatHashList = new ArrayList<HashMap>();
		ArrayList<HashMap> SeatHashList = new ArrayList<HashMap>();
	    String pricestr = "";
	    String hash_key_name = "";
	    int j = 0;
	    String org_str = StringData;
	    String new_str = "";
	    String str_data = "";
	    int len = 0;
        int st_point = 0;
	    
        // System.out.println("[parsingtData]");
	    
  	    for ( int i=0 ; i < Seat_Cnt  ; i++ ){
  	       HashMap <String, String> hash = new HashMap<String, String>();
  	       int point = -1;
  	       
  	       for( j = 0; j < Col_count ; j++){
  	          
  	          point = org_str.indexOf( sep, st_point );   
  	          if( point == -1 ) len = org_str.length();   
  	          else len = point;
  	          
  	          str_data = "";
  	          str_data = org_str.substring(st_point,len);
  	          
  	          hash_key_name = "col_"+ Integer.toString(j,10);
  	          hash.put( hash_key_name , str_data );
  	          
  	          // System.out.println(hash_key_name+ ":" + str_data );
  	          
  	          if( point == -1 ) break;
  	          st_point = point + 1;
  	          
  	       }
  	       SeatHashList.add(hash);
  	       if( point == -1 ) break;

	    }
  	  
    	return SeatHashList;
	}
  	
	
	public  static ArrayList<HashMap> parsingtDataToken( String StringData, char sep, int Seat_Cnt, int Col_count )
	{
		
    	//ArrayList<HashMap> SeatHashList = new ArrayList<HashMap>();
		ArrayList<HashMap> SeatHashList = new ArrayList<HashMap>();
		
		try {
		
		    String pricestr = "";
		    String hash_key_name = "";
		    int j = 0;
		    String org_str = StringData;
		    String new_str = "";
		    String str_data = "";
		    int len = 0;
	        int st_point = 0;
	        
	        String token = String.format("%s", sep);
		    
	        System.out.println("[parsingtDataToken] token:"+ token );
		    
	  	    for ( int i=0 ; i < Seat_Cnt  ; i++ ){
	  	       HashMap <String, String> hash = new HashMap<String, String>();
	  	       int point = -1;
	  	       
	  	       
	  	       String[] strarray = selectToken( org_str, token, Col_count );
	  	       
	  	       for( j = 0; j < Col_count ; j++){
	  	    	 
	   	          hash_key_name = "col_"+ Integer.toString(j,10);
	  	          hash.put( hash_key_name , strarray[j] );
	  	          
	  	          System.out.println(hash_key_name+ ":" + strarray[j] );
	  	    	   
	  	       }
	  	       
	  	       SeatHashList.add(hash);
	  	       if( point == -1 ) break;
	
		    }
		} catch (Exception e) {
			System.out.println("예외 발생"); // e.printStackTrace();
			SeatHashList.clear(); 
		}   
    	return SeatHashList;
	}
  	
	
	public  static ArrayList<HashMap> parsingtData( String StringData, char sep, int Seat_Cnt, int Col_count, boolean seat_sort )
	{
    	//ArrayList<HashMap> SeatHashList = new ArrayList<HashMap>();
		ArrayList<HashMap> SeatHashList = new ArrayList<HashMap>();
	    String pricestr = "";
	    String hash_key_name = "";
	    int j = 0;
	    String org_str = StringData;
	    String new_str = "";
	    String str_data = "";
	    int len = 0;
        int st_point = 0;
	    
	    
  	    for ( int i=0 ; i < Seat_Cnt  ; i++ ){
  	       HashMap <String, String> hash = new HashMap<String, String>();
  	       int point = -1;
  	       
  	       for( j = 0; j < Col_count ; j++){
  	          
  	          point = org_str.indexOf( sep, st_point );   
  	          if( point == -1 ) len = org_str.length();   
  	          else len = point;
  	          
  	          str_data = "";
  	          str_data = org_str.substring(st_point,len);
  	          
  	          hash_key_name = "col_"+ Integer.toString(j,10);
  	          hash.put( hash_key_name , str_data );
  	          
  	          System.out.println(hash_key_name+ ":" + str_data );
  	          
  	          if( point == -1 ) break;
  	          st_point = point + 1;
  	          
  	       }
  	       SeatHashList.add(hash);
  	       if( point == -1 ) break;

	    }
  	    
  	    
	  	Comparator<HashMap> comparator = new Comparator<HashMap>()
	  	{
	  		    public int compare(HashMap o1, HashMap o2){
	  		    	if ( o1 == null || o2 == null){
	  		    		return 0;
	  		    	}
	  		    	// ase 
	  		    	
	  		    	String s1, s2 ;
	  		    	s1 = ""; s2 = "";
	  		    	s1 =  o1.get("col_6").toString()
	  		    	    + o1.get("col_2").toString()
	  		    	    + o1.get("col_0").toString()
	  		    	    + o1.get("col_1").toString()
	  		    	    + o1.get("col_3").toString()
  		    	        + o1.get("col_4").toString();
	  		    	
	  		    	s2 =  o2.get("col_6").toString()
	  		    	    + o2.get("col_2").toString()
	  		    	    + o2.get("col_0").toString()
	  		    	    + o2.get("col_1").toString()
	  		    	    + o2.get("col_3").toString()
		    	        + o2.get("col_4").toString();

	  		    	//System.out.println("s1:"+ s1);
	  		    	//System.out.println("s2:"+ s2);
	  		    	return s1.compareTo(s2);
	  		    	//desc
	  		    	// return o2.compareTo(o1);
	  		    	
	  		    }
	  	};
	  	
	  	if ( seat_sort ) { Collections.sort(SeatHashList, comparator ); }	  	    	    
  	  
    	return SeatHashList;
	}
  		
	
	public  static ArrayList<HashMap> parsingtData_3( String StringData, char sep, int Seat_Cnt, int Col_count, boolean seat_sort )
	{
    	//ArrayList<HashMap> SeatHashList = new ArrayList<HashMap>();
		ArrayList<HashMap> SeatHashList = new ArrayList<HashMap>();
	    String pricestr = "";
	    String hash_key_name = "";
	    int j = 0;
	    String org_str = StringData;
	    String new_str = "";
	    String str_data = "";
	    int len = 0;
        int st_point = 0;
	    
	    
  	    for ( int i=0 ; i < Seat_Cnt  ; i++ ){
  	       HashMap <String, String> hash = new HashMap<String, String>();
  	       int point = -1;
  	       
  	       for( j = 0; j < Col_count ; j++){
  	          
  	          point = org_str.indexOf( sep, st_point );   
  	          if( point == -1 ) len = org_str.length();   
  	          else len = point;
  	          
  	          str_data = "";
  	          str_data = org_str.substring(st_point,len);
  	          
  	          hash_key_name = "col_"+ Integer.toString(j,10);
  	          hash.put( hash_key_name , str_data );
  	          
  	          System.out.println(hash_key_name+ ":" + str_data );
  	          
  	          if( point == -1 ) break;
  	          st_point = point + 1;
  	          
  	       }
  	       SeatHashList.add(hash);
  	       if( point == -1 ) break;

	    }
  	    
  	    
	  	Comparator<HashMap> comparator = new Comparator<HashMap>()
	  	{
	  		    public int compare(HashMap o1, HashMap o2){
	  		    	if ( o1 == null || o2 == null){
	  		    		return 0;
	  		    	}
	  		    	// ase 
	  		    	
	  		    	String s1, s2 ;
	  		    	s1 = ""; s2 = "";
	  		    	s1 =  o1.get("col_0").toString()
	  		    	    + o1.get("col_1").toString()
	  		    	    + o1.get("col_2").toString();
	  		    	
	  		    	s2 =  o2.get("col_0").toString()
	  		    	    + o2.get("col_1").toString()
	  		    	    + o2.get("col_2").toString();

	  		    	//System.out.println("s1:"+ s1);
	  		    	//System.out.println("s2:"+ s2);
	  		    	return s1.compareTo(s2);
	  		    	//desc
	  		    	// return o2.compareTo(o1);
	  		    	
	  		    }
	  	};
	  	
	  	if ( seat_sort ) { Collections.sort(SeatHashList, comparator ); }	  	    	    
  	  
    	return SeatHashList;
	}
  		
	public static int parsingSum( List list, int idx ){
	    String tmp = "";
	    int sum = 0;
	    String key_name = "col_"+ Integer.toString(idx,10);
		for (int i = 0 ; i < list.size()  ; i++) {
			Map Seat_ListMap = (Map)list.get(i);
			if ( Seat_ListMap.size() < idx ){ System.out.println("list idx 보다 작으면 안됩니다."); return 0; } 
			tmp = Seat_ListMap.get(key_name).toString(); // 좌석등급코드
			// System.out.println( key_name +":"+ tmp);
			try {
				sum += Utility.StringToInt(tmp);
			} catch (Exception e) {
				System.out.println("예외 발생"); // e.printStackTrace();
			} 
		}
		return sum;
	}
	
  	public static String GetTel( int iPosition, String data ) throws Exception {
	   String cell1 = ""; 
	   String cell2 = ""; 
	   String cell3 = "";
  	   String ret = ""; 
  	   
	   int length_buf = 0;
	   
	   length_buf = data.length();
	   
       switch (length_buf) {
       case 12: // 000-0000-00000 인 경우
	  	      cell1 = data.substring(0,3);  
	  	      cell2 = data.substring(3,7);  
	  	      cell3 = data.substring(7,12);  
	  	      break;
       case 11: // 000-0000-0000 인 경우
	  	      cell1 = data.substring(0,3);  
	  	      cell2 = data.substring(3,7);  
	  	      cell3 = data.substring(7,11);  
	  	      break;
       case 10: /// 서울전화 - 또는 지방 000-000-0000 인 경우
    	      if ( data.substring(0,2).equals("02") ){
    	  	      cell1 = data.substring(0,2);  
    	  	      cell2 = data.substring(2,6);  
    	  	      cell3 = data.substring(6,10);  
    	      }else {
    	  	      cell1 = data.substring(0,3);  
    	  	      cell2 = data.substring(3,6);  
    	  	      cell3 = data.substring(6,10);  
    	      }
	  	      break;
       case 9: // 서울전화 00-000-0000 인 경우
	  	      cell1 = data.substring(0,2);  
	  	      cell2 = data.substring(2,5);  
	  	      cell3 = data.substring(5,9);  
	  	      break;
       case 8: //0000-0000 인경우
	  	      cell1 = data.substring(0,4);  
	  	      cell2 = data.substring(4,8);  
	  	      break;
       case 7: //000-0000 인경우
	  	      cell1 = data.substring(0,3);  
	  	      cell2 = data.substring(3,7);  
	  	      break;
       case 6: //000-000 인경우
	  	      cell1 = data.substring(0,3);  
	  	      cell2 = data.substring(3,6);  
	  	      break;
       case 5: //00-000 인경우
	  	      cell1 = data.substring(0,2);  
	  	      cell2 = data.substring(2,5);  
	  	      break;
	   default : cell1 = data;
       }
       
       if ( iPosition == 1 ) {
    	   ret = cell1;   
       } else if ( iPosition == 2 ) {
    	   ret = cell2;   
       } else if ( iPosition == 3 ) {
    	   ret = cell3;   
       }
       return ret;
		
	}
  	
  	public static String GetPost( int iPosition, String data ) throws Exception {
  		
	   int length_buf = 0;
	   String post1 = "";
	   String post2 = "";
	   String ret = "";
	   
	   length_buf = data.length();

	   if ( length_buf == 6 ) {
		   post1 = data.substring(0,3);  
		   post2 = data.substring(3,6);  
	   } else if ( length_buf > 3 ){
		   post1 = data.substring(0,3);  
		   post2 = data.substring(3,length_buf);  
	   } else {
		   post1 = data;  
	   }
	   
       if ( iPosition == 1 ) {
    	   ret = post1;   
       } else if ( iPosition == 2 ) {
    	   ret = post2;   
       }
       return ret;

	}
  	
  	public static String targetUrl(HttpServletRequest request) {
		String restOfTheUrl = (String) request.getAttribute(
		        HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		return restOfTheUrl;
	}
  	
}



 
















