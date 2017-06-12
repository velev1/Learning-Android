//package com.example.velev.phonebook.data;
//
//import android.app.LoaderManager;
//import android.content.CursorLoader;
//import android.content.Loader;
//import android.database.Cursor;
//import android.os.Bundle;
//import android.provider.CallLog;
//
//
//public class CallLogDataProvider implements LoaderManager.LoaderCallbacks<Cursor> {
//    @Override
//    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
//
//        switch (id) {
//            case URL_LOADER:
//// Returns a new CursorLoader
//                return new CursorLoader(
//                        this,   // Parent activity context
//                        CallLog.Calls.CONTENT_URI,        // Table to query
//                        null,     // Projection to return
//                        null,            // No selection clause
//                        null,            // No selection arguments
//                        null             // Default sort order
//                );
//            default:
//                return null;
//        }
//
//        return null;
//    }
//
//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//
//    }
//
//    @Override
//    public void onLoaderReset(Loader<Cursor> loader) {
//
//    }
//}
