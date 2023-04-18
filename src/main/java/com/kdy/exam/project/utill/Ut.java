package com.kdy.exam.project.utill;

public class Ut {
	   
	   public static boolean empty(Object obj) {
	      if(obj == null) {
	         return true;
	      }
	      // 이 객체가 String이 아니면 제2의범수
	      if( obj instanceof String == false) {
	         return true;
	      }
	      
	      String str = (String) obj;
	      
	      return str.trim().length() == 0;
	   }

	public static Object f(String format, Object ... args) {
		
		return String.format(format, args);
	}
	   
	}