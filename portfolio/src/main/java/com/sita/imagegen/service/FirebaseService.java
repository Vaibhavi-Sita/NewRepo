package com.sita.imagegen.service;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
@Service
public class FirebaseService {

    private final FirebaseDatabase firebaseDatabase;

    public FirebaseService() {
        this.firebaseDatabase = FirebaseDatabase.getInstance();
    }

    public String getApiKey(String serviceName) throws ExecutionException, InterruptedException {
        DatabaseReference ref = firebaseDatabase.getReference("/api_keys/" + serviceName);
        CompletableFuture<String> future = new CompletableFuture<>();

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String apiKey = dataSnapshot.getValue(String.class);
                    future.complete(apiKey);
                } else {
                    future.completeExceptionally(new Exception("API Key not found"));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(databaseError.toException());
            }
        });

        return future.get();
    }
}