create table Produits (
    ID_Produit number constraint pk_produit primary key,
    Quantitee number,
    Prix_U Double
);

create table Payments (
    ID_Payement number constraint pk_payment primary key,
    Prix_F Double,
    Mode_Payment varchar2(10)
);

create table Commandes (
    ID_Commande number constraint pk_commande primary key,
    ID_Produit number,
    ID_Payement number,
    Quantitee number,
    Prix Double,
    Status_commande varchar2(10)
);
alter table Commandes add constraint fk_pro_com foreign key (ID_Produit) references Produits (ID_Produit);
alter table Commandes add constraint fk_pay_com foreign key (ID_Payement) references Payments (ID_Payement);

create table Panier (
    ID_Panier number constraint pk_panier primary key,
    ID_Commande number,
    Date_C Date default sysdate(),
    Date_U Date default Date_C,
    Status_panier varchar2(10)
);
alter table Panier add constraint fk_com_pan foreign key (ID_Commande) references Commandes (ID_Commande);