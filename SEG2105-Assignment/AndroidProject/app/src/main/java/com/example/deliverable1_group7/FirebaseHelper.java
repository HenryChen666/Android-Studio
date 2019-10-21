package com.example.deliverable1_group7;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelper {
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;

    public FirebaseHelper (){
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
    }

    //firebase not fully implemented yet so commented out
    /*public boolean signUp(String username, String email, String password){
        final User mUser = new User(username, email, password);

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this,
            (task) -> {
                if (task.isSuccessful()){
                    mDatabase.getReference("Users").child(mAuth.getCurrentUser.getUid()).setValue(mUser).addOnCompleteListener( (task) -> {
                            {
                                if (task.isSuccessful()){
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                    });
                } else {
                    return false;
                }
            });
        return true;
    }*/
}
