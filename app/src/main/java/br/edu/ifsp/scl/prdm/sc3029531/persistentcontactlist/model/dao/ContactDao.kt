package br.edu.ifsp.scl.prdm.sc3029531.persistentcontactlist.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.edu.ifsp.scl.prdm.sc3029531.persistentcontactlist.model.entity.Contact

@Dao
interface ContactDao {

    @Query(value = "SELECT * FROM contact")
    fun getContacts(): List<Contact>

    @Query(value = "SELECT * FROM contact WHERE id = :contactId")
    fun getContactById(contactId: Int): Contact?

    @Insert
    fun addContact(contact: Contact)

    @Update
    fun replaceContact(contact: Contact)

    @Delete
    fun deleteContact(contact: Contact)

}