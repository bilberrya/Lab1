package com.example.Lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class BasenameActivity extends AppCompatActivity implements View.OnClickListener{

    Button Add, Clear, BackBtn, BaseresultsBtn;
    EditText etName, etLogin, etPassword, etDolgnost;

    DBHelper dbHelper;
    SQLiteDatabase database;
    ContentValues contentValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basename);

        Add = (Button) findViewById(R.id.add);
        Add.setOnClickListener(this);

        Clear = (Button) findViewById(R.id.clear);
        Clear.setOnClickListener(this);

        BackBtn = (Button) findViewById(R.id.back);
        BackBtn.setOnClickListener(this);

        BaseresultsBtn = (Button) findViewById(R.id.baseresults);
        BaseresultsBtn.setOnClickListener(this);

        etName = (EditText) findViewById(R.id.name);
        etLogin = (EditText) findViewById(R.id.login);
        etPassword = (EditText) findViewById(R.id.password);
        etDolgnost = (EditText) findViewById(R.id.dolgnost);

        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();

        UpdateTable();
    }

    public void UpdateTable() {
        Cursor cursor = database.query(DBHelper.TABLE_USERS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID_USER);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
            int loginIndex = cursor.getColumnIndex(DBHelper.KEY_LOGIN);
            int passwordIndex = cursor.getColumnIndex(DBHelper.KEY_PASSWORD);
            int dolgnostIndex = cursor.getColumnIndex(DBHelper.KEY_DOLGNOST);
            TableLayout dbOutput = findViewById(R.id.output3);
            dbOutput.removeAllViews();
            do {
                TableRow dbOutputRow = new TableRow(this);
                dbOutputRow.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);

                TextView outputName = new TextView(this);
                params.weight = 5.0f;
                outputName.setLayoutParams(params);
                outputName.setText(cursor.getString(nameIndex));
                dbOutputRow.addView(outputName);

                TextView outputLogin = new TextView(this);
                params.weight = 5.0f;
                outputLogin.setLayoutParams(params);
                outputLogin.setText(cursor.getString(loginIndex));
                dbOutputRow.addView(outputLogin);

                TextView outputPassword = new TextView(this);
                params.weight = 5.0f;
                outputPassword.setLayoutParams(params);
                outputPassword.setText(cursor.getString(passwordIndex));
                dbOutputRow.addView(outputPassword);

                TextView outputDolgnost = new TextView(this);
                params.weight = 4.0f;
                outputDolgnost.setLayoutParams(params);
                outputDolgnost.setText(cursor.getString(dolgnostIndex));
                dbOutputRow.addView(outputDolgnost);

                Button btnDelete = new Button(this);
                btnDelete.setOnClickListener(this);
                params.weight = 1.0f;
                btnDelete.setLayoutParams(params);
                btnDelete.setText("Удалить");
                btnDelete.setId(cursor.getInt(idIndex));
                dbOutputRow.addView(btnDelete);

                dbOutput.addView(dbOutputRow);

            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.back:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;

            case R.id.baseresults:
                Intent intent2 = new Intent(this, AdminActivity.class);
                startActivity(intent2);
                break;

            case R.id.add:
                String name = etName.getText().toString();
                String login = etLogin.getText().toString();
                String password = etPassword.getText().toString();
                String dolgnost = etDolgnost.getText().toString();

                contentValues = new ContentValues();

                contentValues.put(DBHelper.KEY_NAME, name);
                contentValues.put(DBHelper.KEY_LOGIN, login);
                contentValues.put(DBHelper.KEY_PASSWORD, password);
                contentValues.put(DBHelper.KEY_DOLGNOST, dolgnost);

                database.insert(DBHelper.TABLE_USERS, null, contentValues);
                UpdateTable();
                break;

            case R.id.clear:
                database.delete(DBHelper.TABLE_USERS, null, null);
                break;

            default:
                View outputDBRow = (View) v.getParent();
                ViewGroup outputDB = (ViewGroup) outputDBRow.getParent();

                outputDB.removeView(outputDBRow);
                outputDB.invalidate();

                database.delete(DBHelper.TABLE_USERS, DBHelper.KEY_ID_USER + " = ?", new String[]{String.valueOf((v.getId()))});

                contentValues = new ContentValues();
                Cursor cursorUpdater = database.query(DBHelper.TABLE_USERS, null, null, null, null, null, null);
                if (cursorUpdater.moveToFirst()) {
                    int idIndex = cursorUpdater.getColumnIndex(DBHelper.KEY_ID_USER);
                    int nameIndex = cursorUpdater.getColumnIndex(DBHelper.KEY_NAME);
                    int loginIndex = cursorUpdater.getColumnIndex(DBHelper.KEY_LOGIN);
                    int passwordIndex = cursorUpdater.getColumnIndex(DBHelper.KEY_PASSWORD);
                    int dolgnostIndex = cursorUpdater.getColumnIndex(DBHelper.KEY_DOLGNOST);
                    int realID = 1;
                    do {
                        if (cursorUpdater.getInt(idIndex) > realID) {
                            contentValues.put(DBHelper.KEY_ID_RESULT, realID);
                            contentValues.put(DBHelper.KEY_NAME2, cursorUpdater.getString(nameIndex));
                            contentValues.put(DBHelper.KEY_SERVICE, cursorUpdater.getString(loginIndex));
                            contentValues.put(DBHelper.KEY_RESULT, cursorUpdater.getString(passwordIndex));
                            contentValues.put(DBHelper.KEY_DATE, cursorUpdater.getString(dolgnostIndex));
                            database.replace(DBHelper.TABLE_RESULTS, null, contentValues);
                        }
                        realID++;
                    } while (cursorUpdater.moveToNext());
                    if (cursorUpdater.moveToLast() && v.getId() != realID) {
                        database.delete(DBHelper.TABLE_USERS, DBHelper.KEY_ID_USER + " = ?", new String[]{cursorUpdater.getString(idIndex)});
                    }
                    UpdateTable();
                }
                break;
        }
    }
}