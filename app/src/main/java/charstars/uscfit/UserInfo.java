package charstars.uscfit;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class UserInfo {
    private static String firstName = "";
    private static String email;
    private static int age = 0;
    private static double weight = 0;
    private static double height = 0;
    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public UserInfo() {
    }

    public UserInfo(boolean fromDb) {

        if (fromDb){
            return;
        }
        if (firstName != "") {
            return;
        }

        readData(new FirebaseCallback() {
            @Override
            public void onCallback(UserInfo userInfo) {
                Log.d("tag", firstName);
                firstName = userInfo.getFirstName();
                age = userInfo.getAge();
                weight = userInfo.getWeight();
                height = userInfo.getHeight();
            }
        });
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public static void updateDB(UserInfo userInfo) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        DatabaseReference myRef = database.getReference("Users");
        DatabaseReference myRef1 = myRef.child(currentUser.getUid());
        DatabaseReference myRef2 = myRef1.child("UserInfo");
        myRef2.setValue(userInfo);
    }

    private void readData(final FirebaseCallback firebaseCallback) {
        // otherwise, need to query!
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        email = currentUser.getEmail();
        DatabaseReference userListRef = database.getReference("Users"); // will not be null
        DatabaseReference currentUserRef = userListRef.child(currentUser.getUid());
        if (currentUserRef == null) { // will be null the first time
            return;
        }
        DatabaseReference userInfoRef = currentUserRef.child("UserInfo");
        // Read from the database

        userInfoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                UserInfo uinfo = dataSnapshot.getValue(UserInfo.class);
                if (uinfo != null){

                }
                firebaseCallback.onCallback(uinfo);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("tag", "Failed to read value.", error.toException());
            }
        });
    }

    private interface FirebaseCallback {
        void onCallback(UserInfo userInfo);
    }
}
