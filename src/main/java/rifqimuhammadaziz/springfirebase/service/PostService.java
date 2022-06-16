package rifqimuhammadaziz.springfirebase.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import rifqimuhammadaziz.springfirebase.dto.PostResponse;
import rifqimuhammadaziz.springfirebase.entity.Category;
import rifqimuhammadaziz.springfirebase.entity.Post;

import java.util.ArrayList;
import java.util.Arrays;
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

    public List<PostResponse> findAllPosts() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        final Query query = dbFirestore.collection("posts");
        final ApiFuture<QuerySnapshot> querySnapshot = query.get();

        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        List<PostResponse> posts = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId());
            posts.add(document.toObject(PostResponse.class));
        }

        return posts;
    }

    public List<Post> findAllPostsByCategory(String postCategory) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        final Query query = dbFirestore.collection("posts").whereEqualTo("category", postCategory);
        final ApiFuture<QuerySnapshot> querySnapshot = query.get();

        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        List<Post> posts = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId());
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
