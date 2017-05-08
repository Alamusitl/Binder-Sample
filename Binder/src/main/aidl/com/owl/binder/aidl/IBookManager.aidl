// IBookMenager.aidl
package com.owl.binder.aidl;

// Declare any non-default types here with import statements
import com.owl.binder.aidl.Book;
import com.owl.binder.aidl.IOnNewBookArrivedListener;

interface IBookManager {

    List<Book> getBookList();

    void addBook(in Book book);

    void registerListener(IOnNewBookArrivedListener listener);

    void unregisterListener(IOnNewBookArrivedListener listener);
}
