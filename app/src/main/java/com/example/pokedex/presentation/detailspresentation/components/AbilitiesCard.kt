package com.example.pokedex.presentation.detailspresentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.domain.model.AbilityDomain

@Composable
fun AbilitiesCard (
    abilities: List<AbilityDomain>,
    modifier: Modifier = Modifier
){
    ElevatedCard (
        modifier = modifier
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly
            ){
                abilities.forEach{ ability->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(5.dp))
                            .border(border = BorderStroke(2.dp, Color.Gray),
                                shape = RoundedCornerShape(5.dp) // You can change this shape
                            ),
                    ){
                        if(ability.isHidden) {
                            Text(
                                text = "Hidden",
                                fontWeight = FontWeight.Normal,
                                fontSize = 10.sp,
                                color = Color.Black,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .background(Color.LightGray)
                                    .padding(5.dp)
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = ability.name?.capitalizeWords() ?: "Unknown", // Fallback value in case ability.name is null,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 10.sp,
                                color = Color.Gray,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(5.dp)
                            )
                        }

                    }

                    Spacer(modifier = Modifier.height(10.dp))
                }

            }
        }

    }
}