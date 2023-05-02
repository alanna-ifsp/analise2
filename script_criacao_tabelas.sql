create database sistema_bancario;
use sistema_bancario; 

create table cliente
(
id int primary key auto_increment, 
nome varchar(60), 
cpf varchar(15),
dt_nascimento date
);


create table conta
(
id int primary key auto_increment, 
id_cliente int,
tipo_conta varchar(3),
limite_saque decimal(20,2),
limite_transferencia decimal (20,2),
saldo decimal(20,2),
data_abertura date
);

alter table conta add constraint foreign key (id_cliente) references cliente (id);



create table conta_especial
(
	id int primary key auto_increment, 
    id_conta int, 
    limite_credito decimal(20,2)
);

alter table conta_especial add constraint foreign key (id_conta) references conta(id);



create table conta_poupanca
(
	id int primary key auto_increment, 
    id_conta int, 
    dia_aniversario int
);

alter table conta_poupanca add constraint foreign key (id_conta) references conta(id);

/*INSERT*/

insert into cliente(nome,cpf,dt_nascimento) values ('Alanna','478717128881','2008-7-04');

insert into conta(id_cliente,tipo_conta,limite_saque,limite_transferencia,saldo,data_abertura)
values(1,'C','1000.00','2000.00','4000.00', CURDATE());

insert into conta(id_cliente,tipo_conta,limite_saque,limite_transferencia,saldo,data_abertura)
values(1,'P','1000.00','2000.00','4000.00', CURDATE());

insert into conta(id_cliente,tipo_conta,limite_saque,limite_transferencia,saldo,data_abertura)
values(1,'E','1000.00','2000.00','4000.00', CURDATE());

insert into conta_especial(id_conta,limite_credito)
values(3,5200.00);

insert into conta_poupanca(id_conta,dia_aniversario) values(2,31);

select * from conta_poupanca;

/*END INSERT*/


