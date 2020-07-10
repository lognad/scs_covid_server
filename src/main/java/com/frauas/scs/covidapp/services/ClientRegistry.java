package com.frauas.scs.covidapp.services;

import com.frauas.scs.covidapp.models.Subscriber;
import com.frauas.scs.covidapp.repo.RegisterRepo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutionException;

/**
 * CreatedBy : edangol
 * CreatedOn : 09/06/2020
 * Description :
 **/
@Service
public class ClientRegistry {
    private RegisterRepo registerRepo;
    private final FCMClient fcmClient;
    private LocationProcessor locationProcessor;

    private final Set<String> tokenRegistry = new CopyOnWriteArraySet<>();

    public ClientRegistry(RegisterRepo registerRepo, FCMClient fcmClient, LocationProcessor locationProcessor) {
        this.registerRepo = registerRepo;
        this.fcmClient = fcmClient;
        this.locationProcessor = locationProcessor;
        System.out.println("client registry created");
    }

    public void saveSubscription(Subscriber s) {
        this.registerRepo.save(s);
        this.registerRepo.saveRegister();
        this.registerRepo.printRegister();
    }

    public void removeSubscription(String token) {
        this.registerRepo.remove(token);
    }

    //    @Scheduled(fixedDelay = 30_000)
    void sendPushMessages() {
        for (String token : this.tokenRegistry) {

            System.out.println("Sending personal message to: " + token);
            Map<String, String> data = new HashMap<>();
            data.put("title", "admin server test");
            data.put("text", "some random text from server");

            try {
                this.fcmClient.sendPersonalMessage(token, data);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveLocation(Subscriber s) {
        registerRepo.save(s);
        locationProcessor.process(s);
    }

    public List<Subscriber> getAllSubscription() {
        return registerRepo.getAll();
    }
}
