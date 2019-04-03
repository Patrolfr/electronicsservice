<<<<<<< HEAD
delete from equip_service_request where id > 0;
delete from equipment_parameters;
delete from equipment where id > 0;
delete from category where id > 0;

insert into equip_service_request(id, service_code) values(66,'XYZ-123');
=======
delete from equipment_parameters;
delete from comments;
delete from equipment where id > 0;
delete from category where id > 0;

>>>>>>> b00ecaaa78e5d9a1e9da2370b34434a3c5184569

insert into category values(1,'phones');
insert into category values(2,'tvs');
insert into category values(3,'pcs');