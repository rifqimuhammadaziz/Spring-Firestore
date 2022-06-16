package rifqimuhammadaziz.springfirebase.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import rifqimuhammadaziz.springfirebase.entity.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CategoryService {

    public Category create(Category category) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> create = dbFirestore
                .collection("categories")
                .document(category.getCategoryId())
                .set(category);

        return category;
    }

    public List<Category> findAllCategory() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        final Query query = dbFirestore.collection("categories");
        final ApiFuture<QuerySnapshot> data = query.get();

        List<QueryDocumentSnapshot> documents = data.get().getDocuments();
        List<Category> categories = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.toObject(Category.class).getName());
            categories.add(document.toObject(Category.class));
        }

        return categories;
    }
}
