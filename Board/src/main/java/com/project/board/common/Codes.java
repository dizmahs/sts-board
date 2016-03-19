package com.project.board.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 코드
 * @author chocco
 *
 */
public class Codes {
	
	/**
	 * 어플리케이션 코드
	 * @author chocco
	 *
	 */
	public static class Application {
		
		public static final int COMPANY_CODE = 102103;
		
		/**
		 * 한 페이지당 리스트 갯수(리스트)
		 */
		public static final int ROW = 20;
		
		/**
		 * 한 페이지당 나올 페이지 갯수
		 */
		public static final int PAGE_ROW = 10;
		
		/**
		 * 공용 로거 이름
		 */
		public static final String LOGGER = "debug";
		
		/**
		 * API 로거 이름
		 */
		public static final String API_LOGGER = "api";
		
		/**
		 * API 로거 구분자
		 */
		public static final String API_DELIMIT = "|";
		
		/**
		 * 기본형 반환 키값
		 */
		public static final String DEFAULT_RESULT = "resultList";
		
		/**
		 * 기본형 반환 전체 갯수 키값
		 */
		public static final String DEFAULT_RESULT_TOTAL_SIZE = "resultListTotalSize";
		
		public static final class TIME_UNIT {
			public static final long MINUTE = 60;
			public static final long HOUR = Codes.Application.TIME_UNIT.MINUTE * 60;
			public static final long DAY = Codes.Application.TIME_UNIT.HOUR * 24;
		}
	}
	
    
	/**
	 * 
	 * 권한
	 * 
	 * @author Song
	 *
	 */
	public static class Auth {
		
		public static class Account {
			
			public static final long LIMIT_LOGIN_COUNT = 5;	// 5번
			
			public static final long LIMIT_LOGIN_BLOCK_TIME = 30;	// 30분
			
			public static final long LIMIT_LOGIN_BLOCK_TIME_MINUTE = Codes.Application.TIME_UNIT.MINUTE * Codes.Auth.Account.LIMIT_LOGIN_BLOCK_TIME;
			
			public static final long PASSWORD_CHANGE_TIME = Codes.Application.TIME_UNIT.DAY * 90;	// 90일
			
		}
	}
    
	public static class PSEQ {
		public static final int FAQ = 32;		
	}
	
	public static class BCODE {
        public static final String NOTICE = "SJ_NEWS";// 공지사항 
        public static final String RENT = "SJ_LENDING_NEWS"; // 대관공고
        public static final String TENDER = "SJ_BIDDING_NEWS"; // 입찰공고
        public static final String REPORT = "SJ_REPORT"; // 보도자료
        public static final String EVENT = "EVENT"; // 이벤트
        public static final String FAQ = "SJ_FAQ"; // FAQ
        public static final String GALLERY = "SJ_PERFORM_GALLERY";
        public static final String MEDIA = "SJ_REHEARSAL";
        public static final String LOST = "SJ_LOST";
        public static final String REVIEW = "PERFORM_REVIEW";

	}
	
	/**
	 * URI 정보
	 * @author chocco
	 *
	 */
	public static class URI {
	}
	
	
	/**
	 * 
	 * 파일  컨텐츠 타입
	 * @author Administrator
	 *
	 */
	public static class FILE_CONTENT_TYPE {
		// 대표 첨부파일 컨텐츠
		public static final int MAIN = 1;
		public static final int EDITOR = 2;
		public static final int ADD_FILE = 3;
		 /**
         * 웹진용
         * @author Hyun Joon Jang
         *
         */
        public static class WebZine {
             /**
              * PDF 파일
              */
             public static final int PDF = 1;
             /**
              * 메인
              */
             public static final int MAIN_IMAGE = 2;
             /**
              * 목차1
              */
             public static final int TABLE_OF_CONTENTS_1_IMAGE = 3;
             /**
              * 목차2
              */
             public static final int TABLE_OF_CONTENTS_2_IMAGE = 4;
             /**
              * 목차3
              */
             public static final int TABLE_OF_CONTENTS_3_IMAGE = 5;
        }
	}
	
	/**
	 * 
	 * 파일  컨텐츠 타입
	 * @author Administrator
	 *
	 */
	public static class FILE {
		// 대표 첨부파일 컨텐츠
		public static final int MAIN = 1;
		public static final int EDITOR = 2;
		public static final int ADD_FILE = 3;
		public static final int THUMB = 4;
		
	}
	
	/**
	 * API 관련
	 * @author chocco
	 *
	 */
	public static class Api {
		
		/**
		 * 리퀘스트 헤더 타이틀
		 */
		public static final String REQUEST = "REQ";
		
		/**
		 * 리스폰 헤더 타이틀
		 */
		public static final String RESPONSE = "RES";
		
	}

	/**
	 * 알럿창 타입 정보
	 * @author chocco
	 *
	 */
	public static class Alert {
		
		/**
		 * 기본
		 */
		public static final int NONE = 0;
		
		/**
		 * 뒤로 가기
		 */
		public static final int GOBACK = 1;
		
		/**
		 * 페이지 이동
		 */
		public static final int MOVE = 2;
		
		/**
		 * 창종료
		 */
		public static final int CLOSE = 3;
		/**
		 * 히스토리 이동
		 */
		public static final int GO = 4;
	}
	
	/**
	 * HTTP 응답 코드
	 * @author chocco
	 *
	 */
	public static class Http {
		
		public static class Response {
			
			/**
			 * 정상
			 */
			public static final String OK = "200";
			
			/**
			 * 요청 데이터가 존재하지 않습니다.
			 */
			public static final String NOT_FOUND_ERROR = "201";
			
			/**
			 * 인증오류
			 */
			public static final String NOT_AUTH_ERROR = "400";
			
			/**
			 * 필수 입력값 오류
			 */
			public static final String PARAMETER_NOT_FOUND_ERROR = "401";
			
			/**
			 * 데이터값 형식이 올바르지 않습니다.
			 */
			public static final String PARAMETER_TYPE_ERROR = "403";
			
			/**
			 * 시스템 점검중
			 */
			public static final String SYSTEM_WORKING_ERROR = "501";
			
			/**
			 * 디비 입력 오류
			 */
			public static final String SYSTEM_DATABASE_ERROR = "502";
			
			/**
			 * 알수없는 오류
			 */
			public static final String SYSTEM_UNKNOWN_ERROR = "500";
			
			
			
			/**
			 * 응답 메시지
			 */
			private static final Map<String, String> messageMap = new HashMap<String, String>();
			
			// 응답 메세지 초기화
			static {
				messageMap.put(OK, "정상처리 되었습니다.");
				messageMap.put(NOT_FOUND_ERROR, "요청 데이터가 존재하지 않습니다.");
				messageMap.put(NOT_AUTH_ERROR, "인증키 오류입니다.");
				messageMap.put(PARAMETER_NOT_FOUND_ERROR, "필수 입력값 오류입니다.");
				messageMap.put(PARAMETER_TYPE_ERROR, "데이터 값 또는 형식이 올바르지 않습니다.");
				messageMap.put(SYSTEM_WORKING_ERROR, "시스템 점검중입니다.");
				messageMap.put(SYSTEM_DATABASE_ERROR, "디비 오류입니다.");
				messageMap.put(SYSTEM_UNKNOWN_ERROR, "내부 시스템에서 예기치 않은 오류가 발생했습니다.");
			}
			
			/**
			 * 응답메시지
			 * @param code
			 * @return
			 */
			public static String getMessage(String code) {
				return messageMap.get(code);
			}
			
		}
	}
}