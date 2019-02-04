CREATE TABLE forgotten_password
(
  id          serial                   not null
    constraint forgotten_password_pkey
      primary key,
  token       text                     not null,
  email       varchar(255)             not null,
  expiry_date timestamp with time zone not null
);

create unique index forgotten_password_token_uindex
on forgotten_password (token);