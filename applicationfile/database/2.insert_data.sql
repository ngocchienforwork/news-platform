set search_path to news;

insert into role(code,name) values('MANAGER','Quản trị hệ thống');
insert into role(code,name) values('USER','người dùng');

insert into users(username,password,fullname,status)
values('admin','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','admin',1);
insert into users(username,password,fullname,status)
values('user','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','user',1);

INSERT INTO users_role(user_id,role_id) VALUES (1,1);
INSERT INTO users_role(user_id,role_id) VALUES (2,2);

insert into category(code, name) 
values('THE_THAO','Thể thao');
insert into category(code, name) 
values('THE_GIOI','Thế giới');
insert into category(code, name) 
values('CHINH_TRI','Chính trị');
insert into category(code, name) 
values('THOI_SU','Thời sự');
insert into category(code, name) 
values('GOC_NHIN','Góc nhìn');
insert into category(code, name) 
values('KINH_DOANH','Kinh doanh');
insert into category(code, name) 
values('PHAP_LUAT','Pháp luật');
insert into category(code, name) 
values('GIAO_DUC','Giao dục');
insert into category(code, name) 
values('SUC_KHOE','Sức khỏe');
insert into category(code, name) 
values('GIA_DINH','Gia đình');
insert into category(code, name) 
values('CONG_DONG','Cộng đồng');
insert into category(code, name) 
values('TAM_SU','Tâm sự');




