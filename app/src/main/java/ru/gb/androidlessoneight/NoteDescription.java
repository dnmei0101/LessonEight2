package ru.gb.androidlessoneight;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class NoteDescription extends Fragment {

    private static final String ARG_INDEX = "index";
    private static final String NOTES = "notes";

    private ArrayList<Note> notes;


    public NoteDescription() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_description, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();

        if (arguments != null) {
            int index = arguments.getInt(ARG_INDEX);
            notes = arguments.getParcelableArrayList(NOTES);
            Note note = notes.get(index);
            ImageView imageCoatOfArms = view.findViewById(R.id.coat_of_arms_image_view);
            TypedArray images = getResources().obtainTypedArray(R.array.coat_of_arms_img);
            imageCoatOfArms.setImageResource(images.getResourceId(index, 0));
            images.recycle();


            TextView name = view.findViewById(R.id.name);
            TextView capital = view.findViewById(R.id.capital);
            TextView dateCreated = view.findViewById(R.id.date_created);
            DatePicker mDatePicker = view.findViewById(R.id.date_picker);
            Calendar today = note.getDateCreated();
            Button changeButton = view.findViewById(R.id.change_button);

            int day = note.getDateCreated().get(Calendar.DAY_OF_MONTH);
            int month = note.getDateCreated().get(Calendar.MONTH);
            int currentYear = note.getDateCreated().get(Calendar.YEAR);

            String conCapital = "Столица: " + note.getCapital();

            name.setText(note.getName());
            name.setTextSize(50);
            capital.setText(conCapital);
            name.setTextSize(30);
            dateCreated.setText(new StringBuilder()
                    .append(getString(R.string.created_string))
                    .append(" ")
                    .append(day).append("/")
                    .append(month).append("/")
                    .append(currentYear));

            mDatePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
                    today.get(Calendar.DAY_OF_MONTH), (view12, dayOfMonth, monthOfYear, year) -> {});

            changeButton.setOnClickListener(view1 -> dateCreated.setText(new StringBuilder()
                    .append(getString(R.string.created_string))
                    .append(mDatePicker.getDayOfMonth()).append("/")
                    .append(mDatePicker.getMonth() + 1).append("/")
                    .append(mDatePicker.getYear())));

        }
    }

    public static NoteDescription newInstance(int index, ArrayList<Note> notes) {
        NoteDescription fragment = new NoteDescription();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        args.putParcelableArrayList(NOTES, notes);
        fragment.setArguments(args);
        return fragment;
    }
}