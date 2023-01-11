create table endereco(
	id			identity	not null,
	pessoa_id		int		not null,
	logradouro		varchar		not null,
	cep			char(8),
	numero			numeric,
	cidade			varchar		not null,
	primary key		(id),
	unique 			(pessoa_id, id)

);

create table pessoa(
	id			identity	not null,
	nome			varchar		not null unique,
	data_de_nascimento	timestamp 	with time zone not null,
	endereco_id		int		not null,
	primary key		(id)
);

alter table endereco add 
	constraint fk_endereco_pessoa_id 
	foreign key(pessoa_id) references pessoa(id);

alter table pessoa add 
	constraint fk_pessoa_endereco_id 
	foreign key(endereco_id) references endereco(id);
