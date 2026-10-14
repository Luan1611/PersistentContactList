package br.edu.ifsp.scl.prdm.sc3029531.persistentcontactlist.model.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "contact")
data class Contact(
    @PrimaryKey val id: Int = INVALID_CONTACT_ID,
    @ColumnInfo(name = "full_name") val name: String = "",
    @ColumnInfo(name = "address") val address: String = "",
    @ColumnInfo(name = "phone_number") val phone: String = "",
    @ColumnInfo(name = "email") val email: String = ""
): Parcelable {
    companion object {
        const val INVALID_CONTACT_ID = -1
    }
}