INSERT INTO airlines (airline_id, name, budget, location_name, lat, lon) VALUES
  ('1', 'The Beatles', 12353, 'test1', 51.5007, 0.1246 ),
  ('2', 'THE WEEKEND', 12353, 'test2', 40.6892, 74.0445),
  ('3', 'COLDPLAY', 12353, 'test3', 51.5007, 0.1246),
  ('4', 'Michael Jackson', 12353, 'test4', 51.5007, 0.1246),
  ('5', 'THE ROLLING STONES', 12353, 'test5', 51.5007, 0.1246);

INSERT INTO aircrafts (aircraft_id, price, distance, airline_id, created_at) VALUES
 ('1', 3435, 1000, 1, '2020-01-08'),
 ('2', 3435, 1000, 1, '2020-01-08'),
 ('3', 3435, 1000, 1, '2020-01-08'),
 ('4', 3435, 1000, 2, '2020-01-08'),
 ('5', 3435, 1000, 2, '2020-01-08'),
 ('6', 3435, 1000, 3, '2020-01-08'),
 ('7', 3435, 1000, 3, '2020-01-08'),
 ('8', 3435, 1000, 4, '2020-01-08');

INSERT INTO rel_airline_aircrafts (airline_id, aircraft_id) VALUES
    (1, 1 ),
    (1, 2 ),
    (1, 3 ),

    (2, 4 ),
    (2, 5 ),

    (3, 6 ),
    (3, 7 ),

    (4, 8 );
