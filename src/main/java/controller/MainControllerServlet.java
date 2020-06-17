package controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import bean.OrderHistory;
import bean.Orders;
import bean.ProductOrder;
import bean.Status;
import bean.User;
import constants.LogisticOperatorConstants;
import constants.ParameterConstants;
import dao.OrdersDAO;
import dao.StatusDAO;
import restClient.RestCallAPI;
import utils.ControllerUtils;
import utils.HibernateUtil;
import utils.ValidationUtils;

public class MainControllerServlet extends HttpServlet {
	 
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher(LogisticOperatorConstants.JSP_URL.ERROR);
            rd.forward(request, response);
		}
	}
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			process(request, response);
		} catch (Exception e) {
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher(LogisticOperatorConstants.JSP_URL.ERROR);
            rd.forward(request, response);
		}
    }
    
    private void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	//recogemos el valor de la request
		String taskCode = request.getParameter(ParameterConstants.PARAMETER_TASK);
		
		HttpSession session = request.getSession(true);
		switch (taskCode) {
		
			case LogisticOperatorConstants.TASK_CONSTANTS.PENDIENTE: 
				getOrders(request, response, session, LogisticOperatorConstants.STATUS_CODES.PENDIENTE);
				break;
			
			case LogisticOperatorConstants.TASK_CONSTANTS.ORDER_DETAIL:
				getOrderDetail(request, response);
				break;
				
			case LogisticOperatorConstants.TASK_CONSTANTS.ADVANCE_STATUS:
				advanceOrder(request, response);
				break;
			
			case LogisticOperatorConstants.TASK_CONSTANTS.PROCESADO:
				getOrders(request, response, session, LogisticOperatorConstants.STATUS_CODES.PROCESADO);
				break;
				
			case LogisticOperatorConstants.TASK_CONSTANTS.ENVIADO:
				getOrders(request, response, session, LogisticOperatorConstants.STATUS_CODES.ENVIADO);
				break;
			
			case LogisticOperatorConstants.TASK_CONSTANTS.ENTREGADO:
				getOrders(request, response, session, LogisticOperatorConstants.STATUS_CODES.ENTREGADO);
				break;
				
			default: 
				throw new Exception();
		}
    }
    
    private void getOrders(HttpServletRequest request, HttpServletResponse response, HttpSession session, int statusCode) throws Exception {
    	User user = (User) session.getAttribute(ParameterConstants.PARAMETER_USER);
    	
    	if(ValidationUtils.isNull(user)) {
    		throw new Exception();
    	}
    	// listado de pedidos
    	List<Orders> orderList = OrdersDAO.getOrders(user, statusCode);
    	
    	//para hacerlo configurable lo voy a extraer de la base de datos aunque se pierda tiempo de acceso
    	Status actualStatus = StatusDAO.getStatusByCode(statusCode);
    	
    	//comprobamos que no estamos en un estado final
    	Boolean isFinalStatus = ControllerUtils.isFinalStatus(actualStatus);
    	
    	request.setAttribute(ParameterConstants.PARAMETER_ORDER_LIST, orderList);
    	request.setAttribute(ParameterConstants.PARAMETER_STATUS, actualStatus);
    	request.setAttribute(ParameterConstants.PARAMETER_IS_FINAL_STATUS, isFinalStatus);
    	
    	RequestDispatcher rd = this.getServletContext().getRequestDispatcher(LogisticOperatorConstants.JSP_URL.ORDERS);
        rd.forward(request, response);
    }
    
    private void advanceOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	//Recuperamos el id de del pedido
    	Integer orderId = ControllerUtils.getIntegerValueFromRequest(request, ParameterConstants.PARAMETER_ORDER_ID);
    	//Recuperamos el estado que ha venido desde el frontal
    	Integer statusId = ControllerUtils.getIntegerValueFromRequest(request, ParameterConstants.PARAMETER_STATUS_ID);
    	
    	if(ValidationUtils.isNull(orderId) || ValidationUtils.isNull(statusId)) {
    		throw new Exception();
    	}
    	
    	//Obtenemos el objeto order
    	Orders order = OrdersDAO.getOrderById(orderId);
    	
    	if(order.getStatus().getId() != statusId) {
    		//el estado ha sido manipulado en el DOM, incorrecto
    		throw new Exception();
    	}
    	//Modificamos el estado del pedido
    	Status status = new Status();
    	status.setId(ControllerUtils.getNextPosibleStatus(statusId));
    	
    	//Insertamos registro en el historico de estados 
    	OrderHistory orderHistory = new OrderHistory();
    	orderHistory.setOrders(order);
    	orderHistory.setStatus(status);
    	
    	Date date = new Date();
    	orderHistory.setDate(date);
    	
    	List<OrderHistory> orderHistoryList =  order.getOrderHistoryList();
    	orderHistoryList.add(orderHistory);
    	
    	//modificamos el objeto order
    	order.setStatus(status);
    	order.setOrderHistoryList(orderHistoryList);
    	order.setModifiedAt(date);
    	
    	// no es momento de aplicar el patr�n estado, controlamos que el estado del pedido
    	// no es estado final, si es estado final hacemos llamada al ws 
    	if(ControllerUtils.isFinalStatus(status)) {
    		
    		Session session = OrdersDAO.modifyOrderWithoutCommit(order);
    		
    		 //si la peticion es correcta, hacemos commit y soltamos session
    		if(sendStockToShop(order.getProductOrderList())) {
    			HibernateUtil.commitAndCloseSession(session);
    		}

    	} else {    		
    		//Actualizamos en BBDD
    		OrdersDAO.modifyOrder(order);
    	}
    	
    	RequestDispatcher rd = this.getServletContext().getRequestDispatcher(LogisticOperatorConstants.JSP_URL.MAIN);
        rd.forward(request, response);
    }
    
    private void getOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//Recuperamos el id de del pedido
    	Integer orderId = ControllerUtils.getIntegerValueFromRequest(request, ParameterConstants.PARAMETER_ORDER_ID);
    	
    	//Aunque tengamos en la request el status del pedido no lo necesitamos
    	//Obtenemos el objeto order 
    	Orders order = OrdersDAO.getOrderDetailsById(orderId);
    	
    	request.setAttribute(ParameterConstants.PARAMETER_ORDER, order);
    	
    	// no me gusta setearlo en la request porque ya lo tenemos en el objeto order pero da fallo en el renderizado de la jsp 
    	// y no se arreglarlo
    	request.setAttribute(ParameterConstants.PARAMETER_ORDER_PRODUCT_LIST, order.getProductOrderList());
    	
    	RequestDispatcher rd = this.getServletContext().getRequestDispatcher(LogisticOperatorConstants.JSP_URL.ORDER_DETAIL);
        rd.forward(request, response);
    }
    
    
    private boolean sendStockToShop(List<ProductOrder> productOrderList) {
    	RestCallAPI restCall = new RestCallAPI();
    	
    	return restCall.PostOrder(productOrderList);
    }
}