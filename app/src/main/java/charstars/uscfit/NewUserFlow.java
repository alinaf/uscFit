package charstars.uscfit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class NewUserFlow extends AppCompatActivity {

    private Button mSelectImage;
    private StorageReference mStorage;
    private static final int GALLERY_INTENT = 2;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_flow);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        mProgressDialog = new ProgressDialog(this);

        mStorage = FirebaseStorage.getInstance().getReference();
        mSelectImage = (Button) findViewById(R.id.select_image);

        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mProgressDialog.setMessage("Uploading...");
        mProgressDialog.show();
        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            StorageReference filePath = mStorage.child("ProfilePics").child(currentUser.getUid()).child("profilePic");
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(NewUserFlow.this, "Uploaded!", Toast.LENGTH_SHORT).show();
                    mProgressDialog.dismiss();
                }
            });
        }
    }

    /** Called when the user touches the button */
    public void sendMessage(View view) {
        String ageString = ((TextView)findViewById(R.id.input_age)).getText().toString();
        String heightString = ((TextView)findViewById(R.id.input_height)).getText().toString();
        String weightString = ((TextView)findViewById(R.id.input_weight)).getText().toString();

        UserInfo userInfo = new UserInfo();
        if (!updateUserInfo(userInfo, ageString, heightString, weightString)) {
            DisplayToast();
            return;
        }

        writeToDatabase(userInfo);
        Intent i;
        i = new Intent(NewUserFlow.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public boolean updateUserInfo(UserInfo userInfo, String ageString, String heightString, String weightString){
        int age;
        double height, weight;
        try {
            age = Integer.parseInt(ageString);
            height = Double.parseDouble(heightString);
            weight = Double.parseDouble(weightString);
        }

        catch (NumberFormatException nfe) {
            return false;
        }

        userInfo.setAge(age);
        userInfo.setHeight(height);
        userInfo.setWeight(weight);
        return true;
    }

    public void writeToDatabase(UserInfo userInfo) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        DatabaseReference myRef = database.getReference("Users");
        DatabaseReference myRef1 = myRef.child(currentUser.getUid());
        DatabaseReference myRef2 = myRef1.child("UserInfo");
        myRef2.setValue(userInfo);
    }

    public void DisplayToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout;

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);

        layout = inflater.inflate(R.layout.goalfail,null);
        toast.setView(layout);
        toast.show();
    }
}
