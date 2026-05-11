-- Sample data for testing the e-commerce application

-- Document Types
INSERT INTO document_types (code, name, created_at, updated_at) VALUES
('CC', 'Cédula de Ciudadanía', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('CE', 'Cédula de Extranjería', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('TI', 'Tarjeta de Identidad', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Categories
INSERT INTO categories (name, parent_id, created_at, updated_at) VALUES
('Electrónicos', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Ropa', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Hogar', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Deportes', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Celulares', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Computadores', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Users
INSERT INTO users (full_name, phone, email, document_type_id, document_number, birth_date, country, address, created_at, updated_at) VALUES
('Juan Pérez', '3001234567', 'juan.perez@example.com', 1, '123456789', '1990-05-15', 'Colombia', 'Calle 123 #45-67, Bogotá', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('María García', '3019876543', 'maria.garcia@example.com', 1, '987654321', '1985-08-20', 'Colombia', 'Carrera 78 #12-34, Medellín', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Carlos Rodríguez', '3025556666', 'carlos.rodriguez@example.com', 1, '456789123', '1992-03-10', 'Colombia', 'Avenida 45 #67-89, Cali', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Products
INSERT INTO products (name, description, price, available, created_at, updated_at) VALUES
('iPhone 15', 'Smartphone Apple iPhone 15 128GB', 4500000.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Samsung Galaxy S24', 'Smartphone Samsung Galaxy S24 256GB', 3800000.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('MacBook Pro 16"', 'Laptop Apple MacBook Pro 16 pulgadas', 12000000.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Camisa Polo', 'Camisa polo algodón talla M', 80000.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Zapatillas Nike', 'Zapatillas deportivas Nike Air Max', 350000.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Sartén Teflón', 'Sartén antiadherente 24cm', 120000.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Product Categories
INSERT INTO product_categories (product_id, category_id) VALUES
(1, 1), (1, 5), -- iPhone: Electrónicos, Celulares
(2, 1), (2, 5), -- Galaxy: Electrónicos, Celulares
(3, 1), (3, 6), -- MacBook: Electrónicos, Computadores
(4, 2), -- Camisa: Ropa
(5, 2), (5, 4), -- Zapatillas: Ropa, Deportes
(6, 3); -- Sartén: Hogar

-- Inventories
INSERT INTO inventories (product_id, quantity, created_at, updated_at) VALUES
(1, 50, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 75, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 40, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Inventory Movements
INSERT INTO inventory_movements (inventory_id, quantity, movement_type, created_at, updated_at) VALUES
(1, 10, 'ENTRADA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 5, 'ENTRADA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 2, 'SALIDA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 20, 'ENTRADA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 15, 'SALIDA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 8, 'ENTRADA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Carts
INSERT INTO carts (user_id, status, created_at, updated_at) VALUES
(1, 'ACTIVO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'ACTIVO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'COMPLETADO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Cart Items
INSERT INTO cart_items (cart_id, product_id, quantity, created_at, updated_at) VALUES
(1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 4, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 5, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 3, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Orders
INSERT INTO orders (user_id, status, total_amount, currency, created_at, updated_at) VALUES
(1, 'PENDIENTE', 4580000.00, 'COP', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'ENVIADO', 4150000.00, 'COP', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'ENTREGADO', 12000000.00, 'COP', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Order Items
INSERT INTO order_items (order_id, product_id, quantity, unit_price, created_at, updated_at) VALUES
(1, 1, 1, 4500000.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 4, 1, 80000.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 2, 1, 3800000.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 5, 1, 350000.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 3, 1, 12000000.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Payments
INSERT INTO payments (order_id, amount, payment_method, status, created_at, updated_at) VALUES
(1, 4580000.00, 'TARJETA_CREDITO', 'APROBADO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 4150000.00, 'TRANSFERENCIA', 'APROBADO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 12000000.00, 'EFECTIVO', 'PENDIENTE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);