package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class AddBookActivity extends AppCompatActivity {

    private EditText edtBookId, edtBookName, edtBookAuthor, edtBookDescription, edtBookPages;

    private Button btnAddBook;

    private TextView txtWarnBookId, txtWarnBookName, txtWarnBookAuthor, txtWarnBookDescription, txtWarnBookPages;


    private ConstraintLayout parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        initViews();

        btnAddBook.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                initRegister();

            }
        });
    }

    private void initRegister() {

        if (validateData()) {
            if (added()) {
                showSnackBar();


                Intent intent = new Intent(AddBookActivity.this, AllBooksActivity.class);
                startActivity(intent);

            } else {
                Toast.makeText(this, "Book cannot be added", Toast.LENGTH_SHORT).show();
            }

        }

        
    }

    private boolean added() {
        int id=Integer.parseInt(edtBookId.getText().toString());
        String name=edtBookName.getText().toString();
        String author=edtBookAuthor.getText().toString();
        int pages=Integer.parseInt(edtBookPages.getText().toString());
        String description =edtBookDescription.getText().toString();

        Book books=new Book(id, name, author, pages, "https://alittleblogofbooks.files.wordpress.com/2012/06/1q84.jpg", description, "long description");

        if(Utils.getInstance(this).addBook(books))
        {

            return true;

        }
        else{
            return false;
        }
    }

    private void showSnackBar() {
        /*txtWarnName.setVisibility(View.GONE);
        txtWarnEmail.setVisibility(View.GONE);
        txtWarnPassword.setVisibility(View.GONE);
        txtWarnRepeatPassword.setVisibility(View.GONE);*/

        String bookname = edtBookName.getText().toString();
        String bookAuthor = edtBookAuthor.getText().toString();





        String snackText="Name "+ bookname +"\n"+ "Author: "+ bookAuthor;

        Snackbar.make(parentLayout, snackText, Snackbar.LENGTH_INDEFINITE).setAction("Dissmiss", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                edtBookId.setText("");
                edtBookName.setText("");
                edtBookAuthor.setText("");
                edtBookDescription.setText("");
                edtBookPages.setText("");

            }
        }).show();




    }

    private boolean validateData() {
        if (edtBookId.getText().toString().equals("")) {
            txtWarnBookId.setVisibility(View.VISIBLE);
            txtWarnBookId.setText("Please enter the book id");
            return false;

        }
        if (edtBookName.getText().toString().equals("")) {
            txtWarnBookName.setVisibility(View.VISIBLE);
            txtWarnBookName.setText("Please enter the book name");
            return false;
        }
        if (edtBookAuthor.getText().toString().equals("")) {
            txtWarnBookAuthor.setVisibility(View.VISIBLE);
            txtWarnBookAuthor.setText("Please enter the book author");
            return false;

        }
        if (edtBookDescription.getText().toString().equals("")) {
            txtWarnBookDescription.setVisibility(View.VISIBLE);
            txtWarnBookDescription.setText("Please enter the book description");
            return false;
        }

        if (edtBookPages.getText().toString().equals("")) {
            txtWarnBookPages.setVisibility(View.VISIBLE);
            txtWarnBookPages.setText("Please enter the book pages");
            return false;
        }


        return true;

    }

    private void initViews() {
        edtBookId = findViewById(R.id.edtTxtBookId);
        edtBookName = findViewById(R.id.txtBookName1);
        edtBookAuthor = findViewById(R.id.txtBookAuthor);
        edtBookDescription = findViewById(R.id.txtBookDescription);
        edtBookPages = findViewById(R.id.txtBookPages);


        btnAddBook = findViewById(R.id.btnSaveBook);

        txtWarnBookId = findViewById(R.id.warniggTxtBookId);
        txtWarnBookName = findViewById(R.id.warningBookName);
        txtWarnBookAuthor = findViewById(R.id.txtBookAuthorWarning);
        txtWarnBookDescription = findViewById(R.id.warningBookDescription);
        txtWarnBookPages = findViewById(R.id.txtWarnBookPages);





        parentLayout = findViewById(R.id.parentLayout23);


    }


}