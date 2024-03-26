package Assets;

import Platforms.CryptoExchange;

public class Crypto extends Asset{

    //crypto exchange
    protected Boolean fixedTokens;
    protected Boolean smartContracts;
    protected String blockchain;
    protected int tokensIssued;
    protected int transactionSeconds;
    protected CryptoExchange exchange;
}
