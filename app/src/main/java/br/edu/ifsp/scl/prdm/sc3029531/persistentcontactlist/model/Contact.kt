package br.edu.ifsp.scl.prdm.sc3029531.persistentcontactlist.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val id: Int = INVALID_CONTACT_ID,
    val name: String = "",
    val address: String = "",
    val phone: String = "",
    val email: String = ""
): Parcelable {
    companion object {
        const val INVALID_CONTACT_ID = -1
    }
}
