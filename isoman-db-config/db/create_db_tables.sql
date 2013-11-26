---
--- Skrypty tworzące tabele dla aplikacji IsoMAN
--- Autor: Michał Toporowski
---


---
---  Tabela typów jednostek
---

CREATE TABLE typy_jednostek
(
  id         BIGINT                NOT NULL,
  nazwa      CHARACTER VARYING(48) NOT NULL,
  id_rodzica BIGINT,
  CONSTRAINT tj_pk PRIMARY KEY (id),
  CONSTRAINT tj_tj_fk FOREIGN KEY (id_rodzica) REFERENCES typy_jednostek (id)
)
WITH (
OIDS = FALSE
);
ALTER TABLE typy_jednostek OWNER TO @db.user@;


---
---  Tabela przedmiotów
---

CREATE TABLE jednostki_organizacyjne
(
  id         BIGINT                NOT NULL,
  id_typu    BIGINT                NOT NULL,
  nazwa      CHARACTER VARYING(48) NOT NULL,
  opis       CHARACTER VARYING(200),
  id_rodzica BIGINT,
  CONSTRAINT jo_pk PRIMARY KEY (id),
  CONSTRAINT jo_tj_fk FOREIGN KEY (id_typu) REFERENCES typy_jednostek (id),
  CONSTRAINT jo_jo_fk FOREIGN KEY (id_rodzica) REFERENCES jednostki_organizacyjne (id)
)
WITH (
OIDS = FALSE
);
ALTER TABLE jednostki_organizacyjne OWNER TO @db.user@;


---
---  Tabela osób
---
CREATE SEQUENCE osoby_seq;
ALTER SEQUENCE osoby_seq
OWNER TO @db.user@;

CREATE TABLE osoby
(
  id           BIGINT                 NOT NULL,
  login        CHARACTER VARYING(30)  NOT NULL,
  imie         CHARACTER VARYING(48)  NOT NULL,
  nazwisko     CHARACTER VARYING(120) NOT NULL,
  pesel        CHARACTER VARYING(11)  NOT NULL,
  email        CHARACTER VARYING(30)  NOT NULL,
  haslo        CHARACTER VARYING(200) NOT NULL,
  id_roli      BIGINT                 NOT NULL,
  id_jednostki BIGINT                 ,
--   last_login TIMESTAMP WITHOUT TIME ZONE,
  CONSTRAINT osoby_pk PRIMARY KEY (id),
  CONSTRAINT osoby_login_uk UNIQUE (login),
  CONSTRAINT osoby_email_uk UNIQUE (email),
  CONSTRAINT osoby_jedn_fk FOREIGN KEY (id_jednostki) REFERENCES jednostki_organizacyjne (id)
)
WITH (
OIDS = FALSE
);
ALTER TABLE osoby OWNER TO @db.user@;

COMMENT ON TABLE osoby IS 'Tabela reprezentująca osoby';
COMMENT ON COLUMN osoby.id IS 'Numer osoby';
COMMENT ON COLUMN osoby.imie IS 'Imię osoby';
COMMENT ON COLUMN osoby.pesel IS 'Nr PESEL';
COMMENT ON COLUMN osoby.email IS 'Adres e-mail';


---
---  Tabela przedmiotów
---


CREATE TABLE przedmioty
(
  id            BIGINT                NOT NULL,
  id_jednostki  BIGINT                NOT NULL,
  id_wykladowcy BIGINT                NOT NULL,
  kod           CHARACTER VARYING(5)  NOT NULL,
  nazwa         CHARACTER VARYING(48) NOT NULL,
  opis          CHARACTER VARYING(200),
  CONSTRAINT prz_pk PRIMARY KEY (id),
  CONSTRAINT prz_jo_fk FOREIGN KEY (id_jednostki) REFERENCES jednostki_organizacyjne (id),
  CONSTRAINT prz_wykl_fk FOREIGN KEY (id_wykladowcy) REFERENCES osoby (id)

)
WITH (
OIDS = FALSE
);
ALTER TABLE przedmioty OWNER TO @db.user@;

---
---  Tabela osób zapisanych na przedmioty
---

CREATE TABLE osoby_na_przedmiotach
(
  id_osoby      BIGINT NOT NULL,
  id_przedmiotu BIGINT NOT NULL,
  CONSTRAINT onp_pk PRIMARY KEY (id_osoby, id_przedmiotu),
  CONSTRAINT onp_os_fk FOREIGN KEY (id_osoby) REFERENCES osoby (id),
  CONSTRAINT onp_prz_fk FOREIGN KEY (id_przedmiotu) REFERENCES przedmioty (id)

)
WITH (
OIDS = FALSE
);
ALTER TABLE osoby_na_przedmiotach OWNER TO @db.user@;


---
---  Tabela archiwów
---

CREATE TABLE archiwa
(
  id                  BIGINT                NOT NULL,
  nazwa               CHARACTER VARYING(48) NOT NULL,
  opis                CHARACTER VARYING(200),
  id_przedmiotu       BIGINT                NOT NULL,
  id_folderu_glownego BIGINT,
  CONSTRAINT arch_pk PRIMARY KEY (id),
  CONSTRAINT ar_prz_fk FOREIGN KEY (id_przedmiotu) REFERENCES przedmioty (id)
)
WITH (
OIDS = FALSE
);
ALTER TABLE archiwa OWNER TO @db.user@;

---
---  Tabela wersji archiwów
---

CREATE TABLE wersje_archiwow
(
  nr              BIGINT                      NOT NULL,
  id_archiwum     BIGINT                      NOT NULL,
  data_utworzenia TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  czy_pokazywana  BOOLEAN DEFAULT FALSE       NOT NULL,
  sciezka         CHARACTER VARYING(160)      NOT NULL,
  opis            CHARACTER VARYING(200),
  CONSTRAINT wer_arch_pk PRIMARY KEY (nr, id_archiwum),
  CONSTRAINT wer_arch_fk FOREIGN KEY (id_archiwum) REFERENCES archiwa (id)
)
WITH (
OIDS = FALSE
);
ALTER TABLE wersje_archiwow OWNER TO @db.user@;

---
---  Tabela folderów źródłowych
---

CREATE TABLE foldery
(
  id         BIGINT                NOT NULL,
  id_rodzica BIGINT,
  nazwa      CHARACTER VARYING(80) NOT NULL,
  CONSTRAINT fold_pk PRIMARY KEY (id),
  CONSTRAINT fold_fold_pk FOREIGN KEY (id_rodzica) REFERENCES foldery (id)
)
WITH (
OIDS = FALSE
);
ALTER TABLE foldery OWNER TO @db.user@;

ALTER TABLE archiwa
ADD CONSTRAINT ar_fg_fk FOREIGN KEY (id_folderu_glownego) REFERENCES foldery (id);

---
---  Tabela plików źródłowych
---

CREATE TABLE pliki
(
  id           BIGINT                NOT NULL,
  id_folderu   BIGINT                NOT NULL,
  czy_aktualny BOOLEAN               NOT NULL,
  nazwa        CHARACTER VARYING(80) NOT NULL,
  CONSTRAINT pl_pk PRIMARY KEY (id),
  CONSTRAINT pl_fold_pk FOREIGN KEY (id_folderu) REFERENCES foldery (id)
)
WITH (
OIDS = FALSE
);
ALTER TABLE pliki OWNER TO @db.user@;


---
---  Tabela typów ról
---


CREATE TABLE role
(
  id    BIGINT                NOT NULL,
  kod   CHARACTER VARYING(16) NOT NULL,
  nazwa CHARACTER VARYING(48) NOT NULL,
  opis  CHARACTER VARYING(200),
  CONSTRAINT role_pk PRIMARY KEY (id)
)
WITH (
OIDS = FALSE
);
ALTER TABLE role OWNER TO @db.user@;

ALTER TABLE osoby
    ADD CONSTRAINT os_ro_fk FOREIGN KEY (id_roli) REFERENCES role (id);

---
---  Tabela przypisań ról osobom
---


-- CREATE TABLE role_osob
-- (
--   id_osoby BIGINT,
--   id_roli  BIGINT,
--   CONSTRAINT ro_pk PRIMARY KEY (id_osoby, id_roli),
--   CONSTRAINT ro_os_fk FOREIGN KEY (id_osoby) REFERENCES osoby (id) ON DELETE CASCADE,
--   CONSTRAINT ro_role_fk FOREIGN KEY (id_roli) REFERENCES role (id) ON DELETE CASCADE
-- )
-- WITH (
-- OIDS = FALSE
-- );
-- ALTER TABLE role_osob OWNER TO @db.user@;
