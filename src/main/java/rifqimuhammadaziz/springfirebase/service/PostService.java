package rifqimuhammadaziz.springfirebase.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import rifqimuhammadaziz.springfirebase.entity.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class PostService {

    @Autowired
    private Environment env;

    public Post create(Post post) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore
                .collection(env.getProperty("firestore.collection.post"))
                .document(post.getPostId())
                .set(post);

        return post;
    }

    public List<Post> findAllPosts() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        // asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("posts").get();

        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Post> posts = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.toObject(Post.class).getTitle());
            posts.add(document.toObject(Post.class));
        }

        return posts;
    }

    public Post findByPostId(String postId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore
                .collection(env.getProperty("firestore.collection.post"))
                .document(postId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        Post post;
        if (document.exists()) {
            post = document.toObject(Post.class);
            return post;
        }
        return null;
    }

    public String updateByPostId(Post post) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore
                .collection(env.getProperty("firestore.collection.post"))
                .document(post.getPostId())
                .set(post);

        return "Post with id " + post.getPostId() + " successfully updated at " + collectionApiFuture.get().getUpdateTime();
    }

    public String deleteByPostId(String postId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore
                .collection(env.getProperty("firestore.collection.post"))
                .document(postId)
                .delete();

        return "Post with id " + postId + " successfully deleted at " + writeResult.get().getUpdateTime();
    }
}
