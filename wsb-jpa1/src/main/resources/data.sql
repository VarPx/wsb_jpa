-- 📌 Wstaw adresy (ID generuje się automatycznie)
INSERT INTO address (address_line1, address_line2, city, postal_code)
VALUES
('ul. Klonowa 5', 'Apt 1', 'Warszawa', '01-234'),  -- ID = 1
('ul. Dębowa 12', 'Apt 2', 'Gdańsk', '80-001'),    -- ID = 2
('ul. Sosnowa 7', 'Apt 3', 'Kraków', '30-400');    -- ID = 3 (dla nowego pacjenta)

-- 📌 Wstaw pacjentów (każdy dostaje swój adres)
INSERT INTO patient (first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id)
VALUES
('Adam', 'Malinowski', '123123123', 'adam.malinowski@example.com', 'PAT001', '1990-05-15', 1),
('Ewa', 'Kwiatkowska', '456456456', 'ewa.kwiatkowska@example.com', 'PAT002', '1985-08-22', 2),
('Marek', 'Lewandowski', '789789789', 'marek.lewandowski@example.com', 'PAT003', '2000-11-10', 3); -- Nowy pacjent z nowym adresem
