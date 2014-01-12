--hasło: qwerty
INSERT INTO osoby (id, login, imie, nazwisko, haslo, pesel, email, id_jednostki, id_roli) VALUES (1000, 'admin', 'Administrator', 'Admiński', '{md5}2FeO34RYzgb7xbt2pYxcpA==', '57031214555', 'admin@isoman.org', 5000, 1);

INSERT INTO osoby (id, login, imie, nazwisko, haslo, pesel, email, id_jednostki, id_roli) VALUES (1001, 'ferdek', 'Ferdynand', 'Kiepski', '{md5}2FeO34RYzgb7xbt2pYxcpA==', '57031214555', 'zbychu@onet.eu', 5000, 3);
INSERT INTO osoby (id, login, imie, nazwisko, haslo, pesel, email, id_jednostki, id_roli) VALUES (1002, 'kiler', 'Jerzy', 'Kiler', '{md5}2FeO34RYzgb7xbt2pYxcpA==', '72101314555', 'kiler@gmail.com', 5000, 3);

INSERT INTO osoby (id, login, imie, nazwisko, haslo, pesel, email, id_jednostki, id_roli) VALUES (1010, 'prof', 'Jan', 'Mądry', '{md5}2FeO34RYzgb7xbt2pYxcpA==', '62101314555', 'prof.madry@gmail.com', 5000, 2);

INSERT INTO osoby (id, login, imie, nazwisko, haslo, pesel, email, id_jednostki, id_roli) VALUES (1102, 'stu001', 'Student', 'Studenciak', '{md5}2FeO34RYzgb7xbt2pYxcpA==', '91121923456', 'stu001@isoman.org', 5000, 4);
INSERT INTO osoby (id, login, imie, nazwisko, haslo, pesel, email, id_jednostki, id_roli) VALUES (1106, 'stu005', 'Student', 'Studenciak', '{md5}2FeO34RYzgb7xbt2pYxcpA==', '91121923456', 'stu005@isoman.org', 5000, 4);
INSERT INTO osoby (id, login, imie, nazwisko, haslo, pesel, email, id_jednostki, id_roli) VALUES (1103, 'stu002', 'Student', 'Studenciak', '{md5}2FeO34RYzgb7xbt2pYxcpA==', '91121923456', 'stu002@isoman.org', 5000, 4);
INSERT INTO osoby (id, login, imie, nazwisko, haslo, pesel, email, id_jednostki, id_roli) VALUES (1104, 'stu003', 'Student', 'Studenciak', '{md5}2FeO34RYzgb7xbt2pYxcpA==', '91121923456', 'stu003@isoman.org', 5000, 4);
INSERT INTO osoby (id, login, imie, nazwisko, haslo, pesel, email, id_jednostki, id_roli) VALUES (1105, 'stu004', 'Student', 'Studenciak', '{md5}2FeO34RYzgb7xbt2pYxcpA==', '91121923456', 'stu004@isoman.org', 5000, 4);
INSERT INTO osoby (id, login, imie, nazwisko, haslo, pesel, email, id_jednostki, id_roli) VALUES (1107, 'stu006', 'Student', 'Studenciak', '{md5}2FeO34RYzgb7xbt2pYxcpA==', '91121923456', 'stu006@isoman.org', 5000, 4);
INSERT INTO osoby (id, login, imie, nazwisko, haslo, pesel, email, id_jednostki, id_roli) VALUES (1108, 'stu007', 'Student', 'Studenciak', '{md5}2FeO34RYzgb7xbt2pYxcpA==', '91121923456', 'stu007@isoman.org', 5000, 4);
INSERT INTO osoby (id, login, imie, nazwisko, haslo, pesel, email, id_jednostki, id_roli) VALUES (1109, 'stu008', 'Student', 'Studenciak', '{md5}2FeO34RYzgb7xbt2pYxcpA==', '91121923456', 'stu008@isoman.org', 5000, 4);

INSERT INTO przedmioty (id, id_jednostki, id_wykladowcy, kod, nazwa, opis) VALUES (1000, 5000, 1001, 'KP', 'Kiepski przedmiot', 'Kiepski przedmiot');
INSERT INTO przedmioty (id, id_jednostki, id_wykladowcy, kod, nazwa, opis) VALUES (1001, 5000, 1001, 'IP', 'Inny przedmiot', 'Inny przedmiot');
INSERT INTO przedmioty (id, id_jednostki, id_wykladowcy, kod, nazwa, opis) VALUES (2000, 5000, 1010, 'PNP', 'Przedmiot nad Przedmiotami', 'Przedmiot nad Przedmiotami');

INSERT INTO osoby_na_przedmiotach (id_osoby, id_przedmiotu) VALUES (1102, 1000);
INSERT INTO osoby_na_przedmiotach (id_osoby, id_przedmiotu) VALUES (1103, 1000);
INSERT INTO osoby_na_przedmiotach (id_osoby, id_przedmiotu) VALUES (1104, 1000);

INSERT INTO osoby_na_przedmiotach (id_osoby, id_przedmiotu) VALUES (1102, 1001);
INSERT INTO osoby_na_przedmiotach (id_osoby, id_przedmiotu) VALUES (1104, 1001);
INSERT INTO osoby_na_przedmiotach (id_osoby, id_przedmiotu) VALUES (1106, 1001);

INSERT INTO osoby_na_przedmiotach (id_osoby, id_przedmiotu) VALUES (1102, 2000);
INSERT INTO osoby_na_przedmiotach (id_osoby, id_przedmiotu) VALUES (1001, 2000);

INSERT INTO foldery(id, fsid, czy_aktualny, do_usuniecia, nazwa) VALUES (1001, 2334778, FALSE, FALSE, 'archi1');
INSERT INTO foldery(id, fsid, czy_aktualny, do_usuniecia, nazwa) VALUES (1002, 2334824, FALSE, FALSE, 'archi2');
INSERT INTO foldery(id, fsid, czy_aktualny, do_usuniecia, nazwa) VALUES (1003, 2334825, FALSE, FALSE, 'archi3');

INSERT INTO archiwa(id, id_przedmiotu, id_folderu_glownego, nazwa, opis) VALUES (1001, 1000, 1001, 'archiwum1', 'Archiwum pierwsze');
INSERT INTO archiwa(id, id_przedmiotu, id_folderu_glownego, nazwa, opis) VALUES (1002, 1000, 1002, 'archiwum2', 'Archiwum drugie');
INSERT INTO archiwa(id, id_przedmiotu, id_folderu_glownego, nazwa, opis) VALUES (1003, 2000, 1003, 'archiwum3', 'Archiwum trzecie');