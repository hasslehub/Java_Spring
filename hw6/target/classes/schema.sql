create table if not exists notes (
    id          INT AUTO_INCREMENT primary key,
    header       varchar(255) not null,
    description varchar(255) not null,
    create_data timestamp    not null
);