-- 📌 Wstaw adresy
INSERT INTO address (address_line1, address_line2, city, postal_code)
VALUES
('ul. Klonowa 5', 'Apt 1', 'Warszawa', '01-234'),
('ul. Dębowa 12', 'Apt 2', 'Gdańsk', '80-001'),
('ul. Sosnowa 7', 'Apt 3', 'Kraków', '30-400');

-- 📌 Wstaw lekarzy (doctor)
INSERT INTO doctor (first_name, last_name, telephone_number, email, doctor_number, specialization)
VALUES
('Anna', 'Nowak', '111111111', 'anna.nowak@example.com', 'DOC001', 'GP'),
('Jan', 'Kowalski', '222222222', 'jan.kowalski@example.com', 'DOC002', 'SURGEON');

-- 📌 Wstaw pacjentów
INSERT INTO patient (first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id)
VALUES
('Adam', 'Malinowski', '123123123', 'adam.malinowski@example.com', 'PAT001', '1990-05-15', 1),
('Ewa', 'Kwiatkowska', '456456456', 'ewa.kwiatkowska@example.com', 'PAT002', '1985-08-22', 2),
('Marek', 'Lewandowski', '789789789', 'marek.lewandowski@example.com', 'PAT003', '2000-11-10', 3);

-- 📌 Wstaw wizyty
INSERT INTO visit (description, time, patient_id, doctor_id)
VALUES
('Kontrola ogólna', '2024-01-10T10:00:00', 1, 1),
('Wizyta kontrolna', '2024-01-15T11:30:00', 1, 2),
('RTG zęba', '2024-02-01T09:00:00', 1, 1),
('Badanie krwi', '2024-03-05T14:00:00', 2, 2),
('Wizyta pediatryczna', '2024-03-15T08:30:00', 3, 1),
('Szczepienie', '2024-03-20T13:15:00', 3, 1);

-- 📌 Nowe adresy dla dodatkowych pacjentów
INSERT INTO address (address_line1, address_line2, city, postal_code)
VALUES
('ul. Grabowa 9', 'Apt 4', 'Lublin', '20-001'),   -- ID = 4
('ul. Brzozowa 3', 'Apt 5', 'Poznań', '60-100'), -- ID = 5
('ul. Jesionowa 6', 'Apt 6', 'Łódź', '90-200');  -- ID = 6

-- 📌 Dodatkowi pacjenci
INSERT INTO patient (first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id) VALUES
('Tomasz', 'Nowak', '333333333', 'tomasz.nowak@example.com', 'PAT004', '2019-12-25', 4),
('Katarzyna', 'Nowak', '444444444', 'katarzyna.nowak@example.com', 'PAT005', '2014-12-26', 5),
('Paweł', 'Kowalczyk', '555555555', 'pawel.kowalczyk@example.com', 'PAT006', '2009-12-27', 6);


-- 📌 Wizyty dla nowych pacjentów (różna liczba wizyt)
INSERT INTO visit (description, time, patient_id, doctor_id) VALUES
('Wizyta nr 1 pacjenta 4', '2024-03-10T10:00:00', 4, 1),
('Wizyta nr 2 pacjenta 4', '2024-03-11T10:00:00', 4, 2),
('Wizyta nr 3 pacjenta 4', '2024-03-12T10:00:00', 4, 1),
('Wizyta nr 4 pacjenta 4', '2024-03-13T10:00:00', 4, 2),

('Wizyta nr 1 pacjenta 5', '2024-03-10T10:00:00', 5, 2),
('Wizyta nr 2 pacjenta 5', '2024-03-11T10:00:00', 5, 2),
('Wizyta nr 3 pacjenta 5', '2024-03-12T10:00:00', 5, 1),
('Wizyta nr 4 pacjenta 5', '2024-03-13T10:00:00', 5, 2),
('Wizyta nr 5 pacjenta 5', '2024-03-14T10:00:00', 5, 1),

('Wizyta nr 1 pacjenta 6', '2024-03-10T10:00:00', 6, 2),
('Wizyta nr 2 pacjenta 6', '2024-03-11T10:00:00', 6, 1),
('Wizyta nr 3 pacjenta 6', '2024-03-12T10:00:00', 6, 2),
('Wizyta nr 4 pacjenta 6', '2024-03-13T10:00:00', 6, 1),
('Wizyta nr 5 pacjenta 6', '2024-03-14T10:00:00', 6, 2),
('Wizyta nr 6 pacjenta 6', '2024-03-15T10:00:00', 6, 1);

-- 📌 Wizyty do testowania BETWEEN
INSERT INTO visit (description, time, patient_id, doctor_id) VALUES
('Wizyta testowa A', '2024-09-17T17:30:00', 1, 1),
('Wizyta testowa B', '2024-09-17T19:30:00', 2, 2);
