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
import java.util.Optional;

@Repository
public class ProductRepository{
    private JdbcTemplate template;
    @Autowired
    public ProductRepository(JdbcTemplate template){this.template = template;}

    public void save(Product product) {
        String sql = "INSERT INTO products (productname, description, url, price, number,type) VALUES (?,?,?,?,?,?)";
        try {
            template.update(sql,product.getProductname(), product.getDescription(), product.getUrl(), product.getPrice(),
                    product.getNumber(),product.getTypeOfProduct().ordinal());
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void update(Product product){
        String sql = "UPDATE products SET productname = ?, description = ?, url = ?, price = ?, number = ?, type = ? WHERE id = ?";
        try{
            template.update(sql,product.getProductname(),product.getDescription(),product.getUrl(),product.getPrice(),
                    product.getNumber(),product.getTypeOfProduct(),product.getId());
        }
        catch(DataAccessException e){
            e.printStackTrace();
            throw e;
        }
    }

    public void delete(Integer idProduct){
        String sql = "DELETE FROM products WHERE id = ?";
        try{
            template.update(sql,idProduct);
        }catch (DataAccessException e){
            e.printStackTrace();
            throw e;
        }
    }

    public List<Product> findAll(){
        String sql = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();
        try {
            RowMapper<Product> productRowMapper = new ProductMapper();
            products = template.query(sql, productRowMapper);
        }catch (DataAccessException e) {
            e.printStackTrace();
            throw e;
        }
        return products;
    }

    // encuentra producto por id
    public Optional<Product> findById(Integer id){
        String sql = "SELECT * FROM products WHERE id=?";
        return template.query(sql,new ProductMapper(),id).stream().findFirst();
    }

    // encuentra por nombre
    public Optional<Product> findByName(String productname){
        String sql = "SELECT * FROM products WHERE productname=?";
        return template.query(sql,new ProductMapper(),productname).stream().findFirst();
    }

    // mapea una fila de una tabla (en este caso la tabla productos) a un
    // objeto de tipo Producto
    private static final class ProductMapper implements RowMapper<Product>{
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Product.builder()
                    .id(rs.getInt("id"))
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
