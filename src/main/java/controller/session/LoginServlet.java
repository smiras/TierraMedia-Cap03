package controller.session;


import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;
import services.LoginService;

@WebServlet("/logueado")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 8308079314140233763L;
	private LoginService loginService;

	@Override
	public void init() throws ServletException {
		super.init();
		loginService = new LoginService();
	}
	
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String username = req.getParameter("username");
    	String password = req.getParameter("password");
    	
    	Usuario user = loginService.login(username, password);
    	
    	if (!user.isNull()) {
    		req.getSession().setAttribute("user", user);
    		RequestDispatcher dispatcher = getServletContext()
        		      .getRequestDispatcher("/logueado.user");
    		 dispatcher.forward(req, resp);
    		//resp.sendRedirect("/turismo/logueado.jsp");    		
       	} else {
    		req.setAttribute("flash", "Nombre de usuario o contraseña incorrectos");	
    		RequestDispatcher dispatcher = getServletContext()
      		      .getRequestDispatcher("");
      		    dispatcher.forward(req, resp);
    	}
    }
}
