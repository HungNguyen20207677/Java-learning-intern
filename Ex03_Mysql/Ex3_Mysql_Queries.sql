-- CREATE DATABASE exercise3

USE exercise3;

-- 1) Tạo bảng
-- Kho: Id, mã kho, tên, địa điểm, ngày tạo, ngày sửa
-- Loại danh mục: Id, mã loại danh mục, tên, mô tả, ngày tạo, ngày sửa
-- Sản phẩm: Id, mã sản phẩm, id danh mục, id kho, tên, giá, mô tả sản phẩm, đường dẫn ảnh, số lượng sản phẩm trong kho, số lượng đã bán, ngày tạo, ngày sửa

CREATE TABLE warehouses (
    warehouse_id INT AUTO_INCREMENT PRIMARY KEY,
    warehouse_code VARCHAR(50),
    name VARCHAR(100),
    location VARCHAR(255),
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_code VARCHAR(50),
    name VARCHAR(100),
    description TEXT,
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_code VARCHAR(50),
    category_id INT,
    warehouse_id INT,
    name VARCHAR(100),
    price DECIMAL(10,2),
    product_description TEXT,
    image_url VARCHAR(255),
    quantity_in_stock INT,
    quantity_sold INT,
    created_at DATETIME,
    updated_at DATETIME,
    FOREIGN KEY (category_id) REFERENCES categories(category_id),
    FOREIGN KEY (warehouse_id) REFERENCES warehouses(warehouse_id)
);


DROP TABLE warehouses;
DROP TABLE products;
DROP TABLE categories;

DELETE FROM products;
DELETE FROM warehouses;
DELETE FROM categories;


-- 2) Thêm các bản ghi vào các bảng

INSERT INTO warehouses (warehouse_code, name, location, created_at, updated_at)
VALUES 
('WH001', 'warehouses A', 'Location A', DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY), DATE_ADD('2023-01-01', INTERVAL FLOOR(RAND() * 365) DAY)),
('WH002', 'warehouses B', 'Location B', DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY), DATE_ADD('2023-01-01', INTERVAL FLOOR(RAND() * 365) DAY)),
('WH003', 'warehouses C', 'Location C', DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY), DATE_ADD('2023-01-01', INTERVAL FLOOR(RAND() * 365) DAY)),
('WH004', 'warehouses D', 'Location D', DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY), DATE_ADD('2023-01-01', INTERVAL FLOOR(RAND() * 365) DAY));

INSERT INTO categories (category_code, name, description, created_at, updated_at)
VALUES 
('Apple', 'Category Type A', 'Description for Category Type A', DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY), DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY)),
('Samsung', 'Category Type B', 'Description for Category Type B', DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY), DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY)),
('Google', 'Category Type C', 'Description for Category Type C', DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY), DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY)),
('Nokia', 'Category Type D', 'Description for Category Type D', DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY), DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY));

INSERT INTO products (product_code, category_id, warehouse_id, name, price, product_description, image_url, quantity_in_stock, quantity_sold, created_at, updated_at)
VALUES 
('P001', 1, 1, 'Điện thoại Pixel 6', 20.50, 'Description for Product A', 'image_url_1.jpg', 100, 50, DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY), DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY)),
('P002', 2, 2, 'Máy tính Lenovo', 15.75, 'Description for Product B', 'image_url_2.jpg', 150, 75, DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY), DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY)),
('P003', 3, 3, 'Máy tính bảng', 30.25, 'Description for Product C', 'image_url_3.jpg', 200, 100, DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY), DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY)),
('P004', 4, 4, 'Ipod', 25.99, 'Description for Product D', 'image_url_4.jpg', 120, 60, DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY), DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY)),
('P005', 1, 2, 'Ipad', 18.50, 'Description for Product E', 'image_url_5.jpg', 180, 90, DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY), DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY)),
('P006', 2, 3, 'Điện thoại Redmi', 22.75, 'Description for Product F', 'image_url_6.jpg', 250, 125, DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY), DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY)),
('P007', 3, 4, 'Bàn phím Rain', 35.99, 'Description for Product G', 'image_url_7.jpg', 140, 70, DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY), DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY)),
('P008', 4, 1, 'Chuột Logitech', 28.25, 'Description for Product H', 'image_url_8.jpg', 160, 80, DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY), DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY)),
('P009', 1, 3, 'Điện thoại Iphone', 24.50, 'Description for Product I', 'image_url_9.jpg', 220, 110, DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY), DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY)),
('P010', 2, 4, 'Điện thoại Oppo', 31.75, 'Description for Product J', 'image_url_10.jpg', 190, 95, DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY), DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY)),
('P011', 2, 1, 'Điện thoại Pixel 6 Pro', 20.50, 'Description for Product A', 'image_url_1.jpg', 100, 50, DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY), DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY)),
('P012', 3, 2, 'Máy tính Asus', 15.75, 'Description for Product B', 'image_url_2.jpg', 150, 75, DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY), DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY)),
('P013', 4, 3, 'Điện thoại Redmi Pro', 30.25, 'Description for Product C', 'image_url_3.jpg', 200, 100, DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY), DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY)),
('P014', 1, 4, 'Màn hình máy tính', 25.99, 'Description for Product D', 'image_url_4.jpg', 120, 60, DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY), DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY)),
('P015', 2, 2, 'Tai nghe Razor', 18.50, 'Description for Product E', 'image_url_5.jpg', 180, 90, DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY), DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY));


-- 3) Sửa kho, sản phẩm, loại danh mục theo id
UPDATE warehouses
SET warehouse_code = 'WH001_UPDATED',
    name = 'Updated warehouses A',
    location = 'Updated Location A',
    updated_at = NOW()
WHERE warehouse_id = 1;

UPDATE products
SET product_code = 'P001_UPDATED',
    category_id = 2,
    warehouse_id = 3,
    name = 'Updated Product A',
    price = 25.99,
    product_description = 'Updated Description for Product A',
    image_url = 'updated_image_url.jpg',
    quantity_in_stock = 200,
    quantity_sold = 100,
    updated_at = NOW()
WHERE product_id = 1;

UPDATE categories
SET category_code = 'Apple',
    name = 'Updated Category A',
    description = 'Updated Description for Category A',
    updated_at = NOW()
WHERE category_id = 1;


-- 4) Xóa kho, sản phẩm, loại danh mục theo id
-- Xóa dữ liệu từ bảng products có liên kết đến bảng categories
DELETE FROM products WHERE category_id IN (SELECT category_id FROM categories);

-- Xóa dữ liệu từ bảng products có liên kết đến bảng warehouses
DELETE FROM products WHERE warehouse_id IN (SELECT warehouse_id FROM warehouses);

-- Sau khi đã xóa dữ liệu, có thể xóa bảng categories và warehouses mà không gặp lỗi
DROP TABLE categories;
DROP TABLE warehouses;

-- 5) Lọc các sản phẩm có chứa từ 'Điện Thoại' và thuộc loại danh mục có mã là 'Apple'
SELECT *
FROM products
WHERE name LIKE '%Điện Thoại%' 
AND category_id IN (
		SELECT category_id FROM categories WHERE category_code = 'Apple'
    );

-- 6) Đếm số sản phẩm trong mỗi loại danh mục, sắp xếp theo thứ tự giảm dần
SELECT c.category_code AS category_code, COUNT(p.product_id) AS products_count FROM products p
JOIN categories c ON p.category_id = c.category_id
GROUP BY c.category_code
ORDER BY products_count DESC;


-- 7) Xóa danh mục đồng thời xóa luôn các sản phẩm thuộc danh mục đó (Có sử dụng transaction)
START TRANSACTION;

DELETE FROM products WHERE category_id = (
		SELECT category_id FROM categories WHERE category_code = 'Samsung'
    );

DELETE FROM categories WHERE category_code = 'Samsung';

COMMIT;


-- 8) Lấy 10 sản phẩm có số lượng bán nhiều nhất
SELECT * 
FROM products p
ORDER BY p.quantity_sold DESC
LIMIT 10;


-- 9) Viết procedure đáp ứng các yêu cầu sau: tìm sản phẩm theo mã hoặc tên và theo mã kho và theo danh mục và theo thời gian tạo, \
-- có phân trang (theo 2 kiểu: phân trang theo kiểu chọn số trạng và phân trang theo kiểu xem thêm, xem nữa, mỗi trang 10 bản ghi), 
-- nếu điều kiện nào không nhập thì bỏ qua.

DELIMITER //

CREATE PROCEDURE search_products1(
    IN product_code_input VARCHAR(50), -- Tham số đầu vào: mã sản phẩm
    IN product_name_input VARCHAR(100), -- Tham số đầu vào: tên sản phẩm
    IN warehouse_code_input VARCHAR(50), -- Tham số đầu vào: mã kho hàng
    IN category_code_input VARCHAR(50), -- Tham số đầu vào: mã danh mục
    IN created_after DATETIME, -- Tham số đầu vào: ngày tạo sau ngày chỉ định
    IN is_pagination_by_number BOOLEAN, -- Tham số đầu vào: xác định cách phân trang
    IN current_page INT -- Tham số đầu vào: trang hiện tại
)
BEGIN
    DECLARE start_index INT;
    DECLARE page_size INT;
    
    -- Tính toán chỉ số bắt đầu và kích thước trang
    SET start_index = (current_page - 1) * 10;
    SET page_size = 10;
    
    -- Kiểm tra xem phân trang theo số lượng hay không
    IF is_pagination_by_number = TRUE THEN
        SET start_index = (current_page - 1) * page_size;
    ELSE
        SET start_index = (current_page - 1) * page_size;
        SET page_size = page_size + start_index;
    END IF;
    
    -- Truy vấn tìm kiếm sản phẩm
    SELECT *
    FROM products p
    INNER JOIN warehouses w ON p.warehouse_id = w.warehouse_id
    INNER JOIN categories c ON p.category_id = c.category_id
    WHERE (product_code_input IS NULL OR p.product_code LIKE CONCAT('%', product_code_input, '%'))
        AND (product_name_input IS NULL OR p.name LIKE CONCAT('%', product_name_input, '%'))
        AND (warehouse_code_input IS NULL OR w.warehouse_code LIKE CONCAT('%', warehouse_code_input, '%'))
        AND (category_code_input IS NULL OR c.category_code LIKE CONCAT('%', category_code_input, '%'))
        AND (created_after IS NULL OR p.created_at >= created_after)
    ORDER BY p.created_at DESC
    LIMIT start_index, page_size;
END //

DELIMITER ;


-- Gọi thủ tục (mã sp, tên sp, mã kho, mã danh mục, ngày tạo, có phân trang hay không, trang hiện tại)
CALL search_products1(NULL, NULL, NULL, NULL, NULL, FALSE, 1);



-- Procedure mới
DELIMITER //

CREATE PROCEDURE search_products(
    IN product_code_input VARCHAR(50),
    IN product_name_input VARCHAR(100),
    IN warehouse_code_input VARCHAR(50),
    IN category_code_input VARCHAR(50),
    IN start_date DATETIME,
    IN end_date DATETIME,
    IN is_pagination_by_number BOOLEAN, 	-- TRUE/'limit_offset' hoặc FALSE/'offset_fetch'
    IN current_page INT,            		-- chỉ cần sử dụng nếu p_pagination_type = 'limit_offset'
    IN p_offset INT                 		-- chỉ cần sử dụng nếu p_pagination_type = 'offset_fetch'
)
BEGIN
    DECLARE query_string VARCHAR(1000);
    SET @query_string = 'SELECT * FROM products WHERE 1';

    -- Điều kiện tìm kiếm theo mã sản phẩm 
    IF product_code_input IS NOT NULL THEN
        SET @query_string = CONCAT(@query_string, ' AND product_code = "', product_code_input, '"');
    END IF;

    -- Điều kiện tìm kiếm theo tên sản phẩm 
    IF product_name_input IS NOT NULL THEN
        SET @query_string = CONCAT(@query_string, ' AND name LIKE CONCAT("%", "', product_name_input, '", "%")');
    END IF;

    -- Điều kiện tìm kiếm theo mã kho 
    IF warehouse_code_input IS NOT NULL THEN
        SET @query_string = CONCAT(@query_string, ' AND warehouse_id = (SELECT warehouse_id FROM warehouses WHERE warehouse_code = "', warehouse_code_input, '")');
    END IF;

    -- Điều kiện tìm kiếm theo danh mục 
    IF category_code_input IS NOT NULL THEN
        SET @query_string = CONCAT(@query_string, ' AND category_id = ', category_code_input);
    END IF;

    -- Điều kiện tìm kiếm theo thời gian tạo (nếu được cung cấp)
    IF start_date IS NOT NULL AND end_date IS NOT NULL THEN
        SET @query_string = CONCAT(@query_string, ' AND created_at BETWEEN "', start_date, '" AND "', end_date, '"');
    END IF;

    -- Điều kiện phân trang
    IF is_pagination_by_number = TRUE THEN
        SET @query_string = CONCAT(@query_string, ' LIMIT 10 OFFSET ', (current_page - 1) * 10);
    ELSEIF is_pagination_by_number = FALSE THEN
        SET @query_string = CONCAT(@query_string, ' OFFSET ', p_offset, ' ROWS FETCH NEXT 10 ROWS ONLY');
    END IF;

    -- Thực hiện câu truy vấn
    PREPARE stmt FROM @query_string;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END//

DELIMITER ;

-- Gọi thủ tục (mã sp, tên sp, mã kho, mã danh mục, start date, end date, có phân trang hay không, trang hiện tại, offset)
CALL search_products(NULL, NULL, NULL, 53, NULL, NULL, TRUE, 1, NULL);

DROP PROCEDURE IF EXISTS search_products;

-- 10) Tạo, lấy danh sách, xóa index bảng sản phẩm

CREATE INDEX idx_product_name ON products(product_code);

SHOW INDEX FROM products;

DROP INDEX idx_product_name ON products;




