package com.frauas.scs.covidapp.repo;

import com.frauas.scs.covidapp.models.Coordinates;
import com.frauas.scs.covidapp.models.Subscriber;
import com.frauas.scs.covidapp.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * CreatedBy : edangol
 * CreatedOn : 22/06/2020
 * Description :
 **/
@Repository
public class RegisterRepo {
    static final String SUBSCRIBER_FILE_NAME = "subscribers_register.ser";

    private Map<String, Subscriber> register;

    public RegisterRepo() {
        System.out.println("Register Repo Initialized");
//        seedRegister();
//        System.out.println("final print");
//        printRegister();
    }

    public void save(Subscriber s) {
        register.put(s.getToken(), s);
    }

    public void remove(String key) {
        register.remove(key);
    }

    private void seedRegister() {
        Map<String, Subscriber> m = new ConcurrentHashMap<>();
        for (int i = 0; i < 5; i++) {
            Subscriber s = new Subscriber();
            s.setToken("test " + i);
            s.setLocation(new Coordinates(0.1, 0.1));
            m.put(s.getToken(), s);
        }
        register = m;
        saveRegister();
    }

    @Autowired
    protected void loadRegister() {
        FileService fs = new FileService();
        Map m = (ConcurrentHashMap<String, Subscriber>) fs.load(SUBSCRIBER_FILE_NAME);
        if (m != null)
            register = m;
    }

    public void saveRegister() {
        FileService fs = new FileService();
        fs.save(SUBSCRIBER_FILE_NAME, register);
    }

    public void printRegister() {
        if (register != null)
            for (Map.Entry<String, Subscriber> m : register.entrySet()) {
                System.out.println(m.getKey() + " : " + m.getValue().toString());
            }
    }

    public List<Subscriber> getAll() {
        Collection<Subscriber> list = register.values();
        return new ArrayList<>(list);
    }
}
