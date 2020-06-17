package services;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import bean.OrderHistory;
import bean.Orders;
import bean.Product;
import bean.ProductOrder;
import bean.Status;
import constants.LogisticOperatorConstants;
import dao.OrdersDAO;
import dao.ProductDAO;
import dao.StatusDAO;
import dao.UserDAO;
import utils.ValidationUtils;


@Path("/service")
public class LogisticOperatorServices {
	
	@GET
	@Path("/getMsg/{name}")
	public Response getMsg(@PathParam("name") String name) {
		  
        String output = "Welcome   : " + name;
  
        return Response.status(200).entity(output).build();
  
    }
	
	@POST
	@Path("/createDataInJSON")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response createDataInJSON(String data) { 

        String result = "Data post: "+data;

        return Response.status(201).entity(result).build(); 
    }
	
	
	@POST
	@Path("/insertNewProduct")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertNewProduct(ProductBean product) throws Exception {
			
			System.out.println("param1 = " + product.getId());
		    System.out.println("param2 = " + product.getName());
			if(ValidationUtils.isNull(product.getId()) || ValidationUtils.isNull(product.getName())) {
				throw new Exception("Elemento no informado");
			}
			
			Product newProduct = new Product();
			newProduct.setId(Integer.parseInt(product.getId()));
			newProduct.setName(product.getName());
			
			ProductDAO.createNewProduct(newProduct);
			String result = product.toString();
			return Response.status(201).entity(result).build();
	}
	
	@POST
	@Path("/insertNewOrder")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertNewOrder(OrderBean order) throws Exception {
		// las dos listas tienen que tener el mismo length, sino error
		if(ValidationUtils.isNull(order.getProductOrder()) || ValidationUtils.isBlank(order.getUserName())) {
			throw new Exception ("Error en los datos de entrada");
		}
		
		//Instanciamos el objeto Order
		Orders newOrder = new Orders();
		newOrder.setId(OrdersDAO.getLastOrderId() + 1);
		
		
		//Preparamos el primer estado del pedido
		Status initialStatus = StatusDAO.getStatusByCode(LogisticOperatorConstants.STATUS_CODES.PENDIENTE);
		newOrder.setStatus(initialStatus);
		
		// ProductOrder
		List<ProductOrder> productOrderList = new ArrayList<>(order.getProductOrder().length); 
		for(int i = 0; i < order.getProductOrder().length; i++) {
			ProductOrderBean pO = (ProductOrderBean) Array.get(order.getProductOrder(), i); // obtenemos el objeto
			ProductOrder prodOrder = new ProductOrder ();
			
			Product product = new Product();
			product.setId(Integer.parseInt(pO.getId()));
			
			prodOrder.setOrder(newOrder);
			prodOrder.setProduct(product);
			prodOrder.setQuantity(Integer.parseInt(pO.getQuantity()));
			
			productOrderList.add(prodOrder);
		}
		
		newOrder.setProductOrderList(productOrderList);
		
		// OrderHistory
		OrderHistory orderHistory = new OrderHistory();
		orderHistory.setStatus(initialStatus);
		Date date = new Date();
		orderHistory.setDate(date);
		orderHistory.setOrders(newOrder);
		
		List<OrderHistory> orderHistoryList = new ArrayList<>(1);
		orderHistoryList.add(orderHistory);
		
		newOrder.setOrderHistoryList(orderHistoryList);
		
		newOrder.setUser(UserDAO.getUserbyUserName(order.getUserName()));
		newOrder.setCreatedAt(date);
		
		// insertamos el order
		OrdersDAO.createNewOrder(newOrder);
		
		String result = "c'est fini";
		return Response.status(201).entity(result).build();
	}
}
