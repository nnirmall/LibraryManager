package com.example.nirmal.LibraryManager;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BookList extends ArrayAdapter<Books> {

    private Activity context;
    private List<Books> booksList;

    public BookList(Activity context, List<Books> booksList) {
        super(context, R.layout.list_item, booksList);
        this.context = context;
        this.booksList = booksList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_item, null, true);
        TextView textViewBook = listViewItem.findViewById(R.id.textViewBookName);
        TextView textViewAuthor = listViewItem.findViewById(R.id.textViewAuthor);

        Books books = booksList.get(position);
        textViewBook.setText(books.getBookName());
        textViewAuthor.setText(books.getAutherName());

        return listViewItem;
    }
}
