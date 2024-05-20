package com.example.security.repository.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productoRepository;

    private List<Product> findAllTypeOfProduct(Type typeOfProduct){
        List<Product> selectedProducts = new ArrayList<Product>();
        List<Product> products = productoRepository.findAll();
        for(Product p:products){
            if(p.getTypeOfProduct().equals(typeOfProduct)){
                selectedProducts.add(p);
            }
        }
        return selectedProducts;
    }

    public List<Product> findAllMouses(){return findAllTypeOfProduct(Type.MOUSE);}
    public List<Product> findAllKeyboards(){return findAllTypeOfProduct(Type.KEYBOARD);}
    public List<Product> findAllEarphones(){return findAllTypeOfProduct(Type.EARPHONES);}
    public List<Product> findAllLaptops(){return findAllTypeOfProduct(Type.LAPTOPS);}
    public List<Product> findAllScreens(){return findAllTypeOfProduct(Type.SCREEN);}


}
