drop table parking_lot if exists;
drop table parking_order if exists;
drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start with 1 increment by 1;
create table parking_lot (id integer not null, capacity integer not null check (capacity>=0), name varchar(255) not null, position varchar(255) not null, primary key (id));
create table parking_order (id integer not null, car_number varchar(255) not null, end_time bigint not null, start_time bigint not null, status varchar(255) not null, parking_lot_id integer not null, primary key (id));
alter table parking_lot add constraint UK_2w49woqis4x25gei7vnre7x1i unique (name);
alter table parking_order add constraint FK701du5mj20ogq7fyhccisnj foreign key (parking_lot_id) references parking_lot;