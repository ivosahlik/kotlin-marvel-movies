package cz.ivosahlik.marvel_movies.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import cz.ivosahlik.marvel_movies.comicsToString
import cz.ivosahlik.marvel_movies.model.CharacterResult

@Entity(tableName = Constants.CHARACTER_TABLE)
data class DbCharacter(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val apiId: Int?,
    val name: String?,
    val thumbnail: String?,
    val comics: String?,
    val description: String?
) {
    companion object{
        fun mapperFromCharacterResultToDbCharacter(character: CharacterResult) =
            DbCharacter(
                id = 0,
                apiId = character.id,
                name = character.name,
                thumbnail = character.thumbnail?.path + "." + character.thumbnail?.extension,
                comics = character.comics?.items?.mapNotNull { it.name }?.comicsToString()
                    ?: "no comics",
                description = character.description
            )
    }
}