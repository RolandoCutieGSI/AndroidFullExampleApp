package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";

    private TextView txtBook, txtAuthor, txtPages, txtDescription;
    private Button btnAddToWantToRead, btnAddToAlreadyRead, btnAddToCurrentlyReading, btnAddToFavorite;
    private ImageView bookImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();
        //TODO:GET THE INCOMING DATA FROM THE RECYCLER VIEW

        //Para recibir el income intent
        Intent intent = getIntent();
        if (null != intent) {
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (bookId != -1) {
                Book incomingBook = Utils.getInstance().getBookById(bookId);
                if (incomingBook != null) {
                    setData(incomingBook);

                    //para validar si el libro esta en alguina de las otras listas y desactivar los botones


                }
            }
        }

    }

    private void setData(Book book) {
        txtBook.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getShortDesc());
        Glide.with(this).asBitmap().load(book.getImageUrl()).into(bookImage);
    }

    private void initViews() {
        txtAuthor = findViewById(R.id.textViewAuthorBookActBook);
        txtBook = findViewById(R.id.textViewBookNameActBook);
        txtPages = findViewById(R.id.textViewCantPagesBookActBook);
        txtDescription = findViewById(R.id.textView7);

        btnAddToAlreadyRead = findViewById(R.id.btnAddAllreadyRead);
        btnAddToWantToRead = findViewById(R.id.btnAddWantToRead);
        btnAddToCurrentlyReading = findViewById(R.id.btnAddCurrentlyReading);
        btnAddToFavorite = findViewById(R.id.btnaddToFavorites);

        bookImage = findViewById(R.id.imgBookImage);
    }
}