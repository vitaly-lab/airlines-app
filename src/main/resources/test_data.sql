INSERT INTO crew_members VALUES (1, 'Jonh', 'Smitt', 'CAPTAIN_OF_THE_SHIP', '1975-07-12', 'USA');
INSERT INTO crew_members VALUES (2, 'Dik', 'Cheny', 'FIRST_PILOT', '1973-09-12', 'UK');
INSERT INTO crew_members VALUES (3, 'Dominic', 'Red', 'BOARD_CONDUCTOR', '1975-02-12', 'AUSTRALIA');
INSERT INTO crew_members VALUES (4, 'Sandra', 'Cooper', 'STEWARDESS', '1989-09-05', 'New_ZEALAND');
INSERT INTO crew_members VALUES (5, 'Didrih', 'Kolc', 'CAPTAIN_OF_THE_SHIP', '1978-03-05', 'GERMANY');
INSERT INTO crew_members VALUES (6, 'Peter', 'Stock', 'FIRST_PILOT', '1979-07-30', 'USA');
INSERT INTO crew_members VALUES (7, 'Stiv', 'Jocker', 'BOARD_CONDUCTOR', '1987-10-17', 'UK');
INSERT INTO crew_members VALUES (8, 'Anna', 'Bush', 'STEWARDESS', '2000-12-09', 'AUSTRIA');
INSERT INTO crew_members VALUES (9, 'Peter', 'Stock', 'FIRST_PILOT', '1979-07-30', 'USA');
INSERT INTO crew_members VALUES (10, 'Stiv', 'Jocker', 'BOARD_CONDUCTOR', '1987-10-17', 'UK');

INSERT INTO crews VALUES (1, 'Command_1');
INSERT INTO crews VALUES (2, 'Command_2');
INSERT INTO crews VALUES (3, 'Command_3');

INSERT INTO crews_crew_members VALUES (1, 1);
INSERT INTO crews_crew_members VALUES (3, 1);
INSERT INTO crews_crew_members VALUES (1, 2);
INSERT INTO crews_crew_members VALUES (1, 3);
INSERT INTO crews_crew_members VALUES (1, 4);
INSERT INTO crews_crew_members VALUES (3, 4);
INSERT INTO crews_crew_members VALUES (2, 5);
INSERT INTO crews_crew_members VALUES (2, 6);
INSERT INTO crews_crew_members VALUES (2, 7);
INSERT INTO crews_crew_members VALUES (2, 8);
INSERT INTO crews_crew_members VALUES (3, 9);
INSERT INTO crews_crew_members VALUES (3, 10);

INSERT INTO airplanes VALUES (1, 'YH-42', 'B_737_100_BOEING', '1990-02-12', '130', '2960', 2);
INSERT INTO airplanes VALUES (2, 'AB-43', 'A300B2_AIRBUS_SAS', '1991-03-01', '345', '5300', 1);
INSERT INTO airplanes VALUES (3, 'XTR-838', 'Q400_BOMBARDIER', '1989-08-11', '78', '2500', 3);
INSERT INTO airplanes VALUES (4, 'YH-43', 'B_737_100_BOEING', '1991-04-12', '130', '2960', 2);
INSERT INTO airplanes VALUES (5, 'XTR-840', 'Q400_BOMBARDIER', '1989-08-11', '78', '2500', 1);