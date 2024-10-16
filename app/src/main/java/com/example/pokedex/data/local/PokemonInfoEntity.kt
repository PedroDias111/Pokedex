package com.example.pokedex.data.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation


@Entity
data class PokemonBasicInfoEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val presentationImageUrl: String,
    val threeDModelUrl: String,
)

@Entity(tableName = "cries")
data class CriesEntity(
    @PrimaryKey val pokemonId: Int,
    val latest: String, // The latest depiction of this Pokémon's cry.
)

@Entity(tableName = "species")
data class SpeciesEntity(
    @PrimaryKey val pokemonId: Int,
    val name: String,
    val url: String
)

data class PokemonDetailedInfoEntity(
    // Non primitive types of data so room flatten their properties into the same table as the entity that references them
    @Embedded val pokemonBasicInfoEntity: PokemonBasicInfoEntity,

    // One to One Relationships
    @Relation(
        parentColumn = "id",
        entityColumn = "pokemonId"
    )
    val cries: CriesEntity?,  // Nullable to handle cases without associated cries

    @Relation(
        parentColumn = "id",
        entityColumn = "pokemonId"
    )
    val species: SpeciesEntity?,  // Nullable for the same reason

    //One to Many Relationships
    @Relation(
        parentColumn = "id",
        entityColumn = "pokemonId"
    )
    val abilities: List<AbilityEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "pokemonId"
    )
    val stats: List<StatEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "pokemonId"
    )
    val types: List<TypeEntity>,

    //------------------------ Unused Resources -----------------------
    //val baseExperience: Int,
    //val forms: List<Form>,
    //val gameIndices: List<GameIndice>,
    //val heldItems: List<HeldItem>,

    //val isDefault: Boolean,
    //val locationAreaEncounters: String,
    //val moves: List<Move>,

    //val order: Int,
    //val pastTypes: List<PastType>,

)

@Entity(tableName = "stat", primaryKeys = ["name", "pokemonId"])
data class StatEntity(
    val name: String,
    val baseStat: Int,
    val pokemonId: Int,
)

@Entity(tableName = "type", primaryKeys = ["name", "pokemonId"])
data class TypeEntity(
    val name: String,
    val pokemonId: Int,
)

@Entity(tableName = "ability", primaryKeys = ["name", "pokemonId"])
data class AbilityEntity(
    val name: String,
    val isHidden: Boolean,
    val url: String,
    val pokemonId: Int,
)