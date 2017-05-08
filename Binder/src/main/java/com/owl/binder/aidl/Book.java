package com.owl.binder.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alamusi on 2016/12/8.
 */

public class Book implements Parcelable {

    public static final Parcelable.Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel parcel) {
            return new Book(parcel);
        }

        @Override
        public Book[] newArray(int i) {
            return new Book[i];
        }
    };
    private int mBookId;
    private String mBookName;

    public Book(int bookId, String bookName) {
        mBookId = bookId;
        mBookName = bookName;
    }

    public Book(Parcel parcel) {
        mBookId = parcel.readInt();
        mBookName = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mBookId);
        parcel.writeString(mBookName);
    }
}
