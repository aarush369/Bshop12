package com.example.bshop1.Utils;



import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseUtils {

    public  static String currentUserId(){

        return FirebaseAuth.getInstance().getUid();

    }
public static   boolean isLoggedIn(){
    return currentUserId() != null;
}






    public static DocumentReference currentUserDetails(){

        return FirebaseFirestore.getInstance().collection("user").document(currentUserId());

    }


}