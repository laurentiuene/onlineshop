DROP TABLE IF EXISTS stock;
DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS cart_item;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS user;

CREATE TABLE user (
  id_user INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  username varchar(45) UNIQUE NOT NULL,
  password varchar(100) NOT NULL,
  email varchar(45) UNIQUE NOT NULL,
  first_name varchar(45) NOT NULL,
  last_name varchar(45) NOT NULL,
  city varchar(45) NOT NULL,
  address varchar(90) NOT NULL,
  phone varchar(15) NOT NULL,
  gender varchar(10) NULL,
  birthday DATE NOT NULL,
  start_date DATE NOT NULL
  );

CREATE TABLE cart (
 id_cart INT AUTO_INCREMENT PRIMARY KEY NOT NULL
);

CREATE TABLE cart_item (
 id_cart_item INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
 id_product INT NOT NULL,
 id_cart INT NOT NULL,
 quantity INT NOT NULL
);


CREATE TABLE product (
 id_product INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
 product_name varchar(30) NOT NULL,
 product_category varchar(30) NOT NULL,
 product_description varchar(100) NOT NULL,
 product_specifications varchar(200) NOT NULL,
 product_price FLOAT NOT NULL,
 product_image varchar(50) NULL
);

CREATE TABLE orders (
 id_order INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
 id_user INT NOT NULL,
 status varchar(30) NOT NULL
);

CREATE TABLE order_item (
 id_order_item INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
 id_product INT NOT NULL,
 id_order INT NOT NULL,
 quantity INT NOT NULL
);

CREATE TABLE stock (
 id_stock INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
 id_product INT NOT NULL,
 quantity INT NOT NULL,
 city varchar(45) NOT NULL
);

INSERT INTO user (username, password, email, first_name, last_name, city, address, phone, gender, birthday, start_date) VALUES
  ('laur','primutilizator','laurreene@gmail.com','Laurentiu','Ene','Bucuresti','Strada Resita, nr.16, ap.4','0734839325','male','1995-11-08','2020-07-07'),
  ('marcela','aldoileautilizator','u@u.u','Marcela','Stroe','Arad','Strada Resita, nr.16, ap.4','0734839322','female','1994-12-08','2020-07-01')
  ;

INSERT INTO product (product_name, product_category, product_description, product_specifications, product_price, product_image) VALUES
  ('KINGSTON_A400','STORAGE','Very good ssd','480 gb, 500mb/s writing, 520mb/s reading','310.00',''),
  ('KINGSTON_UV400','STORAGE','Very good ssd','128 gb, 1200mb/s writing, 1333mb/s reading','390.00',''),
  ('INTEL_660P','STORAGE','Very good ssd','980 gb, 2000mb/s writing, 2222mb/s reading','580.00','')
  ;


INSERT INTO stock (id_product, quantity, city) VALUES
  ('1', '20', 'Bucuresti'),
  ('1', '20', 'Valcea'),
  ('1', '15', 'Tulcea'),
  ('2', '33', 'Bucuresti'),
  ('2', '18', 'Arad'),
  ('2', '0', 'Cluj')
  ;


