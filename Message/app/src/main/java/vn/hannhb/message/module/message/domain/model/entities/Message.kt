package vn.hannhb.message.module.message.domain.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "messages")
data class Message @JvmOverloads constructor(
    @TypeConverters(AttachmentConverter::class)
    @ColumnInfo(name = "attachmentList") var attachmentList: List<Attachment> = emptyList(),
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "content") var content: String = "",
    @ColumnInfo(name = "postTime") var postTime: Long? = null,
    @TypeConverters(OwnerConverter::class)
    @ColumnInfo(name = "owner") var owner: Owner? = null,
    @ColumnInfo(name = "totalComments") var totalComments: Int = 0,
    @ColumnInfo(name = "isFavourite") var isFavourite: Boolean = false,
    @PrimaryKey @ColumnInfo(name = "id") var id: Int?
) : Base()
