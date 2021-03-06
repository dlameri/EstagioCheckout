UPDATE CLIENTE SET CD_ENDERECO_PRINCIPAL = NULL;

DELETE FROM CARRINHO_COMPRAS;
DELETE FROM CARRINHO_COMPRAS_LINHA;
DELETE FROM ENDERECO;
DELETE FROM CLIENTE;
DELETE FROM ORDEM_DE_COMPRA;
DELETE FROM IMAGENS;
DELETE FROM ITEM;
DELETE FROM PRODUTO;
DELETE FROM SUBCATEGORIA;
DELETE FROM CATEGORIA;
DELETE FROM DIMENSOES;

ALTER TABLE IMAGENS AUTO_INCREMENT = 1;
ALTER TABLE ITEM AUTO_INCREMENT = 1;
ALTER TABLE PRODUTO AUTO_INCREMENT = 1;
ALTER TABLE SUBCATEGORIA AUTO_INCREMENT = 1;
ALTER TABLE CATEGORIA AUTO_INCREMENT = 1;
ALTER TABLE DIMENSOES AUTO_INCREMENT = 1;

INSERT INTO DIMENSOES VALUES(1, 10, 30, 10);

INSERT INTO CATEGORIA VALUES(1, 'Categoria 1');
INSERT INTO CATEGORIA VALUES(2, 'Categoria 2');
INSERT INTO CATEGORIA VALUES(3, 'Categoria 3');

INSERT INTO SUBCATEGORIA VALUES(1,'Subcategoria 1', 1);
INSERT INTO SUBCATEGORIA VALUES(2, 'Subcategoria 2', 1);
INSERT INTO SUBCATEGORIA VALUES(3, 'Subcategoria 3', 1);

INSERT INTO PRODUTO VALUES(1, 1, 'Nome da Marca', 'Descrição longa', 'Nome do modelo', 'Nome do produto',  'Descrição curta', 2, 5, 1,1,1 );
INSERT INTO PRODUTO VALUES(2, 1, 'Nome da Marca', 'Descrição longa', 'Nome do modelo', 'Nome do produto',  'Descrição curta', 2, 5, 1,1,1 );
INSERT INTO PRODUTO VALUES(3, 1, 'Nome da Marca', 'Descrição longa', 'Nome do modelo', 'Nome do produto', 'Descrição curta', 2, 5, 2,1,1 );

INSERT INTO ITEM VALUES(1, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 10, 1);
INSERT INTO ITEM VALUES(2, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 15, 1);
INSERT INTO ITEM VALUES(3, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 200, 2);
INSERT INTO ITEM VALUES(4, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 150, 2);
INSERT INTO ITEM VALUES(5, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 30,  3);
INSERT INTO ITEM VALUES(6, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 20,  3);
INSERT INTO ITEM VALUES(7, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 98,  1);
INSERT INTO ITEM VALUES(8, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 99,  1);

INSERT INTO IMAGENS VALUES(1, '', '', 1, '', '', '', 'http://isuba.s8.com.br/produtos/01/00/item/117218/9/117218927G1.jpg', 1);
INSERT INTO IMAGENS VALUES(2, '', '', 1, '', '', '', 'http://isuba.s8.com.br/produtos/01/00/item/117218/9/117218927G1.jpg',  2);
INSERT INTO IMAGENS VALUES(3, '', '', 1, '', '', '', 'http://isuba.s8.com.br/produtos/01/00/item/117218/9/117218927G1.jpg',  3);
INSERT INTO IMAGENS VALUES(4, '', '', 1, '', '', '', 'http://isuba.s8.com.br/produtos/01/00/item/117218/9/117218927G1.jpg',  4);
INSERT INTO IMAGENS VALUES(5, '', '', 1, '', '', '', 'http://isuba.s8.com.br/produtos/01/00/item/117218/9/117218927G1.jpg',  5);
INSERT INTO IMAGENS VALUES(6, '', '', 1, '', '', '', 'http://isuba.s8.com.br/produtos/01/00/item/117218/9/117218927G1.jpg',  6);
INSERT INTO IMAGENS VALUES(7, '', '', 1, '', '', '', 'http://isuba.s8.com.br/produtos/01/00/item/117218/9/117218927G1.jpg',  7);
INSERT INTO IMAGENS VALUES(8, '', '', 1, '', '', '', 'http://isuba.s8.com.br/produtos/01/00/item/117218/9/117218927G1.jpg',  8);

INSERT INTO CLIENTE VALUES(1,'12345678900','test@test.com','TestUser','password','12345678','testUser','testLogin',null);
INSERT INTO ENDERECO VALUES(1,'Nova iguaçu','casa','BR','42','RJ','Rua das cabritas','22000123',1);
UPDATE CLIENTE SET CD_ENDERECO_PRINCIPAL = 1; 


COMMIT;