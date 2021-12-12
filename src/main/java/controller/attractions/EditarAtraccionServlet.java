package controller.attractions;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Atraccion;
import services.AtraccionService;

@WebServlet("/attractions/edit.do")
public class EditarAtraccionServlet extends HttpServlet {

	private static final long serialVersionUID = 7598291131560345626L;
	private AtraccionService attractionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AtraccionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));

		Atraccion atraccion = attractionService.find(id);
		req.setAttribute("atraccion", atraccion);

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/views/attractions/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String nombre = req.getParameter("nomnbre");
		Double costo = Double.parseDouble(req.getParameter("costo"));
		Double duracion = Double.parseDouble(req.getParameter("duracion"));
		Integer cupo = Integer.parseInt(req.getParameter("cupo"));
		String descripcion = req.getParameter("descripcion");
		String imagen = req.getParameter("imagen");

		Atraccion attraction = attractionService.create(id, nombre, costo, duracion, cupo, descripcion, imagen);

		if (attraction.isValid()) {
			resp.sendRedirect("/turismo/attractions/index.do");
		} else {
			req.setAttribute("attraction", attraction);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/attractions/edit.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
