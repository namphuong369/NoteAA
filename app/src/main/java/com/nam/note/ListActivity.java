package com.nam.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private NoteDbHelper dbHelper;
    List<Item> list=new ArrayList<>();
    ItemAdapter adapter;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView=findViewById(R.id.recycler);
        t1=findViewById(R.id.tv1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


    }

    @Override
    protected void onResume() {
        super.onResume();
        dbHelper = new NoteDbHelper(ListActivity.this);
        list=dbHelper.getAllTitle();
        if(list.isEmpty())
        {
            t1.setVisibility(View.VISIBLE);
        }else {
            t1.setVisibility(View.GONE);
        }
        adapter=new ItemAdapter(list,getApplicationContext());
        recyclerView.setAdapter(adapter);
        adapter.setItemClickListener(new ItemAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent=new Intent(getApplicationContext(),EditActivity.class);
                intent.putExtra("LIST",list.get(position));
                startActivity(intent);
            }
        });
    }
}