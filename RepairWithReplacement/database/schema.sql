CREATE DATABASE IF NOT EXISTS repair_replace;
USE repair_replace;
CREATE TABLE IF NOT EXISTS repair_requests (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 customer_name VARCHAR(255), email VARCHAR(255), phone VARCHAR(50),
 product_type VARCHAR(100), product_name VARCHAR(255), problem_description VARCHAR(1500),
 repair_cost DOUBLE NOT NULL DEFAULT 0, replacement_cost DOUBLE NOT NULL DEFAULT 0,
 product_age_years INT NOT NULL DEFAULT 0, overall_condition VARCHAR(50), previous_repairs INT NOT NULL DEFAULT 0, recommendation_reason VARCHAR(1000),
 recommendation VARCHAR(100), status VARCHAR(100), created_at DATETIME
);
