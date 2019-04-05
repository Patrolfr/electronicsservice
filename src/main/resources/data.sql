--# use only with create-drop hibernate ddl option
insert into categories values(1,'phone');
insert into categories values(2,'tv');
insert into categories values(3,'pc');
insert into categories values(4,'fringe');

insert into equipments (category_id, name, service_code, service_status, id) values (1, 'huawei p30 pro', 'ABC-123','WORKING', 1001);
insert into equipments (category_id, name, service_code, service_status, id) values (1, 'iphone x10', 'XYZ-123','WORKING', 1002);
insert into equipments (category_id, name, service_code, service_status, id) values (1, 'xiaomi u2', 'RAW-532','WORKING', 1003);
insert into equipments (category_id, name, service_code, service_status, id) values (1, 'google pixel 3', 'gOg-909','WORKING', 1004);

insert into equipments (category_id, name, service_code, service_status, id) values (2, 'samsung view', 'TVC-123','WORKING', 1005);
insert into equipments (category_id, name, service_code, service_status, id) values (2, 'phillips4k', 'TVZ-123','WORKING', 1006);
insert into equipments (category_id, name, service_code, service_status, id) values (2, 'lg plasma 2701', 'TVW-532','WORKING', 1007);
insert into equipments (category_id, name, service_code, service_status, id) values (2, 'stary kineskop', 'TVg-909','IN_SERVICE', 1008);

insert into parameters values(1,'headphones','yes');
insert into parameters values(2,'resolution','1920x1080');
insert into parameters values(3,'inchees','27');
insert into parameters values(4,'inchees','55');
insert into parameters values(5,'headphones','no');
insert into parameters values(6,'color','black');
insert into parameters values(7,'color','blue');
insert into parameters values(8,'color','white');
insert into parameters values(9,'color','red');
insert into parameters values(10,'resolution','4k');

insert into equipments_parameters values(1001,1);
insert into equipments_parameters values(1001,2);
insert into equipments_parameters values(1001,6);
insert into equipments_parameters values(1002,5);
insert into equipments_parameters values(1002,7);
insert into equipments_parameters values(1003,8);
insert into equipments_parameters values(1004,9);
insert into equipments_parameters values(1005,4);
insert into equipments_parameters values(1006,10);
insert into equipments_parameters values(1007,3);

-- insert into comments values(1001, 'decent phone');
-- insert into comments values(1001, 'advertised by RL9');
-- insert into comments values(1002, 'Hasn"t been in service.');
-- insert into comments values(1002, 'Premiered in 2018');
-- insert into comments values(1003, 'Premiered in 2018');
-- insert into comments values(1004, 'Premiered in 2017');


