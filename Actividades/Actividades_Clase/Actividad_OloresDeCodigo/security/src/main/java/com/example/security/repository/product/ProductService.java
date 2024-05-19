package com.example.security.repository.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productoRepository;

    public List<Product> findAllMouses(){return productoRepository.findAllTypeOfProducto(Type.MOUSE);}
    public List<Product> findAllKeyboards(){
        return productoRepository.findAllTypeOfProducto(Type.KEYBOARD);
    }
    public List<Product> findAllEarphones(){
        return productoRepository.findAllTypeOfProducto(Type.EARPHONES);
    }
    public List<Product> findAllLaptops(){
        return productoRepository.findAllTypeOfProducto(Type.LAPTOPS);
    }
    public List<Product> findAllScreens(){return productoRepository.findAllTypeOfProducto(Type.SCREEN);}




}
