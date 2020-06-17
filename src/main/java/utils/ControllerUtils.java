package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import bean.Status;
import constants.LogisticOperatorConstants;

public class ControllerUtils {
	
	
	public static Integer getIntegerValueFromRequest(HttpServletRequest request, String parameterName) {
		return Integer.parseInt(request.getParameter(parameterName));
	}
	
	/**
	 * Devuelve el siguiente posible estado desde un estado en concreto.
	 * En este caso, al ser tan pocos estados y NO permitir RollBack, el siguiente Estado es siempre un numero mas
	 * en cuanto se implemente los rollbacks habra que establecer reglas de transicion de estado
	 * @param status Integer
	 * @return Integer
	 */
	public static Integer getNextPosibleStatus(Integer status) {
		Integer nextStatus = status;
		
		if(status < LogisticOperatorConstants.STATUS_CODES.ENTREGADO) {
			nextStatus++;
		}
		
		return nextStatus;
	}
	
	/**
	 * Comprueba que el estado recibido es final, hardcodeado
	 * @param status Status
	 * @return boolean
	 */
	public static Boolean isFinalStatus(Status status) {
		return status.getId() == LogisticOperatorConstants.STATUS_CODES.ENTREGADO.intValue();
	}
	
	/**
	 * Recupera el Fichero de properties
	 * @return Properties
	 */
	public static Properties getPropertiesFile() {
		Properties propertiesFile = new Properties();
		InputStream iS = null;
		
		try {
			iS = ControllerUtils.class.getClassLoader().getResourceAsStream("resources\\LogisticOperator.properties");
			propertiesFile.load(iS);
		} catch(IOException e) {
			System.out.println(e.toString());
		}
		
		return propertiesFile;
	}
}
