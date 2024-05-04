package org.charr0max.herculeskmp.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter
import dev.sergiobelda.compose.vectorize.images.Images
import dev.sergiobelda.compose.vectorize.images.icons.HumidityIcon
import org.charr0max.herculeskmp.data.model.WeatherForecastAPIResponse
import org.charr0max.herculeskmp.domain.model.DHTSensorData
import org.charr0max.herculeskmp.util.roundToDecimals
import kotlin.math.roundToInt

@Composable
fun WeatherInformationComponent(
    modifier: Modifier = Modifier,
    dhtSensorData: DHTSensorData,
    weatherData: WeatherForecastAPIResponse
) {
    val painter = rememberImagePainter(weatherData.currentEarlyMorning?.iconUrl.orEmpty())
    Card(modifier = modifier, shape = RectangleShape) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.size(120.dp)
                )
                Text(
                    "Hoy",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(8.dp)
                )
                Box(contentAlignment = Alignment.BottomCenter) {
                    Text(modifier = Modifier.padding(end = 8.dp, top = 24.dp),
                        text = buildAnnotatedString {
                            val emphasisSpan = MaterialTheme.typography.displayLarge.copy(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            ).toSpanStyle()
                            val defaultSpan =
                                MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.primary)
                                    .toSpanStyle()
                            append(AnnotatedString("${dhtSensorData.t}", spanStyle = emphasisSpan))
                            append(AnnotatedString(text = "ºC", spanStyle = defaultSpan))
                        })
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().absoluteOffset(y = (-16).dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = CenterVertically
            ) {
                Text(
                    text = "${weatherData.currentEarlyMorning?.main?.tempMin?.roundToDecimals()}º / ${weatherData.currentNoon?.main?.tempMax?.roundToDecimals()}º",
                    style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.primary)
                )
                Icon(
                    imageVector = Images.Icons.HumidityIcon,
                    modifier = Modifier.padding(start = 8.dp).size(20.dp),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "${dhtSensorData.h?.roundToInt()}%",
                    style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.primary)
                )
            }
            Divider(
                modifier = Modifier.padding(8.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.secondary
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    "Mañana",
                    style = MaterialTheme.typography.titleMedium,
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                ) {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier.size(48.dp)
                    )
                    Text(
                        text = "${weatherData.tomorrowEarlyMorning?.main?.tempMin?.roundToDecimals()}º / ${weatherData.tomorrowNoon?.main?.tempMax?.roundToDecimals()}º",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Icon(
                        imageVector = Images.Icons.HumidityIcon,
                        modifier = Modifier.padding(start = 8.dp).size(20.dp),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "${weatherData.tomorrowEarlyMorning?.main?.humidity}%",
                        style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onBackground)
                    )
                }
            }
        }
    }
}