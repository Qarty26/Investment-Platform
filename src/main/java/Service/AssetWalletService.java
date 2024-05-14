package Service;

import Model.DataStoring.Wallet;
import Persistence.AssetRepository;
import Persistence.WalletRepository;

public class AssetWalletService {

    public WalletRepository walletRepository;
    public AssetRepository assetRepository;

    public AssetWalletService(WalletRepository walletRepository, AssetRepository assetRepository) {
        this.walletRepository = walletRepository;
        this.assetRepository = assetRepository;
    }

}
