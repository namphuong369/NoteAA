package com.nam.note;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ContentActivity extends AppCompatActivity {
    TextView t1;
    EditText ed_1;
    NoteDbHelper noteDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        t1=findViewById(R.id.tv1);
        noteDbHelper = new NoteDbHelper(ContentActivity.this);
        ed_1=findViewById(R.id.ed_1);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ed_1.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Bạn chưa nhập nội dung",Toast.LENGTH_SHORT).show();
                }else{
                    noteDbHelper.addTitle(new Item(ed_1.getText().toString().trim()));
                    Toast.makeText(getApplicationContext(),"Thêm mới thành công",Toast.LENGTH_SHORT).show();

                    finish();
                }
            }
        });


    }
}