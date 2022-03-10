package com.example.Lab;

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

import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener{
    Button Add, Clear, Upd, BackBtn, BasenameBtn;
    EditText etId, etName, etService, etResult, etDate, etPrice;

    DBHelper dbHelper;
    SQLiteDatabase database;
    ContentValues contentValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Add = (Button) findViewById(R.id.add);
        Add.setOnClickListener(this);

        Upd = (Button) findViewById(R.id.upd);
        Upd.setOnClickListener(this);

        Clear = (Button) findViewById(R.id.clear);
        Clear.setOnClickListener(this);

        BackBtn = (Button) findViewById(R.id.back);
        BackBtn.setOnClickListener(this);

        BasenameBtn = (Button) findViewById(R.id.basename);
        BasenameBtn.setOnClickListener(this);

        etId = (EditText) findViewById(R.id.id);
        etName = (EditText) findViewById(R.id.name);
        etService = (EditText) findViewById(R.id.service);
        etResult = (EditText) findViewById(R.id.result);
        etDate = (EditText) findViewById(R.id.date);
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

                TextView outputId = new TextView(this);
                params.weight = 1.0f;
                outputId.setLayoutParams(params);
                outputId.setText(cursor.getString(idIndex));
                dbOutputRow.addView(outputId);

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

            case R.id.basename:
                Intent intent2 = new Intent(this, BasenameActivity.class);
                startActivity(intent2);
                break;

            case R.id.add:
                String name = etName.getText().toString();
                String service = etService.getText().toString();
                String result = etResult.getText().toString();
                String date = etDate.getText().toString();
                String price = etPrice.getText().toString();

                contentValues = new ContentValues();

                contentValues.put(DBHelper.KEY_NAME2, name);
                contentValues.put(DBHelper.KEY_SERVICE, service);
                contentValues.put(DBHelper.KEY_RESULT, result);
                contentValues.put(DBHelper.KEY_DATE, date);
                contentValues.put(DBHelper.KEY_PRICE, price);

                database.insert(DBHelper.TABLE_RESULTS, null, contentValues);
                UpdateTable();
                break;

            case R.id.upd:
                String name2 = etName.getText().toString();
                String service2 = etService.getText().toString();
                String result2 = etResult.getText().toString();
                String date2 = etDate.getText().toString();
                String price2 = etPrice.getText().toString();

                contentValues = new ContentValues();

                contentValues.put(DBHelper.KEY_NAME2, name2);
                contentValues.put(DBHelper.KEY_SERVICE, service2);
                contentValues.put(DBHelper.KEY_RESULT, result2);
                contentValues.put(DBHelper.KEY_DATE, date2);
                contentValues.put(DBHelper.KEY_PRICE, price2);

                database.update(DBHelper.TABLE_RESULTS, contentValues, DBHelper.KEY_ID_RESULT + " = ?", new String[]{etId.getText().toString()});
                UpdateTable();
                break;

            case R.id.clear:
                database.delete(DBHelper.TABLE_RESULTS, null, null);
                break;

            default:
                View outputDBRow = (View) v.getParent();
                ViewGroup outputDB = (ViewGroup) outputDBRow.getParent();

                outputDB.removeView(outputDBRow);
                outputDB.invalidate();

                database.delete(DBHelper.TABLE_RESULTS, DBHelper.KEY_ID_RESULT + " = ?", new String[]{String.valueOf((v.getId()))});

                contentValues = new ContentValues();
                Cursor cursorUpdater = database.query(DBHelper.TABLE_RESULTS, null, null, null, null, null, null);
                if (cursorUpdater.moveToFirst()) {
                    int idIndex = cursorUpdater.getColumnIndex(DBHelper.KEY_ID_RESULT);
                    int nameIndex = cursorUpdater.getColumnIndex(DBHelper.KEY_NAME2);
                    int serviceIndex = cursorUpdater.getColumnIndex(DBHelper.KEY_SERVICE);
                    int resultIndex = cursorUpdater.getColumnIndex(DBHelper.KEY_RESULT);
                    int dateIndex = cursorUpdater.getColumnIndex(DBHelper.KEY_DATE);
                    int priceIndex = cursorUpdater.getColumnIndex(DBHelper.KEY_PRICE);
                    int realID = 1;
                    do {
                        if (cursorUpdater.getInt(idIndex) > realID) {
                            contentValues.put(DBHelper.KEY_ID_RESULT, realID);
                            contentValues.put(DBHelper.KEY_NAME2, cursorUpdater.getString(nameIndex));
                            contentValues.put(DBHelper.KEY_SERVICE, cursorUpdater.getString(serviceIndex));
                            contentValues.put(DBHelper.KEY_RESULT, cursorUpdater.getString(resultIndex));
                            contentValues.put(DBHelper.KEY_DATE, cursorUpdater.getString(dateIndex));
                            contentValues.put(DBHelper.KEY_PRICE, cursorUpdater.getString(priceIndex));
                            database.replace(DBHelper.TABLE_RESULTS, null, contentValues);
                        }
                        realID++;
                    } while (cursorUpdater.moveToNext());
                    if (cursorUpdater.moveToLast() && v.getId() != realID) {
                        database.delete(DBHelper.TABLE_RESULTS, DBHelper.KEY_ID_RESULT + " = ?", new String[]{cursorUpdater.getString(idIndex)});
                    }
                    UpdateTable();
                }
                break;
        }
    }
}