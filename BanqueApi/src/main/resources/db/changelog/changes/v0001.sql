create table "compte" (
        id varchar(255) not null,
        nom varchar(50) not null,
        prenom varchar(50) not null,
        pays varchar(50) not null,
        no_passeport varchar(9) not null,
        num_tel varchar(15) not null,
        secret varchar(500) not null,
        iban varchar(50) not null,
        primary key (id)
);

create table "carte" (
        id varchar(255) not null,
        num_carte varchar(16) not null,
        code varchar(4) not null,
        cryptogramme varchar(3) not null,
        bloquee boolean not null default false,
        localisation boolean not null default false,
        plafond decimal(10,2) not null,
        sans_contact boolean not null default true,
        virtuelle boolean not null default false,
        compte_id varchar(255) not null,
        primary key (id)
);

create table "operation" (
        id varchar(255) not null,
        date date not null,
        libelle varchar(50) not null,
        montant decimal(6,2) not null,
        taux_applique decimal(3,2),
        compte_crediteur_id varchar(255) not null,
        nom_compte_crediteur varchar(50) not null,
        categorie varchar (50) not null,
        pays varchar (50) not null,
        primary key (id)
);
