## Projeto OCDP Back-end 

Repositório destinado à gerência de configuração do Back-end Mobile do Projeto OCDP (Oral Cancer Dectect Project).


## Organização do projeto (diretórios)

- `Código` Ambiente destinado para controle de mudanças, versionamento e construção do back-end.

## Flyway 
### Executando local
Dentro do diretório raiz do projeto execute: 
- Executar postgres: `docker-compose up`
- Depois de subir docker do postgres execute: `./mvnw flyway:baseline -Dflyway.user=postgres -Dflyway.password=123456 -Dflyway.url=jdbc:postgresql://localhost:5432/ocd-db`

## Equipe de desenvolvimento
- Leandro Pedrosa
- Daurio Filho
- Marcelo Anderson
- Igor Guimarães