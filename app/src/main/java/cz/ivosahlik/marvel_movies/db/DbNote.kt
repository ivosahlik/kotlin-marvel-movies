package cz.ivosahlik.marvel_movies.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import cz.ivosahlik.marvel_movies.model.Note

@Entity(tableName = Constants.NOTE_TABLE)
data class DbNote(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val characterId: Int,
    val title: String,
    val text: String
) {
    companion object {
        fun fromNote(note: Note) =
            DbNote(
                id = 0,
                characterId = note.characterId,
                title = note.title,
                text = note.text
            )
    }
}