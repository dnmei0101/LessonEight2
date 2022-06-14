package ru.gb.androidlessoneight;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ru.gb.androidlessoneight.Note;
import ru.gb.androidlessoneight.NoteDescription;


public class NoteFragment extends Fragment {

    private ArrayList<Note> notes;

    private final String NOTES = "notes";
    private static final String CURRENT_COUNTRY = "CurrentCountry";
    private int currentPosition = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            getParentFragmentManager().popBackStack();
            requireActivity().getSupportFragmentManager().popBackStack();}

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) notes = getArguments().getParcelableArrayList(NOTES);
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(CURRENT_COUNTRY, 0);
        }
        initList(view, notes);
        if (isLandscape()) {
            showLandNoteDescription(currentPosition, notes);
        }

    }

    private void initList(View view, ArrayList<Note> notes) {
        LinearLayout layoutView = (LinearLayout) view;

        for (int i = 0; i < notes.size(); i++) {
            String country = notes.get(i).getName();
            TextView tv = new TextView(getContext());
            tv.setText(country);
            tv.setTextSize(30);
            layoutView.addView(tv);
            int position = notes.get(i).getIndex();
            tv.setOnClickListener(v -> {
                currentPosition = position;
                showNoteDescription(position, notes);
            });
        }
    }


    private void showNoteDescription(int index, ArrayList<Note> notes) {
        if (isLandscape()) {
            showLandNoteDescription(index, notes);
        }
        else {
            showPortNoteDescription(index, notes);
        }

    }

    private void showLandNoteDescription(int index, ArrayList<Note> notes) {
        NoteDescription noteDescription =
                NoteDescription.newInstance(index, notes);
        FragmentManager fragmentManager =
                requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.desc_container, noteDescription);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private void showPortNoteDescription(int index, ArrayList<Note> notes) {
        NoteDescription noteDescription =
                NoteDescription.newInstance(index, notes);
        FragmentManager fragmentManager =
                requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, noteDescription);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(CURRENT_COUNTRY, currentPosition);
        super.onSaveInstanceState(outState);
    }
    private boolean isLandscape() {
        return getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }

}