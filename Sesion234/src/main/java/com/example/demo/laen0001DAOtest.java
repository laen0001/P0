package com.example.demo;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class laen0001DAOtest implements laen001DAOinterface{
	public boolean isAdmin(Usuario usuario) {
		String user = "admin";
		String password = "admin";
		
		return (usuario.getUser().equals(user) && usuario.getPassword().equals(password));
		
		
			
	}
	
	
	
		
	private JdbcTemplate jdbcTemplate;
	@Autowired
	public void setDataSource(DataSource dataSource) {
	this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	//aqui es donde se mapea los atributos que necesitamos de nuetra base de datos
	//pueden existir varios atributos ,y nosotros elegimos los que queremos selesccionar
	//tenemos indicar el tipo de dato y el parametro que necesitamos
	private final RowMapper<Usuario> mapper = (rs,numRow) -> {
		Usuario usuario = new Usuario();
		usuario.setId(rs.getInt("id"));
		usuario.setPassword(rs.getString("password"));
		usuario.setUser(rs.getString("user"));
		return usuario;
		};
	
	public List<Usuario> leeUsuarios(){
		//String sql="SELECT *from usuarios";
		String sql = "select * from clientes";
		List<Usuario> usuarios = this.jdbcTemplate.query(sql, mapper);
		return usuarios;

	}
	
	public Usuario buscaUsuario(String user,String password ){ //Devuelve el usuario buscado o null si no existe
		String sql = "select * from clientes where user = ? and password=?";
		List<Usuario> usuarios = this.jdbcTemplate.query(sql, mapper, user,password);
		if (usuarios.isEmpty()) return null;
		else return usuarios.get(0);
		}
	
	public void insertaUsuario( Integer id,String user,String password){
		String sql = "insert into clientes values(?,?,?)";
		this.jdbcTemplate.update(sql,id,user,password);
		}
	
	
	
	
	
}
