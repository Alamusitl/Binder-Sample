package com.owl.binder.provider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.owl.binder.R;
import com.owl.binder.provider.entity.Book;
import com.owl.binder.provider.entity.User;

public class ProviderActivity extends Activity {

    private static final String TAG = "ProviderActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);

        Uri bookUri = Uri.parse("content://com.owl.binder.provider/book");
        ContentValues values = new ContentValues();
        values.put("_id", 6);
        values.put("name", "程序设计的艺术");
        getContentResolver().insert(bookUri, values);

        Cursor bookCursor = getContentResolver().query(bookUri, new String[]{"_id", "name"}, null, null, null);
        if (bookCursor != null) {
            while (bookCursor.moveToNext()) {
                Book book = new Book();
                book.bookId = bookCursor.getInt(0);
                book.bookName = bookCursor.getString(1);
                Log.d(TAG, "query book:" + book.toString());
            }
            bookCursor.close();
        }

        Uri userUri = Uri.parse("content://com.owl.binder.provider/user");
        Cursor userCursor = getContentResolver().query(userUri, new String[]{"_id", "name", "sex"}, null, null, null);
        if (userCursor != null) {
            while (userCursor.moveToNext()) {
                User user = new User();
                user.userId = userCursor.getInt(0);
                user.userName = userCursor.getString(1);
                user.isMale = userCursor.getInt(2) == 1;
                Log.d(TAG, "query user:" + user.toString());
            }
            userCursor.close();
        }

    }
}
