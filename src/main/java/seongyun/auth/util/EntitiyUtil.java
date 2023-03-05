package seongyun.auth.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EntitiyUtil {
	//Object -> LocalDateTime
	public static LocalDateTime toLocalDateTime(Object localDateTime) {
		try {
			String str = String.valueOf(localDateTime);
			String strRep = str.replace("T", " ");
			return Timestamp.valueOf(strRep).toLocalDateTime();
		} catch(NumberFormatException e) {
			log.warn("toLocalDateTime: {}", e.getMessage());
			return LocalDateTime.now();
		} catch(IllegalArgumentException e2) {
			log.warn("toLocalDateTime: {}", e2.getMessage());
			return LocalDateTime.now();
		}
	}
	
	//Object -> String
	public static String toString(Object str) {
		return String.valueOf(str);
	}
	
	//Object -> Long
	public static Long toLong(Object longg) {
		try {
			return Long.parseLong(String.valueOf(longg));
		} catch(NumberFormatException e) {
			log.error("err: {}", e.getMessage());
			return null;
		}
		
	}
	
	//Object -> Integer
	public static Integer toIntger(Object intt) {
		try {
			return Integer.parseInt(String.valueOf(intt));
		} catch (NumberFormatException e) {
			log.error("err: {}", e.getMessage());
			return null;
		}
		
	}
	
	//Object -> List
	public static <T> List<T> toList(Object item){
		List<T> returnList = new ArrayList<>();
		if(item instanceof List) {
			List<?> l = (List<?>) item;
			for(Object e: l) {
				@SuppressWarnings("unchecked")
				T t = (T) e;
				returnList.add(t);
			}
		}
		return returnList;
	}
}
