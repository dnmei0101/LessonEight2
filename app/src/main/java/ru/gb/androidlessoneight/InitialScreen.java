package ru.gb.androidlessoneight;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import ru.gb.androidlessoneight.NoteFragment;

public class InitialScreen extends Fragment {

    private ArrayList<Note> notes;

    private final String NOTES = "notes";

    public InitialScreen() {
        // Required empty public constructor
    }

    public static InitialScreen newInstance(String param1, String param2) {
        InitialScreen fragment = new InitialScreen();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
            requireActivity().getSupportFragmentManager().popBackStack();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_initial_screen, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) notes = getArguments().getParcelableArrayList(NOTES);

        Button settingsButton = view.findViewById(R.id.settings_button);
        Button aboutAppButton = view.findViewById(R.id.about_app);
        Button listButton = view.findViewById(R.id.countries_list_button);

        listButton.setOnClickListener(v -> {
            NoteFragment noteFragment = new NoteFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(NOTES, notes);
            noteFragment.setArguments(bundle);

            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.initial_screen_frame, noteFragment)
                    .addToBackStack("")
                    .commit();
        });

        settingsButton.setOnClickListener(v -> {
            Toast toast = Toast.makeText(getContext(), getString(R.string.not_working_yet), Toast.LENGTH_LONG);
            toast.show();
        });

        aboutAppButton.setOnClickListener(v -> {
            AboutFragment aboutFragment = new AboutFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(NOTES, notes);
            aboutFragment.setArguments(bundle);

            getChildFragmentManager()
                    .beginTransaction()
                    .addToBackStack("")
                    .replace(R.id.initial_screen_frame, aboutFragment)
                    .commit();
        });

    }
}