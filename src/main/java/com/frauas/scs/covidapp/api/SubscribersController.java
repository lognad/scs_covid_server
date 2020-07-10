package com.frauas.scs.covidapp.api;

import com.frauas.scs.covidapp.models.Subscriber;
import com.frauas.scs.covidapp.services.ClientRegistry;
import com.frauas.scs.covidapp.services.FCMClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * CreatedBy : edangol
 * CreatedOn : 09/06/2020
 * Description :
 **/
@RestController
public class SubscribersController {

    private ClientRegistry registry;
    private FCMClient fcmClient;

    public SubscribersController(ClientRegistry registry, FCMClient fcmClient) {
        this.registry = registry;
        this.fcmClient = fcmClient;
    }

    @PostMapping("/subscribe")
    String saveSubscription(@RequestBody Subscriber s) {
        System.out.println("subscription received: " + s.toString());
        registry.saveSubscription(s);
        System.out.println("Subscribed: " + s.toString());
        return "success";
    }

    @PostMapping("/unsubscribe")
    String removeSubscription(@RequestBody Subscriber s) {
        System.out.println("removing subscription: " + s.toString());
        registry.removeSubscription(s.getToken());
        System.out.println("UnSubscribed: " + s.toString());
        return "success";
    }

    @GetMapping("/subscribers/list")
    List<Subscriber> getSubscriptionList() {
        return registry.getAllSubscription();
    }

    @GetMapping("/test")
    String hello() {
        Map<String, String> data = new HashMap<>();
        data.put("title", "attention");
        try {
            fcmClient.sendMessage(data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "hello";
    }

    @GetMapping("/send")
    String sendPersonalMessage() {
        String userToken = "eWJs8SL1S5qodBm_MqqDFF:APA91bHZetgmNtMNvDGvUCs-rlDCY75LJFwWTm-KwwjVZB2v5qZ5VR8n74R2x_BDzD8aMvoVHlVvAHsaczHLYLx83KvuLyz8W433nUqYkbNvM_d2xPghZkx8ClJHhq8J5Khi0c2JP0AG";
        Map<String, String> data = new HashMap<>();
        data.put("title", "hi there good night");
        try {
            fcmClient.sendPersonalMessage(userToken, data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "hello";
    }
}
