package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {

    private RecyclerView booksRecView;
    private BookRecViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);


        adapter = new BookRecViewAdapter(this);
        booksRecView = findViewById(R.id.booksReclycerView);


        booksRecView.setAdapter(adapter);
        booksRecView.setLayoutManager(new GridLayoutManager(this, 2));

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1, "1Q84", "Harakumi", 1350, "https://alittleblogofbooks.files.wordpress.com/2012/06/1q84.jpg", "A work of maddening brillance", "long description"));


        adapter.setBooks(books);


    }
}