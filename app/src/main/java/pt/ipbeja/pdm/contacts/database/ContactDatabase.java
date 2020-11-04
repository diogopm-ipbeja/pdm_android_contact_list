package pt.ipbeja.pdm.contacts.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import pt.ipbeja.pdm.contacts.Contact;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class ContactDatabase extends RoomDatabase {

    private static ContactDatabase instance;

    public static ContactDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context, ContactDatabase.class, "contacts_db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract ContactDao contactDao();

}
