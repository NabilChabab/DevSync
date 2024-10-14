package org.example.scheduler;

import org.example.models.Token;
import org.example.models.TokenRequest;
import org.example.repository.TokenRepositoryImpl;
import org.example.repository.TokenRequestImpl;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TokenDeleteScheduler {
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final TokenRepositoryImpl tokenRepository;


    public TokenDeleteScheduler() {
        this.tokenRepository = new TokenRepositoryImpl();

    }

    public void startScheduler() {
        scheduler.scheduleAtFixedRate(this::checkAndUpdateDeleteTokenCounts, 0, 30, TimeUnit.DAYS);
    }

    private void checkAndUpdateDeleteTokenCounts(){
        List<Token> tokens = tokenRepository.findAll();

        for(Token token : tokens){
                token.setDeleteTokenCount(1);
            tokenRepository.update(token);
        }
    }

    public void stopScheduler() {
        scheduler.shutdown();
    }
}
