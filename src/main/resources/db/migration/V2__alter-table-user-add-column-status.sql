alter table user add column active tinyint not null;
update user set active = 1;