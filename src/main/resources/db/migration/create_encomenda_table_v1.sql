create table if not exists encomenda (
id integer not null auto_increment,
encomendaName varchar(50) not null,
descricaoEncomenda varchar(100) not null,
quantidade varchar(50) not null,
primary key(id),
foreign key(cliente_id) references Cliente(id));