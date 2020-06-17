package utils;

import java.util.Collection;

public class ValidationUtils {
	
	
	/**
	 * Metodo para validar que un String no es null o vacio 
	 * @param o Object objeto a comprobar
	 * @return
	 */
	public static boolean isBlank(Object o) {
		
		return o == null || ((o instanceof String) && ((String) o).trim().isEmpty());
	}
	
	public static boolean isNotBlank(Object o) {
		return !isBlank(o);
	}
	
	/**
	 * Metodo para validar que un objeto no es null
	 * @param o Object
	 * @return boolean
	 */
	public static boolean isNull(Object o) {
		return o == null;
	}
	
	public static boolean isNotNull(Object o) {
		return !isNull(o);
	}
	
	public static boolean isNotEmpty(Collection<?> c) {
		return c != null && c.size() > 0;
	}
}
