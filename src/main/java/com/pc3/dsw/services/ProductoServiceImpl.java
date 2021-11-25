package com.pc3.dsw.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc3.dsw.entities.Producto;
import com.pc3.dsw.repository.ProductoRepository;

@Service
public class ProductoServiceImpl  implements ProductoService{

	@Autowired
	private ProductoRepository repository;

	
	
	@Override
	public Producto insertUpdateProducto(Producto obj) {
		return repository.save(obj);
	}

	@Override
	public List<Producto> listProducto() {
		return repository.findAll();
	}

	@Override
	public void eliminarProducto(Long idProducto) {
		repository.deleteById(idProducto);
	}

	@Override
	public Optional<Producto> buscaPorId(Long idProducto) {
		
		return repository.findById(idProducto);
	}

	@Override
	public List<Producto> listaProductoporLike(String nombreProducto) {
		
		return repository.findBynombreProductoLike(nombreProducto);
	}
	

}
