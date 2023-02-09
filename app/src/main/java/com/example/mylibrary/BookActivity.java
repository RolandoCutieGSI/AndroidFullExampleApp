package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

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

        //Para recibir el income intent y de ahi una vez que tengamos el libro entonces ver si pertenece a las otras listas
        Intent intent = getIntent();
        if (null != intent) {
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (bookId != -1) {
                Book incomingBook = Utils.getInstance(this).getBookById(bookId);
                if (incomingBook != null) {
                    setData(incomingBook);

                    //para validar si el libro esta en la lista de alreadyRead y desactivar los botones
                    handleAlreadyRead(incomingBook);

                    //para validar si el libro esta en la lista de wantToRead y desactivar los botones
                    handleWantToRead(incomingBook);

                    //para validar si el libro esta en la lista de Currently Reading Books y desactivar los botones
                    handleCurrentlyReadingBooks(incomingBook);

                    //para validar si el libro esta en la lista de FavoriteBooks y desactivar los botones
                    handleFavoriteBooks(incomingBook);


                }
            }
        }
    }

    private void handleFavoriteBooks(Book incomingBook) {

        ArrayList<Book> favoriteBooks = Utils.getInstance(this).getFavoriteBooks();
        boolean existInFavoriteReadBooks = false;

        for (Book book : favoriteBooks) {
            if (book.getId() == incomingBook.getId()) {

                existInFavoriteReadBooks = true;
            }

        }
        if (existInFavoriteReadBooks) {
            btnAddToFavorite.setEnabled(false);
        } else {
            btnAddToFavorite.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToFavoriteBooks(incomingBook)) {
                        //TODO:Navigate to the user
                        Toast.makeText(BookActivity.this, "The book is added to the list", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, FavoriteBooksActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(BookActivity.this, "Something wrong happend", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            btnAddToFavorite.setEnabled(true);
        }

    }

    private void handleCurrentlyReadingBooks(Book incomingBook) {
        ArrayList<Book> currentlyReadingBooks = Utils.getInstance(this).getCurrentlyReadingBooks();
        boolean existInCurrentlyReadingBooks = false;

        for (Book book : currentlyReadingBooks) {
            if (book.getId() == incomingBook.getId()) {

                existInCurrentlyReadingBooks = true;
            }

        }
        if (existInCurrentlyReadingBooks) {
            btnAddToCurrentlyReading.setEnabled(false);
        } else {
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToCurrentlyReadingBooks(incomingBook)) {
                        //TODO:Navigate to the user
                        Toast.makeText(BookActivity.this, "The book is added to the list", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, CurrentlyReadingBooksActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(BookActivity.this, "Something wrong happend", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            btnAddToCurrentlyReading.setEnabled(true);
        }

    }

    private void handleWantToRead(final Book incomingBook) {
        ArrayList<Book> wantToReadBooks = Utils.getInstance(this).getWantToReadBooks();
        boolean existInwantToReadBooks = false;

        for (Book book : wantToReadBooks) {
            if (book.getId() == incomingBook.getId()) {

                existInwantToReadBooks = true;
            }

        }
        if (existInwantToReadBooks) {
            btnAddToWantToRead.setEnabled(false);
        } else {
            btnAddToWantToRead.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToWantTOReadRead(incomingBook)) {
                        //TODO:Navigate to the user
                        Toast.makeText(BookActivity.this, "The book is added to the list", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, WantToReadActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(BookActivity.this, "Something wrong happend", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            btnAddToWantToRead.setEnabled(true);
        }
    }

    //Este metodo se encarga de validar si el book en el que estamos trabajando esta en la lusta de libros leidos
    private void handleAlreadyRead(Book incomingBook) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadBooks();
        boolean existInAlreadyReadBooks = false;

        for (Book book : alreadyReadBooks) {
            if (book.getId() == incomingBook.getId()) {

                existInAlreadyReadBooks = true;
            }

        }
        if (existInAlreadyReadBooks) {
            btnAddToAlreadyRead.setEnabled(false);
        } else {
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToAlreadyRead(incomingBook)) {
                        //TODO:Navigate to the user
                        Toast.makeText(BookActivity.this, "The book is added to the list", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, AllreadyReadBook.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(BookActivity.this, "Something wrong happend", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            btnAddToAlreadyRead.setEnabled(true);
        }
    }


    //Este metodo se encarga de llenar los datos de la vista con l libro que se obtiene del recyclerview
    private void setData(Book book) {
        txtBook.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getShortDesc());
        Glide.with(this).asBitmap().load(book.getImageUrl()).into(bookImage);
    }


    //Este metodo se encarga de enlazar la parte logica con la vista
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