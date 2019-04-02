delete from equipment_parameters;
delete from hibernate_sequence;
delete from comments;
delete from equipment where id > 0;
delete from category where id > 0;

insert into equip_service_request(id, service_code) values(66,'XYZ-123');

insert into category values(1,'phones');
insert into category values(2,'tvs');
insert into category values(3,'pcs');