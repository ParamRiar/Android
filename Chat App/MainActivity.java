
// Submitted By:
// Jaspreet kaur Student Id: C0709466
//Paramjeet kaur Student Id: C0710778
//Koushal Puliyala Student Id: C0709212




package com.example.macstudent.chitchat;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    //variables
    FirebaseDatabase messagedata;
    DatabaseReference messagenode;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messagedata = FirebaseDatabase.getInstance();
        messagenode = messagedata.getReference();


    }

    public void btnclicked(View v) {
        // Log.d("prabh", "btnclicked");
        EditText text = (EditText) findViewById(R.id.messageText);
        String m1 = text.getText().toString();
        Date date = new Date();
        SimpleDateFormat d1 = new SimpleDateFormat("yyyy.MM.dd h:mma");
        String formdate = d1.format(date);
        message m = new message(m1, formdate);
        messagenode.child("ChitChat").push().setValue(m);


        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {


                // A new comment has been added, add it to the displayed list
                message message1 = dataSnapshot.getValue(message.class);
                //String a = messagenode.push().getKey();

                Log.d("Prabh",message1.msg);
                //Log.d("Prabh",message1.d);

                TextView txt = (TextView) findViewById(R.id.txtview);
                txt.setText(message1.msg);


               // TextView txt1 = (TextView) findViewById(R.id.txtview1);
               // txt1.setText(message1.d);
                // ...
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        messagenode.child("ChitChat").addChildEventListener(childEventListener);
    }
    public void login(View v){

    }
}