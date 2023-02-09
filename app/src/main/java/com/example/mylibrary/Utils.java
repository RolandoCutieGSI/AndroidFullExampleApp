package com.example.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String ALL_READBOOKS_KEY = "allready_read_books";
    private static final String WANT_TO_READ_BOOKS = "want_to_read_books";
    private static final String CURRENTLY_READING_BOOKS = "current_reading_books";
    private static final String FAVORITE_BOOKS = "favorite_books";


    private static Utils instance;

    private SharedPreferences sharedPreferences;


    //private static ArrayList<Book> allBooks;
    //private static ArrayList<Book> alreadyReadBooks;
    //private static ArrayList<Book> wantToReadBooks;
    //private static ArrayList<Book> currentlyReadingBooks;
    //private static ArrayList<Book> favoriteBooks;


    private Utils(Context context) {
        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);

        if (null == getAllBooks()) {
            initData();

        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (null == getAlreadyReadBooks()) {

            editor.putString(ALL_READBOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();


        }
        if (null == getWantToReadBooks()) {

            editor.putString(WANT_TO_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();


        }
        if (null == getCurrentlyReadingBooks()) {
            editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();


        }
        if (null == getFavoriteBooks()) {

            editor.putString(WANT_TO_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();

        }

    }


    private void initData() {
        //TODO:add initial data

        ArrayList<Book> books = new ArrayList<>();


        books.add(new Book(1, "1Q84", "Harakumi", 1350, "https://alittleblogofbooks.files.wordpress.com/2012/06/1q84.jpg", "A work of maddening brillance", "long description"));
        books.add(new Book(2, "1Q834", "Haradekumi", 1350, "https://alittleblogofbooks.files.wordpress.com/2012/06/1q84.jpg", "A work of maddening brillance", "long description"));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        //Para guardar los cambios
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
        editor.commit();


    }

    public static synchronized Utils getInstance(Context context) {
        if (null != instance) {
            return instance;
        } else {
            instance = new Utils(context);
            return instance;
        }
    }

    public ArrayList<Book> getAllBooks() {

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();

        return gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null), type);
    }


    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();

        return gson.fromJson(sharedPreferences.getString(ALL_READBOOKS_KEY, null), type);
    }

    public ArrayList<Book> getWantToReadBooks() {


        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();

        return gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS, null), type);
    }

    public ArrayList<Book> getCurrentlyReadingBooks() {

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();

        return gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS, null), type);
    }

    public ArrayList<Book> getFavoriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();

        return gson.fromJson(sharedPreferences.getString(ALL_READBOOKS_KEY, null), type);

    }

    public Book getBookById(int id) {

        ArrayList<Book> books = getAllBooks();

        if (books != null) {
            for (Book book : books) {
                if (book.getId() == id) {
                    return book;
                }
            }
        }


        return null;
    }

    public boolean addToAlreadyRead(Book book) {

        ArrayList<Book> books = getAlreadyReadBooks();
        if (books != null) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALL_READBOOKS_KEY);
                editor.putString(ALL_READBOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }

        return false;
    }

    public boolean addToWantTOReadRead(Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if (books != null) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_BOOKS);
                editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }

        return false;
    }


    public boolean addToFavoriteBooks(Book incomingBook) {

        ArrayList<Book> books = getFavoriteBooks();
        if (books != null) {
            if (books.add(incomingBook)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVORITE_BOOKS);
                editor.putString(FAVORITE_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }

        return false;
    }

    public boolean addToCurrentlyReadingBooks(Book incomingBook) {

        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (books != null) {
            if (books.add(incomingBook)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }

        return false;
    }

    public boolean removeFromAlreadyRead(Book incomingBook) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null != books) {
            for (Book book : books) {
                if (book.getId() == incomingBook.getId()) {
                    if (books.remove(book)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALL_READBOOKS_KEY);
                        editor.putString(ALL_READBOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        return true;

                    }
                }
            }
        }
        return  false;
    }

    public boolean removeFromWantToRead(Book incomingBook) {
        ArrayList<Book> books = getWantToReadBooks();
        if (null != books) {
            for (Book book : books) {
                if (book.getId() == incomingBook.getId()) {
                    if (books.remove(book)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_BOOKS);
                        editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;

                    }
                }
            }
        }
        return  false;
    }

    public boolean removeFromCurrentlyReading(Book incomingBook) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books) {
            for (Book book : books) {
                if (book.getId() == incomingBook.getId()) {
                    if (books.remove(book)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS);
                        editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;

                    }
                }
            }
        }
        return  false;
    }

    public boolean removeFromFavorite(Book incomingBook) {

        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books) {
            for (Book book : books) {
                if (book.getId() == incomingBook.getId()) {
                    if (books.remove(book)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS);
                        editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;

                    }
                }
            }
        }
        return  false;
    }
}
