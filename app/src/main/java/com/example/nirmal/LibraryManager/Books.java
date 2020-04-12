package com.example.nirmal.LibraryManager;

public class Books {

    private String BookName;
    private String AutherName;

    public Books(){

    }

    Books(String bookName, String autherName) {
        BookName = bookName;
        AutherName = autherName;
    }

    public void setBookName(String BookName){
        this.BookName=BookName;
    }

    public void setAutherName(String autherName){
        this.AutherName=autherName;
    }

    public String getBookName() {

        return BookName;
    }

    public String getAutherName() {
        return AutherName;
    }
}