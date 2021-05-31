# 增

## 1.创建数据库

```mysql
create database 数据库名;

create database 数据库名 charset 'utf8'; 

create database 数据库名 charset 'gbk';
```

## 2.创建表格

```mysql
create table 【数据名.】表名(
	字段名1 数据类型,
	字段名2 数据类型,
	....
);
```

## 3.增加一列

```mysql
alter table 【数据库名.]表名称 add 【column】 字段名 数据类型;
alter table 【数据库名.]表名称 add 【column】 字段名 数据类型 first;
alter table 【数据库名.]表名称 add 【column】 字段名 数据类型 after 另一个字段;
```

## 4.增加数据【一行】

```mysql
insert into 【数据库名.]表名称 values(值列表)； 
#要求值列表的顺序、个数、类型，要与表格中的字段的顺序、个数、类型一一匹配

insert into 【数据库名.]表名称(部分字段列表) values(值列表)；
#要求列表的顺序、个数、类型，要与前面的(部分字段列表)的顺序、个数、类型一一匹配

insert into 【数据库名.]表名称 values(值列表1)，(值列表2)。。。;

insert into 【数据库名.]表名称(部分字段列表) values(值列表1)，(值列表2)。。。;

insert into [数据库名.]表名 set 字段名=值, 字段名=值......;

```

## 5.创建主键约束【建表时】

```mysql

字段名1 数据类型  primary key ,

primary key(字段名1)

primary key(字段名1，字段名2...)
#复合主键，只能采用这种方式指定
```

## 6.唯一键约束【建表时】

```
字段名1 数据类型 unique key,

unique key(字段名1，字段名2...)

```

## 7.自增约束【建表时】

```mysql
字段名1 数据类型  primary key auto_increment,

字段名2 数据类型 【unique key  not null】 auto_increment,
#前面必须要有key
```

## 8.外键约束【建表时】

```
no action 默认的

restrict  严格的  父表数据被 子表引用时 不能 修改和删除
	
set null: 当父表数据发生改变时 子表数据对应字段会变为null
	
cascade: 当父表数据发生改变时
			删除 子表数据会删除
			修改 子表数据会修改
```

```mysql
-- 将本表中的 cid 字段 关联 clazz 表的 id字段
foreign key(cid) references clazz(id) 
【on update 外键约束等级】【on delete 外键约束等级】
```

## 9.默认值【建表时】

```mysql
字段名1 数据类型 【unique key】 【not null】 【default 默认值】,
```

## 10非空约束【建表时】

```mysql
字段名1 数据类型 【not null】
```



# 删

## 1.删除数据库

```
drop database 数据库名;
```

## 2.删除表格

```
drop table 【数据库名.]表名称;
```

## 3.删除一列

```mysql
alter table 【数据库名.]表名称 drop 【column】 字段名;
```

## 4.删除数据

```
delete from 【数据库名.]表名称 【where 条件】;

truncate 【数据库名.]表名称;
#删除整张表的数据，还可以使用这个语句，效率更高，但是它不能回滚
```

## 5.删除主键约束

```
alter table 表名称 drop primary key;
```

## 6.删除唯一键约束

```mysql
ALTER TABLE 表名称 DROP INDEX 唯一性约束名;
#注意：如果忘记名称，可以通过“查看约束信息”查看
# show index from 表名称;
```

## 7.删除自增长列

```mysql
alter table 【数据名.】表名 modify 自增字段名 数据类型;
#相当于重新定义一下
```

## 8.删除外键约束

```mysql
ALTER TABLE 表名称 DROP FOREIGN KEY 外键约束名;
#查看约束名 SELECT * FROM information_schema.table_constraints WHERE table_name = '表名称';

#删除外键约束不会删除对应的索引，如果需要删除索引，需要用ALTER TABLE 表名称 DROP INDEX 索引名;

#查看索引名 show index from 表名称;
```

## 9.取消默认值

```mysql
ALTER TABLE 表名称 MODIFY 字段名 数据类型 【NOT NULL】;
#如果该字段原来设置了非空约束，要跟着一起再写一遍，否则非空约束会丢失

【就是重新定义了一遍】
```

## 10.取消非空约束

```mysql
ALTER TABLE 表名称 MODIFY 字段名 数据类型 【default 默认值】;
#如果该字段原来设置了默认值约束，要跟着一起再写一遍，否则默认值约束会丢失
【就是重新定义了一遍】
```



# 改

## 1.修改列数据类型 

【将现有的  列的数据类型   进行更改】

```mysql
alter table 【数据库名.]表名称 modify 【column】 字段名 新数据类型;
```

## 2.修改列名

【将现有的  列的字段名   更改为新的字段名】

```
alter table 【数据库名.]表名称 change【column】 旧字段名 新字段名 新数据类型;
```

## 3.修改列的位置

【将现有的列  进行  移位】

```mysql
alter table 【数据库名.]表名称 modify 【column】字段名 数据类型 first;
alter table 【数据库名.]表名称 modify 【column】字段名 数据类型 after 另一个字段;
```

## 4.修改表名称

```mysql
alter table 旧表名 rename 新表名;
rename table 旧表名 to 新表名;
```

## 5.修改数据

<!--如果没有加where条件，表示修改所有行，这个字段的值-->

```mysql
update 【数据库名.]表名称 set 字段名1 = 值1, 字段名2 = 值2 。。。 【where 条件】;
```

## 6.指定主键【建表后】

```
alter table 表名称 add primary key (主键字段列表);
```

## 7.指定唯一键约束【建表后】

```mysql
alter table 表名称 add 【constraint 约束名】 unique 【key】 (字段名列表);
```

## 8.自增长列【建表后】

```mysql
alter table 【数据名.】表名 modify 自增字段名 数据类型 auto_increment;
```

## 9.指定外键约束【建表后】

```mysql
alter table student add foreign key(cid)
references clazz(id) 
on delete set null on update set null;

#从student表中 让cid  与  class表中的id进行关联
#约束等级为：set null

```

## 10.指定某个字段的默认值约束【建表后】

```mysql
ALTER TABLE 表名称 MODIFY 字段名 数据类型  【default 默认值】 【NOT NULL】;
#如果该字段原来设置了非空约束，要跟着一起再写一遍，否则非空约束会丢失
```

## 11.指定某个字段的非空约束【建表后】

```mysql
ALTER TABLE 表名称 MODIFY 字段名 数据类型 NOT NULL 【default 默认值】;
#如果该字段原来设置了默认值约束，要跟着一起再写一遍，否则默认值约束会丢失
```


# 查

## 1.查看数据库

```
show databases
```

## 2.指定使用某个数据库

```
use 数据库名;
```

## 3.查看某个库下的所有表格

```mysql
show tables ;  #前提是前面有use 数据库名;的语句
show tables from 数据库名;
```

## 4.查看某个表结构

```
describe 【数据库名.]表名称;
desc 【数据库名.]表名称;
```

## 5.查询数据

```mysql
select * from 【数据库名.]表名称; #查询整张表的所有数据

select 字段列表  from 【数据库名.]表名称;  #查询部分列表

select * from 【数据库名.]表名称 【where 条件】;

select 字段列表  from 【数据库名.]表名称 【where 条件】;

```

## 6.查看某个表的约束信息

```mysql
SELECT * FROM information_schema.table_constraints WHERE table_name = '表名称';
或
SHOW CREATE TABLE 表名;

[这两个展示的数据不太一样，上面那个详细一些]
```

## 7.查看某个表的索引

```mysql
SHOW INDEX FROM 表名称;
```

# 运算符

1、算术运算符

```
加：+
减：-
乘：*
除：/   div（只保留整数部分）
模：%   mod
```

2、比较运算符

```
大于：>
小于：<
大于等于：>=
小于等于：>=
等于：=   不能用于null判断
不等于：!=  或 <>
安全等于：<=>  可以用于null值判断
```

3、逻辑运算符（建议用单词，可读性来说）

```
逻辑与：&& 或 and
逻辑或：|| 或 or
逻辑非：! 或 not
逻辑异或：^ 或 xor
```

4、范围

```mysql
区间范围：between x  and  y
	    not between x  and  y
集合范围：in (x,x,x)
	    not  in(x,x,x)
```

5、模糊查询和正则匹配（只针对字符串类型，日期类型）

```
like 'xxx'
如果想要表示0~n个字符，用%
如果想要表示确定的1个字符，用_
```



6、位运算符（很少使用）

```
左移：<<
右移：>>
按位与：&
按位或：|
按位异或：^
按位取反：~
```

7、特殊的null值处理

```
xx is null
xx is not null
xx <=> null
```

![1620474174720](C:\Users\98634\AppData\Roaming\Typora\typora-user-images\1620474174720.png)
