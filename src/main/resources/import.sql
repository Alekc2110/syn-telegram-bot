insert into users (id,email, gender, first_name, phone, already_registered, is_active) values ('1','edd@ai.com','MALE', 'ALeksander','380985590167','true','true');
insert into users (id,email, gender, first_name, phone, already_registered, is_active) values ('2','ddfff@ai.com','MALE', 'John', '380956886449','true','true');
insert into users (id,email, gender, first_name, phone, already_registered, is_active) values ('3','jdjdjd@ai.ru','FEMALE', 'Ksusha','380509135146', 'true','true');


insert into goals (id,description, finish_time, registered_time, goal_status, user_id) values ('1', 'some desc','2020-11-21 15:30','2020-11-15 15:30', 'OVERDUE', (select id from users u where u.id = 1));
insert into goals (id,description, finish_time, registered_time, goal_status, user_id) values ('2','some another desc','2020-11-14 15:30','2020-11-11 15:30', 'ACTIVE', (select id from users u where u.id = 2));
insert into goals (id,description, finish_time, registered_time, goal_status, user_id) values ('3', 'some another another desc','2020-11-13 16:30','2020-11-11 15:30', 'OVERDUE', (select id from users u where u.id = 3));