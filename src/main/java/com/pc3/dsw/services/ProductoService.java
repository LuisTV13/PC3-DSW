package com.pc3.dsw.services;

import java.util.List;
import java.util.Optional;

import com.pc3.dsw.entities.Producto;

public interface ProductoService {
	public Producto insertUpdateProducto(Producto obj);
	
	public List<Producto> listProducto();
	
	public  void eliminarProducto(Long idProducto);
	
	public abstract Optional<Producto> buscaPorId(Long idProducto);
	
	public abstract List<Producto> listaProductoporLike(String nombreProducto);
}
