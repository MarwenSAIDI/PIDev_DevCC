create table Paniers (
    ID_Panier int,
    ID_User int,
    Date_C Date,
    Date_U Date default Date_c, 
    Status_panier varchar(10)
);

create table Commandes (
    ID_Commande int,
    ID_Panier int,
    ID_Produit int,
    Quantitee int,
    Prix_U Double
);

create table Payments (
    ID_Payment int,
    ID_Panier int,
    Prix_F Double,
    Mode_payment varchar(10)
);

alter table paniers add constraint pk_panier primary key (ID_Panier);
alter table commandes add constraint pk_commande primary key (ID_Commande);
alter table payments add constraint pk_payment primary key (ID_Payment);

alter table payments add constraint fk_pan_pay foreign key (ID_Panier) references paniers (ID_Panier) on delete cascade;
alter table commandes add constraint fk_pan_com foreign key (ID_Panier) references paniers (ID_Panier) on delete cascade;
alter table paniers add constraint fk_usr_pan foreign key (ID_User) references users (ID_User) on delete cascade;
