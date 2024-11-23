-- Criação da tabela Fornecedor
CREATE TABLE IF NOT EXISTS Fornecedor (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    telefone TEXT NOT NULL,
    endereco TEXT NOT NULL
);

-- Criação da tabela Produto
CREATE TABLE IF NOT EXISTS Produto (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    id_fornecedor INTEGER NOT NULL,
    nome TEXT NOT NULL,
    preco REAL NOT NULL,
    validade DATE NOT NULL,
    FOREIGN KEY (id_fornecedor) REFERENCES Fornecedor(id)
);

-- Criação da tabela Identificacao
CREATE TABLE IF NOT EXISTS Identificacao (
    id INTEGER PRIMARY KEY,
    nome TEXT NOT NULL,
    descricao TEXT NOT NULL,
    FOREIGN KEY (id) REFERENCES Produto(id)
);

-- Criação da tabela Filial
CREATE TABLE IF NOT EXISTS Filial (
    cnpj TEXT PRIMARY KEY,
    nome TEXT NOT NULL,
    telefone TEXT NOT NULL,
    endereco TEXT NOT NULL
);

-- Criação da tabela Estoque
CREATE TABLE IF NOT EXISTS Estoque (
    id_produto INTEGER NOT NULL,
    cnpj_filial TEXT NOT NULL,
    quantidade INTEGER NOT NULL,
    PRIMARY KEY (id_produto, cnpj_filial),
    FOREIGN KEY (id_produto) REFERENCES Produto(id),
    FOREIGN KEY (cnpj_filial) REFERENCES Filial(cnpj)
);
