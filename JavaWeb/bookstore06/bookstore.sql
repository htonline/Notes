-- 创建users表
create table users(
    id int primary key auto_increment,
    username varchar(100) unique not null ,
    password varchar(100) not null,
    email varchar(100)
)

-- 创建books表
create table books(
    id int primary key auto_increment,
    title varchar(100) not null,
    author varchar(100) not null,
    price double(11,2) not null,
    sales int not null,
    stock int not null,
    img_path varchar(100)
)

-- 创建orders表
create table orders(
    id varchar(100) primary key,
    order_time datetime not null,
    total_count int not null,
    total_amount double(11,2) not null,
    state int not null,
    user_id int not null,
    foreign key(user_id) references users(id)
)

-- 创建order_items表
create table order_items(
    id int primary key auto_increment,
    count int not null,
    amount double(11,2) not null,
    title varchar(100) not null,
    author varchar(100) not null,
    price double(11,2) not null,
    img_path varchar(100) not null,
    order_id varchar(100) not null,
    foreign key(order_id) references orders(id)
)

