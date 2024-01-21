package com.event_managment_system.DataManager;

import com.event_managment_system.entities.root;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class FileHandler<T>{
    private String fileName;
    private List<T> data;
    private Map<UUID, T> dataMap;

    public FileHandler(String fileName) {
        this.fileName = getClass().getResource(fileName).getFile();;
        this.data = new ArrayList<>();
        this.dataMap = new HashMap<>();
        loadFromFile();
    }

    public void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            this.data = (List<T>) ois.readObject();
            for (T item : data) {
                dataMap.put(((root) item).getId(), item);
            }
        } catch (EOFException e) {
            System.out.println("End of file reached unexpectedly.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public T getItemById(UUID id) {
        return dataMap.get(id);
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this.data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addItem(T item) {
        this.data.add(item);
        saveToFile();
        loadFromFile();
    }
    public void removeItem(T item) {
        this.data.remove(item);
        saveToFile();
        loadFromFile();
    }
    public void updateItem(int index, T updatedItem) {
        this.data.set(index, updatedItem);
        saveToFile();
        loadFromFile();
    }
    public int findIndexOfItem(T item) {
        for (int i = 0; i < this.data.size(); i++) {
            if (this.data.get(i).equals(item)) {
                return i;
            }
        }
        return -1;
    }
    public List<T> getAllItems() {
        return this.data;
    }
}
