package com.example.security.repository.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    private JdbcTemplate template;
    private static Integer id = 0;

    @Autowired
    public ProductRepository(JdbcTemplate template){this.template = template;}

    public void save(Product product) {
        String sql = "INSERT INTO products (id,productname, description, url, price, number,type) VALUES (?,?,?,?,?,?,?)";
        try {
            template.update(sql,id,product.getProductname(), product.getDescription(), product.getUrl(), product.getPrice(),
                    product.getNumber(),product.getTypeOfProduct().ordinal());
            id++;
        } catch (DataAccessException e) {
            // Registrar el error
            System.err.println("Error al ejecutar la sentencia SQL: " + e.getMessage());
            e.printStackTrace();
            throw e; // Re-lanzar la excepción si es necesario
        }
    }

    public List<Product> findAll(){
        String sql = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();
        try {
            RowMapper<Product> productRowMapper = new ProductMapper();
            products = template.query(sql, productRowMapper);
        }catch (DataAccessException e) {
            // Registrar el error
            System.err.println("Error al ejecutar la sentencia SQL: " + e.getMessage());
            e.printStackTrace();
            throw e; // Re-lanzar la excepción si es necesario
        }
        return products;
    }

    public List<Product> findAllTypeOfProducto(Type typeOfProduct){
        List<Product> selectedProducts = new ArrayList<>();
        List<Product> products = findAll();
        for(Product product:products){
            if(product.getTypeOfProduct().equals(typeOfProduct)){
                selectedProducts.add(product);
            }
        }
        return selectedProducts;
    }

    // mapea una fila de una tabla (en este caso la tabla productos) a un
    // objeto de tipo Producto
    private static final class ProductMapper implements RowMapper<Product>{
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Product.builder()
                    .productname(rs.getString("productname"))
                    .description(rs.getString("description"))
                    .url(rs.getString("url"))
                    .price(rs.getFloat("price"))
                    .number(rs.getInt("number"))
                    .typeOfProduct(Type.values()[rs.getInt("type")])
                    .build();
        }
    }
}
