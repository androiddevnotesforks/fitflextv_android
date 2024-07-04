package com.dreamsoftware.fitflextv.ui.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Card
import androidx.tv.material3.Text
import coil.compose.AsyncImage

@Composable
fun CommonCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit ,
    imageUrl: String,
    title: String,
    timeText: String,
    typeText: String,
    timeTextStyle: TextStyle = TextStyle(color = Color.LightGray),
    typeTextStyle: TextStyle = TextStyle(color = Color.LightGray),
    titleTextStyle : TextStyle = TextStyle(color = Color.White),
    dividerColor: Color = Color.LightGray,
    cardAspectRatio: Float = 16f / 9f
) {
    Column (
        modifier = Modifier
            .size(width = 196.dp, height = 158.25.dp)
    ){
        Card(
            onClick = onClick ,
            modifier = modifier
                .aspectRatio(cardAspectRatio)
                .background(Color.Transparent, RoundedCornerShape(16.dp))
                .padding(bottom = 8.dp),
        ) {
            AsyncImage(
                model = imageUrl, contentDescription = "Image",
                modifier = Modifier
                    .fillMaxSize()
                    .height(110.25.dp)
                    .aspectRatio(cardAspectRatio),
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center,
            )
        }
        Text(
            text = title,
            style = titleTextStyle
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = timeText,
                style = timeTextStyle,
                modifier = Modifier.padding( end = 4.dp)
            )
            Text(
                modifier = Modifier
                    .width(1.dp)
                    .alpha(1f),
                text = "|",
                style = TextStyle(color = dividerColor),
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = typeText,
                style = typeTextStyle,
                modifier = Modifier.padding(start = 4.dp, end = 4.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewCustomCard() {
    CommonCard(
        imageUrl = "https://live.staticflickr.com/3060/3304130387_1f3c41d5ab.jpg",
        timeText = "30 min",
        typeText = "Yoga",
        title = "Yoga for Beginners",
        onClick = {},
    )
}