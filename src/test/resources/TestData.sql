delete from equipments where id>0;
delete from categories where id>0;
insert into categories values(1,'phone');
insert into equipments (category_id, name, service_code, service_status, id) values (1, 'huawei p30 pro', 'ABC-123','WORKING', 1001);