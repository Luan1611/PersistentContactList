package br.edu.ifsp.scl.prdm.sc3029531.persistentcontactlist.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.edu.ifsp.scl.prdm.sc3029531.persistentcontactlist.model.dao.ContactDao
import br.edu.ifsp.scl.prdm.sc3029531.persistentcontactlist.model.entity.Contact

@Database(entities = [Contact::class], version = 1)
abstract class ContactDataBase: RoomDatabase() {

    abstract fun getContactDao(): ContactDao

}