package com.example.douglas_lkf.notebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.douglas_lkf.notebook.Note;

import java.util.ArrayList;

/**
 * Created by Douglas_lkf on 6/4/2016.
 */
public class NoteAdapter extends ArrayAdapter<Note> {


    public static class ViewHolder{

        TextView title;
        TextView bodyNote;
        ImageView noteIcon;


    }

    //pass all the note we want for our list
    public NoteAdapter(Context context, ArrayList<Note> notes){

        super(context, 0, notes);


    }

    @Override
    //return the row of information for our row list
    public View getView(int position, View convertView, ViewGroup parent){

        //get the data item for this position
        Note note = getItem(position);

        ViewHolder viewHolder;
        //Check if an existing view is being reused, otherwise make a new view from custom row layout
              if(convertView == null){
                  //  LayoutInflater.from(getContext())  => Obtains the LayoutInflater from the current activity.
                  // LayoutInflater.from(getContext()).inflate() => create a list_row.xml layout
                  // inflate(R.layout.list_row, parent, false) => Inflate a new view hierarchy from the specified XML node.
                  // false =>  If false, root is only used to create the correct subclass of LayoutParams for the root view in the XML.


                  //if we don't have a view that is being used create one, and make sure you create a
                  //view holder along with it to save our view references to.
                  viewHolder = new ViewHolder();
                  convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);

                  viewHolder.title = (TextView) convertView.findViewById(R.id.listItemNoteTitle);
                  viewHolder.bodyNote = (TextView) convertView.findViewById(R.id.listItemNoteBody);
                  viewHolder.noteIcon = (ImageView) convertView.findViewById(R.id.listItemNoteImg);

                  convertView.setTag(viewHolder);
        }else{
                viewHolder = (ViewHolder) convertView.getTag();

              }

        //grab references of views so we can populate them with specific note row data

        //give me the textView in list_row.xml
        /*TextView noteTitle = (TextView) convertView.findViewById(R.id.listItemNoteTitle);
        TextView noteText = (TextView) convertView.findViewById(R.id.listItemNoteBody);
        ImageView noteIcon = (ImageView) convertView.findViewById(R.id.listItemNoteImg);*/

        //get the text from the Note class, then set it to TextView
        viewHolder.title.setText(note.getTitle());
        viewHolder.bodyNote.setText(note.getMessage());
        viewHolder.noteIcon.setImageResource(note.getAssociatedDrawable());

        //now that we modified the view to display appropriate data,
        //return it so it will be displayed.

        return convertView;



    }


}









