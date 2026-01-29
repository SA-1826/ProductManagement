-- データベース初期化スクリプト

-- 文字コード設定
SET NAMES utf8mb4;

-- テーブル削除
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;

SET FOREIGN_KEY_CHECKS = 1;

-- categoriesテーブル
CREATE TABLE categories (
	category_id VARCHAR(50) PRIMARY KEY NOT NULL,
	category_name VARCHAR(100) NOT NULL
);

-- categoriesテーブルサンプルデータ
INSERT INTO categories (category_id, category_name) VALUES
	('c00001', '家電'),
	('c00002', '家具'),
	('c00003', '食品'),
	('c00004', '書籍'),
	('c00005', '衣類');

-- productsテーブル
CREATE TABLE products (
	product_id VARCHAR(50) PRIMARY KEY NOT NULL,
	product_name VARCHAR(100) NOT NULL,
	price INT NOT NULL,
	quantity INT NOT NULL,
	description VARCHAR(500),
	category_id VARCHAR(50) NOT NULL,
	FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

-- productsテーブルサンプルデータ
INSERT INTO products (product_id, product_name, price, quantity, description, category_id) VALUES
	('p00001', '冷蔵庫', 50000, 10, '家庭用冷蔵庫', 'c00001'),
	('p00002', 'ソファ', 10000, 5, '小さめソファ', 'c00002'),
	('p00003', '米', 4500, 50, '5kg', 'c00003'),
	('p00004', '小説', 2000, 20, '', 'c00004'),
	('p00005', 'Tシャツ', 1500, 15, '', 'c00005');