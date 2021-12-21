create table "compte" (
                        id varchar(255) not null,
                        nom varchar(50) not null,
                        prenom varchar(50) not null,
                        pays varchar(50) not null,
                        no_passeport varchar(9) not null,
                        num_tel varchar(15) not null,
                        secret varchar(50),
                        iban varchar(50),
                        primary key (id)
);