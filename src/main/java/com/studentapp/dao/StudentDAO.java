package com.studentapp.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.studentapp.model.Student;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object encapsulating all MongoDB operations for Student documents.
 */
public class StudentDAO {

    private static final String COLLECTION_NAME = "students";

    private MongoCollection<Document> getCollection() {
        MongoDatabase db = MongoConnection.getDatabase();
        return db.getCollection(COLLECTION_NAME);
    }

    /** Insert a new student, returns the generated id. */
    public String addStudent(Student student) {
        Document doc = new Document("name", student.getName())
                .append("age", student.getAge())
                .append("email", student.getEmail())
                .append("course", student.getCourse())
                .append("phone", student.getPhone());
        getCollection().insertOne(doc);
        return doc.getObjectId("_id").toString();
    }

    /** Return all students, most recently added first. */
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        FindIterable<Document> docs = getCollection().find();
        for (Document doc : docs) {
            students.add(documentToStudent(doc));
        }
        return students;
    }

    /** Fetch a single student by id, or null if not found. */
    public Student getStudentById(String id) {
        Document doc = getCollection().find(new Document("_id", new ObjectId(id))).first();
        return doc == null ? null : documentToStudent(doc);
    }

    /** Update an existing student. Returns true if a document was modified. */
    public boolean updateStudent(Student student) {
        Document updated = new Document("name", student.getName())
                .append("age", student.getAge())
                .append("email", student.getEmail())
                .append("course", student.getCourse())
                .append("phone", student.getPhone());

        UpdateResult result = getCollection().updateOne(
                new Document("_id", new ObjectId(student.getId())),
                new Document("$set", updated)
        );
        return result.getModifiedCount() > 0;
    }

    /** Delete a student by id. Returns true if a document was removed. */
    public boolean deleteStudent(String id) {
        DeleteResult result = getCollection().deleteOne(new Document("_id", new ObjectId(id)));
        return result.getDeletedCount() > 0;
    }

    private Student documentToStudent(Document doc) {
        Student s = new Student();
        s.setId(doc.getObjectId("_id").toString());
        s.setName(doc.getString("name"));
        Object ageObj = doc.get("age");
        s.setAge(ageObj instanceof Integer ? (Integer) ageObj : Integer.parseInt(String.valueOf(ageObj)));
        s.setEmail(doc.getString("email"));
        s.setCourse(doc.getString("course"));
        s.setPhone(doc.getString("phone"));
        return s;
    }
}
