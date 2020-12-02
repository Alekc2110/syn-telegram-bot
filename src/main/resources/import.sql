insert into users (id,email, gender, first_name, phone, already_registered, is_active) values ('1','0509135146o@gmail.com','MALE', 'ALeksander','+380985590167','true','true');
insert into users (id,email, gender, first_name, phone, already_registered, is_active) values ('2','alalexalexal@gmail.com','MALE', 'John', '+380956886449','true','true');
insert into users (id,email, gender, first_name, phone, already_registered, is_active) values ('3','0504268686@ukr.net','FEMALE', 'Ksusha','+380509135146', 'true','true');
insert into users (id,email, gender, first_name, phone, already_registered, is_active) values ('4','jhhjh@i.ua','MALE', 'Kolya','+380509988450', 'true','true');
-- insert into users (id,email, gender, first_name, phone, already_registered, is_active) values ('5','gri@i.ua','MALE', 'Sasha','+380939363668', 'true','true');



insert into goals (id,description, finish_time, registered_time, goal_status, user_id) values ('1', 'some desc','2020-11-21 15:30','2020-11-15 15:30', 'ACTIVE', (select id from users u where u.id = 1));
insert into goals (id,description, finish_time, registered_time, goal_status, user_id) values ('2','какая то цель','2020-11-14 15:30','2020-11-11 15:30', 'FAIL', (select id from users u where u.id = 2));
insert into goals (id,description, finish_time, registered_time, goal_status, user_id) values ('3', 'some another another desc','2020-11-13 16:30','2020-11-11 15:30', 'DONE', (select id from users u where u.id = 3));
-- insert into goals (id,description, finish_time, registered_time, goal_status, user_id) values ('4', 'another another desc','2020-12-05 16:30','2020-11-11 15:30', 'ACTIVE', (select id from users u where u.id = 4));