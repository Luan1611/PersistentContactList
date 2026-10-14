package br.edu.ifsp.scl.prdm.sc3029531.persistentcontactlist.model.repository

import android.content.Context
import androidx.room.Room
import br.edu.ifsp.scl.prdm.sc3029531.persistentcontactlist.model.dao.ContactDao
import br.edu.ifsp.scl.prdm.sc3029531.persistentcontactlist.model.database.ContactDataBase
import br.edu.ifsp.scl.prdm.sc3029531.persistentcontactlist.model.entity.Contact

object ContactRepository {
    private lateinit var applicationContext: Context


    //ViewModel vai chamar esta função para inicializar e atribuir um valor p/ o appContext antes
    // de começar a usar o db de fato
    fun init(applicationContext: Context) {
        this.applicationContext = applicationContext
    }

    private val contactDao: ContactDao by lazy {
        Room.databaseBuilder(
            applicationContext,
            ContactDataBase::class.java,
            "contact_database"
        ).build().getContactDao()
    }

    //single expression functions
    fun getContacts() = contactDao.getContacts()
    fun getContactById(id: Int) = contactDao.getContactById(id)
    fun addContact(contact: Contact) = contactDao.addContact(contact)
    fun replaceContact(contact: Contact) = contactDao.replaceContact(contact)
    fun deleteContact(contact: Contact) = contactDao.deleteContact(contact)

}