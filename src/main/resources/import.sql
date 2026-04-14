-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

-- For testing purposes on dev mode
insert into USER_AUTH (EMAIL, PASSWORD, ROLE, USERNAME, ID)
values('admin',
'$2a$10$UbYlPA/iZ1e7MHczllnW/uEmNLPB369zEQzxmYA3UQMEG5MRyWed.',
'ADMIN', 'admin', 957134);