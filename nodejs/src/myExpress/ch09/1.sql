create database company;

use company;

create table products (
        id int not null auto_increment primary key,
        name varchar(50) not null,
        modelNumber varchar(15) not null,
        series varchar(30) not null
);

describe products;

insert into products (name, modelNumber, series) values
('냉장고', '01234567', '가전'),
('세탁기', '45612345', '가전'),
('컴퓨터', '34567894', '가전'),
('침대', '32423423', '가구'),
('쇼파', '33335621', '가구');

select * from products;
