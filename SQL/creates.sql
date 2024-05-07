CREATE TABLE Asset (
    idAsset NUMBER(10,0) PRIMARY KEY,
    name VARCHAR2(255),
    symbol VARCHAR2(20),
    issuer VARCHAR2(255),
    industry VARCHAR2(255),
    marketCapitalization NUMBER(18,2),
    price NUMBER(18,2)
);


CREATE TABLE Crypto (
    idAsset NUMBER(10,0) PRIMARY KEY,
    name VARCHAR2(255),
    symbol VARCHAR2(20),
    issuer VARCHAR2(255),
    industry VARCHAR2(255),
    marketCapitalization NUMBER(20, 10), 
    price NUMBER(20, 10),                  
    fixedTokens NUMBER(1) CHECK (fixedTokens IN (0, 1)),
    smartContracts NUMBER(1) CHECK (smartContracts IN (0, 1)),
    blockchain VARCHAR2(100),
    tokensIssued NUMBER(10),
    transactionSeconds NUMBER(10)      
);


CREATE TABLE CryptoPOS (
    idAsset NUMBER(10,0) PRIMARY KEY,
    name VARCHAR2(255),
    symbol VARCHAR2(20),
    issuer VARCHAR2(255),
    industry VARCHAR2(255),
    marketCapitalization NUMBER(20, 10), 
    price NUMBER(20, 10),                  
    fixedTokens NUMBER(1) CHECK (fixedTokens IN (0, 1)),
    smartContracts NUMBER(1) CHECK (smartContracts IN (0, 1)),
    blockchain VARCHAR2(100),
    tokensIssued NUMBER(10),
    transactionSeconds NUMBER(10),
    apr NUMBER(10, 2),
    minStakeRequirement NUMBER(20, 2),     
    stakingDuration NUMBER(10)          
);


CREATE TABLE CryptoPOW (
    idAsset NUMBER(10,0) PRIMARY KEY,
    name VARCHAR2(255),
    symbol VARCHAR2(20),
    issuer VARCHAR2(255),
    industry VARCHAR2(255),
    marketCapitalization NUMBER(20, 10), 
    price NUMBER(20, 10),                  
    fixedTokens NUMBER(1) CHECK (fixedTokens IN (0, 1)),
    smartContracts NUMBER(1) CHECK (smartContracts IN (0, 1)),
    blockchain VARCHAR2(100),
    tokensIssued NUMBER(10),
    transactionSeconds NUMBER(10),
    cutRate NUMBER(10),          
    cutAmount NUMBER(10),       
    coinsPerBlock NUMBER(12, 2)
);


CREATE TABLE Transaction (
    idTransaction NUMBER(10,0) PRIMARY KEY,
    symbol VARCHAR2(20),
    price NUMBER(18,2),
    amount NUMBER(20,10),
    type VARCHAR2(4) CHECK (TYPE IN ('BUY', 'SELL'))
);


CREATE TABLE Stock (
    idAsset NUMBER(10,0) PRIMARY KEY,
    name VARCHAR2(255),
    symbol VARCHAR2(20),
    issuer VARCHAR2(255),
    industry VARCHAR2(255),
    marketCapitalization NUMBER(18,2),
    price NUMBER(18,2),
    market varchar(100),
    dividedRate number(10,2)
);

CREATE TABLE Userr (
    idUser NUMBER(10,0) PRIMARY KEY,
    name VARCHAR2(255),
    nickname VARCHAR2(50),
    email VARCHAR2(255),
    balance NUMBER(20, 5)
);

CREATE TABLE Exchange (
    idExchange NUMBER(10,0) PRIMARY KEY,
    name VARCHAR2(255),
    allowDemo NUMBER(1) CHECK (allowDemo IN (0, 1)), 
    requireKYC NUMBER(1) CHECK (requireKYC IN (0, 1)) 
);


CREATE TABLE CryptoExchange (
    idExchange NUMBER(10,0) PRIMARY KEY,
    name VARCHAR2(255),
    allowDemo NUMBER(1),
    requireKYC NUMBER(1),
    allowLeverage NUMBER(1),
    ICOs NUMBER(1),
    cardPlans NUMBER(1)
);

CREATE TABLE StockExchange (
    idExchange NUMBER(10,0) PRIMARY KEY,
    name VARCHAR2(255),
    allowDemo NUMBER(1) CHECK (allowDemo IN (0, 1)), 
    requireKYC NUMBER(1) CHECK (requireKYC IN (0, 1)),
    openingHour VARCHAR2(10),
    closingHour VARCHAR2(10)
);


CREATE TABLE Wallet (
    idWallet NUMBER(10,0) PRIMARY KEY,
    balance NUMBER(20, 5)
);

CREATE TABLE Spot (
    idSpot NUMBER(10) CONSTRAINT pk_spot PRIMARY KEY,
    idAsset NUMBER(10),
    sizee NUMBER(20, 10),
    idWallet NUMBER(10),
    FOREIGN KEY (idAsset) REFERENCES Asset(idAsset),
    FOREIGN KEY (idWallet) REFERENCES Wallet(idWallet)
);

CREATE TABLE Earn (
    idSpot NUMBER(10) CONSTRAINT pk_spot PRIMARY KEY,
    idAsset NUMBER(10),
    sizee NUMBER(20, 10),
    idWallet NUMBER(10),
    FOREIGN KEY (idAsset) REFERENCES Asset(idAsset),
    FOREIGN KEY (idWallet) REFERENCES Wallet(idWallet)
);

CREATE TABLE Spot (
    idSpot NUMBER(10) CONSTRAINT pk_spot PRIMARY KEY,
    idAsset NUMBER(10),
    sizee NUMBER(20, 10),
    idWallet NUMBER(10),
    FOREIGN KEY (idAsset) REFERENCES Asset(idAsset),
    FOREIGN KEY (idWallet) REFERENCES Wallet(idWallet)
);

CREATE TABLE History (
    idHistory NUMBER(10) CONSTRAINT pk_history PRIMARY KEY,
    idTransaction NUMBER(10),
    idWallet NUMBER(10),
    FOREIGN KEY (idTransaction) REFERENCES Transaction(idTransaction),
    FOREIGN KEY (idWallet) REFERENCES Wallet(idWallet)
);


CREATE TABLE Account (
    idAccount NUMBER(10) CONSTRAINT pk_account PRIMARY KEY,
    idUser NUMBER(10),
    idExchange NUMBER(10),
    idWallet NUMBER(10),
    FOREIGN KEY (idUser) REFERENCES Userr(idUser),
    FOREIGN KEY (idExchange) REFERENCES Exchange(idExchange),
    FOREIGN KEY (idWallet) REFERENCES Wallet(idWallet)
);
