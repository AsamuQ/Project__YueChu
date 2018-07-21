package com.example.yuechu.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.yuechu.data.Person;

public class PersonDAO {
    private DBHelper helper;
    private SQLiteDatabase db;

    public PersonDAO(Context context) {
        helper = new DBHelper(context);
    }

    /**

     * 添加新的学生信息

     *

     * @param person

     */

    public void add(Person person) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into t_person (name, password,age) values (?,?,?)", new Object[]
                {person.getName(), person.password, person.getAge()});

    }

    /**

     * 更新学生信息

     *

     * @param person

     */

    public void update(Person person) {
        db = helper.getWritableDatabase();
        db.execSQL("update t_person set age = ?, password = ? where name = ?", new Object[]
                {person.password, person.getAge(), person.getName()});
    }

    /**

     * 查找学生信息

     *

     * @param name

     * @return

     */
    public Person find(String name) {
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select name, password, age from t_person where name = ?", new String[] {name});
        if (cursor.moveToNext()) {
            return new Person(cursor.getString(cursor.getColumnIndex("name")), cursor.getString(cursor.getColumnIndex("password")), cursor.getShort(cursor.getColumnIndex("age")));
        }
        return null;
    }
}
