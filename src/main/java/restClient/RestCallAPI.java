package restClient;

import java.util.List;
import java.util.Properties;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import bean.ProductOrder;
import utils.ControllerUtils;

public class RestCallAPI {
	
	
	public RestCallAPI() {
		
	}
	
	public void getWebResource() {
		
		Client client = Client.create();
		WebResource webResource = client.resource("https://localhost:44340/api/ApiController/3");
		
		ClientResponse response = webResource.accept("application/json")
                .get(ClientResponse.class);
		
		if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
		}

		String output = response.getEntity(String.class);

		System.out.println("Output from Server .... \n");
		System.out.println(output);
	}
	
	
	
	public void postThings() {
		
		Properties propFile = ControllerUtils.getPropertiesFile();
		
		
		Client client = Client.create();
		WebResource webResource = client.resource(propFile.getProperty("URL"));
		
		JSONObject userPass = new JSONObject();
		userPass.put("Password", propFile.getProperty("Password"));
		
		JSONObject user = new JSONObject();
		user.put("UserName", propFile.getProperty("UserName"));
		user.put("UserPass", userPass);
		
		JSONObject item1 = new JSONObject();
		item1.put("ProductId", "8");
		item1.put("Stock", "3000");
		
		JSONObject item2 = new JSONObject();
		item2.put("ProductId", "9");
		item2.put("Stock", "15");
		
		JSONArray ja = new JSONArray();
		ja.put(item1);
		ja.put(item2);
		
		
		JSONObject mainObj = new JSONObject();
		mainObj.put("ProductStockList", ja);
		mainObj.put("User", user);
		
		ClientResponse response = webResource
				.type(MediaType.APPLICATION_JSON_TYPE)
                .post(ClientResponse.class, mainObj.toString());
		
		if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
		}
		
		String output = response.getEntity(String.class);

		System.out.println("Output from Server .... \n");
		System.out.println(output);
	}
	
	
	public boolean PostOrder(List<ProductOrder> productOrderList) {
		
		boolean resp = true;
		
		Properties propFile = ControllerUtils.getPropertiesFile();
		
		Client client = Client.create();
		WebResource webResource = client.resource(propFile.getProperty("URL"));
		
		JSONObject userPass = new JSONObject();
		userPass.put("Password", propFile.getProperty("Password"));
		
		JSONObject user = new JSONObject();
		user.put("UserName", propFile.getProperty("UserName"));
		user.put("UserPass", userPass);
		
		JSONArray ProductStockList = new JSONArray();
		
		for(int i = 0; i < productOrderList.size(); i++) {
			JSONObject productOrderItem = new JSONObject();
			
			productOrderItem.put("ProductId", String.valueOf(productOrderList.get(i).getProduct().getId()));
			productOrderItem.put("Stock", String.valueOf(productOrderList.get(i).getQuantity()));
			
			ProductStockList.put(productOrderItem);
		}
		
		JSONObject mainObj = new JSONObject();
		mainObj.put("ProductStockList", ProductStockList);
		mainObj.put("User", user);
		
		ClientResponse response = webResource
				.type(MediaType.APPLICATION_JSON_TYPE)
                .post(ClientResponse.class, mainObj.toString());
		
		if (response.getStatus() != 200) {
			resp = false;
		}
		
		return resp;
	}
}
