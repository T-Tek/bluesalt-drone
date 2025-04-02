INSERT INTO drone (serial_number, model, weight_limit, battery_capacity, state)
SELECT 'DRONE006', 'MIDDLEWEIGHT', 350, 90, 'IDLE'
    WHERE NOT EXISTS (SELECT 1 FROM drone WHERE serial_number = 'DRONE006');

INSERT INTO drone (serial_number, model, weight_limit, battery_capacity, state)
SELECT 'DRONE007', 'CRUISERWEIGHT', 450, 75, 'LOADED'
    WHERE NOT EXISTS (SELECT 1 FROM drone WHERE serial_number = 'DRONE007');

INSERT INTO drone (serial_number, model, weight_limit, battery_capacity, state)
SELECT 'DRONE008', 'HEAVYWEIGHT', 500, 50, 'DELIVERING'
    WHERE NOT EXISTS (SELECT 1 FROM drone WHERE serial_number = 'DRONE008');

INSERT INTO drone (serial_number, model, weight_limit, battery_capacity, state)
SELECT 'DRONE009', 'LIGHTWEIGHT', 250, 40, 'RETURNING'
    WHERE NOT EXISTS (SELECT 1 FROM drone WHERE serial_number = 'DRONE009');

INSERT INTO drone (serial_number, model, weight_limit, battery_capacity, state)
SELECT 'DRONE010', 'MIDDLEWEIGHT', 300, 85, 'IDLE'
    WHERE NOT EXISTS (SELECT 1 FROM drone WHERE serial_number = 'DRONE010');
