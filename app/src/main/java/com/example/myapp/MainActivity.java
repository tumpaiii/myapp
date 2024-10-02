package com.example.myapp;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.regex.Pattern;
public class MainActivity extends AppCompatActivity {
    private EditText etUsername, etEmail, etMobile, etId, etPassword, etConfirmPassword;
    // Updated regex patterns
    private Pattern namePattern = Pattern.compile("^[a-zA-Z.]+$");
    private Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@(gmail|yahoo|outlook)\\.com$");
    private Pattern mobilePattern = Pattern.compile("^(017|018|019)[0-9]{8}$");
    private Pattern idPattern = Pattern.compile("^01822[a-zA-Z0-9]+$");
    private Pattern passwordPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,20}$");
    private Button btnLogin, btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsername = findViewById(R.id.et_sign_in_username);
        etEmail = findViewById(R.id.et_sign_in_email);
        etMobile = findViewById(R.id.et_sign_in_mobile);
        etId = findViewById(R.id.et_sign_in_id);
        etPassword = findViewById(R.id.et_sign_in_password);
        etConfirmPassword = findViewById(R.id.et_sign_in_confirm_password);
        btnLogin = findViewById(R.id.btn_login);
        btnSignIn = findViewById(R.id.btn_sign_in);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String email = etEmail.getText().toString();
                String mobile = etMobile.getText().toString();
                String id = etId.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();
                if (validateInput(username, email, mobile, id, password, confirmPassword)) {
                    Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Login Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private boolean validateInput(String username, String email, String mobile, String id, String password, String confirmPassword) {
        if (TextUtils.isEmpty(username) || !namePattern.matcher(username).matches()) {
            etUsername.setError("Username can only contain letters and periods.");
            return false;
        }
        if (TextUtils.isEmpty(email) || !emailPattern.matcher(email).matches()) {
            etEmail.setError("Invalid email format. Use a valid gmail, yahoo, or outlook email.");
            return false;
        }
        if (TextUtils.isEmpty(mobile) || !mobilePattern.matcher(mobile).matches()) {
            etMobile.setError("Mobile number must start with 017, 018, or 019 and be 11 digits long.");
            return false;
        }
        if (TextUtils.isEmpty(id) || !idPattern.matcher(id).matches()) {
            etId.setError("ID must start with 01822 and only contain alphanumeric characters.");
            return false;
        }
        if (TextUtils.isEmpty(password) || !passwordPattern.matcher(password).matches()) {
            etPassword.setError("Password must be 6-20 characters long, contain at least one digit, one uppercase letter, and one lowercase letter.");
            return false;
        }
        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Passwords do not match.");
            return false;
        }
        return true;
    }
}

