package biniam.berhane.kaiserpermanenteexercise.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Designed and developed by Biniam Berhane on 14/05/2020.
 */
@Parcelize
@Entity(tableName = "books")
data class Books(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String = "",
    @ColumnInfo(name = "title")
    var title: String? = "",
    @ColumnInfo(name = "author")
    var author: String? = "",
    @ColumnInfo(name = "description")
    var description: String? = ""
) : Parcelable