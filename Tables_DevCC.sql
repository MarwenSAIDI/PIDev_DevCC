create table Payments (
    ID_Payment int,
    Prix_F Double,
    Mode_Payment varchar(10)
);
alter table Payments add constraint pk_payment primary key (ID_Payment);

create table Commandes (
    ID_Commande int,
    ID_Produit int,
    ID_Payment int,
    Quantitee int,
    Prix Double,
    Status_commande varchar(10)
);
alter table Commandes add constraint pk_commande primary key (ID_Commande);
alter table Commandes add constraint fk_pay_com foreign key (ID_Payment) references Payments (ID_Payment) ON DELETE CASCADE;

create table Paniers (
    ID_Panier int,
    ID_Commande int,
    Date_C Date,
    Date_U Date default Date_C,
    Status_panier varchar(10)
);

alter table Paniers add constraint pk_panier primary key (ID_Panier);
alter table Paniers add constraint fk_com_pan foreign key (ID_Commande) references Commandes (ID_Commande) ON DELETE CASCADE;
