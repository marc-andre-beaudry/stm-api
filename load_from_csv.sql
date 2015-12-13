LOAD DATA INFILE '/var/tmp/routes.txt' 
INTO TABLE mtlmasstransit.route
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(id, agency, short_name, long_name, type, url, color, text_color);

LOAD DATA INFILE '/var/tmp/stops.txt' 
INTO TABLE mtlmasstransit.stop
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(id, code, name, latitude, longitude, url, wheelchair_boarding);

LOAD DATA INFILE '/var/tmp/trips.txt' 
INTO TABLE mtlmasstransit.trip
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(route_id, service_id, id, head_sign, direction_id, shape_id, wheelchair_accessible, note_fr, note_en);

LOAD DATA INFILE '/var/tmp/stop_times.txt' 
INTO TABLE mtlmasstransit.stop_time
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(trip_id, arrival_time, departure_time, stop_id, stop_sequence);