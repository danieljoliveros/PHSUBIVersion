package com.example.phsubiversion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    Button SUDialog;

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String uN = "123";
    private String pw = "123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        SUDialog = (Button) findViewById(R.id.sign_up_button);

        SUDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUpDialog.class);
                startActivity(intent);
            }
        });

        validUser(uN, pw);

    }


    public void validUser (String userName, String password)
    {
        DatabaseReference ref = database.getReference("Persons");
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String vpw = dataSnapshot.child(uN + "/password").getValue().toString();
                if(vpw.equals(pw))
                {

                }
                else
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Invalid login info";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void CreateUser(String userName, String password)
    {
        DatabaseReference ref = database.getReference("Persons");
        Persons user = new Persons(userName, password);
        ref.child(userName).setValue(user);
    }


}