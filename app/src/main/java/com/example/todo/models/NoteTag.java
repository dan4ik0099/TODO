package com.example.todo.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NoteTag implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "idNote")
    public int idNote;
    @ColumnInfo(name = "idTag")
    public int idTag;

    public NoteTag(){};

    protected NoteTag(Parcel in) {
        id = in.readInt();
        idNote = in.readInt();
        idTag = in.readInt();
    }

    public static final Creator<NoteTag> CREATOR = new Creator<NoteTag>() {
        @Override
        public NoteTag createFromParcel(Parcel in) {
            return new NoteTag(in);
        }

        @Override
        public NoteTag[] newArray(int size) {
            return new NoteTag[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(idNote);
        parcel.writeInt(idTag);
    }
}
