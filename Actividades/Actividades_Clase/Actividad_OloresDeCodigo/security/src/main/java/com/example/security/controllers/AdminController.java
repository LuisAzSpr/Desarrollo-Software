package com.example.security.controllers;

import com.example.security.repository.product.Product;
import com.example.security.repository.product.ProductRepository;
import com.example.security.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private ProductRepository productRepository;

    @PostMapping("/addNewProduct")
    public ResponseEntity<String> addNewProduct(@RequestBody Product product){
        try{
            productRepository.save(product);
            return ResponseEntity.ok("Se agrego el producto exitosamente");
        }catch (Exception e){
            return ResponseEntity.ok("No se pudo guardar el producto: "+e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Product>> addNewProduct(){
        try{
            return ResponseEntity.ok(productRepository.findAll());
        }catch (Exception e){
            System.out.println(e.getClass()+"\n"+e.getMessage()+"\n"+e.getCause());
            return ResponseEntity.ok(null);
        }
    }

    /*
    @PostMapping("/addProduct")
    public ResponseEntity<Integer> addProduct(@RequestBody Integer integer){

    }*/


}
