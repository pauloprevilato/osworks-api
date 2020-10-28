create table cliente(
	 id bigint NOT NULL DEFAULT nextval(1),
	 nome character varying (100) not null,
	 email character varying(255) not null,
	 telefone character varying(20) not null,
	 
	 CONSTRAINT cliente_pkey PRIMARY KEY (id)
 )