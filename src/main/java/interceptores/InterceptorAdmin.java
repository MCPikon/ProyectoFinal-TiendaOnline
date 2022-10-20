package interceptores;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class InterceptorAdmin extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//para el caso en que se identifique el admin:
		if(request.getParameter("pass") != null && request.getParameter("pass").equals("123")) {
			request.getSession().setAttribute("admin", "ok");
			//si el checkbox recordar contraseña esta activo, guardo la contraseña en una cookie
			if(request.getParameter("recordar_pass") != null) {
				Cookie c = new Cookie("pass_admin", "123");
				c.setMaxAge(365*24*60*60); //la cookie caducará en un año
				response.addCookie(c);	
			}
		}
				
		//para sucesivas llamadas en cualquier ruta en /admin
		if(request.getSession().getAttribute("admin") != null && 
			request.getSession().getAttribute("admin").equals("ok")) {
			return true;
		}else {
			//las cookies estan asociadas a la ruta de donde se crean
			//si desde /admin creamos una cookie para guardar el pass de admin
			//dicha cookie esta disponible y asociada a dicha ruta /admin
			
			//en este caso antes de redirigir aprovecho, ya que el interceptor actua en /admin
			//y obtengo la cookie del pass para darsela a ../loginAdmin.jsp
			Cookie[] cookies = request.getCookies();
			String pass_a_recordar = "";
			for (Cookie c : cookies) {
				if(c.getName().equals("pass_admin")) {
					pass_a_recordar = c.getValue();
				}
			}
			request.getSession().setAttribute("campo_pass", pass_a_recordar);
			response.sendRedirect("../loginAdmin.jsp");
			return false;
		}
	}
	
}
