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
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity implements View.OnClickListener{

    Button Add, Clear, BackBtn;
    EditText etName, etService, etResult, etDate, etPrice;

    DBHelper dbHelper;
    SQLiteDatabase database;
    ContentValues contentValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Add = (Button) findViewById(R.id.add);
        Add.setOnClickListener(this);

        Clear = (Button) findViewById(R.id.clear);
        Clear.setOnClickListener(this);

        BackBtn = (Button) findViewById(R.id.back);
        BackBtn.setOnClickListener(this);

        etName = (EditText) findViewById(R.id.name);
        etService = (EditText) findViewById(R.id.login);
        etResult = (EditText) findViewById(R.id.password);
        etDate = (EditText) findViewById(R.id.dolgnost);
        etPrice = (EditText) findViewById(R.id.price);

        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();

        UpdateTable();
    }

    public void UpdateTable() {
        Cursor cursor = database.query(DBHelper.TABLE_RESULTS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID_RESULT);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME2);
            int serviceIndex = cursor.getColumnIndex(DBHelper.KEY_SERVICE);
            int resultIndex = cursor.getColumnIndex(DBHelper.KEY_RESULT);
            int dateIndex = cursor.getColumnIndex(DBHelper.KEY_DATE);
            int priceIndex = cursor.getColumnIndex(DBHelper.KEY_PRICE);
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

                TextView outputService = new TextView(this);
                params.weight = 5.0f;
                outputService.setLayoutParams(params);
                outputService.setText(cursor.getString(serviceIndex));
                dbOutputRow.addView(outputService);

                TextView outputResult = new TextView(this);
                params.weight = 2.0f;
                outputResult.setLayoutParams(params);
                outputResult.setText(cursor.getString(resultIndex));
                dbOutputRow.addView(outputResult);

                TextView outputDate = new TextView(this);
                params.weight = 4.0f;
                outputDate.setLayoutParams(params);
                outputDate.setText(cursor.getString(dateIndex));
                dbOutputRow.addView(outputDate);

                TextView outputPrice = new TextView(this);
                params.weight = 3.0f;
                outputPrice.setLayoutParams(params);
                outputPrice.setText(cursor.getString(priceIndex));
                dbOutputRow.addView(outputPrice);

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
        }
    }
}