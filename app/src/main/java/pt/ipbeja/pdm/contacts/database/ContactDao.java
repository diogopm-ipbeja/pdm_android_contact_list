package pt.ipbeja.pdm.contacts.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import pt.ipbeja.pdm.contacts.Contact;

@Dao
public interface ContactDao {

    @Query("select * from contacts")
    List<Contact> getAll();

    @Query("select * from contacts where id = :id")
    Contact getContact(long id);

    @Insert
    long insert(Contact contact);

    @Delete
    int delete(Contact contact);

    @Delete
    int delete(List<Contact> contacts);

    @Update
    int update(Contact contact);

    @Update
    int update(List<Contact> contacts);


}
