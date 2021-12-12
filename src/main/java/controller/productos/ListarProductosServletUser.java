package controller.productos;

import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Atraccion;
import modelo.Producto;
import modelo.Promocion;
import modelo.Usuario;
import services.AtraccionService;
import services.PromocionService;

@WebServlet("/logueado.user")
public class ListarProductosServletUser extends HttpServlet {

	private static final long serialVersionUID = 343260491714476062L;

	private AtraccionService atraccionService;
	private PromocionService promocionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.atraccionService = new AtraccionService();
		this.promocionService = new PromocionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LinkedList<Atraccion> atracciones = atraccionService.list();
		LinkedList<Promocion> promociones = promocionService.list();
		LinkedList<Producto> productos = new LinkedList<Producto>();

		Usuario user = (Usuario) ((HttpServletRequest) req).getSession().getAttribute("user");
		Promocion promocion = new Promocion();
		promociones = promocion.promosPosibles(promociones, user);
		Atraccion atraccion = new Atraccion();
		atracciones = atraccion.atraccionesPosibles(atracciones, user);

		for (Promocion pr : promociones) {
			Producto producto = new Producto(pr.getId(), pr.getNombre(), pr.getCosto(), pr.getDuracion(),
					pr.getDescripcion(), pr.getImagen());
			System.out.println(pr.getImagen());
			productos.add(producto);
		}

		for (Atraccion at : atracciones) {
			Producto producto = new Producto(at.getId(), at.getNombre(), at.getCosto(), at.getDuracion(),
					at.getDescripcion(), at.getImagen());
			productos.add(producto);
		}

		req.setAttribute("productosposibles", productos);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/logueado.jsp");
		dispatcher.forward(req, resp);

	}
}
