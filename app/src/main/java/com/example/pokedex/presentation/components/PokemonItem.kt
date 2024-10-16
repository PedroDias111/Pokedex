package com.example.pokedex.presentation.components

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.palette.graphics.Palette
import coil.compose.SubcomposeAsyncImage
import com.example.pokedex.domain.model.PokemonDetailedInfoDomain
import com.example.pokedex.presentation.utils.CircleCutSquareShape
import com.example.pokedex.presentation.utils.darker
import com.example.pokedex.presentation.utils.getContrastingTextColor
import com.example.pokedex.presentation.utils.getTypeColor
import com.example.pokedex.presentation.utils.lighter
import com.example.pokedex.util.orDefaultColor
import java.util.Locale


@Composable
fun PokemonItem(
    pokemon: PokemonDetailedInfoDomain,
    modifier: Modifier = Modifier
) {
      // State to hold the extracted colors
    val dominantColor = remember { mutableStateOf(Color.Transparent)}

    ElevatedCard(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = dominantColor.value,
        ),
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.65f),
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(0.80f),
                    ){
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top
                        ){
                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 20.dp, vertical = 15.dp),
                                text= "#${pokemon.pokemonBasicInfoDomain.id}",
                                color = getContrastingTextColor(dominantColor.value),
                                fontWeight = FontWeight.Thin,
                            )

                            Text(
                                modifier = Modifier
                                    .padding(vertical = 15.dp),
                                text= pokemon.pokemonBasicInfoDomain.name.replaceFirstChar {
                                    if (it.isLowerCase()) it.titlecase(
                                        Locale.getDefault()
                                    ) else it.toString()},
                                color = getContrastingTextColor(dominantColor.value),
                                fontWeight = FontWeight.Normal,
                                fontSize = 20.sp,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 2
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(0.20f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        IconButton(
                            onClick = {},
                        ){
                            Icon(
                                //imageVector = if(breed.isFavourite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                                imageVector = Icons.Filled.Star,
                                contentDescription = "Favourite"
                            )
                        }
                    }

                }
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ){
                    pokemon.types.forEach { type->
                        val typeColor = type.name?.let { getTypeColor(it) } ?: Color.Gray
                        Box(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth()
                                .weight(0.5f)
                                .clip(RoundedCornerShape(5.dp))
                                .border(border = BorderStroke(2.dp, dominantColor.value.darker(0.4f)),
                                        shape = RoundedCornerShape(5.dp) // You can change this shape
                                )
                                .background(typeColor),

                            contentAlignment = Alignment.Center
                        ) {
                            type.name?.let {
                                Text(
                                    modifier = Modifier.padding(vertical = 5.dp),
                                    text= type.name.replaceFirstChar {
                                        if (it.isLowerCase()) it.titlecase(
                                            Locale.getDefault()
                                        ) else it.toString()},
                                    color = Color.White,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1
                                )
                            }

                        }
                    }
                }

            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.35f),
                horizontalAlignment = Alignment.End
            ){
                Box(
                    modifier = Modifier
                        .fillMaxHeight() // Set a fixed size for the image
                        .clip(shape = CircleCutSquareShape()),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    SubcomposeAsyncImage(
                        model = pokemon.pokemonBasicInfoDomain.presentationImageUrl,
                        contentDescription = pokemon.pokemonBasicInfoDomain.name,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(dominantColor.value.lighter(0.4f)), // Ensure the image fills the box
                        contentScale = ContentScale.Fit, // Adjust content scaling
                        loading = {
                            CircularProgressIndicator(
                                modifier = Modifier.scale(0.5f)
                            )},
                        onSuccess = {
                            val drawable = it.result.drawable
                            val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)
                            Palette.from(bmp).generate { palette ->
                                palette?.let { pal->
                                    dominantColor.value = Color(pal.dominantSwatch?.rgb.orDefaultColor())
                                }
                            }
                        }
                    )
                }
            }
        }

    }
}

