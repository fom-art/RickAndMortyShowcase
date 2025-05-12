package com.fomart.rms.core.utils.preview

import com.fomart.rms.core.data.domain.repository.CharactersRepository
import com.fomart.rms.core.model.Character
import com.fomart.rms.core.model.CharacterPreview
import com.fomart.rms.core.model.PagedCharactersResult
import com.fomart.rms.core.model.domain.Result
import com.fomart.rms.core.model.domain.error.Error
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeCharactersRepository : CharactersRepository {
    private val allCharacters = mutableListOf<CharacterPreview>()
    private val favorites = mutableSetOf<String>()

    init {
        repeat(50) { index ->
            allCharacters.add(
                CharacterPreview(
                    id = "char_$index",
                    name = "Character $index",
                    status = if (index % 2 == 0) "Alive" else "Dead",
                    favorite = index % 2 == 0,
                    imageUrl = "https://example.com/char_$index.png"
                )
            )
        }
    }

    private fun getPaged(
        characters: List<CharacterPreview>,
        page: Int,
        pageSize: Int = 10
    ): PagedCharactersResult {
        val fromIndex = (page - 1) * pageSize
        val toIndex = (fromIndex + pageSize).coerceAtMost(characters.size)
        val pageItems = if (fromIndex in characters.indices) {
            characters.subList(fromIndex, toIndex)
        } else emptyList()

        val totalPages = (characters.size + pageSize - 1) / pageSize

        return PagedCharactersResult(
            charactersPreviews = pageItems.map { it.copy(favorite = favorites.contains(it.id)) },
            currentPage = page,
            totalPages = totalPages
        )
    }

    override fun loadCharactersPreviewsPage(page: Int): Flow<Result<PagedCharactersResult, Error>> {
        return flowOf(Result.Success(getPaged(allCharacters, page)))
    }

    override fun loadCharactersPreviewsPageByName(
        name: String,
        page: Int
    ): Flow<Result<PagedCharactersResult, Error>> {
        val filtered = allCharacters.filter {
            it.name.contains(name, ignoreCase = true)
        }
        return flowOf(Result.Success(getPaged(filtered, page)))
    }

    override fun getCharacterById(id: String): Flow<Result<Character, Error>> {
        val preview = allCharacters.find { it.id == id }
            ?.copy(favorite = favorites.contains(id))
            ?: return flowOf(Result.Error(Error.Unexpected))

        val character = Character(
            base = preview,
            species = "Human",
            type = "Unknown",
            gender = "Male",
            origin = "Earth",
            location = "Citadel of Ricks"
        )

        return flowOf(Result.Success(character))
    }

    override fun getFavoriteCharactersPreviews(): Flow<Result<List<CharacterPreview>, Error>> {
        val favoriteList = allCharacters.filter { favorites.contains(it.id) }
            .map { it.copy(favorite = true) }
        return flowOf(Result.Success(favoriteList))
    }

    override suspend fun upsertCharacterToFavouritesById(id: String): Result<Unit, Error> {
        favorites.add(id)
        return Result.Success(Unit)
    }

    override suspend fun deleteCharacterFromFavouritesById(id: String): Result<Unit, Error> {
        favorites.remove(id)
        return Result.Success(Unit)
    }
}
