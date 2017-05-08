// IOnNewBookArrivedListener.aidl
package com.owl.binder.aidl;

// Declare any non-default types here with import statements
import com.owl.binder.aidl.Book;

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}
