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
    phone INTEGER
);

CREATE TABLE receipts (
    id BIGINT IDENTITY PRIMARY KEY,
    description VARCHAR(512),
    docID BIGINT NOT NULL,
    patientID BIGINT NOT NULL,
    creationDate DATE NOT NULL,
    expiration INTEGER NOT NULL,
    priority INTEGER,
    FOREIGN KEY (docID) REFERENCES doctors(id),
    FOREIGN KEY (patientID) REFERENCES patients(id)
);