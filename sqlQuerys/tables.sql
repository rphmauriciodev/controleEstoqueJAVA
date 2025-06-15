CREATE TABLE Categorias (
	id 		SERIAL PRIMARY KEY,
	nome	VARCHAR(50)
);

CREATE TABLE Clientes (
	id 			SERIAL PRIMARY KEY,
	nome		VARCHAR(50)
);

CREATE TABLE Funcionarios (
	id 		SERIAL PRIMARY KEY,
	nome 	VARCHAR(50)
);

CREATE TABLE Produtos (
	id 				SERIAL PRIMARY KEY,
	nome			VARCHAR(50),
	precoUnit		NUMERIC(10, 2),
	categoriaId 	INT,
	quantidade		INT,
	isDesativado	BOOLEAN,
	FOREIGN KEY (categoriaId) REFERENCES Categorias(id)
);

CREATE TABLE Alugados (
	id 				SERIAL PRIMARY KEY,
	produtoId 		INT,
	quantidade		INT,
	dataAluguel		DATE,
	dataDevolucao	DATE,
	clienteId		INT,
	funcionarioId	INT,
	isDevolvido 	BOOLEAN,
	FOREIGN KEY (produtoId) 	REFERENCES Produtos(id),
	FOREIGN KEY (clienteId) 	REFERENCES Clientes(id),
	FOREIGN KEY (funcionarioId) REFERENCES Funcionarios(id)
);
