-- View: public.VW_Produtos

-- DROP VIEW public."VW_Produtos";

CREATE OR REPLACE VIEW public."VW_Produtos"
 AS
 SELECT p.id,
    p.nome,
    p.precounit,
    p.categoriaid,
    p.quantidade,
    p.isdesativado,
        CASE
            WHEN a.produtoid IS NOT NULL AND a.isdevolvido = false THEN true
            ELSE false
        END AS isalugado
   FROM alugados a
     RIGHT JOIN produtos p ON a.produtoid = p.id;

ALTER TABLE public."VW_Produtos"
    OWNER TO postgres;

