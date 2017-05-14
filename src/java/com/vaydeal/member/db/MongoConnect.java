/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.member.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.exclude;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.UpdateResult;
import com.vaydeal.member.jsn.JSONParser;
import com.vaydeal.member.message.ErrMsg;
import com.vaydeal.member.mongo.mod.MemberID;
import com.vaydeal.member.mongo.mod.VerifyToken;
import com.vaydeal.member.req.mod.NewPassword;
import java.io.IOException;
import java.net.URLEncoder;
import org.bson.Document;

/**
 * @company techvay
 * @author rifaie
 */
public class MongoConnect {
    private final MongoDatabase db;
    private final MongoClient mongoClient;

    public MongoConnect() throws Exception {
        String pass = "V@YDR#@MS";
        pass = URLEncoder.encode(pass);
        MongoClientURI uri = new MongoClientURI("mongodb://vaydeal:"+pass+"@13.126.8.181/vaydeal");
        mongoClient = new MongoClient(uri);
        db = mongoClient.getDatabase("vaydeal");
    }
    
    public void closeConnection(){
        mongoClient.close();
    }

    public boolean updateAccessToken(String member_id, String accessToken) {
        boolean status = false;
        MongoCollection<Document> fgp = db.getCollection("member_access_token");
        UpdateResult updateOne = fgp.updateOne(eq("member_id", member_id), combine(set("token", "" + accessToken), set("status", "logged")));
        if (updateOne.getMatchedCount() == 1) {
            status = true;
        }
        return status;
    }

    public VerifyToken verifyToken(String token) throws IOException {
        MongoCollection<Document> fgp = db.getCollection("member_password_token");
        FindIterable<Document> find = fgp.find(Filters.and(eq("token", token))).projection(exclude("token", "_id"));
        VerifyToken vt;
        if (find.first() != null) {
            vt = JSONParser.parseJSONVT(find.first().toJson());
        } else {
            vt = new VerifyToken();
        }
        return vt;
    }

    public long updateTokenStatus(NewPassword req) {
        MongoCollection<Document> fgp = db.getCollection("member_password_token");
        UpdateResult updateOne = fgp.updateOne(eq("member_id", req.getMember_id()), combine(set("status", "verified")));
        return updateOne.getMatchedCount();
    }

    public VerifyToken getMemberId(String token) throws IOException {
        MongoCollection<Document> fgp = db.getCollection("member_password_token");
        FindIterable<Document> find = fgp.find(Filters.and(eq("token", token))).projection(exclude("token", "_id"));
        VerifyToken vt = JSONParser.parseJSONVT(find.first().toJson());
        return vt;
    }

    public void addActivity(String act) {
        MongoCollection collection = db.getCollection("member_activities");
        Document doc = Document.parse(act);
        collection.insertOne(doc);
    }

    public MemberID getMemberID(String at) throws IOException {
        MongoCollection<Document> gpi = db.getCollection("member_access_token");
        FindIterable<Document> find = gpi.find(Filters.and(eq("token", at), eq("status", "logged"))).projection(exclude("token", "_id", "status"));
        MemberID mid = null;
        if (find.iterator().hasNext()) {
            mid = JSONParser.parseJSONMID(find.first().toJson());
        } else {
            mid = new MemberID();
            mid.setMember_id(ErrMsg.ERR_ACCESS_TOKEN);
        }
        return mid;
    }

    public boolean logout(String at) {
        boolean status = false;
        MongoCollection<Document> collection = db.getCollection("member_access_token");
        UpdateResult updateOne = collection.updateOne(eq("token", at), combine(set("status", "not logged")));
        if (updateOne.getMatchedCount() == 1) {
            status = true;
        }
        return status;
    }

    public boolean updateAUPasswordToken(String mid, String passwordToken) {
        boolean status = false;
        MongoCollection<Document> fgp = db.getCollection("member_password_token");
        UpdateResult updateOne = fgp.updateOne(eq("member_id", mid), combine(set("token", "" + passwordToken), set("status", "not changed"), set("toe", "" + (System.currentTimeMillis() + 300000))));
        if (updateOne.getMatchedCount() == 1) {
            status = true;
        }
        return status;
    }
}
