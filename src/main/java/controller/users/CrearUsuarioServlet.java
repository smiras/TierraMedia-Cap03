package controller.users;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;
import services.UsuarioService;

@WebServlet("/users/create.do")
public class CrearUsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private UsuarioService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UsuarioService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/views/users/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		String pass = req.getParameter("pass");
		Double presupuesto = Double.parseDouble(req.getParameter("presupuesto"));
		Double tiempodisponible = Double.parseDouble(req.getParameter("tiempodisponible"));
		String tipo = req.getParameter("tipo");

		Usuario tmp_user = userService.create(nombre, pass, presupuesto, tiempodisponible, tipo);
		
		if (tmp_user.isValid()) {
			resp.sendRedirect("/turismo/users/index.do");
		} else {
			req.setAttribute("tmp_user", tmp_user);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/users/create.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
