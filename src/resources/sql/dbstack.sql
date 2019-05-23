INSERT INTO doctors (firstname, lastname, dadsname, speciality)
values
    ('Alexander', 'Ivanov', 'Semyonovich', 'narkolog'),
    ('Victor', 'Tsoy', 'Sergeevich', 'urolog'),
    ('Jeremy', 'Franklin', 'Ls.', 'proktolog')
    ;

INSERT INTO patients (firstname, lastname, dadsname, phone)
values
    ('James', 'Franko', 'M.', '555-02952'),
    ('Marco', 'Polo', 'K.', '555-77604'),
    ('Tomas', 'Anderson','Jr.','555-14968')
    ;

INSERT INTO receipts (description, docID, patientID, creationDate, expirationDate)
values
    ('drug Depilix use 2 tablets per day, 10 days', 1, 2, TO_DATE('17/05/2019', 'DD/MM/YYYY'), TO_DATE('27/05/2019', 'DD/MM/YYYY')),
    ('drug Sparcion use 1 tablet after dinner, 8 days', 1, 0, TO_DATE('14/05/2019', 'DD/MM/YYYY'), TO_DATE('22/05/2019', 'DD/MM/YYYY')),
    ('drug Giogin use 4 tablets every 6 hours, 12 days', 2, 2, TO_DATE('20/05/2019', 'DD/MM/YYYY'), TO_DATE('01/06/2019', 'DD/MM/YYYY'))
    ;
