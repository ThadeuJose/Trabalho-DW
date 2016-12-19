create table usuarios (
       idusu char(4) not null,
       emailusu varchar(30) unique,
       nmusu varchar(50) not null,
       senhausu varchar(50) not null,
       primary key (idusu)       
);

create table cidades(
       idcid char(4) not null,
       nmcid varchar(50) not null,
       primary key (idcid)       
);

create table paises(
       idpais char(3) not null,
       nmpais varchar(50) not null,
       primary key (idpais)
);

create table aeroportos (
       idaer char(4) not null,
       nmaer varchar(50) not null,
       txemb float not null,
       idcid char(4) not null,
       primary key (idaer),       
       CONSTRAINT fk_aeroportos FOREIGN KEY (idcid)REFERENCES cidades(idcid)
);

create table classesassento (
       idcla char(4) not null,
       nmcla varchar(50) not null,
       pccla float not null,
       primary key (idcla)
);

create table passageiros(
       idpas char(4) not null,
       nmpas varchar(50) not null,
       snpas varchar(50) not null,
       idpaispas char(3) not null,
       telpas varchar(20) not null,
       endpas varchar(200) not null,
       iddpas int not null , 
       cpfpas varchar(11) default '00000000000',
       pstpas varchar(10) default '0000000000', 
       primary key (idpas),
       foreign key (idpaispas) references paises(idpais)
       
);

create table voos(
       idvoo char(4) not null,
       idaerpar char(4) not null,
       idaerche char(4) not null,
       hrpar time not null,
       dtpar date not null,
       hrche time not null,
       dtche date not null,
       primary key (idvoo),
       foreign key (idaerpar) references aeroportos (idaer),
       foreign key (idaerche) references aeroportos (idaer)       
);

create table assentos(
       idass char(4) not null,
       idvoo char(4) not null,
       idpas char(4),
       idcla char(4) not null,
       nmass varchar(5) not null,
       primary key (idass),
       foreign key (idvoo) references voos(idvoo),
       foreign key (idpas) references passageiros(idpas),
       foreign key (idcla) references classesassento(idcla)
);

create table reservas(
       idres char(4) not null,
       cdchk char(4) not null,
       idusu char(4) not null,
       primary key (idres),
       foreign key (idusu) references usuarios(idusu)
);

create table assentoscomprados(
       idres char(4) not null,
       idass char(4) not null,
       idvoo char(4) not null,
       vrpag float not null,
       primary key (idres,idass, idvoo),
       foreign key (idvoo) references voos(idvoo),
       foreign key (idass) references assentos(idass)
);
