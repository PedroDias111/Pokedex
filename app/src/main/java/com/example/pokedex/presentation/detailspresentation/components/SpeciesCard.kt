package com.example.pokedex.presentation.detailspresentation.components

import android.media.MediaPlayer
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.domain.model.CriesDomain

@Composable
fun SpeciesScreen(
    height: Int,
    weight: Int,
    cries: CriesDomain,
    modifier: Modifier = Modifier
){
    // State to manage if the music is playing or not
    var isPlaying by remember { mutableStateOf(false) }

    // MediaPlayer instance to play the sound
    var mediaPlayer: MediaPlayer? by remember { mutableStateOf<MediaPlayer?>(null) }

    ElevatedCard (
        modifier = modifier
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .weight(0.5f)
                    .clip(RoundedCornerShape(5.dp))
                    .border(border = BorderStroke(2.dp, Color.Gray),
                            shape = RoundedCornerShape(5.dp) // You can change this shape
                    ),
                    contentAlignment = Alignment.Center
            ){
                Text(
                    text = "${height.toFloat() / 10} m",
                    fontWeight = FontWeight.Normal,
                    fontSize = 10.sp,
                    color = Color.Gray,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(5.dp)
                )
            }

            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .weight(0.5f)
                    .clip(RoundedCornerShape(5.dp))
                    .border(border = BorderStroke(2.dp, Color.Gray),
                        shape = RoundedCornerShape(5.dp) // You can change this shape
                    ),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "${weight.toFloat() / 10} Kg",
                    fontWeight = FontWeight.Normal,
                    fontSize = 10.sp,
                    color = Color.Gray,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(5.dp)
                )
            }
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .fillMaxWidth()
                    .weight(0.5f)
                    .clip(RoundedCornerShape(5.dp)),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Height(m)",
                    fontWeight = FontWeight.Normal,
                    fontSize = 10.sp,
                    color = Color.Gray,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                )
            }

            Box(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .fillMaxWidth()
                    .weight(0.5f)
                    .clip(RoundedCornerShape(5.dp)),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Weight(Kg)",
                    fontWeight = FontWeight.Normal,
                    fontSize = 10.sp,
                    color = Color.Gray,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                )
            }
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Box(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp))
                    .border(border = BorderStroke(2.dp, Color.Gray),
                        shape = RoundedCornerShape(5.dp) // You can change this shape
                    ),
                contentAlignment = Alignment.Center
            ){
                IconButton(
                    onClick = {
                        if (isPlaying) {
                            // Stop the sound if already playing
                            mediaPlayer?.stop()
                            mediaPlayer?.release()
                            mediaPlayer = null
                            isPlaying = false
                        } else {
                            // Play the sound from the URL
                            mediaPlayer = MediaPlayer().apply {
                                setDataSource(cries.latest)  // The URL from the CriesDomain object
                                prepareAsync()
                                setOnPreparedListener {
                                    it.start()
                                    isPlaying = true
                                }
                                setOnCompletionListener {
                                    isPlaying = false
                                    it.release()
                                    mediaPlayer = null
                                }
                            }
                        }
                    }
                ) {
                    if (isPlaying) {
                        // Show Stop Icon when playing
                        Icon(
                            imageVector = Icons.Filled.Stop,
                            contentDescription = "Stop Sound",
                            modifier = Modifier.size(20.dp),
                            tint = Color.Gray
                        )
                    } else {
                        // Show Play Icon when not playing
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = "Play Sound",
                            modifier = Modifier.size(20.dp),
                            tint = Color.Gray
                        )
                    }
                }
            }
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp)),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Cry",
                    fontWeight = FontWeight.Normal,
                    fontSize = 10.sp,
                    color = Color.Gray,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
    }
}


