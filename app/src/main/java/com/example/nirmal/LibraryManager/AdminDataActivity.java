package com.example.nirmal.LibraryManager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminDataActivity extends AppCompatActivity {


    private EditText BookTitle, Author;
    private Button addBook;
    private TextView BookTitles, Authors;
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_data);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mDatabase = FirebaseDatabase.getInstance().getReference("library");

        BookTitle = findViewById(R.id.etBookTitle);
        Author = findViewById(R.id.etAuthor);
        addBook = findViewById(R.id.btnAdd);

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Bookname = BookTitle.getText().toString();
                String BookWriter = Author.getText().toString();

                if (!TextUtils.isEmpty(Bookname) && !TextUtils.isEmpty(BookWriter)) {

                    String id = mDatabase.push().getKey();
// creating user object

                    Books book = new Books(Bookname, BookWriter);


// pushing user to 'users' node using the userId
                    mDatabase.child(id).setValue(book);


                    Toast.makeText(AdminDataActivity.this, "Book Added", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AdminDataActivity.this, "Enter all the details", Toast.LENGTH_LONG).show();
                }

            }
        });

    }


}