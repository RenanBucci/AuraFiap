package br.com.fiap.appaura.model

import android.os.Parcel
import android.os.Parcelable

data class EmailDTO(
    val emailId: String?,
    val remetente: String?,
    val destinatario: String,
    val assunto: String,
    val mensagem: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(emailId)
        parcel.writeString(remetente)
        parcel.writeString(destinatario)
        parcel.writeString(assunto)
        parcel.writeString(mensagem)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EmailDTO> {
        override fun createFromParcel(parcel: Parcel): EmailDTO {
            return EmailDTO(parcel)
        }

        override fun newArray(size: Int): Array<EmailDTO?> {
            return arrayOfNulls(size)
        }
    }
}