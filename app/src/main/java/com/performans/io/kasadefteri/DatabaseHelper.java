package com.performans.io.kasadefteri;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final ReadWriteLock rwLock = new ReentrantReadWriteLock(true);

    public DatabaseHelper(Context context) {
        super(context, "myCash.sqlite", null, 1);
    }

    private static void beginReadLock() {
        rwLock.readLock().lock();
    }

    private static void endReadLock() {
        rwLock.readLock().unlock();
    }

    private static void beginWriteLock() {
        rwLock.writeLock().lock();
    }

    private static void endWriteLock() {
        rwLock.writeLock().unlock();
    }


    private static final String DROP_CASH_TABLE = "DROP TABLE IF EXISTS _cash";
    private static final String CREATE_CASH_TABLE = "CREATE TABLE IF NOT EXISTS _cash (_id INTEGER PRIMARY KEY, _amount REAL NOT NULL DEFAULT 0, _isExpense INTEGER NOT NULL DEFAULT 0, _description TEXT, _date TEXT)";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DROP_CASH_TABLE);
        db.execSQL(CREATE_CASH_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }








    public void addRecord(ExpenseModel model) {
        SQLiteDatabase db = null;
        try {
            beginWriteLock();
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("_amount", model.getAmount());
            values.put("_description", model.getDescription());
            values.put("_isExpense", model.isExpense() ? 1 : 0);
            values.put("_date", model.getDate());
            db.insert("_cash", null, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                try {
                    if (db.isOpen()) {
                        db.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                endWriteLock();
            }
        }
    }

    public ArrayList<ExpenseModel> getAllRecords() {
        SQLiteDatabase db = null;
        ArrayList<ExpenseModel> modelList = new ArrayList<>();
        try {
            beginReadLock();
            db = this.getReadableDatabase();
            Cursor cursor = db.query(
                    "_cash",
                    new String[]{"_id", "_amount", "_description", "_isExpense", "_date"},
                    null,
                    null,
                    null,
                    null,
                    "_date DESC"
            );
            if (cursor != null && !cursor.isClosed() && cursor.moveToFirst()) {
                do {
                    modelList.add(new ExpenseModel(
                            cursor.getInt(cursor.getColumnIndex("_id")),
                            cursor.getDouble(cursor.getColumnIndex("_amount")),
                            cursor.getString(cursor.getColumnIndex("_description")),
                            (cursor.getInt(cursor.getColumnIndex("_isExpense")) == 1),
                            cursor.getString(cursor.getColumnIndex("_date"))
                    ));
                } while (cursor.moveToNext());
            }
            assert cursor != null;
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                try {
                    if (db.isOpen()) {
                        db.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                endReadLock();
            }
        }

        return modelList;
    }

    public void deleteRecord(int id) {
        SQLiteDatabase db = null;
        try {
            beginWriteLock();
            db = this.getWritableDatabase();
            db.delete("_cash", "_id=?", new String[]{String.valueOf(id)});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                try {
                    if (db.isOpen()) {
                        db.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                endWriteLock();
            }
        }
    }

    public double getExpenseSum(){
        SQLiteDatabase db = null;
       double result = 0;
        try {
            beginReadLock();
            db = this.getReadableDatabase();
            Cursor cursor = db.query(
                    "_cash",
                    new String[]{"_amount"},
                    "_isExpense = ?",
                    new String[]{"1"},
                    null,
                    null,
                    null
            );
            if (cursor != null && !cursor.isClosed() && cursor.moveToFirst()) {
                do {
                   result += cursor.getDouble(cursor.getColumnIndex("_amount"));
                } while (cursor.moveToNext());
            }
            assert cursor != null;
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                try {
                    if (db.isOpen()) {
                        db.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                endReadLock();
            }
        }

        return result;
    }

    public double getIncomeSum(){
        SQLiteDatabase db = null;
        double result = 0;
        try {
            beginReadLock();
            db = this.getReadableDatabase();
            Cursor cursor = db.query(
                    "_cash",
                    new String[]{"_amount"},
                    "_isExpense = ?",
                    new String[]{"0"},
                    null,
                    null,
                    null
            );
            if (cursor != null && !cursor.isClosed() && cursor.moveToFirst()) {
                do {
                    result += cursor.getDouble(cursor.getColumnIndex("_amount"));
                } while (cursor.moveToNext());
            }
            assert cursor != null;
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                try {
                    if (db.isOpen()) {
                        db.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                endReadLock();
            }
        }

        return result;
    }
}
