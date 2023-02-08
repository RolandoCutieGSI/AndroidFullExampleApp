package com.example.mylibrary;

import java.util.ArrayList;

public class Utils {

    private static Utils instance;


    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> favoriteBooks;


    private Utils() {

        if (null == allBooks) {
            allBooks = new ArrayList<>();
            initData();

        }
        if (null == alreadyReadBooks) {
            alreadyReadBooks = new ArrayList<>();


        }
        if (null == wantToReadBooks) {
            wantToReadBooks = new ArrayList<>();


        }
        if (null == currentlyReadingBooks) {
            currentlyReadingBooks = new ArrayList<>();


        }
        if (null == favoriteBooks) {
            favoriteBooks = new ArrayList<>();


        }

    }

    private void initData() {
        //TODO:add initial data


        allBooks.add(new Book(1, "1Q84", "Harakumi", 1350, "https://alittleblogofbooks.files.wordpress.com/2012/06/1q84.jpg", "A work of maddening brillance", "long description"));
        allBooks.add(new Book(2, "1Q834", "Haradekumi", 1350, "https://alittleblogofbooks.files.wordpress.com/2012/06/1q84.jpg", "A work of maddening brillance", "long description"));

    }

    public static synchronized Utils getInstance() {
        if (null != instance) {
            return instance;
        } else {
            instance = new Utils();
            return instance;
        }
    }

    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }


    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static ArrayList<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public static ArrayList<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Book> getFavoriteBooks() {
        return favoriteBooks;
    }

    public Book getBookById(int id) {
        for (Book book : allBooks) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
}
