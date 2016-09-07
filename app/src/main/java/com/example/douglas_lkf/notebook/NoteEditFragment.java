package com.example.douglas_lkf.notebook;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.content.DialogInterface;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteEditFragment extends Fragment {

    private ImageButton noteCatButton;
    private Note.Category savedButtonCategory;
    private AlertDialog categoryDialogObject, confirmDailogObject;
    private EditText title, message;
    private static final String MODIFIED_CATEGORY = "Modified Category";
    private boolean newNote = false;


    public NoteEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //grab the bundle that sends along whether or note our noteEditFragment is creating a new note
        Bundle bundle = this.getArguments();

        //ask: Is any information stored in the Bundle before the orientation changed?
        if(bundle != null){

            newNote = bundle.getBoolean(NoteDetailActivity.NEW_NOTE_EXTRA, false);

        }

        if(savedInstanceState != null){

            savedButtonCategory = (Note.Category) savedInstanceState.get(MODIFIED_CATEGORY);

        }

        //inflate our fragment edit layout
        View fragmentLayout = inflater.inflate(R.layout.fragment_note_edit, container, false);

        //grab widget references from layout
         title = (EditText) fragmentLayout.findViewById(R.id.editNoteTitle);
         message = (EditText) fragmentLayout.findViewById(R.id.editNoteMessage);
        noteCatButton = (ImageButton) fragmentLayout.findViewById(R.id.editNoteButton);
        Button saveButton = (Button) fragmentLayout.findViewById(R.id.saveNote);


        //populate widgets with note data
        Intent intent = getActivity().getIntent();


        //if we can't find the string value by the flag "MainActivity.NOTE_TITLE_EXTRA",
        //then, return the empty string that is the second parameter.
        title.setText(intent.getExtras().getString(MainActivity.NOTE_TITLE_EXTRA, ""));

        //if we can't find the string value by the flag "MainActivity.NOTE_TITLE_EXTRA",
        //then, return the empty string that is the second parameter.
        message.setText(intent.getExtras().getString(MainActivity.NOTE_MESSAGE_EXTRA, ""));



        //if we grabed the category from our Bundle that we know we changed orientation and saved information
        //so set our image button background to that category
        if(savedButtonCategory != null){

            noteCatButton.setImageResource(Note.categoryToDrawable(savedButtonCategory));

            //otherwise, we came from our list fragment so just do everything normally
        }else if(!newNote){
            //if this isn't the new note, then do this
            //if this is a new note, then skip this part
            Note.Category noteCat = (Note.Category) intent.getSerializableExtra(MainActivity.NOTE_GATEGORY_EXTRA);
            savedButtonCategory = noteCat;
            noteCatButton.setImageResource(Note.categoryToDrawable(noteCat));
        }



        buildCategoryDialog();
        buildConfirmDialog();

        noteCatButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                categoryDialogObject.show();
            }

        });


        saveButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                confirmDailogObject.show();
            }
        });

        // Inflate the layout for this fragment
        return fragmentLayout;
    }


    // communicate between two different orientation: landscape and portrait
    @Override
    public void onSaveInstanceState (Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putSerializable(MODIFIED_CATEGORY, savedButtonCategory);
    }



    private void buildCategoryDialog (){

        final String[] categories = new String[] {"PERSONAL", "TECHNICAL", "QUOTE", "FINANCE"};

        AlertDialog.Builder categoryBuilder = new AlertDialog.Builder(getActivity());
        categoryBuilder.setTitle("Choose the Note Type");

        categoryBuilder.setSingleChoiceItems(categories, 0, new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int item) {
                //dismiss our dialog window
                categoryDialogObject.cancel();

                switch (item) {

                    case 0:
                        savedButtonCategory = Note.Category.PERSONAL;
                        noteCatButton.setImageResource(R.drawable.eight);
                        break;
                    case 1:
                        savedButtonCategory = Note.Category.TECHNICAL;
                        noteCatButton.setImageResource(R.drawable.five);
                        break;
                    case 2:
                        savedButtonCategory = Note.Category.QUOTE;
                        noteCatButton.setImageResource(R.drawable.four);
                        break;
                    case 3:
                        savedButtonCategory = Note.Category.FINANCE;
                        noteCatButton.setImageResource(R.drawable.seven);
                        break;

                }


            }
        });

        categoryDialogObject = categoryBuilder.create();

    }


    private void buildConfirmDialog (){

        AlertDialog.Builder confirmBuilder = new AlertDialog.Builder(getActivity());
        confirmBuilder.setTitle("Are you sure?");
        confirmBuilder.setMessage("Are you sure you want to save the note?");

        confirmBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {




            @Override
            public void onClick(DialogInterface dialog, int which) {

                Log.d("Save Note", "Note Title: " + title.getText() + "Note Message: "
                        + message.getText() + "Note Category: " + savedButtonCategory);

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });




        confirmBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                //do nothing
            }


        });

        confirmDailogObject = confirmBuilder.create();
    }


}
