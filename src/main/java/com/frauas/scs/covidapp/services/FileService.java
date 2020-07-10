package com.frauas.scs.covidapp.services;

import org.springframework.stereotype.Service;

import java.io.*;

/**
 * CreatedBy : edangol
 * CreatedOn : 22/06/2020
 * Description :
 **/
@Service
public class FileService {

    public boolean save(String fileName, Object obj) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(obj);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Object load(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
