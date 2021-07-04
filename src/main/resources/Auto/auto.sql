create table engine(
id serial primary key,
name varchar(255)
);

create table carcass(
id serial primary key,
name varchar(255)
);
create table ACPP(
id serial primary key,
name varchar(255)
);

create table auto(
id serial primary key,
name varchar(255),
ACPP_id int references ACPP(id),
carcass_id int references carcass(id),
engine_id int references engine(id)
);

insert into engine (name) values ('V16');
('V8');
('V4');
insert into carcass (name) values ('comfort');
('luxe'),
('prime');
insert into ACPP (name) values ('auto'),
('robot'),
('mechanic');

insert into auto (name, ACPP_id, carcass_id, engine_id) values ('audi', 1, 2, 2);
('ferrari', 1, 2, 1),
('lada', 3, 1, 1),
('hyndai', 3, 1, 1),
('volvo', 3, 3, 2),

select a.name AUTO,
(select name from engine where engine.id = a.engine_id) ENGINE,
(select name from ACPP where ACPP.id = a.ACPP_id) ACPP,
(select name from carcass where carcass.id = a.carcass_id) from auto a;

select e.name from engine e left join auto a on a.engine_id = e.id where a.engine_id is null;
select p.name from ACPP p left join auto a on a.ACPP_id = p.id where a.ACPP_id is null;
select c.name from carcass c left join auto a on a.carcass_id = c.id where a.carcass_id is null;