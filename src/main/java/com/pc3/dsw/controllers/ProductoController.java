package com.pc3.dsw.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pc3.dsw.entities.Producto;
import com.pc3.dsw.services.ProductoService;

@RestController
@RequestMapping("/producto")
@CrossOrigin(origins ="http://localhost:4200")
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	
	@PostMapping("/registraProducto")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaAlumno(@RequestBody Producto obj) {
		
		Map<String, Object> salida = new HashMap<>();
		try {
			if (obj.getIdProducto() == 0) {
				Producto objSalida = productoService.insertUpdateProducto(obj);
				if (objSalida == null) {
					salida.put("mensaje","Error al registrar");
				} else {
					salida.put("mensaje", "Exito al registrar");
				}	
			}else {
				salida.put("mensaje", "El IdProveedor debe ser cero");
			}

		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje","Error al registrar");
		}
		return ResponseEntity.ok(salida);
	}
	@PutMapping("/actualizaProducto")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaProducto(@RequestBody Producto obj) {
		
		Map<String, Object> salida = new HashMap<>();
		try {
			if (obj.getIdProducto() != 0) {
				Producto objSalida = productoService.insertUpdateProducto(obj);
				if (objSalida == null) {
					salida.put("mensaje", "Error al actualizar");
				} else {
					salida.put("mensaje", "Se actualizo correctamente");
				}	
			}else {
				salida.put("mensaje", "El ID del Producto debe ser diferente cero");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje",  "Error al actualizar");
		}
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Producto>> listProveedor(){
		List<Producto> list = productoService.listProducto();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/listaProductoPorId/{id}")
	@ResponseBody
	public ResponseEntity<Producto> listaAlumnoPorId(@PathVariable("id") Long idProducto){
		Producto objProducto = null;
		try {
			 Optional<Producto> optProducto =  productoService.buscaPorId(idProducto);
			 if (optProducto.isEmpty()) {
				 objProducto = new Producto(); 
			 }else {
				 objProducto = optProducto.get();
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(objProducto);
	}
	
	@GetMapping("/listaProductoPorNombreLike/{nom}")
	@ResponseBody
	public ResponseEntity<List<Producto>> listaProductoPorNombreLike(@PathVariable("nom") String nom){
		List<Producto> lista = null;
		try {
			if(nom.equals("todos")) {
				lista = productoService.listProducto();
			}
			else {
			lista = productoService.listaProductoporLike("%"+nom+"%");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(lista);
	}
	@DeleteMapping("/eliminarProductoPorId/{id}")
	@ResponseBody
	public ResponseEntity<Boolean> eliminarProducto(@PathVariable("id") Long idProducto){
		productoService.eliminarProducto(idProducto);
		return ResponseEntity.ok(!(productoService.buscaPorId(idProducto)!=null));
	}
	
	
	
}
