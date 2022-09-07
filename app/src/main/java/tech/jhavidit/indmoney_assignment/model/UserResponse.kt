package tech.jhavidit.indmoney_assignment.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserResponse(
    @Json(name = "description") val description: String? = null,
    @Json(name = "image_url") val imageUrl: String? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "phone_number") val phoneNumber: String? = null,
    @Json(name = "title") val title: String? = null
) : Parcelable
