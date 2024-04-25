package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
public class laen0001HomeController {
	@Autowired
	laen001DAOinterface dao;
	
	@GetMapping("/login")
	public String index(HttpServletRequest req, Model model) {
		//Creación de la sesión http
				HttpSession session=req.getSession(true);
                Usuario usuario = (Usuario)session.getAttribute("usuario");
                
				//Comprobar si el objeto usuario existe
				if(usuario==null) {
					usuario=new Usuario();
					
				}
				
				else {
					
			
					
					if(dao.isAdmin(usuario)) {
						
						
						List<Usuario>usuarios=new ArrayList<Usuario>();
						usuarios=dao.leeUsuarios();
						model.addAttribute("usuarios",usuarios);
						
						return "user-form/laen0001lista";
					}
					
						else {
							
							model.addAttribute("usuario",usuario);
							
							return "user-form/laen0001tienda";
						}
					
					
				}
					
				return "laen0001Portal";
	}
	
	
	@PostMapping(value="/datos")
	public String metodo(HttpServletRequest req, HttpServletResponse resp, Model model) {
		

		
		//Obtener los datos del formulario
		String user = req.getParameter("user");
		String password= req.getParameter("password");
		
		
	
		
		//Obtención de la sesión
		HttpSession session=req.getSession(true);
		//Insertar los datos en el objeto javabean
		Usuario usuario= new Usuario(user, password);
		//Guardar los datos en la sesión
		session.setAttribute("usuario", usuario);
		
		
		//Prueba Pasar el objeto usuario a la vista
		model.addAttribute("usuario",usuario);
		
		System.out.println(user + ", "  + password);
		
		if(dao.isAdmin(usuario)) {
			
			
			List<Usuario>usuarios=new ArrayList<Usuario>();
			usuarios=dao.leeUsuarios();
			for(Usuario usuario1:usuarios){
				System.out.println("password:"+usuario1.getPassword()+",user:"+usuario1.getUser());
			}
			model.addAttribute("usuarios",usuarios);
			
			
			
			return "user-form/laen0001lista";
		}
		else {
			usuario=dao.buscaUsuario(user,password);
			if(usuario != null) {
				
				model.addAttribute("usuarios",usuario);
				return "user-form/laen0001tienda";
			}
			else {
				
				model.addAttribute("usuario",usuario);
				return "user-form/laen0001user";
			}
			
			
		}
		
		
		
	}
	
	
	
	
	@GetMapping(value="/datos")
	public String datos(HttpServletRequest req, Model model, HttpServletResponse resp) {
		
		//Obtener los datos del formulario
		
				String user = req.getParameter("user");
				String password= req.getParameter("password");
				HttpSession session = req.getSession(true);
				Usuario usuario = (Usuario)session.getAttribute("usuario");
				//Comprobar si el objeto usuario existe
				if(usuario==null) {
					usuario=new Usuario();
					//Guardar los datos en la sesión
					session.setAttribute("usuario", usuario);
					//si no nos loguamos y hacemos el getmapping,nos va adevolver una vista vacia,ya que no hemos introducido 
					//ningun usuario
				}
				
				
				//Obtener los datos del objeto obtenido de la sesión para verificar por consola
				String user1 = usuario.getUser();
				String password1 = usuario.getPassword();
				System.out.println(user1 + ", "  + password1);
				//Añadir datos a la vista como objeto javabean
				model.addAttribute("usuario",usuario);
				return "user-form/laen0001datos";
				
				
			}
	
	
	
	@PostMapping(value="/comentarios")
	public String comentarios(HttpServletRequest req, HttpServletResponse resp, Model model) {
		

		
		//Obtener los datos del formulario
		String nombre = req.getParameter("nombre");
		String email= req.getParameter("email");
		String mensaje= req.getParameter("mensaje");
		
		System.out.println(mensaje);
	
	return"user-form/laen0001Opiniones";
	
	}	
	
	
	@PostMapping(value="/registro")
	public String registro(HttpServletRequest req, HttpServletResponse resp, Model model) {
		

		
		//Obtener los datos del formulario
		String user = req.getParameter("user");
		String password= req.getParameter("password");
		
		//Obtención de la sesión
		HttpSession session=req.getSession(true);
		//Insertar los datos en el objeto javabean
		Usuario usuario= new Usuario(user, password);
		//Guardar los datos en la sesión
		session.setAttribute("usuario", usuario);
		
		usuario=dao.buscaUsuario(user,password);
		if(usuario == null) {
			dao.insertaUsuario(null, user, password);
			model.addAttribute("usuarios",usuario);
			return "laen0001Portal";
		}
		else {
			
		
			model.addAttribute("usuario",usuario);
			return "user-form/laen0001tienda";
		}
		
		
	
	}	
}
