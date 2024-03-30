package com.example.demo;

import java.util.List;

public interface DAOinterface {
	public boolean isAdmin(Usuario usuario);
	public List<Usuario> leeUsuarios();
	public Usuario buscaUsuario(String user,String password);
	public void insertaUsuario(Usuario usuario);
	
}

