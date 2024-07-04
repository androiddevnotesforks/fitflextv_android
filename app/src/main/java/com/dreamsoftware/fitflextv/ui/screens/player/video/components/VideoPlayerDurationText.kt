package com.dreamsoftware.fitflextv.ui.screens.player.video.components

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.dreamsoftware.fitflextv.ui.theme.FitFlexTVTheme

@Composable
internal fun VideoPlayerDurationText(
    textProgress: String,
    textDuration: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface
) {
    Row(modifier = modifier) {
        Text(
            text = "$textProgress / ",
            color = color,
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = textDuration,
            color = color.copy(alpha = 0.60f),
            style = MaterialTheme.typography.bodySmall
        )
    }
}


@Preview(device = Devices.TV_1080p)
@Composable
fun PreviewVideoPlayerControllerText() {
    FitFlexTVTheme {
        VideoPlayerDurationText(textProgress = "19:00", textDuration = "/20:35")
    }
}