package lk.javainstitute.admintranspo;

//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//
//public class AdminLoginActivity extends AppCompatActivity {
//
//    private EditText editTextEmail;
//    private EditText editTextPassword;
//    private Button buttonLogin;
//
//    private FirebaseAuth firebaseAuth;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_admin_login);
//
//        editTextEmail = findViewById(R.id.editTextEmail);
//        editTextPassword = findViewById(R.id.editTextPassword);
//        buttonLogin = findViewById(R.id.buttonLogin);
//
//        firebaseAuth = FirebaseAuth.getInstance();
//
//        buttonLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String email = editTextEmail.getText().toString().trim();
//                String password = editTextPassword.getText().toString().trim();
//
//                // Authenticate the user with Firebase
//                firebaseAuth.signInWithEmailAndPassword(email, password)
//                        .addOnCompleteListener(AdminLoginActivity.this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//                                    // Authentication successful
//                                    FirebaseUser user = firebaseAuth.getCurrentUser();
//                                    // Add your logic for successful login
//                                } else {
//                                    // If authentication fails, display a message to the user.
//                                    // You can customize this based on your requirements.
//                                    // For example, you can show a toast message or update a TextView.
//                                }
//                            }
//                        });
//            }
//        });
//
//
//    }
//}

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdminLoginActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = editTextEmail.getText().toString().trim();
                final String password = editTextPassword.getText().toString().trim();

                // Check if the email and password exist in the adminProfile collection
                firestore.collection("AdminProfile").document(email).get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // Email exists in adminProfile, now check the password
                                        String storedPassword = document.getString("password");
                                        if (storedPassword.equals(password)) {
                                            // Password is correct, proceed with Firebase authentication
                                            firebaseAuth.signInWithEmailAndPassword(email, password)
                                                    .addOnCompleteListener(AdminLoginActivity.this, new OnCompleteListener<AuthResult>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                                            if (task.isSuccessful()) {
                                                                // Authentication successful
                                                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                                                Toast.makeText(AdminLoginActivity.this, "Login Successful!",
                                                                        Toast.LENGTH_SHORT).show();

                                                                startActivity(new Intent(AdminLoginActivity.this, MainActivity.class));

                                                            } else {
                                                                // If authentication fails, display a message to the user.
                                                                Toast.makeText(AdminLoginActivity.this, "Authentication failed.",
                                                                        Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });
                                        } else {
                                            // Incorrect password, display a message to the user
                                            Toast.makeText(AdminLoginActivity.this, "Incorrect password.",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        // Email does not exist in adminProfile, display a message to the user
                                        Toast.makeText(AdminLoginActivity.this, "Email not found.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    // An error occurred while checking the adminProfile collection
                                    Toast.makeText(AdminLoginActivity.this, "Error checking adminProfile.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
