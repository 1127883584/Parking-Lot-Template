create sequence hibernate_sequence start with 1 increment by 1;
create table parking_lot (id integer not null, capacity integer not null check (capacity>=0), name varchar(255) not null, position varchar(255) not null, primary key (id));
alter table parking_lot add constraint UK_2w49woqis4x25gei7vnre7x1i unique (name);