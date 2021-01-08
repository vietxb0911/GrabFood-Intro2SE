package com.example.grabfood.Customer.category_rcv_x2;
import com.example.grabfood.R;

import com.example.grabfood.Customer.book_rcv_x1.Book;

import java.util.ArrayList;

public class Category {
    int resourceBackground;
    private String nameCategory;
    private ArrayList<Book> books;

    public static ArrayList<Category> allRestaurant=null;

    public static ArrayList<Category> getListCategory() {
        if (allRestaurant!=null) return allRestaurant;

        allRestaurant = new ArrayList<>();

        ArrayList<Book> listBook = new ArrayList<>();
        listBook.add(new Book(R.drawable.item_tscc,"Trà sữa trân châu","20000"));
        listBook.add(new Book(R.drawable.item_mcdx,"Matcha đá xay","30000"));
        listBook.add(new Book(R.drawable.item_stcc,"Sữa tươi trân châu đường đen","25000"));
        listBook.add(new Book(R.drawable.item_tstx,"Trà sữa thái xanh","25000"));
        listBook.add(new Book(R.drawable.item_tscc,"Trà sữa trân châu","20000"));
        listBook.add(new Book(R.drawable.item_mcdx,"Matcha đá xay","30000"));
        listBook.add(new Book(R.drawable.item_stcc,"Sữa tươi trân châu đường đen","25000"));
        listBook.add(new Book(R.drawable.item_tstx,"Trà sữa thái xanh","25000"));

        ArrayList<Book> listBook2 = new ArrayList<>();

        listBook2.add(new Book(R.drawable.item_stcc,"Sữa tươi trân châu đường đen","25000"));
        listBook2.add(new Book(R.drawable.item_tstx,"Trà sữa thái xanh","25000"));
        listBook2.add(new Book(R.drawable.item_tscc,"Trà sữa trân châu","20000"));
        listBook2.add(new Book(R.drawable.item_mcdx,"Matcha đá xay","30000"));
        listBook2.add(new Book(R.drawable.item_stcc,"Sữa tươi trân châu đường đen","25000"));
        listBook2.add(new Book(R.drawable.item_tstx,"Trà sữa thái xanh","25000"));
        listBook2.add(new Book(R.drawable.item_tscc,"Trà sữa trân châu","20000"));
        listBook2.add(new Book(R.drawable.item_mcdx,"Matcha đá xay","30000"));

        allRestaurant.add(new Category(R.drawable.bg_restaurant_1,"Trà sữa Tocotoco",listBook));
        allRestaurant.add(new Category(R.drawable.bg_restaurant_2,"Trà sữa BonBon",listBook2));
        allRestaurant.add(new Category(R.drawable.bg_restaurant_3,"Trà sữa Koi",listBook));
        allRestaurant.add(new Category(R.drawable.bg_restaurant_4,"Trà sữa Royaltea",listBook2));



        return allRestaurant;
    }

    public Category(int resourceBackground,String nameCategory, ArrayList<Book> books) {
        this.resourceBackground = resourceBackground;
        this.nameCategory = nameCategory;
        this.books = books;
    }

    public int getResourceBackground() {
        return resourceBackground;
    }

    public void setResourceBackground(int resourceBackground) {
        this.resourceBackground = resourceBackground;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}
