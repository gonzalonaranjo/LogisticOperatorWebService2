package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import constants.LogisticOperatorConstants;
import constants.ParameterConstants;
import dao.UserDAO;
import utils.ValidationUtils;


public class UserControllerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        String userName = request.getParameter(ParameterConstants.PARAMETER_USERNAME);
        String password = request.getParameter(ParameterConstants.PARAMETER_PASSWORD);
        
        HttpSession session = request.getSession(true);
        try {
        	
        	if(ValidationUtils.isBlank(userName) || ValidationUtils.isBlank(password)) {
        		throw new Exception();
        	}
        	
            UserDAO userDAO = new UserDAO();
            
            User user = userDAO.checkLogin(userName, password);
            
            session.setAttribute(ParameterConstants.PARAMETER_USER, user);
            
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher(LogisticOperatorConstants.JSP_URL.MAIN);
            rd.forward(request, response);
                
        } catch (Exception e) {
        	
        	RequestDispatcher rd = this.getServletContext().getRequestDispatcher(LogisticOperatorConstants.JSP_URL.ERROR);
            rd.forward(request, response);
        }
 
    }
}
