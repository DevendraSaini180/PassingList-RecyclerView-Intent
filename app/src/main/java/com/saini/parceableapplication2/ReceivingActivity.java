package com.saini.parceableapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ReceivingActivity extends AppCompatActivity {

    TextView mAuthor, mId, mUrl;
    ArrayList<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recieving);

        viewInitialization();


        Intent intent = getIntent();
        mList = (ArrayList<String>) intent.getSerializableExtra("ApiData");
        mAuthor.setText(mList.get(1));
        mId.setText(mList.get(0));
        mUrl.setText(mList.get(2));
        Toast.makeText(this, "Data received from adapter", Toast.LENGTH_SHORT).show();

    }

    public void viewInitialization() {
        mAuthor = findViewById(R.id.textViewAuthorIntent);
        mId = findViewById(R.id.textViewIdIntent);
        mUrl = findViewById(R.id.textViewurlIntent);
    }
}