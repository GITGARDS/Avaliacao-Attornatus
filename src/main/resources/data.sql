create table pessoa(
	id			identity	not null,
	nome			varchar		not null unique,
	data_de_nascimento	timestamp 	with time zone not null,
	primary key		(id)
);

insert into pessoa (nome, data_de_nascimento) values
	('pessoa1', '1991-01-01'),
	('pessoa2', '1992-02-02'),
	('pessoa3', '1991-03-03'),
	('pessoa4', '1994-04-04'),
	('pessoa5', '1995-05-05');

create table endereco(
	id			identity	not null,
	pessoa_id		int		not null,
	logradouro		varchar		not null,
	cep			char(8),
	numero			numeric,
	cidade			varchar		not null,
	endereco_principal	boolean		default false,
	primary key		(id),

	constraint uk_endereco_pessoa_id_id unique (pessoa_id, id),

	constraint fk_endereco_pessoa_id 
		foreign key(pessoa_id) references pessoa(id)
);


insert into endereco (pessoa_id, logradouro, cep, numero, cidade) values 
	(1, 'endereco1', '01000000', 1, 'cidade1'),
	(2, 'endereco2', '02000000', 2, 'cidade2'),
	(2, 'endereco3', '03000000', 3, 'cidade3'),
	(3, 'endereco4', '04000000', 4, 'cidade4'),
	(3, 'endereco5', '05000000', 5, 'cidade5'),
	(3, 'endereco6', '06000000', 4, 'cidade6'),
	(4, 'endereco7', '07000000', 5, 'cidade7'),
	(4, 'endereco8', '08000000', 5, 'cidade8'),
	(4, 'endereco9', '09000000', 5, 'cidade9'),
	(4, 'endereco10', '10101010', 5, 'cidade10'),
	(5, 'endereco11', '11111111', 5, 'cidade11'),
	(5, 'endereco12', '12121212', 5, 'cidade12'),
	(5, 'endereco13', '13000000', 5, 'cidade13'),
	(5, 'endereco14', '14000000', 5, 'cidade14'),
	(5, 'endereco15', '15000000', 5, 'cidade15');