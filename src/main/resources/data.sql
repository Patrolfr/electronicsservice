delete from equipment_parameters;
delete from comments;
delete from equipment where id > 0;
delete from category where id > 0;


insert into category values(1,'phones');
insert into category values(2,'tvs');
insert into category values(3,'pcs');