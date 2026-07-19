package com.studentapp.dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 * Holds a single shared MongoClient/MongoDatabase for the application.
 * Edit CONNECTION_STRING / DB_NAME below to match your MongoDB setup.
 */
public class MongoConnection {

    // Change this if your MongoDB is remote or uses authentication, e.g.:
    // "mongodb://username:password@host:27017"
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DB_NAME = "student_registration_db";

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    private MongoConnection() {
    }

    public static synchronized MongoDatabase getDatabase() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(CONNECTION_STRING);
            database = mongoClient.getDatabase(DB_NAME);
        }
        return database;
    }

    public static synchronized void close() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }
    }
}
