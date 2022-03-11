package com.example.Lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Button loginBtn;
    EditText loginField, passwordField;

    DBHelper dbHelper;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginField = (EditText) findViewById(R.id.loginField);
        passwordField = (EditText) findViewById(R.id.passwordField);

        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);

        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                Cursor logCursor = database.query(DBHelper.TABLE_USERS, null, null, null, null, null, null);

                boolean logged = false;
                if (logCursor.moveToFirst()) {
                    int userIndex = logCursor.getColumnIndex(DBHelper.KEY_LOGIN);
                    int nameIndex = logCursor.getColumnIndex(DBHelper.KEY_NAME);
                    int passwordIndex = logCursor.getColumnIndex(DBHelper.KEY_PASSWORD);
                    int dolgnost = logCursor.getColumnIndex(DBHelper.KEY_DOLGNOST);
                    do {
                        if ((loginField.getText().toString().equals(logCursor.getString(userIndex))) && (passwordField.getText().toString().equals(logCursor.getString(passwordIndex))) && (logCursor.getString(dolgnost).equals("admin"))) {
                            startActivity(new Intent(this, AdminActivity.class));
                            logged = true;
                        } else if ((loginField.getText().toString().equals(logCursor.getString(userIndex))) && (passwordField.getText().toString().equals(logCursor.getString(passwordIndex))) && (logCursor.getString(dolgnost).equals("patient"))) {
                            Intent intent = new Intent(this, UserActivity.class);
                            intent.putExtra("name", logCursor.getString(nameIndex));
                            startActivity(intent);
                            logged = true;
                        }
                    } while (logCursor.moveToNext());
                }
                logCursor.close();
                if (!logged)
                    Toast.makeText(this, "Введённая комбинация логина и пароля не была найдена.", Toast.LENGTH_LONG).show();
                break;
        }
    }
}