package com.example.calculator;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean fnum=true;
    String sym="";
    float summary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button num1 = findViewById(R.id.num1);
        Button num2 = findViewById(R.id.num2);
        Button num3 = findViewById(R.id.num3);
        Button num4 = findViewById(R.id.num4);
        Button num5 = findViewById(R.id.num5);
        Button num6 = findViewById(R.id.num6);
        Button num7 = findViewById(R.id.num7);
        Button num8 = findViewById(R.id.num8);
        Button num9 = findViewById(R.id.num9);
        Button num0 = findViewById(R.id.num0);
        final Button plus = findViewById(R.id.sym_plus);
        Button minus = findViewById(R.id.sym_minus);
        Button ymn = findViewById(R.id.sym_ymn);
        Button del = findViewById(R.id.sym_del);
        Button sum = findViewById(R.id.sym_equal);
        Button delete = findViewById(R.id.sym_C);
        final TextView first_num = findViewById(R.id.first_num);
        final TextView second_num= findViewById(R.id.second_num);
        final TextView result=findViewById(R.id.result);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnum=!fnum;
                sym = "plus";
            }
        });

        minus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                fnum = !fnum;
                sym = "minus";
            }
        });

        ymn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                fnum = !fnum;
                sym = "ymn";
            }
        });

        del.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                fnum = !fnum;
                sym = "del";
            }
        });

        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                first_num.setText("");
                second_num.setText("");
                result.setText("");
            }
        });

        num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fnum){
                    String first_num_text=first_num.getText().toString();
                    first_num_text+="1";
                    first_num.setText(first_num_text);
                }else{
                    String second_num_text=second_num.getText().toString();
                    second_num_text+="1";
                    second_num.setText(second_num_text);
                }
            }
        });
        num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fnum){
                    String first_num_text=first_num.getText().toString();
                    first_num_text+="2";
                    first_num.setText(first_num_text);
                }else{
                    String second_num_text=second_num.getText().toString();
                    second_num_text+="2";
                    second_num.setText(second_num_text);
                }
            }
        });
        num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fnum){
                    String first_num_text=first_num.getText().toString();
                    first_num_text+="3";
                    first_num.setText(first_num_text);
                }else{
                    String second_num_text=second_num.getText().toString();
                    second_num_text+="3";
                    second_num.setText(second_num_text);
                }
            }
        });
        num4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fnum){
                    String first_num_text=first_num.getText().toString();
                    first_num_text+="4";
                    first_num.setText(first_num_text);
                }else{
                    String second_num_text=second_num.getText().toString();
                    second_num_text+="4";
                    second_num.setText(second_num_text);
                }
            }
        });
        num5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fnum){
                    String first_num_text=first_num.getText().toString();
                    first_num_text+="5";
                    first_num.setText(first_num_text);
                }else{
                    String second_num_text=second_num.getText().toString();
                    second_num_text+="5";
                    second_num.setText(second_num_text);
                }
            }
        });
        num6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fnum){
                    String first_num_text=first_num.getText().toString();
                    first_num_text+="6";
                    first_num.setText(first_num_text);
                }else{
                    String second_num_text=second_num.getText().toString();
                    second_num_text+="6";
                    second_num.setText(second_num_text);
                }
            }
        });
        num7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fnum){
                    String first_num_text=first_num.getText().toString();
                    first_num_text+="7";
                    first_num.setText(first_num_text);
                }else{
                    String second_num_text=second_num.getText().toString();
                    second_num_text+="7";
                    second_num.setText(second_num_text);
                }
            }
        });
        num8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fnum){
                    String first_num_text=first_num.getText().toString();
                    first_num_text+="8";
                    first_num.setText(first_num_text);
                }else{
                    String second_num_text=second_num.getText().toString();
                    second_num_text+="8";
                    second_num.setText(second_num_text);
                }
            }
        });
        num9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fnum){
                    String first_num_text=first_num.getText().toString();
                    first_num_text+="9";
                    first_num.setText(first_num_text);
                }else{
                    String second_num_text=second_num.getText().toString();
                    second_num_text+="9";
                    second_num.setText(second_num_text);
                }
            }
        });
        num0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fnum){
                    String first_num_text=first_num.getText().toString();
                    first_num_text+="0";
                    first_num.setText(first_num_text);
                }else{
                    String second_num_text=second_num.getText().toString();
                    second_num_text+="0";
                    second_num.setText(second_num_text);
                }
            }
        });

        sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float first_number=Float.valueOf(first_num.getText().toString());
                float second_number=Float.valueOf(second_num.getText().toString());

                switch (sym)
                {
                    default:
                        break;
                    case "plus":
                        summary = first_number+second_number;
                        break;
                    case "minus":
                        summary = first_number-second_number;
                        break;
                    case "ymn":
                        summary = first_number*second_number;
                        break;
                    case "del":
                        summary = first_number/second_number;
                        break;
                }
                result.setText(String.valueOf(summary));
                fnum=true;
            }
        });
    }
}