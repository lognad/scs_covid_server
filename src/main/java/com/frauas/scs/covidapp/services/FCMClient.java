package com.frauas.scs.covidapp.services;

import com.frauas.scs.covidapp.configs.FCMConfiguration;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import lombok.extern.java.Log;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * CreatedBy : edangol
 * CreatedOn : 09/06/2020
 * Description :
 **/
@Service
public class FCMClient {
    public FCMClient(FCMConfiguration settings) {
        Path p = Paths.get(settings.getServiceAccountFile());
        try (InputStream serviceAccount = Files.newInputStream(p)) {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Map<String, String> data)
            throws InterruptedException, ExecutionException {

        AndroidConfig androidConfig = AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey("chuck")
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder().setTag("chuck").build()).build();

        ApnsConfig apnsConfig = ApnsConfig.builder()
                .setAps(Aps.builder().setCategory("chuck").setThreadId("chuck").build()).build();

        Message message = Message.builder().putAllData(data).setTopic("chuck")
                .setApnsConfig(apnsConfig).setAndroidConfig(androidConfig)
                .setNotification(Notification.builder().setTitle("Chuck Norris Joke")
                        .setBody("A new Chuck Norris joke has arrived").build())
                .build();

        String response = FirebaseMessaging.getInstance().sendAsync(message).get();
        System.out.println("Sent message: " + response);
    }

    public void sendPersonalMessage(String clientToken, Map<String, String> data)
            throws InterruptedException, ExecutionException {
        AndroidConfig androidConfig = AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey("personal")
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder().setTag("personal").build())
                .build();

        ApnsConfig apnsConfig = ApnsConfig.builder()
                .setAps(Aps.builder().setCategory("personal").setThreadId("personal").build())
                .build();

        Message message = Message.builder().putAllData(data).setToken(clientToken)
                .setApnsConfig(apnsConfig).setAndroidConfig(androidConfig)
                .setNotification(Notification.builder().setTitle("Personal Message")
                        .setBody("A Personal Message").build())
                .build();

        String response = FirebaseMessaging.getInstance().sendAsync(message).get();
        System.out.println("Sent message: " + response);
    }
}
