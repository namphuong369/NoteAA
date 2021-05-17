package com.nam.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    EditText e1;
    TextView t1,t2;
    NoteDbHelper noteDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        e1=findViewById(R.id.ed_1);
        t1=findViewById(R.id.tv2);
        t2=findViewById(R.id.tv3);
        Intent intent = getIntent();
        Item item = intent.getParcelableExtra("LIST");
        e1.setText(item.getContent());
        noteDbHelper=NoteDbHelper.getInstance(getApplicationContext());
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e1.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Bạn chưa nhập nội dung",Toast.LENGTH_SHORT).show();
                }else{
                    noteDbHelper.updateTitle(String.valueOf(item.getId()),new Item(e1.getText().toString().trim()));
                    Toast.makeText(getApplicationContext(),"Cập nhật thành công",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noteDbHelper.deleteOneTitle(String.valueOf(item.getId()));
                Toast.makeText(getApplicationContext(),"Xóa thành công",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}