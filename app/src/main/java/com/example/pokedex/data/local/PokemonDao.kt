package com.example.pokedex.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert

@Dao
interface PokemonDao {

    @Upsert
    suspend fun upsertPokemonBasicInfoEntity(pokemon: PokemonBasicInfoEntity)

    @Query("DELETE FROM pokemonbasicinfoentity")
    suspend fun clearPokemonBasicInfoEntity()

    @Upsert
    suspend fun upsertPokemonCriesEntity(cries: CriesEntity)

    @Query("DELETE FROM cries")
    suspend fun clearPokemonCriesEntity()

    @Upsert
    suspend fun upsertPokemonSpeciesEntity(species: SpeciesEntity)

    @Query("DELETE FROM species")
    suspend fun clearPokemonSpeciesEntity()

    @Upsert
    suspend fun upsertPokemonStatEntity(stats: List<StatEntity>)

    @Query("DELETE FROM stat")
    suspend fun clearPokemonStatEntity()

    @Upsert
    suspend fun upsertPokemonTypeEntity(types: List<TypeEntity>)

    @Query("DELETE FROM type")
    suspend fun clearPokemonTypeEntity()

    @Upsert
    suspend fun upsertPokemonAbilityEntity(abilities: List<AbilityEntity>)

    @Query("DELETE FROM ability")
    suspend fun clearPokemonAbilityEntity()

    @Transaction
    suspend fun upsertPokemonDetailedInfoEntity(
        pokemon: PokemonBasicInfoEntity,
        cries: CriesEntity,
        species: SpeciesEntity,
        stats: List<StatEntity>,
        types: List<TypeEntity>,
        abilities: List<AbilityEntity>
    ) {
        upsertPokemonBasicInfoEntity(pokemon)
        upsertPokemonCriesEntity(cries)
        upsertPokemonSpeciesEntity(species)
        upsertPokemonStatEntity(stats)
        upsertPokemonTypeEntity(types)
        upsertPokemonAbilityEntity(abilities)
    }

    // Use a transaction to delete everything in one go
    @Transaction
    suspend fun clearPokemonDetailedInfoEntity() {
        clearPokemonBasicInfoEntity()
        clearPokemonCriesEntity()
        clearPokemonSpeciesEntity()
        clearPokemonStatEntity()
        clearPokemonTypeEntity()
        clearPokemonAbilityEntity()
    }

//    @Transaction
//    @Query("SELECT * FROM pokemonbasicinfoentity")
//    suspend fun getPokemonDetailedInfo(): List<PokemonDetailedInfoEntity>
//
//    @Transaction
//    @Query(
//        """
//            SELECT *
//            FROM pokemonbasicinfoentity
//            WHERE LOWER(name)
//            LIKE '%' || LOWER(:name) || '%'
//        """
//    )
//    suspend fun getPokemonDetailedInfoByName(name: String): List<PokemonDetailedInfoEntity>?
//
//    @Transaction
//    @Query("SELECT * FROM pokemonbasicinfoentity ORDER BY name ASC ")
//    suspend fun getAllPokemonsSortedByName(): List<PokemonDetailedInfoEntity>
//
//    @Transaction
//    @Query(
//        """
//        SELECT p.*, SUM(s.baseStat) as totalStats
//        FROM pokemonbasicinfoentity p
//        JOIN stat s ON p.id = s.pokemonId
//        GROUP BY p.id
//        ORDER BY totalStats DESC
//        """
//    )
//    suspend fun getAllPokemonsSortedByTotalStats(): List<PokemonDetailedInfoEntity>
//
//    @Transaction
//    @Query(
//        """
//        SELECT p.*, s.baseStat
//        FROM pokemonbasicinfoentity p
//        JOIN stat s ON p.id = s.pokemonId
//        WHERE s.name = '%' || LOWER(:statName) || '%'
//        ORDER BY s.baseStat DESC
//        """
//    )
//    suspend fun getPokemonsSortedBySpecificStat(statName: String): List<PokemonDetailedInfoEntity>


    //Pagination
    @Query(
        """
            SELECT *
            FROM pokemonbasicinfoentity
        """
    )
    fun pagingSource(): PagingSource<Int, PokemonDetailedInfoEntity>

}

