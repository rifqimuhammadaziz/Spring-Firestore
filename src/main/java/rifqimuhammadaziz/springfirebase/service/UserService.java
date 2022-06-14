package rifqimuhammadaziz.springfirebase.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import rifqimuhammadaziz.springfirebase.entity.User;

import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    // Get properties file
    @Autowired
    private Environment env;

    public String create(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore
                .collection(env.getProperty("firestore.collection.user"))
                .document(user.getDocumentId())
                .set(user);

        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public User findByDocumentId(String documentId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore
                .collection(env.getProperty("firestore.collection.user"))
                .document(documentId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        User user;
        if (document.exists()) {
            user = document.toObject(User.class);
            return user;
        }
        return null;
    }

    public String updateByDocumentId(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore
                .collection(env.getProperty("firestore.collection.user"))
                .document(user.getDocumentId())
                .set(user);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public String deleteByDocumentId(String documentId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore
                .collection(env.getProperty("firestore.collection.user"))
                .document(documentId)
                .delete();

        return "User with documentId " + documentId + " successfully deleted at " + writeResult.get().getUpdateTime();
    }
}
