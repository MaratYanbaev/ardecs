use car_configuration;

create table accessory
(
    id   bigint auto_increment
        primary key,
    name varchar(64) not null
);

create table brand
(
    id   bigint auto_increment
        primary key,
    name varchar(32) not null
);

create table color
(
    id   bigint auto_increment
        primary key,
    name varchar(32) not null
);

create table complectation
(
    id   bigint auto_increment
        primary key,
    name varchar(32) not null
);

create table engine
(
    id          bigint auto_increment
        primary key,
    description varchar(190) not null,
    name        varchar(64)  not null
);

create table hibernate_sequence
(
    next_val bigint null
);

create table model
(
    id       bigint auto_increment
        primary key,
    name     varchar(15)   not null,
    brand_id bigint        not null,
    price    int default 0 not null,
    constraint UK_h8964x0xnbdtwafcsrshnktih
        unique (name),
    constraint UK_model
        unique (name),
    constraint model_fk
        foreign key (brand_id) references brand (id)
);

create table model_complectation
(
    model_id bigint not null,
    comp_id  bigint not null,
    price    int    not null,
    primary key (model_id, comp_id),
    constraint complectation_model_fk
        foreign key (comp_id) references complectation (id),
    constraint model_complectation_fk
        foreign key (model_id) references model (id)
);

create table accessory_model_complect
(
    access_id bigint not null,
    model_id  bigint not null,
    comp_id   bigint not null,
    price     int    not null,
    primary key (access_id, model_id, comp_id),
    constraint accessory_model_complect_fk
        foreign key (access_id) references accessory (id),
    constraint model_complect_accessory_fk
        foreign key (model_id, comp_id) references model_complectation (model_id, comp_id)
);

create table color_model_complect
(
    color_id bigint not null,
    model_id bigint not null,
    comp_id  bigint not null,
    price    int    not null,
    primary key (color_id, model_id, comp_id),
    constraint color_model_complect_fk
        foreign key (color_id) references color (id),
    constraint model_complect_color_fk
        foreign key (model_id, comp_id) references model_complectation (model_id, comp_id)
);

create table engine_model_complect
(
    engine_id bigint not null,
    model_id  bigint not null,
    comp_id   bigint not null,
    price     int    not null,
    primary key (engine_id, model_id, comp_id),
    constraint engine_model_complect_fk
        foreign key (engine_id) references engine (id),
    constraint model_complect_engine_fk
        foreign key (model_id, comp_id) references model_complectation (model_id, comp_id)
);

create table roles
(
    name varchar(255) not null,
    constraint roles_name_uindex
        unique (name)
);

alter table roles
    add primary key (name);

create table users
(
    name     varchar(100) not null,
    password varchar(255) not null,
    constraint users_name_uindex
        unique (name)
);

alter table users
    add primary key (name);

create table user_role
(
    user varchar(100) not null,
    role varchar(255) not null,
    constraint role_user_fk
        foreign key (role) references roles (name),
    constraint user_role_fk
        foreign key (user) references users (name)
);




INSERT INTO accessory (id, name) VALUES (1, 'спойлер');
INSERT INTO accessory (id, name) VALUES (2, 'хромированная накладка');
INSERT INTO accessory (id, name) VALUES (3, 'бокс для груза');
INSERT INTO accessory (id, name) VALUES (4, 'крепление для велосипеда');
INSERT INTO accessory (id, name) VALUES (5, 'поперечены для багажника');
INSERT INTO accessory (id, name) VALUES (6, 'дефлекторы на окна');
INSERT INTO accessory (id, name) VALUES (7, 'фаркоп съемный');
INSERT INTO accessory (id, name) VALUES (8, 'защита для дисков');
INSERT INTO accessory (id, name) VALUES (9, 'защита для кузова');
INSERT INTO accessory (id, name) VALUES (10, 'защитная пленка');
INSERT INTO accessory (id, name) VALUES (11, 'комплект брызговиков');
INSERT INTO accessory (id, name) VALUES (12, 'молдинги для дверей');
INSERT INTO accessory (id, name) VALUES (13, 'секретные колесные гайки');
INSERT INTO accessory (id, name) VALUES (14, 'навигационный блок');
INSERT INTO accessory (id, name) VALUES (15, 'пепельница');
INSERT INTO accessory (id, name) VALUES (16, 'комплект резиновых ковриков');
INSERT INTO accessory (id, name) VALUES (17, 'комплект текстильных ковриков');
INSERT INTO accessory (id, name) VALUES (18, 'коврик багажника');
INSERT INTO accessory (id, name) VALUES (19, 'ремонтный комплект шины');
INSERT INTO accessory (id, name) VALUES (20, 'легкосплавные диски 16"');
INSERT INTO accessory (id, name) VALUES (21, 'легкосплавные диски 17"');
INSERT INTO accessory (id, name) VALUES (22, 'легкосплавные диски 18"');
INSERT INTO accessory (id, name) VALUES (23, 'легкосплавные диски 19"');
INSERT INTO accessory (id, name) VALUES (24, 'легкосплавные диски 20"');
INSERT INTO accessory (id, name) VALUES (25, 'легкосплавные диски 21"');


INSERT INTO brand (id, name) VALUES (5, 'Toyota');
INSERT INTO brand (id, name) VALUES (130, 'Lexus');

INSERT INTO color (id, name) VALUES (1, 'белый');
INSERT INTO color (id, name) VALUES (2, 'черный');
INSERT INTO color (id, name) VALUES (3, 'красный');
INSERT INTO color (id, name) VALUES (4, 'синий');
INSERT INTO color (id, name) VALUES (5, 'серый');
INSERT INTO color (id, name) VALUES (6, 'коричневый');
INSERT INTO color (id, name) VALUES (7, 'бронзовый');
INSERT INTO color (id, name) VALUES (8, 'серебристый');
INSERT INTO color (id, name) VALUES (9, 'желтый');
INSERT INTO color (id, name) VALUES (10, 'оранжевый');
INSERT INTO color (id, name) VALUES (11, 'вишневый');
INSERT INTO color (id, name) VALUES (12, 'золотой');
INSERT INTO color (id, name) VALUES (13, 'бирюзовый');

INSERT INTO complectation (id, name) VALUES (1, 'Стандарт');
INSERT INTO complectation (id, name) VALUES (2, 'Классик');
INSERT INTO complectation (id, name) VALUES (3, 'Комфорт');
INSERT INTO complectation (id, name) VALUES (4, 'Стандарт Плюс');
INSERT INTO complectation (id, name) VALUES (5, 'Престиж');
INSERT INTO complectation (id, name) VALUES (6, 'Престиж Safety');
INSERT INTO complectation (id, name) VALUES (149, 'Люкс');
INSERT INTO complectation (id, name) VALUES (150, 'Executive Lounge');
INSERT INTO complectation (id, name) VALUES (151, 'Exclusive');
INSERT INTO complectation (id, name) VALUES (152, 'Люкс Safety');

INSERT INTO engine (id, description, name) VALUES (1, '122 л.с.', '1.6/6МКПП');
INSERT INTO engine (id, description, name) VALUES (2, '122 л.с.', '1.6/вариатор');
INSERT INTO engine (id, description, name) VALUES (3, '150 л.с.', '2.0/6АКПП');
INSERT INTO engine (id, description, name) VALUES (4, '181 л.с.', '2.5/6АКПП');
INSERT INTO engine (id, description, name) VALUES (5, '249 л.с.', '3.5/8АКПП');
INSERT INTO engine (id, description, name) VALUES (6, '148 л.с.', '2.0/вариатор');
INSERT INTO engine (id, description, name) VALUES (7, '115 л.с.', '1.2t/вариатор');
INSERT INTO engine (id, description, name) VALUES (8, '166 л.с.', '2.7/5МКПП');
INSERT INTO engine (id, description, name) VALUES (9, '166 л.с.', '2.7/6АКПП');
INSERT INTO engine (id, description, name) VALUES (10, '177 л.с.', '2.8d/6АКПП');
INSERT INTO engine (id, description, name) VALUES (11, '249 л.с.', '4.0/6АКПП');
INSERT INTO engine (id, description, name) VALUES (12, '309 л.с.', '4.6/6АКПП');
INSERT INTO engine (id, description, name) VALUES (13, '249 л.с.', '4.5d/6АКПП');



INSERT INTO hibernate_sequence (next_val) VALUES (165);
INSERT INTO hibernate_sequence (next_val) VALUES (165);
INSERT INTO hibernate_sequence (next_val) VALUES (1);
INSERT INTO hibernate_sequence (next_val) VALUES (1);
INSERT INTO hibernate_sequence (next_val) VALUES (1);
INSERT INTO hibernate_sequence (next_val) VALUES (1);
INSERT INTO hibernate_sequence (next_val) VALUES (1);
INSERT INTO hibernate_sequence (next_val) VALUES (1);
INSERT INTO hibernate_sequence (next_val) VALUES (1);
INSERT INTO hibernate_sequence (next_val) VALUES (1);
INSERT INTO hibernate_sequence (next_val) VALUES (1);

INSERT INTO model (id, name, brand_id, price) VALUES (131, 'NX', 130, 2300000);
INSERT INTO model (id, name, brand_id, price) VALUES (132, 'RX', 130, 3400000);
INSERT INTO model (id, name, brand_id, price) VALUES (139, 'Rav4', 5, 1400000);
INSERT INTO model (id, name, brand_id, price) VALUES (149, 'Camry', 5, 1630000);

INSERT INTO model_complectation (model_id, comp_id, price) VALUES (132, 5, 0);
INSERT INTO model_complectation (model_id, comp_id, price) VALUES (139, 1, 50000);
INSERT INTO model_complectation (model_id, comp_id, price) VALUES (139, 3, 300000);
INSERT INTO model_complectation (model_id, comp_id, price) VALUES (149, 3, 0);
INSERT INTO model_complectation (model_id, comp_id, price) VALUES (149, 151, 1000000);

INSERT INTO accessory_model_complect (access_id, model_id, comp_id, price) VALUES (1, 132, 5, 120000);
INSERT INTO accessory_model_complect (access_id, model_id, comp_id, price) VALUES (1, 139, 1, 25000);
INSERT INTO accessory_model_complect (access_id, model_id, comp_id, price) VALUES (1, 139, 3, 100);
INSERT INTO accessory_model_complect (access_id, model_id, comp_id, price) VALUES (2, 139, 1, 30000);
INSERT INTO accessory_model_complect (access_id, model_id, comp_id, price) VALUES (3, 139, 1, 120000);

INSERT INTO engine_model_complect (engine_id, model_id, comp_id, price) VALUES (3, 139, 1, 100000);
INSERT INTO engine_model_complect (engine_id, model_id, comp_id, price) VALUES (4, 132, 5, 0);
INSERT INTO engine_model_complect (engine_id, model_id, comp_id, price) VALUES (4, 139, 1, 120000);
INSERT INTO engine_model_complect (engine_id, model_id, comp_id, price) VALUES (5, 132, 5, 200000);

INSERT INTO color_model_complect (color_id, model_id, comp_id, price) VALUES (1, 132, 5, 0);
INSERT INTO color_model_complect (color_id, model_id, comp_id, price) VALUES (1, 139, 1, 31000);
INSERT INTO color_model_complect (color_id, model_id, comp_id, price) VALUES (3, 139, 1, 10000);

INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('CREATOR');
INSERT INTO roles (name) VALUES ('UPDATER');
INSERT INTO roles (name) VALUES ('VIEWER');

INSERT INTO users (name, password) VALUES ('admin', '$2a$10$PJB01c2LSrTclGBmAcUYY.a7rkM70EZCI7EFdLEyWMeCBO8xTRYoG');
INSERT INTO users (name, password) VALUES ('creator', '$2a$10$IOIDy4FUUwSr9Udy.a.N2.l3GwfEF/DefKoHouyK.BwlAGWUbA5ne');
INSERT INTO users (name, password) VALUES ('updater', '$2a$10$cnqPzjSiod37NQ5D207HHu.UM8EmvcZUzPv6GVp3hNGrFhtZSuY0.');
INSERT INTO users (name, password) VALUES ('viewer', '$2a$10$F/wbxUNH1vhMZknhHO7zR.LM9vVk6H42qlCkbSjbLSBAUbMeQaS1m');

INSERT INTO user_role (user, role) VALUES ('admin', 'ADMIN');
INSERT INTO user_role (user, role) VALUES ('creator', 'CREATOR');
INSERT INTO user_role (user, role) VALUES ('updater', 'UPDATER');
INSERT INTO user_role (user, role) VALUES ('viewer', 'VIEWER');