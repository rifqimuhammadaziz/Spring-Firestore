package rifqimuhammadaziz.springfirebase.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import rifqimuhammadaziz.springfirebase.dto.AppUserRequest;
import rifqimuhammadaziz.springfirebase.entity.AppUser;

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
}
