package com.example.nirmal.LibraryManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentDataActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button logout, viewbtn;
    DatabaseReference databaseBooks;
    ArrayList<String> bookNameList;
    ArrayList<String> autherNameList;

    ListView listViewBooks;
    List<com.example.nirmal.LibraryManager.Books> booksList;
    ProgressBar progress;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_student_data);
        progress = findViewById(R.id.progress);
        databaseBooks = FirebaseDatabase.getInstance().getReference("library");
        listViewBooks = findViewById(R.id.listviewBook);
        booksList = new ArrayList<>();
        firebaseAuth = FirebaseAuth.getInstance();
        bookNameList = new ArrayList<>();
        autherNameList = new ArrayList<>();
        progress.setVisibility(ProgressBar.VISIBLE);
    }

    private void Logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(StudentDataActivity.this, MainActivity.class));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search: {
                super.onSearchRequested();
                return true;
            }
            case R.id.logoutMenu: {
                Logout();
            }
            case R.id.aboutMenu: {
                //Do activity for this about page
                Intent intent = new Intent(StudentDataActivity.this, AboutUsActivity.class);

            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseBooks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                booksList.clear();

                for (DataSnapshot bookSnapshot : dataSnapshot.getChildren()) {
                    Books books = bookSnapshot.getValue(Books.class);

                    booksList.add(books);
                    progress.setVisibility(ProgressBar.GONE);
                }

                BookList adapter = new BookList(StudentDataActivity.this, booksList);
                listViewBooks.setAdapter(adapter);

                listViewBooks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(StudentDataActivity.this, ItemDetailsActivity.class);
                        intent.putExtra("Book Name", listViewBooks.getItemAtPosition(position).toString());
                        startActivity(intent);

                    }

                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    public void newpage(View v) {
//        // does something very interesting
//        Intent intent = new Intent(StudentDataActivity.this,ItemDetailsActivity.class);
//        startActivity(intent);
//    }
}

