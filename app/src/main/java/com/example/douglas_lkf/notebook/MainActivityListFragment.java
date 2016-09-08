package com.example.douglas_lkf.notebook;


import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import com.example.douglas_lkf.notebook.Note;

import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityListFragment extends ListFragment {

    private ArrayList<Note> notes;
    private NoteAdapter noteAdapter;
    @Override
    public void onActivityCreated (Bundle savedInstanceState){

        super.onActivityCreated(savedInstanceState);


        /*notes = new ArrayList<Note>();
        notes.add(new Note("This is a new not title!", "This is the body of our note!", Note.Category.PERSONAL));
        notes.add(new Note("Woo, I'm goinng to have a new app!", "Today I learnt how to implement list fragment.", Note.Category.QUOTE));
        notes.add(new Note("What should we do?", "I love the sentence that says hello to world.", Note.Category.FINANCE));
        notes.add(new Note("Can anyone help me to do the maths?", "My question is quite simple, but complicated.", Note.Category.PERSONAL));
        notes.add(new Note("Anyone knows who am I?", "I'm coming from a planet that no one knows, but you may find what you want there.", Note.Category.QUOTE));
        notes.add(new Note("I'm a sporty guy.", "Come to join us.", Note.Category.PERSONAL));
        notes.add(new Note("Better Sleep.", " sufficient sleep is important for your health, well-being and " +
                "happiness. When you sleep better, you feel better. Sleep Foundation Sleep Diary will help you track your sleep,\n" +
                "allowing you to see habits and trends that are helping you sleep\n" +
                "or that can be improved.", Note.Category.TECHNICAL));
        notes.add(new Note("I want to sleep.", "The National Sleep Foundation is dedicated to improving health and\n" +
                "well-being through sleep education and advocacy. It is well-known ", Note.Category.QUOTE));
        notes.add(new Note("Wake you up!", "Woke up too late and missed\n" +
                "the first class.", Note.Category.FINANCE));
        notes.add(new Note("Stress", "I couldn’t finish the assignment\n" +
                "that was due by class today\n" +
                "although I worked very hard\n" +
                "all morning. It took longer\n" +
                "to finish than I expected.", Note.Category.PERSONAL));
        notes.add(new Note("Triggering event", "I put off doing all of my\n" +
                "assignments until the last\n" +
                "minute and now need to\n" +
                "finish all the assignments\n" +
                "in one week under\n" +
                "deadline pressure.", Note.Category.TECHNICAL));*/

        NotebookDbAdapter dbAdapter = new NotebookDbAdapter(getActivity().getBaseContext());
        dbAdapter.open();
        notes = dbAdapter.getAllNotes();
        dbAdapter.close();

        noteAdapter = new NoteAdapter(getActivity(), notes);
        setListAdapter(noteAdapter);

        // if someone click on the list view, then lanuch onCreaterContextMenu()
        registerForContextMenu(getListView());
    }

    @Override
    public void onListItemClick (ListView l, View v, int position, long id){

        super.onListItemClick(l, v, position, id);
        launchNoteDetailActivity(MainActivity.FragmentToLaunch.VIEW,position);
    }


    @Override
    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){

        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.long_press_menu, menu);
    }

    @Override
    public boolean onContextItemSelected (MenuItem item){


        // give me the position of whatever note I long passed on
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int rowPosition = info.position;

        // return to us id of whatever menu item we selected
        switch (item.getItemId()){
            //if we pass edit

            case R.id.edit:
                launchNoteDetailActivity(MainActivity.FragmentToLaunch.EDIT,rowPosition);
                Log.d("Menu Click", "We pressed edit!");
            return true;

        }

        return super.onContextItemSelected(item);
    }



    private void launchNoteDetailActivity (MainActivity.FragmentToLaunch ftl, int position){
        //grab the note information associated with hatever note item we clicked on
        Note note = (Note) getListAdapter().getItem(position);

        //create a new intent launches our NoteDetailActivity
        Intent intent = new Intent(getActivity(), NoteDetailActivity.class);

        //pass along the information of the note we clicked on to our NoteDetailActivity
        intent.putExtra(MainActivity.NOTE_TITLE_EXTRA, note.getTitle());
        intent.putExtra(MainActivity.NOTE_MESSAGE_EXTRA, note.getMessage());

        intent.putExtra(MainActivity.NOTE_GATEGORY_EXTRA, note.getCategory());
        intent.putExtra(MainActivity.NOTE_ID_EXTRA, note.getId());


        switch (ftl){
            case VIEW:
                intent.putExtra(MainActivity.NOTE_FRAGMENT_TO_LOAD_EXTRA, MainActivity.FragmentToLaunch.VIEW);
            break;
            case EDIT:
                intent.putExtra(MainActivity.NOTE_FRAGMENT_TO_LOAD_EXTRA, MainActivity.FragmentToLaunch.EDIT);
                break;
        }

        startActivity(intent);

    }




}
