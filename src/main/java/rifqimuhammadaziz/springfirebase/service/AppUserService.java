package rifqimuhammadaziz.springfirebase.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import rifqimuhammadaziz.springfirebase.dto.AppUserResponse;
import rifqimuhammadaziz.springfirebase.entity.AppUser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class AppUserService {

    public AppUser registration(AppUser user) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore
                .collection("appuser")
                .document(user.getIdentityNumber())
                .set(user);

        return user;
    }

    public List<AppUserResponse> findAllUsers() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        final Query query = dbFirestore.collection("appuser");
        final ApiFuture<QuerySnapshot> querySnapshot = query.get();

        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        List<AppUserResponse> users = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.toObject(AppUser.class).getCreatedDate());
            users.add(document.toObject(AppUserResponse.class));
        }

        return users;
    }

    public AppUserResponse findByIdentityNumber(String identityNumber) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore
                .collection("appuser")
                .document(identityNumber);

        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        AppUserResponse userResponse;
        if (document.exists()) {
            userResponse = document.toObject(AppUserResponse.class);
            return userResponse;
        }
        return null;
    }
}
