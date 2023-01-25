# Basket

SELECT player_name, player_surname, trophy_name FROM PLAYERS_TROPHIES INNER JOIN PLAYERS ON players.player_id = players_trophies.player_id  INNER JOIN TROPHIES ON trophies.trophy_id = players_trophies.trophy_id


https://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/#repository-query-keywords

https://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/#jpa.query-methods

jpa crée des query automatiquement en fonction du nom qu'on donne à la méthode dans le repository, il en crée aussi de manière auto (findAll)