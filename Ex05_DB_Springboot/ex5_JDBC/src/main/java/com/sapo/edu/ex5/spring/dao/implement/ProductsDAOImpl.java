package com.sapo.edu.ex5.spring.dao.implement;

import com.sapo.edu.ex5.model.Products;
import com.sapo.edu.ex5.model.mapper.ProductCountMapper;
import com.sapo.edu.ex5.model.mapper.ProductsMapper;
import com.sapo.edu.ex5.spring.dao.ProductsDAO;
import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class ProductsDAOImpl implements ProductsDAO {
    JdbcTemplate jdbcTemplate;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    SimpleJdbcCall simpleJdbcCalljdbcCall;

    private final String SQL_FIND_PRODUCT = "SELECT * FROM products WHERE product_id = ?";
    private final String SQL_DELETE_PRODUCT = "DELETE FROM products WHERE product_id = ?";
    private final String SQL_UPDATE_PRODUCT = "UPDATE products SET product_code = ?, category_id = ?, warehouse_id = ?, name = ?, price = ?, product_description = ?, image_url = ?, quantity_in_stock = ?, quantity_sold = ?, updated_at = NOW() WHERE product_id = ?";
    private final String SQL_GET_ALL = "SELECT * FROM products";
    private final String SQL_INSERT_PRODUCT = "INSERT INTO products (product_code, category_id, warehouse_id, name, price, product_description, image_url, quantity_in_stock, quantity_sold, NOW(), null)";
    private final String SQL_FILTER_PRODUCT = "SELECT * FROM products WHERE name LIKE :productName AND category_id IN (" +
            "SELECT category_id FROM categories WHERE category_code = :categoryCode)";
    private final String SQL_FIND_BEST_SELLING = "SELECT * FROM products p ORDER BY p.quantity_sold DESC LIMIT :limitValue";

    private final String SQL_PRODUCT_COUNT_PER_CATEGORY = "SELECT c.categoryId, c.categoryCode, COUNT(p.productId) FROM Products p JOIN p.category c GROUP BY c.categoryId, c.categoryCode ORDER BY COUNT(p.productId) DESC";



    @Autowired
    public ProductsDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        simpleJdbcCalljdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("search_products");
    }


    // Lấy thông tin của một danh mục dựa trên ID
    @Override
    public Products getProductById(int id) {
        return jdbcTemplate.queryForObject(SQL_FIND_PRODUCT, new Object[] { id }, new ProductsMapper());
    }

    // Lấy thông tin của tất cả các danh mục
    @Override
    public List<Products> getAllProducts() {
        return jdbcTemplate.query(SQL_GET_ALL, new ProductsMapper());
    }

    // Xóa một danh mục
    @Override
    public boolean deleteProduct(Products product) {
        return jdbcTemplate.update(SQL_DELETE_PRODUCT, product.getProductId()) > 0;
    }

    // Cập nhật thông tin của một danh mục
    @Override
    public boolean updateProduct(Products product) {
        return jdbcTemplate.update(SQL_UPDATE_PRODUCT, 
                product.getProductCode(),
                product.getProductId(), 
                product.getCategory(),
                product.getWarehouse(),
                product.getPrice(),
                product.getName(),
                product.getProductDescription(),
                product.getImageUrl(),
                product.getQuantityInStock(),
                product.getQuantitySold(),
                product.getProductId()) > 0;
    }

    // Tạo mới một danh mục
    @Override
    public boolean createProduct(Products product) {
        return jdbcTemplate.update(SQL_INSERT_PRODUCT,
                product.getProductCode(),
                product.getProductId(),
                product.getCategory(),
                product.getWarehouse(),
                product.getPrice(),
                product.getName(),
                product.getProductDescription(),
                product.getImageUrl(),
                product.getQuantityInStock(),
                product.getQuantitySold(),
                product.getProductId()) > 0;}

    // Lọc các sản phẩm có chứa từ 'Điện Thoại' và thuộc loại danh mục có mã là 'Apple' (Sử dụng NamedParameterJdbcTemplate)
    // Sử dụng NamedParameterJdbcTemplate
    @Override
    public List<Products> filterProduct(String name, String categoryCode) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("productName", "%"+name+"%");
        params.addValue("categoryCode", categoryCode);

        return namedParameterJdbcTemplate.query(SQL_FILTER_PRODUCT, params, new ProductsMapper());
    }

    // Lấy top n sản phẩm có số lượng bán nhiều nhất
    @Override
    public List<Products> findTopSelling(int limitValue) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("limitValue", limitValue);
        return namedParameterJdbcTemplate.query(SQL_FIND_BEST_SELLING, params, new ProductsMapper());
    }

    @Override
    public List<Object[]> getProductCountPerCategory() {
        return jdbcTemplate.query(SQL_PRODUCT_COUNT_PER_CATEGORY, new ProductCountMapper());
    }


    @Override
    public List<Products> searchProductPagination(String productCode, String name, String warehouseCode, String categoryCode, Date startDate, Date endDate, boolean isPaginationByNumber, int currentPage, int offset) {
        // Thiết lập các tham số cho Stored Procedure
        simpleJdbcCalljdbcCall.declareParameters(
                new SqlParameter("product_code_input", Types.VARCHAR),
                new SqlParameter("product_name_input", Types.VARCHAR),
                new SqlParameter("warehouse_code_input", Types.VARCHAR),
                new SqlParameter("category_code_input", Types.VARCHAR),
                new SqlParameter("start_date", Types.DATE),
                new SqlParameter("end_date", Types.DATE),
                new SqlParameter("is_pagination_by_number", Types.BOOLEAN),
                new SqlParameter("current_page", Types.INTEGER),
                new SqlParameter("p_offset", Types.INTEGER),
                new SqlOutParameter("out_cursor", OracleTypes.CURSOR, new ProductsMapper()));

        // Gán giá trị cho các tham số input
        MapSqlParameterSource inParams = new MapSqlParameterSource();
        inParams.addValue("product_code_input", productCode);
        inParams.addValue("product_name_input", name);
        inParams.addValue("warehouse_code_input", warehouseCode);
        inParams.addValue("category_code_input", categoryCode);
        inParams.addValue("start_date", startDate);
        inParams.addValue("end_date", endDate);
        inParams.addValue("is_pagination_by_number", isPaginationByNumber);
        inParams.addValue("current_page", currentPage);
        inParams.addValue("p_offset", offset);

        // Thực thi Stored Procedure
        Map<String, Object> resultMap = simpleJdbcCalljdbcCall.execute(inParams);

        List<Products> productsList = (List<Products>) resultMap.get("out_cursor");
        return productsList;
    }
}
