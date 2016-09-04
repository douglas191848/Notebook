package com.example.douglas_lkf.notebook;

/*IMPORTANT for fragment*/
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoteViewFragment extends Fragment {


    public NoteViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragment_layout = inflater.inflate(R.layout.fragment_note_view, container, false);

        TextView title = (TextView) fragment_layout.findViewById(R.id.viewNoteTitle);
        TextView message = (TextView) fragment_layout.findViewById(R.id.viewNoteMessage);
        ImageView icon = (ImageView) fragment_layout.findViewById(R.id.viewNoteIcon);

        Intent intent = getActivity().getIntent();

        title.setText(intent.getExtras().getString(MainActivity.NOTE_TITLE_EXTRA));
        message.setText(intent.getExtras().getString(MainActivity.NOTE_MESSAGE_EXTRA));

        //because the eum type is serializable
        Note.Category noteCat = (Note.Category) intent.getSerializableExtra(MainActivity.NOTE_GATEGORY_EXTRA);
        icon.setImageResource(Note.categoryToDrawable(noteCat));


        // Inflate the layout for this fragment
        return fragment_layout;
    }


}
