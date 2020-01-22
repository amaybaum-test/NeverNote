package com.naani.NeverNote.model;

import com.naani.NeverNote.rest.LocalDateTimeAdapter;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

/**
 * This is a POJO class for handling JSON to MONGO
 * MONGO doesn't support Date Time SO the class LocalDateTime
 *
 */

public class Note {
    private String id;
    private String userId;
    private String title;
    private String body;
    private LocalDateTime created = LocalDateTime.now();
    private LocalDateTime modified = null;

    public Note(){

    }
    public Note (String title){
        this.title = title;
    }

    public Note(final Document doc){
        final LocalDateTimeAdapter adapter = new LocalDateTimeAdapter();
        userId = doc.getString("User_id");
        id = doc.get("_id", ObjectId.class).toHexString();
        title = doc.getString("title");
        body = doc.getString("body");
        created = adapter.unmarshal(doc.getString("created"));
        modified = adapter.unmarshal(doc.getString("modified"));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
    public Document toDocument(){
        final LocalDateTimeAdapter adapter = new LocalDateTimeAdapter();
        Document doc = new Document();
        if(id!=null){
            doc.append("_id", new ObjectId(getId()));
        }
        doc.append("user_id", getUserId())
                .append("title",getTitle())
                .append("body", getBody())
                .append("created", adapter.marshal(getCreated()!= null ? getCreated() : LocalDateTime.now()))
                .append("modified",adapter.marshal(getModified()));
        return doc;
    }

    public void setUser(String id) {

    }
}

