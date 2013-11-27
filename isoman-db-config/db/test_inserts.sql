--hasło: qwerty
INSERT INTO osoby (id, login, imie, nazwisko, haslo, pesel, email, id_jednostki, id_roli) VALUES (1000, 'admin', 'Administrator', 'Admiński', '{md5}2FeO34RYzgb7xbt2pYxcpA==', '57031214555', 'admin@isoman.org', 5000, 1);

INSERT INTO osoby (id, login, imie, nazwisko, haslo, pesel, email, id_jednostki, id_roli) VALUES (1001, 'ferdek', 'Ferdynand', 'Kiepski', '{md5}2FeO34RYzgb7xbt2pYxcpA==', '57031214555', 'zbychu@onet.eu', 5000, 3);
INSERT INTO osoby (id, login, imie, nazwisko, haslo, pesel, email, id_jednostki, id_roli) VALUES (1002, 'kiler', 'Jerzy', 'Kiler', '{md5}2FeO34RYzgb7xbt2pYxcpA==', '72101314555', 'kiler@gmail.com', 5000, 3);

INSERT INTO osoby (id, login, imie, nazwisko, haslo, pesel, email, id_jednostki, id_roli) VALUES (1102, 'stu001', 'Student', 'Studenciak', '{md5}2FeO34RYzgb7xbt2pYxcpA==', '91121923456', 'stu001@isoman.org', 5000, 4);
INSERT INTO osoby (id, login, imie, nazwisko, haslo, pesel, email, id_jednostki, id_roli) VALUES (1106, 'stu005', 'Student', 'Studenciak', '{md5}2FeO34RYzgb7xbt2pYxcpA==', '91121923456', 'stu005@isoman.org', 5000, 4);
INSERT INTO osoby (id, login, imie, nazwisko, haslo, pesel, email, id_jednostki, id_roli) VALUES (1103, 'stu002', 'Student', 'Studenciak', '{md5}2FeO34RYzgb7xbt2pYxcpA==', '91121923456', 'stu002@isoman.org', 5000, 4);
INSERT INTO osoby (id, login, imie, nazwisko, haslo, pesel, email, id_jednostki, id_roli) VALUES (1104, 'stu003', 'Student', 'Studenciak', '{md5}2FeO34RYzgb7xbt2pYxcpA==', '91121923456', 'stu003@isoman.org', 5000, 4);
INSERT INTO osoby (id, login, imie, nazwisko, haslo, pesel, email, id_jednostki, id_roli) VALUES (1105, 'stu004', 'Student', 'Studenciak', '{md5}2FeO34RYzgb7xbt2pYxcpA==', '91121923456', 'stu004@isoman.org', 5000, 4);
INSERT INTO osoby (id, login, imie, nazwisko, haslo, pesel, email, id_jednostki, id_roli) VALUES (1107, 'stu006', 'Student', 'Studenciak', '{md5}2FeO34RYzgb7xbt2pYxcpA==', '91121923456', 'stu006@isoman.org', 5000, 4);
INSERT INTO osoby (id, login, imie, nazwisko, haslo, pesel, email, id_jednostki, id_roli) VALUES (1108, 'stu007', 'Student', 'Studenciak', '{md5}2FeO34RYzgb7xbt2pYxcpA==', '91121923456', 'stu007@isoman.org', 5000, 4);
INSERT INTO osoby (id, login, imie, nazwisko, haslo, pesel, email, id_jednostki, id_roli) VALUES (1109, 'stu008', 'Student', 'Studenciak', '{md5}2FeO34RYzgb7xbt2pYxcpA==', '91121923456', 'stu008@isoman.org', 5000, 4);

INSERT INTO przedmioty (id, id_jednostki, id_wykladowcy, kod, nazwa, opis) VALUES (1000, 5000, 1001, 'KP', 'KP', 'Kiepski przedmiot');

INSERT INTO osoby_na_przedmiotach (id_osoby, id_przedmiotu) VALUES (1102, 1000);
INSERT INTO osoby_na_przedmiotach (id_osoby, id_przedmiotu) VALUES (1103, 1000);
INSERT INTO osoby_na_przedmiotach (id_osoby, id_przedmiotu) VALUES (1107, 1000);