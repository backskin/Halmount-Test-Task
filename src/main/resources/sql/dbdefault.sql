CREATE TABLE doctors (
    id BIGINT IDENTITY PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    dadsname VARCHAR(255) NOT NULL,
    speciality VARCHAR(255)
);

CREATE TABLE patients (
    id BIGINT IDENTITY PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    dadsname VARCHAR(255) NOT NULL,
    phone VARCHAR(255)
);

CREATE TABLE receipts (
    id BIGINT IDENTITY PRIMARY KEY,
    description VARCHAR(512),
    doctorID BIGINT NOT NULL,
    patientID BIGINT NOT NULL,
    creationDate DATE NOT NULL,
    validity INTEGER NOT NULL,
    priority INTEGER,
    FOREIGN KEY (docID) REFERENCES doctors(id),
    FOREIGN KEY (patientID) REFERENCES patients(id)
);