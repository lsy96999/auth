package seongyun.auth.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EntitiyUtil {
	//Object -> LocalDateTime
	public static LocalDateTime toLocalDateTime(Object localDateTime) {
		String str = String.valueOf(localDateTime);
		String strRep = str.replace("T", " ");
		return Timestamp.valueOf(strRep).toLocalDateTime();
	}
	
	//Object -> String
	public static String toString(Object str) {
		return String.valueOf(str);
	}
	
	//Object -> Long
	public static Long toLong(Object longg) {
		return Long.parseLong(String.valueOf(longg));
	}
	
	//Object -> Integer
	public static Integer toIntger(Object intt) {
		return Integer.parseInt(String.valueOf(intt));
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
