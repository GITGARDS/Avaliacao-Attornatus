create schema Attornatus;
use Attornatus;
create table pessoa(
	id					bigint not null auto_increment,
	nome				varchar(100)	not null unique,
	data_de_nascimento	timestamp,
	primary key			(id)
);

create table endereco(
	id					bigint not null auto_increment,
	pessoa_id			bigint not null,
	logradouro			varchar(100)	not null,
	cep					char(8),
	numero				numeric,
	cidade				varchar(100)	not null,
	endereco_principal	boolean			default false,
	primary key			(id),

	constraint fk_endereco_pessoa_id 
		foreign key(pessoa_id) references pessoa(id)
);

insert into pessoa (nome, data_de_nascimento) values
	('pessoa1', '1991-01-01'),
	('pessoa2', '1992-02-02'),
	('pessoa3', '1991-03-03'),
	('pessoa4', '1994-04-04'),
	('pessoa5', '1995-05-05');

insert into endereco (pessoa_id, logradouro, cep, numero, cidade, endereco_principal) values 
	(1, 'endereco1', '01000000', 1, 'cidade1', false),
	(2, 'endereco2', '02000000', 2, 'cidade2', false),
	(2, 'endereco3', '03000000', 3, 'cidade3', false),
	(3, 'endereco4', '04000000', 4, 'cidade4', false),
	(3, 'endereco5', '05000000', 5, 'cidade5', false),
	(3, 'endereco6', '06000000', 4, 'cidade6', false),
	(4, 'endereco7', '07000000', 5, 'cidade7', false),
	(4, 'endereco8', '08000000', 5, 'cidade8', false),
	(4, 'endereco9', '09000000', 5, 'cidade9', false),
	(4, 'endereco10', '10101010', 5, 'cidade10', false),
	(5, 'endereco11', '11111111', 5, 'cidade11', false),
	(5, 'endereco12', '12121212', 5, 'cidade12', false),
	(5, 'endereco13', '13000000', 5, 'cidade13', false),
	(5, 'endereco14', '14000000', 5, 'cidade14', false),
	(5, 'endereco15', '15000000', 5, 'cidade15', false);