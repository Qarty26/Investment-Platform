INSERT INTO Asset (idAsset, name, symbol, issuer, industry, marketCapitalization, price)
VALUES (1, 'Apple Inc.', 'AAPL', 'Apple', 'Technology', 2250000000000, 145.75);

INSERT INTO Asset (idAsset, name, symbol, issuer, industry, marketCapitalization, price)
VALUES (2, 'Amazon.com Inc.', 'AMZN', 'Amazon', 'Technology', 1900000000000, 3240.98);

INSERT INTO Asset (idAsset, name, symbol, issuer, industry, marketCapitalization, price)
VALUES (3, 'Tesla Inc.', 'TSLA', 'Tesla', 'Automotive', 850000000000, 678.90);

INSERT INTO Asset (idAsset, name, symbol, issuer, industry, marketCapitalization, price)
VALUES (4, 'Microsoft Corporation', 'MSFT', 'Microsoft', 'Technology', 2200000000000, 310.25);

INSERT INTO Asset (idAsset, name, symbol, issuer, industry, marketCapitalization, price)
VALUES (5, 'Alphabet Inc. Class A', 'GOOGL', 'Alphabet', 'Technology', 1800000000000, 2770.10);

select * from asset;


INSERT INTO Crypto (idAsset, name, symbol, issuer, industry, marketCapitalization, price, fixedTokens, smartContracts, blockchain, tokensIssued, transactionSeconds)
VALUES (101, 'Bitcoin', 'BTC', 'Bitcoin Foundation', 'Cryptocurrency', 950000000000, 59874.56, 1, 1, 'Bitcoin', 18750000, 600);

INSERT INTO Crypto (idAsset, name, symbol, issuer, industry, marketCapitalization, price, fixedTokens, smartContracts, blockchain, tokensIssued, transactionSeconds)
VALUES (102, 'Ethereum', 'ETH', 'Ethereum Foundation', 'Blockchain', 350000000000, 3921.78, 0, 1, 'Ethereum', 115000000, 15);

INSERT INTO Crypto (idAsset, name, symbol, issuer, industry, marketCapitalization, price, fixedTokens, smartContracts, blockchain, tokensIssued, transactionSeconds)
VALUES (103, 'Binance Coin', 'BNB', 'Binance', 'Cryptocurrency', 100000000000, 591.45, 1, 1, 'Binance Smart Chain', 168000000, 5);

INSERT INTO Crypto (idAsset, name, symbol, issuer, industry, marketCapitalization, price, fixedTokens, smartContracts, blockchain, tokensIssued, transactionSeconds)
VALUES (104, 'Cardano', 'ADA', 'Cardano Foundation', 'Blockchain', 82000000000, 1.98, 1, 1, 'Cardano', 32000000000, 2);

INSERT INTO Crypto (idAsset, name, symbol, issuer, industry, marketCapitalization, price, fixedTokens, smartContracts, blockchain, tokensIssued, transactionSeconds)
VALUES (105, 'Solana', 'SOL', 'Solana Foundation', 'Blockchain', 70000000000, 43.29, 1, 1, 'Solana', 270000000, 0.5);


INSERT INTO CryptoPOS (idAsset, name, symbol, issuer, industry, marketCapitalization, price, fixedTokens, smartContracts, blockchain, tokensIssued, transactionSeconds, apr, minStakeRequirement, stakingDuration)
VALUES (201, 'Tezos', 'XTZ', 'Tezos Foundation', 'Blockchain', 5500000000, 6.23, 1, 1, 'Tezos', 870000000, 1, 5.25, 1000, 90);

INSERT INTO CryptoPOS (idAsset, name, symbol, issuer, industry, marketCapitalization, price, fixedTokens, smartContracts, blockchain, tokensIssued, transactionSeconds, apr, minStakeRequirement, stakingDuration)
VALUES (202, 'Cosmos', 'ATOM', 'Interchain Foundation', 'Blockchain', 4500000000, 34.80, 1, 1, 'Cosmos', 310000000, 1, 9.75, 500, 180);

INSERT INTO CryptoPOS (idAsset, name, symbol, issuer, industry, marketCapitalization, price, fixedTokens, smartContracts, blockchain, tokensIssued, transactionSeconds, apr, minStakeRequirement, stakingDuration)
VALUES (203, 'Polkadot', 'DOT', 'Web3 Foundation', 'Blockchain', 4200000000, 25.19, 1, 1, 'Polkadot', 1110000000, 1, 12.50, 100, 365);

INSERT INTO CryptoPOS (idAsset, name, symbol, issuer, industry, marketCapitalization, price, fixedTokens, smartContracts, blockchain, tokensIssued, transactionSeconds, apr, minStakeRequirement, stakingDuration)
VALUES (204, 'Algorand', 'ALGO', 'Algorand Foundation', 'Blockchain', 3200000000, 0.88, 1, 1, 'Algorand', 3000000000, 0.5, 6.75, 5000, 365);

INSERT INTO CryptoPOS (idAsset, name, symbol, issuer, industry, marketCapitalization, price, fixedTokens, smartContracts, blockchain, tokensIssued, transactionSeconds, apr, minStakeRequirement, stakingDuration)
VALUES (205, 'Avalanche', 'AVAX', 'Avalanche Foundation', 'Blockchain', 3100000000, 55.35, 1, 1, 'Avalanche', 190000000, 0.25, 8.00, 200, 180);



INSERT INTO CryptoPOW (idAsset, name, symbol, issuer, industry, marketCapitalization, price, fixedTokens, smartContracts, blockchain, tokensIssued, transactionSeconds, cutRate, cutAmount, coinsPerBlock)
VALUES (301, 'Litecoin', 'LTC', 'Litecoin Foundation', 'Cryptocurrency', 14000000000, 140.2, 1, 1, 'Scrypt', 71000000, 60, 5, 25, 12.50);

INSERT INTO CryptoPOW (idAsset, name, symbol, issuer, industry, marketCapitalization, price, fixedTokens, smartContracts, blockchain, tokensIssued, transactionSeconds, cutRate, cutAmount, coinsPerBlock)
VALUES (302, 'Bitcoin Cash', 'BCH', 'Bitcoin ABC', 'Cryptocurrency', 13000000000, 612.7, 1, 1, 'Bitcoin Cash', 19000000, 120, 6, 30, 6.25);

INSERT INTO CryptoPOW (idAsset, name, symbol, issuer, industry, marketCapitalization, price, fixedTokens, smartContracts, blockchain, tokensIssued, transactionSeconds, cutRate, cutAmount, coinsPerBlock)
VALUES (303, 'Ethereum Classic', 'ETC', 'ETC Cooperative', 'Blockchain', 7000000000, 43.8, 1, 1, 'Ethash', 150000000, 15, 3, 15, 4.00);

INSERT INTO CryptoPOW (idAsset, name, symbol, issuer, industry, marketCapitalization, price, fixedTokens, smartContracts, blockchain, tokensIssued, transactionSeconds, cutRate, cutAmount, coinsPerBlock)
VALUES (304, 'Dash', 'DASH', 'Dash Core Group', 'Cryptocurrency', 7000000000, 171.5, 1, 1, 'X11', 10000000, 45, 4, 20, 1.50);

INSERT INTO CryptoPOW (idAsset, name, symbol, issuer, industry, marketCapitalization, price, fixedTokens, smartContracts, blockchain, tokensIssued, transactionSeconds, cutRate, cutAmount, coinsPerBlock)
VALUES (305, 'Zcash', 'ZEC', 'Zcash Foundation', 'Cryptocurrency', 900000000, 86.7, 1, 1, 'Equihash', 12000000, 30, 2, 10, 1.25);


INSERT INTO Stock (idAsset, name, symbol, issuer, industry, marketCapitalization, price, market, dividedRate)
VALUES (401, 'Apple Inc.', 'AAPL', 'NASDAQ', 'Technology', 2250000000000, 145.75, 'NASDAQ', 0.68);

INSERT INTO Stock (idAsset, name, symbol, issuer, industry, marketCapitalization, price, market, dividedRate)
VALUES (402, 'Microsoft Corporation', 'MSFT', 'NASDAQ', 'Technology', 2200000000000, 310.25, 'NASDAQ', 0.90);

INSERT INTO Stock (idAsset, name, symbol, issuer, industry, marketCapitalization, price, market, dividedRate)
VALUES (403, 'Amazon.com Inc.', 'AMZN', 'NASDAQ', 'Technology', 1900000000000, 3240.98, 'NASDAQ', 0.00);

INSERT INTO Stock (idAsset, name, symbol, issuer, industry, marketCapitalization, price, market, dividedRate)
VALUES (404, 'Tesla Inc.', 'TSLA', 'NASDAQ', 'Automotive', 850000000000, 678.90, 'NASDAQ', 0.00);

INSERT INTO Stock (idAsset, name, symbol, issuer, industry, marketCapitalization, price, market, dividedRate)
VALUES (405, 'Alphabet Inc. Class A', 'GOOGL', 'NASDAQ', 'Technology', 1800000000000, 2770.10, 'NASDAQ', 0.00);


INSERT INTO Userr (idUser, name, nickname, email, balance)
VALUES (1, 'Alice Johnson', 'ali_j', 'alice@example.com', 5000.25);

INSERT INTO Userr (idUser, name, nickname, email, balance)
VALUES (2, 'Bob Smith', 'bobby', 'bob@example.com', 25000.75);

INSERT INTO Userr (idUser, name, nickname, email, balance)
VALUES (3, 'Charlie Brown', 'charlie_b', 'charlie@example.com', 100.00);

INSERT INTO Userr (idUser, name, nickname, email, balance)
VALUES (4, 'David Lee', 'dlee', 'david@example.com', 15000.00);

INSERT INTO Userr (idUser, name, nickname, email, balance)
VALUES (5, 'Eva White', 'evaw', 'eva@example.com', 750.50);


INSERT INTO Exchange (idExchange, name, allowDemo, requireKYC)
VALUES (101, 'Coinbase', 1, 1);

INSERT INTO Exchange (idExchange, name, allowDemo, requireKYC)
VALUES (102, 'Binance', 1, 1);

INSERT INTO Exchange (idExchange, name, allowDemo, requireKYC)
VALUES (103, 'Kraken', 0, 1);

INSERT INTO Exchange (idExchange, name, allowDemo, requireKYC)
VALUES (104, 'Gemini', 1, 0);

INSERT INTO Exchange (idExchange, name, allowDemo, requireKYC)
VALUES (105, 'Bitstamp', 0, 0);


INSERT INTO CryptoExchange (idExchange, name, allowDemo, requireKYC, allowLeverage, ICOs, cardPlans)
VALUES (201, 'Binance', 1, 1, 1, 1, 1);

INSERT INTO CryptoExchange (idExchange, name, allowDemo, requireKYC, allowLeverage, ICOs, cardPlans)
VALUES (202, 'Bitfinex', 0, 1, 1, 0, 1);

INSERT INTO CryptoExchange (idExchange, name, allowDemo, requireKYC, allowLeverage, ICOs, cardPlans)
VALUES (203, 'KuCoin', 1, 1, 0, 1, 0);

INSERT INTO CryptoExchange (idExchange, name, allowDemo, requireKYC, allowLeverage, ICOs, cardPlans)
VALUES (204, 'Huobi', 1, 0, 1, 0, 1);

INSERT INTO CryptoExchange (idExchange, name, allowDemo, requireKYC, allowLeverage, ICOs, cardPlans)
VALUES (205, 'OKEx', 0, 1, 1, 1, 0);


INSERT INTO StockExchange (idExchange, name, allowDemo, requireKYC, openingHour, closingHour)
VALUES (301, 'NYSE', 1, 1, '09:30', '16:00');

INSERT INTO StockExchange (idExchange, name, allowDemo, requireKYC, openingHour, closingHour)
VALUES (302, 'NASDAQ', 1, 1, '09:30', '16:00');

INSERT INTO StockExchange (idExchange, name, allowDemo, requireKYC, openingHour, closingHour)
VALUES (303, 'London Stock Exchange', 0, 1, '08:00', '16:30');

INSERT INTO StockExchange (idExchange, name, allowDemo, requireKYC, openingHour, closingHour)
VALUES (304, 'Tokyo Stock Exchange', 1, 0, '09:00', '15:00');

INSERT INTO StockExchange (idExchange, name, allowDemo, requireKYC, openingHour, closingHour)
VALUES (305, 'Hong Kong Stock Exchange', 0, 0, '09:30', '16:00');


