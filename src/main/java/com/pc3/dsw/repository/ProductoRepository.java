package com.pc3.dsw.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pc3.dsw.entities.Producto;

public interface ProductoRepository  extends JpaRepository<Producto, Long>{

	
	public List<Producto> findBynombreProductoLike(String nombreProducto);
}
