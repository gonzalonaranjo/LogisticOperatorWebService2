package restClient;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class prueba {

	public static void main(String[] args) throws IOException, KeyManagementException, NoSuchAlgorithmException {
		RestCallAPI restCall = new RestCallAPI();
		
		// restCall.getWebResource();
		 restCall.postThings();
	}

}
