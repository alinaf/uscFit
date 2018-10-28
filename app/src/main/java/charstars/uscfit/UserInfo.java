package charstars.uscfit;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserInfo {
    private static String firstName = "Tianqin";
    private static String email;
    private static int age = 23;
    private static double weight = 150.5;
    private static double height = 72;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public UserInfo() {
    }

    public UserInfo(boolean fromDb) {
        if (fromDb){
            return;
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        DatabaseReference myRef = database.getReference("Users"); // will not be null
        DatabaseReference myRef1 = myRef.child(currentUser.getUid());
        if (myRef1 == null) { // will be null the first time
            return;
        }
        DatabaseReference myRef2 = myRef1.child("UserInfo");

        // Read from the database
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                UserInfo uinfo = dataSnapshot.getValue(UserInfo.class);
                if (uinfo != null){
                    age = uinfo.getAge();
                    weight = uinfo.getWeight();
                    height = uinfo.getHeight();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
               Log.w("tag", "Failed to read value.", error.toException());
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


}
