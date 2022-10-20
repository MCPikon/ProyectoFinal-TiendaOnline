package controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControladorInicio {
	
	@RequestMapping("inicio")
	public String inicio() {
		System.out.println("se ejecuta el método inicio del ControladorInicio");
		return "inicio";
	}

}
