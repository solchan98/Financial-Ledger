create table account (
    id bigint not null auto_increment,
    create_at datetime(6),
    email varchar(255) not null,
    login_at datetime(6),
    name varchar(255) not null,
    password varchar(255) not null,
    primary key (id)
);

create table ledger (
    id bigint not null auto_increment,
    content TEXT,
    create_at datetime(6),
    is_delete bit,
    price bigint,
    update_at datetime(6),
    write_at date,
    account_id bigint,
    primary key (id)
);

create table trash_basket_history (
    id bigint not null auto_increment,
    date datetime(6),
    type varchar(255),
    ledger_id bigint,
    primary key (id)
);

alter table ledger
    add constraint accountandledger
        foreign key (account_id)
            references account (id);

alter table trash_basket_history
    add constraint ledgerandhistory
        foreign key (ledger_id)
            references ledger (id);