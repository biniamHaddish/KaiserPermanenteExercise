package biniam.berhane.kaiserpermanenteexercise.model

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Designed and developed by Biniam Berhane on 14/05/2020.
 */
@Parcelize
@Keep
data class Books(
    var id: String = "",
    var title: String? = "",
    var author: String? = "",
    var description: String? = ""
) : Parcelable