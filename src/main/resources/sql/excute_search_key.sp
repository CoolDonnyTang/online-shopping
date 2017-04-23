DELIMITER $$
CREATE PROCEDURE excute_search_key(IN excute_all INT, success_flag INT)
BEGIN
	DECLARE counter INT;
	DECLARE comm_entity_id INT;
	DECLARE key_string VARCHAR(255);
	DECLARE EXIT HANDLER FOR SQLEXCEPTION SET success_flag=0;
	DROP TABLE IF EXISTS search_key;
	CREATE TEMPORARY TABLE search_key(id INT AUTO_INCREMENT  PRIMARY KEY, commEntityId INT, searchKey VARCHAR(255));
	IF excute_all IS NULL
		THEN INSERT INTO search_key(commEntityId, searchKey) SELECT ce.id, CONCAT(m.kindName, s.kindName, c.brand, c.titleName, ce.propty1, ce.propty2) 
			FROM kind m 
			JOIN kind s ON m.id = s.belong 
			JOIN commodity c ON c.belongKindId = s.id 
			JOIN commodity_entity ce ON c.id = ce.belongCommId
			WHERE ce.searchKey IS NULL;
		ELSE INSERT INTO search_key(commEntityId, searchKey) SELECT ce.id, CONCAT(m.kindName, s.kindName, c.brand, c.titleName, ce.propty1, ce.propty2) 
			FROM kind m 
			JOIN kind s ON m.id = s.belong 
			JOIN commodity c ON c.belongKindId = s.id 
			JOIN commodity_entity ce ON c.id = ce.belongCommId;
	END IF;
	SELECT MAX(id) INTO counter FROM search_key;
	WHILE  counter > 0 DO
		SELECT commEntityId, searchKey INTO comm_entity_id, key_string FROM search_key WHERE id = counter;
		UPDATE commodity_entity SET searchKey = key_string WHERE id = comm_entity_id;
		SET counter = counter - 1;
	END WHILE;
	SET success_flag = 1;
END
$$
DELIMITER ;  