package br.edu.ifsp.scl.prdm.sc3029531.persistentcontactlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.scl.prdm.sc3029531.persistentcontactlist.model.entity.Contact
import br.edu.ifsp.scl.prdm.sc3029531.persistentcontactlist.model.entity.Contact.Companion.INVALID_CONTACT_ID
import br.edu.ifsp.scl.prdm.sc3029531.persistentcontactlist.model.Operation
import br.edu.ifsp.scl.prdm.sc3029531.persistentcontactlist.model.repository.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContactViewModel(application: Application): AndroidViewModel(application) {

    //tipagem checada por inferência
    private val _currentContact = MutableStateFlow(value = Contact())
    val currentContact: StateFlow<Contact> = _currentContact.asStateFlow()

    private val _contactList: MutableStateFlow<List<Contact>> = MutableStateFlow(value = emptyList())

    //a lista emite que foi alterada, pois é um State (é um estado)
    //como eu sei que é um STATE a lista abaixo?
    val contactList: StateFlow<List<Contact>> = _contactList.asStateFlow()

    //tipo explicitado
    private val _operation: MutableStateFlow<Operation> = MutableStateFlow(Operation.NEW_OR_EDIT)
    val operation: StateFlow<Operation> = _operation.asStateFlow()

    init {
        ContactRepository.init(application.applicationContext)
        updateContactList()
    }
    private fun updateContactList() {
        viewModelScope.launch(Dispatchers.IO) {
            _contactList.value = ContactRepository.getContacts()
        }
    }

    fun saveContact(contact: Contact) {

        viewModelScope.launch(Dispatchers.IO) {

            //verifica se o id = -1
            if (contact.id == INVALID_CONTACT_ID) {

                val nextId = (_contactList.value.maxOfOrNull { lastListContact -> lastListContact.id } ?: 0) + 1

                //eu copio o contato inteiro preenchido, só que com id diferente
                val newContact = contact.copy(id = nextId)

                //estou atualizando o fluxo da minha lista de contatos
                _contactList.update { it + newContact }
                //o update já cria uma nova lista e a reatribui ao state

                ContactRepository.addContact(newContact)
            } else {
                //procurar o contato na lista, e somente alterar a posição onde esse contato está
                _contactList.update { list -> list.map { if (it.id == contact.id) contact else it } }
                //o map recebe um predicado (algo que retorna true ou false)
                //a recriação da lista vai emitir o que vai ser recriado na tela

                ContactRepository.replaceContact(contact)
            }
        }
    }

    fun removeContact(contact: Contact) {
        _contactList.update {
            list -> list.filterNot {
            currentIterationContact -> currentIterationContact.id == contact.id
            }
        }
        ContactRepository.deleteContact(contact)
    }


    //atualiza o contato atual e a operacao
    fun updateCurrentContactAndOperation(contact: Contact, operation: Operation) {
        _currentContact.value = contact
        _operation.value = operation
    }



}