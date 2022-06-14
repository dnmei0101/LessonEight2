package ru.gb.androidlessoneight;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.DatePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Note implements Parcelable {
    private final String name;
    private final String capital;
    private int index;
    private Calendar dateCreated;

    public Note(String name, String capital, int index) {
        this.name = name;
        this.capital = capital;
        this.index = index;
        this.dateCreated = Calendar.getInstance();
    }

    protected Note(Parcel in) {
        name = in.readString();
        capital = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public Calendar getDateCreated() {
        return dateCreated;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(capital);
    }

    public static ArrayList<Note> initArrayNote(String[] capital, String[] countries) {
        ArrayList<Note> notes = new ArrayList<>();

        for (int i = 0; i < countries.length; i++) {
            notes.add(new Note(countries[i], capital[i], i));
        }
        return notes;
    }
}
