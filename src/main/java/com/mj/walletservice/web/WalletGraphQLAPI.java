package com.mj.walletservice.web;

import com.mj.walletservice.dto.AddWalletRequestDTO;
import com.mj.walletservice.entities.Currency;
import com.mj.walletservice.entities.Wallet;
import com.mj.walletservice.entities.WalletTransaction;
import com.mj.walletservice.repositories.CurrencyRepository;
import com.mj.walletservice.repositories.WalletRepository;
import com.mj.walletservice.service.WalletService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class WalletGraphQLAPI {
    private WalletRepository walletRepository;
    private WalletService walletService;
    private CurrencyRepository currencyRepository;

    public WalletGraphQLAPI(WalletRepository walletRepository, WalletService walletService, CurrencyRepository currencyRepository) {
        this.walletRepository = walletRepository;
        this.walletService = walletService;
        this.currencyRepository = currencyRepository;
    }
    @QueryMapping
    public List<Wallet> userWallets(){
        return walletRepository.findAll();
    }

    @QueryMapping
    public Wallet walletById(@Argument String id){
        return walletRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Wallet %s not found",id)));
    }
    @MutationMapping
    public Wallet addWallet(@Argument AddWalletRequestDTO wallet){
        return walletService.save(wallet);
    }
    @MutationMapping
    public List<WalletTransaction> walletTransfer(@Argument String sourceWalletId,@Argument String destinationWalletId,@Argument Double amount){
        return walletService.walletTransfer(sourceWalletId,destinationWalletId,amount);
    }
    @QueryMapping
    public List<Currency> currencies(){
        return currencyRepository.findAll();
    }
}

