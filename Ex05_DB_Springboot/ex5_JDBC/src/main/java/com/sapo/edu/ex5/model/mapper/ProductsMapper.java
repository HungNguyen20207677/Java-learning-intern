package com.sapo.edu.ex5.model.mapper;

import com.sapo.edu.ex5.model.Categories;
import com.sapo.edu.ex5.model.Products;
import com.sapo.edu.ex5.model.Warehouses;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductsMapper implements RowMapper<Products> {
    @Override
    public Products mapRow(ResultSet rs, int rowNum) throws SQLException {
        Products product = new Products();
        product.setProductId(rs.getInt("product_id"));
        product.setProductCode(rs.getString("product_code"));

        // Lấy category ID và tạo Categories object
        Categories category = new Categories();
        category.setCategoryId(rs.getInt("category_id"));
        product.setCategory(category);

        // Lấy warehouse ID và tạo a new Warehouses object
        Warehouses warehouse = new Warehouses();
        warehouse.setWarehouseId(rs.getInt("warehouse_id"));
        product.setWarehouse(warehouse);

        product.setPrice(rs.getFloat("price"));
        product.setName(rs.getString("name"));
        product.setProductDescription(rs.getString("product_description"));
        product.setImageUrl(rs.getString("image_url"));
        product.setQuantityInStock(rs.getInt("quantity_in_stock"));
        product.setQuantitySold(rs.getInt("quantity_sold"));
        product.setCreatedAt(rs.getDate("created_at"));
        product.setUpdatedAt(rs.getDate("updated_at"));
        return product;
    }
}
