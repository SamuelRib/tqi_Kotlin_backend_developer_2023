-- Cria a tabela
CREATE TABLE forma_pagamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pagamento VARCHAR(50) NOT NULL
);

-- Adiciona as linhas à tabela
INSERT INTO forma_pagamento (pagamento) VALUES
    ('Cartão de Credito'),
    ('Debito'),
    ('Dinheiro Fisico'),
    ('Pix');
