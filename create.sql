
    create table order_detail (
        id serial not null,
        quantity integer not null,
        product_id bigint unique,
        remaining_preparation_time timestamp(6),
        request_date_time timestamp(6),
        start_preparation_date_time timestamp(6),
        comments varchar(255),
        preparation_order_status varchar(255) check (preparation_order_status in ('REQUESTED','READY','PREPARING','RELIVERED')),
        primary key (id)
    );

    create table person (
        has_active boolean not null,
        id serial not null,
        pis integer not null unique,
        vip boolean,
        admission_date timestamp(6),
        date_of_birth timestamp(6),
        resignation_date timestamp(6),
        dtype varchar(31) not null,
        allergies varchar(255),
        city varchar(255),
        cpf varchar(255) not null unique,
        employee_aviability varchar(255) check (employee_aviability in ('BUSY','AVAILABLE')),
        merital_status varchar(255) check (merital_status in ('SINGLE','MARRIED','WIDOVED','DIVORCED','SEPARATE')),
        name varchar(255) not null,
        observations varchar(255),
        phone varchar(255),
        responsability varchar(255) check (responsability in ('CLEANER','WAITER','COOK','MANAGER')),
        rg varchar(255) not null unique,
        schooling varchar(255) check (schooling in ('ELEMENTARY','HIGH','HIGHER')),
        state varchar(255),
        street varchar(255),
        zip_code varchar(255),
        primary key (id)
    );

    create table product (
        cost_price float(53) not null,
        has_active boolean not null,
        sale_price float(53) not null,
        created_date timestamp(6),
        id bigserial not null,
        updated_date timestamp(6),
        code varchar(255) not null unique,
        comments varchar(255),
        created_by varchar(255),
        description varchar(255) not null,
        name varchar(255) not null,
        preparation_time varchar(255),
        product_type varchar(255) not null check (product_type in ('SNACK','DRINK','DESSERT')),
        updated_by varchar(255),
        primary key (id)
    );

    create table restaurant_table (
        employee_id integer unique,
        id serial not null,
        number integer unique,
        code varchar(255) not null unique,
        name varchar(255) not null unique,
        table_status varchar(255) check (table_status in ('RESERVED','OCCUPIED','AVAILABLE')),
        primary key (id)
    );

    create table tab (
        customer_id integer,
        id serial not null,
        table_id integer,
        code varchar(255) not null unique,
        comments varchar(255),
        tab_status varchar(255) check (tab_status in ('OPEN','PAID','CLOSED')),
        primary key (id)
    );

    create table tab_order_details (
        order_details_id integer not null unique,
        tab_id integer not null
    );

    alter table if exists order_detail 
       add constraint FKb8bg2bkty0oksa3wiq5mp5qnc 
       foreign key (product_id) 
       references product;

    alter table if exists restaurant_table 
       add constraint FKp5v6aun4kkd2pnthrh6fp3oah 
       foreign key (employee_id) 
       references person;

    alter table if exists tab 
       add constraint FKqn1o2q113cq9nttg741bo5rk 
       foreign key (customer_id) 
       references person;

    alter table if exists tab 
       add constraint FK5q1wepg1479p2ytwrhr4aga0v 
       foreign key (table_id) 
       references restaurant_table;

    alter table if exists tab_order_details 
       add constraint FKdw339t53x57288tvspcm777lw 
       foreign key (order_details_id) 
       references order_detail;

    alter table if exists tab_order_details 
       add constraint FKqk646hrhtperogt3q3dfd7qup 
       foreign key (tab_id) 
       references tab;

    create table order_detail (
        id serial not null,
        quantity integer not null,
        product_id bigint unique,
        remaining_preparation_time timestamp(6),
        request_date_time timestamp(6),
        start_preparation_date_time timestamp(6),
        comments varchar(255),
        preparation_order_status varchar(255) check (preparation_order_status in ('REQUESTED','READY','PREPARING','RELIVERED')),
        primary key (id)
    );

    create table person (
        has_active boolean not null,
        id serial not null,
        pis integer not null unique,
        vip boolean,
        admission_date timestamp(6),
        date_of_birth timestamp(6),
        resignation_date timestamp(6),
        dtype varchar(31) not null,
        allergies varchar(255),
        city varchar(255),
        cpf varchar(255) not null unique,
        employee_aviability varchar(255) check (employee_aviability in ('BUSY','AVAILABLE')),
        merital_status varchar(255) check (merital_status in ('SINGLE','MARRIED','WIDOVED','DIVORCED','SEPARATE')),
        name varchar(255) not null,
        observations varchar(255),
        phone varchar(255),
        responsability varchar(255) check (responsability in ('CLEANER','WAITER','COOK','MANAGER')),
        rg varchar(255) not null unique,
        schooling varchar(255) check (schooling in ('ELEMENTARY','HIGH','HIGHER')),
        state varchar(255),
        street varchar(255),
        zip_code varchar(255),
        primary key (id)
    );

    create table product (
        cost_price float(53) not null,
        has_active boolean not null,
        sale_price float(53) not null,
        created_date timestamp(6),
        id bigserial not null,
        updated_date timestamp(6),
        code varchar(255) not null unique,
        comments varchar(255),
        created_by varchar(255),
        description varchar(255) not null,
        name varchar(255) not null,
        preparation_time varchar(255),
        product_type varchar(255) not null check (product_type in ('SNACK','DRINK','DESSERT')),
        updated_by varchar(255),
        primary key (id)
    );

    create table restaurant_table (
        employee_id integer unique,
        id serial not null,
        number integer unique,
        code varchar(255) not null unique,
        name varchar(255) not null unique,
        table_status varchar(255) check (table_status in ('RESERVED','OCCUPIED','AVAILABLE')),
        primary key (id)
    );

    create table tab (
        customer_id integer,
        id serial not null,
        table_id integer,
        code varchar(255) not null unique,
        comments varchar(255),
        tab_status varchar(255) check (tab_status in ('OPEN','PAID','CLOSED')),
        primary key (id)
    );

    create table tab_order_details (
        order_details_id integer not null unique,
        tab_id integer not null
    );

    alter table if exists order_detail 
       add constraint FKb8bg2bkty0oksa3wiq5mp5qnc 
       foreign key (product_id) 
       references product;

    alter table if exists restaurant_table 
       add constraint FKp5v6aun4kkd2pnthrh6fp3oah 
       foreign key (employee_id) 
       references person;

    alter table if exists tab 
       add constraint FKqn1o2q113cq9nttg741bo5rk 
       foreign key (customer_id) 
       references person;

    alter table if exists tab 
       add constraint FK5q1wepg1479p2ytwrhr4aga0v 
       foreign key (table_id) 
       references restaurant_table;

    alter table if exists tab_order_details 
       add constraint FKdw339t53x57288tvspcm777lw 
       foreign key (order_details_id) 
       references order_detail;

    alter table if exists tab_order_details 
       add constraint FKqk646hrhtperogt3q3dfd7qup 
       foreign key (tab_id) 
       references tab;
