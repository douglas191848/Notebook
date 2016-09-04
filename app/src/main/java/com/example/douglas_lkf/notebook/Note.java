package com.example.douglas_lkf.notebook;

/**
 * Created by Douglas_lkf on 6/4/2016.
 */
public class Note {
    private String title, message;
    private long noteId, dateCreatedMilli;
    private Category category;

    public enum Category{PERSONAL, TECHNICAL, QUOTE, FINANCE};

    public Note (String title, String message, Category category){
        this.title = title;
        this.message = message;
        this.noteId = 0;
        this.dateCreatedMilli = 0;
        this.category = category;

    }

    public Note (String title, String message, Category category, long noteId, long dateCreatedMilli){
        this.title = title;
        this.message = message;
        this.noteId = noteId;
        this.dateCreatedMilli = dateCreatedMilli;
        this.category = category;

    }

    public String getTitle(){
        return this.title;
    }

    public String getMessage(){
        return this.message;
    }

    public Category getCategory(){
        return this.category;
    }

    public long getDate (){

        return dateCreatedMilli;
    }

    public long getId(){
        return noteId;
    }

    public String toString(){
        return "ID: " + noteId + "Title: " + title + "Message: " + message + "IconID: " + category.name() + "Date: " + dateCreatedMilli;
    }

    public static int categoryToDrawable (Category noteCategory){


        if(noteCategory == Category.PERSONAL){

            return R.drawable.eight;

        }else if (noteCategory == Category.TECHNICAL){

            return R.drawable.five;

        }else if (noteCategory == Category.QUOTE){

            return R.drawable.four;

        }else if(noteCategory == Category.FINANCE){

            return R.drawable.seven;

        }else{
            return R.drawable.seven;
        }

    }

    public int getAssociatedDrawable(){return categoryToDrawable(this.category);}
}


