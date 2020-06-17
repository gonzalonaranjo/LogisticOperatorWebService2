package constants;

public class LogisticOperatorConstants {
	
	/** Nombre de las tablas en BBDD*/
	public static interface TABLE_NAMES {
		/** ORDERS Tabla ORDERS donde van todas las solicitudes de stock al operador logistico*/
		public String ORDERS = "ORDERS";
		/** ORDER_HISTORY Historico de estados de la solicitudes de stock*/
		public String ORDER_HISTORY = "ORDER_HISTORY";
		/** PRODUCT Tabla de Productos*/
		public String PRODUCT = "PRODUCT";
		/** PRODUCT_ORDER Tabla de relaci�n entre solicitudes y productos*/
		public String PRODUCT_ORDER = "PRODUCT_ORDER";
		/** STATUS Estados de las solicitudes */
		public String STATUS = "STATUS";
		/** USERS Tabla de usuarios*/
		public String USERS = "USERS";
		/**USER_PASSWORD tabla de contrase�a de los usuarios*/
		public String USER_PASSWORD = "USER_PASSWORD";
	}
	
	/** Nombre de las columnas de la tabla ORDERS en BBDD*/
	public static interface ORDERS_COLUMNS {
		/** ID */
		public String ID = "ID";
		/** STATUS*/
		public String STATUS = "STATUS";
		/** USERNAME*/
		public String USERNAME = "USERNAME";
		/** CREATED_AT*/
		public String CREATED_AT = "CREATED_AT";
		/** MODIFIED_AT*/
		public String MODIFIED_AT = "MODIFIED_AT";
	}
	
	/** Nombre de las columnas de la tabla ORDERS_HISTORY en BBDD*/
	public static interface ORDER_HISTORY_COLUMNS {
		/** DATE*/
		public String DATE = "DATE";
		/** ID */
		public String ID = "ID";
		/** STATUS*/
		public String STATUS = "STATUS";
	}
	
	/** Nombre de las columnas de la tabla PRODUCT en BBDD*/
	public static interface PRODUCT_COLUMNS {
		/** ID*/
		public String ID = "ID";
		/** NAME*/
		public String NAME = "NAME";
	}
	
	/** Nombre de las columnas de la tabla PRODUCT_ORDER en BBDD*/
	public static interface PRODUCT_ORDER_COLUMNS {
		/** ORDERID*/
		public String ORDERID = "ORDERID";
		/** PRODUCTID*/
		public String PRODUCTID = "PRODUCTID";
		/** QUANTITY*/
		public String QUANTITY = "QUANTITY";
	}
	
	/** Nombre de las columnas de la tabla STATUS en BBDD*/
	public static interface STATUS_COLUMNS {
		/** ID */
		public String ID = "ID";
		/** NAME*/
		public String NAME = "NAME";
	}
	
	/** Nombre de las columnas de la tabla USERS en BBDD*/
	public static interface USERS_COLUMNS {
		/** USERNAME*/
		public String USERNAME = "USERNAME";
		/** NAME*/
		public String NAME = "NAME";
	}
	
	/** Nombre de las columnas de la tabla USER_PASSWORD en BBDD*/
	public static interface USER_PASSWORD_COLUMNS {
		/** USERNAME*/
		public String USERNAME = "USERNAME";
		/** PASSWORD*/
		public String PASSWORD = "PASSWORD";
	}
	
	/** Codigos de tarea asociados a distintos puntos de menu */
	public static interface TASK_CONSTANTS {
		/** Tarea asociada a los pedidos con estado PENDIENTE */
		public String PENDIENTE = "00000000";
		public String ORDER_DETAIL = "00000001";
		public String ADVANCE_STATUS = "00000002";
		
		/**Tarea asociada a los pedidos con estado PROCESADO */
		public String PROCESADO = "00000100";
		
		/**Tarea asociada a los pedidos con estado ENVIADO */
		public String ENVIADO = "00010000";
		/**Tarea asociada a los pedidos con estado ENTREGADO */
		public String ENTREGADO = "01000000";
	}
	
	/** Codigos de identificador asociados a los posibles estados de un pedido */
	public static interface STATUS_CODES {
		/**1 - Estado PENDIENTE */
		public Integer PENDIENTE = 1;
		/**2- Estado PROCESADO */
		public Integer PROCESADO = 2;
		/**3- Estado ENVIADO*/
		public Integer ENVIADO = 3;
		/**4- Estado ENTREGADO*/
		public Integer ENTREGADO = 4;
	}
	
	/** Rutas de las JSP del sistema */
	public static interface JSP_URL {
		/** JSP de error */
		public String ERROR = "/jsp/error.jsp";
		/** JSP de login del sistema*/
		public String LOGIN = "/jsp/login.jsp";
		/** JSP principal del sistema */
		public String MAIN = "/jsp/main.jsp";			
		/** JSP del detalle de una solicitud*/
		public String ORDER_DETAIL = "/jsp/orderDetail.jsp";
		/** JSP de lo solicitudes segun estado  */
		public String ORDERS = "/jsp/orders.jsp";
		
		public String SUCCESS = "/jsp/success.jsp";
	}
}
