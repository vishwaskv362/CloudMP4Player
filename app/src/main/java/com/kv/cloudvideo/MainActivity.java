package com.kv.cloudvideo;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    memberAdapter adapter;
    DatabaseReference mbase;
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    ImageButton img;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbase = FirebaseDatabase.getInstance("https://cloudvideo-780c7-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Video");
        recyclerView = findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<members> options
                = new FirebaseRecyclerOptions.Builder<members>()
                .setQuery(mbase, members.class)
                .build();
        adapter = new memberAdapter(options);
        recyclerView.setAdapter(adapter);

    }



    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu,menu);

        MenuItem item=menu.findItem(R.id.search1);

        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    private void processsearch(String s)
    {
        FirebaseRecyclerOptions<members> options =
               new FirebaseRecyclerOptions.Builder<members>()
                .setQuery(FirebaseDatabase.getInstance("https://cloudvideo-780c7-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Video").orderByChild("search").startAt(s).endAt(s+"\uf8ff"), members.class)
                .build();
        adapter=new memberAdapter(options);
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                about();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
        private void about() {

            setContentView(R.layout.activity_about);
        }
}
