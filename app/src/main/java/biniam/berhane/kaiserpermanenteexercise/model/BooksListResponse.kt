package biniam.berhane.kaiserpermanenteexercise.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BooksListResponse(
    val items: List<Item> = mutableListOf(),
    val kind: String = "",
    val totalItems: Int = 0
) : Parcelable

@Parcelize
data class Item(
    val etag: String = "",
    val id: String = "",
    val kind: String = "",
    val selfLink: String = "",
    val volumeInfo: VolumeInfo? = null
) : Parcelable

@Parcelize
data class VolumeInfo(
    val allowAnonLogging: Boolean = false,
    val authors: List<String>? = mutableListOf(),
    val averageRating: Double = 0.0,
    val canonicalVolumeLink: String = "",
    val categories: List<String> = mutableListOf(),
    val contentVersion: String = "",
    val description: String = "",
    val infoLink: String = "",
    val language: String = "",
    val maturityRating: String = "",
    val pageCount: Int = 0,
    val previewLink: String = "",
    val printType: String = "",
    val publishedDate: String = "",
    val publisher: String = "",
    val ratingsCount: Int = 0,
    val subtitle: String = "",
    val title: String = ""
) : Parcelable {
    fun getAuthorsString(): String {
        var authorsString = ""
        if (authors != null) {
            for (author in authors) {
                authorsString += author
            }
        }
        return authorsString
    }
}
