INSERT INTO typy_jednostek (id, nazwa, id_rodzica) VALUES (1000, 'uczelnia', null);
INSERT INTO typy_jednostek (id, nazwa, id_rodzica) VALUES (2000, 'wydział', null);

INSERT INTO jednostki_organizacyjne (id, id_typu, nazwa, opis, id_rodzica) VALUES (4000, 1000, 'PW', 'Politechnika Warszawska', null);
INSERT INTO jednostki_organizacyjne (id, id_typu, nazwa, opis, id_rodzica) VALUES (5000, 2000, 'EiTI', 'Wydział Elektroniki i Technik Informacyjnych', 4000);
INSERT INTO jednostki_organizacyjne (id, id_typu, nazwa, opis, id_rodzica) VALUES (5001, 2000, 'MiNI', 'Wydział Matematyki i Nauk Informacyjnych', 4000);
INSERT INTO jednostki_organizacyjne (id, id_typu, nazwa, opis, id_rodzica) VALUES (5002, 2000, 'IChiP', 'Wydział Inżynierii Chemicznej i Procesowej', 4000);
INSERT INTO jednostki_organizacyjne (id, id_typu, nazwa, opis, id_rodzica) VALUES (5003, 2000, 'IŚ', 'Wydział Inżynierii Środowiska', 4000);



