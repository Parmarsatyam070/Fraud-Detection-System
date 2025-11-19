CREATE DATABASE IF NOT EXISTS fraud_detection_db DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
USE fraud_detection_db;
CREATE TABLE users (
    user_id VARCHAR(50) PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20),
    registration_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    account_status ENUM('ACTIVE', 'SUSPENDED', 'CLOSED') DEFAULT 'ACTIVE',
    risk_level ENUM('LOW', 'MEDIUM', 'HIGH', 'CRITICAL') DEFAULT 'LOW',
    last_login DATETIME,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP);

CREATE TABLE accounts (
    account_id VARCHAR(50) PRIMARY KEY,
    user_id VARCHAR(50) NOT NULL,
    account_type ENUM('CHECKING', 'SAVINGS', 'CREDIT', 'DEBIT') NOT NULL,
    account_number VARCHAR(50) NOT NULL UNIQUE,
    balance DECIMAL(15,2) DEFAULT 0.00 CHECK (balance >= 0),
    currency VARCHAR(3) NOT NULL DEFAULT 'USD',
    is_active BOOLEAN DEFAULT TRUE,
    created_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    INDEX idx_user_account (user_id));

CREATE TABLE merchants (
    merchant_id VARCHAR(50) PRIMARY KEY,
    merchant_name VARCHAR(255) NOT NULL,
    merchant_category VARCHAR(100),
    merchant_location VARCHAR(255),
    merchant_country VARCHAR(50),
    risk_score DECIMAL(5,2) DEFAULT 0.00,
    is_active BOOLEAN DEFAULT TRUE,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP);

CREATE TABLE transactions (
    transaction_id VARCHAR(50) PRIMARY KEY,
    account_id VARCHAR(50) NOT NULL,
    user_id VARCHAR(50) NOT NULL,
    merchant_id VARCHAR(50),
    amount DECIMAL(15,2) NOT NULL CHECK (amount >= 0),
    currency VARCHAR(3) NOT NULL DEFAULT 'USD',
    transaction_type ENUM('PURCHASE', 'WITHDRAWAL', 'TRANSFER', 'DEPOSIT', 'REFUND') NOT NULL,
    transaction_status ENUM('PENDING', 'COMPLETED', 'FAILED', 'BLOCKED') DEFAULT 'PENDING',
    timestamp DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    merchant_category VARCHAR(100),
    merchant_name VARCHAR(255),
    location VARCHAR(255),
    country VARCHAR(50),
    device_id VARCHAR(100),
    ip_address VARCHAR(45),
    user_agent TEXT,
    is_fraudulent BOOLEAN DEFAULT FALSE,
    fraud_score DECIMAL(5,4) DEFAULT 0.0000,
    fraud_reason TEXT,
    detection_method ENUM('RULE_BASED', 'ML_MODEL', 'HYBRID', 'MANUAL') DEFAULT 'HYBRID',
    card_last_four VARCHAR(4),
    authorization_code VARCHAR(50),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES accounts(account_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (merchant_id) REFERENCES merchants(merchant_id) ON DELETE SET NULL ON UPDATE CASCADE,
    INDEX idx_acc_id (account_id),
    INDEX idx_usr_id (user_id),
    INDEX idx_merc_id (merchant_id));

CREATE TABLE fraud_alerts (
    alert_id VARCHAR(50) PRIMARY KEY,
    transaction_id VARCHAR(50) NOT NULL,
    user_id VARCHAR(50) NOT NULL,
    alert_type ENUM('VELOCITY_CHECK', 'AMOUNT_THRESHOLD', 'LOCATION_ANOMALY','ML_DETECTION', 'PATTERN_MATCH', 'MANUAL_REVIEW') NOT NULL,
    severity ENUM('LOW', 'MEDIUM', 'HIGH', 'CRITICAL') NOT NULL,
    status ENUM('OPEN', 'IN_PROGRESS', 'RESOLVED', 'FALSE_POSITIVE', 'CONFIRMED_FRAUD') DEFAULT 'OPEN',
    detection_timestamp DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    assigned_to VARCHAR(50),
    resolution_timestamp DATETIME,
    resolution_notes TEXT,
    alert_details JSON,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (transaction_id) REFERENCES transactions(transaction_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    INDEX idx_txn_id (transaction_id),
    INDEX idx_alert_usr (user_id)
);

CREATE TABLE system_configuration (
    config_id INT PRIMARY KEY AUTO_INCREMENT,
    config_key VARCHAR(100) NOT NULL UNIQUE,
    config_value TEXT NOT NULL,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP);

SHOW TABLES;
SELECT * FROM users;
SELECT * FROM accounts;
SELECT * FROM merchants;
SELECT * FROM transactions;
SELECT * FROM fraud_alerts;
SELECT * FROM system_configuration;
