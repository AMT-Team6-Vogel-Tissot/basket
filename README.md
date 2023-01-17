# Basket

SELECT player_name, player_surname, trophy_name FROM PLAYERS_TROPHIES INNER JOIN PLAYERS ON players.player_id = players_trophies.player_id  INNER JOIN TROPHIES ON trophies.trophy_id = players_trophies.trophy_id
  
    
