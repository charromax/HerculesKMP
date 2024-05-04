package org.charr0max.herculeskmp.presentation.home.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter
import org.charr0max.herculeskmp.data.model.WeatherForecastAPIResponse
import org.charr0max.herculeskmp.domain.model.DHTSensorData
import org.charr0max.herculeskmp.util.normalize

@Composable
fun DhtSensorComponent(
    modifier: Modifier = Modifier,
    dhtSensorData: DHTSensorData,
    weatherData: WeatherForecastAPIResponse
) {
    val painter =
        rememberImagePainter("https://openweathermap.org/img/wn/${weatherData.daily.firstOrNull()?.weather?.firstOrNull()?.icon}@2x.png")

    ElevatedCard(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            CardHeader(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                title = dhtSensorData.model.name,
                id = dhtSensorData.id,
                state = dhtSensorData.state
            )
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(50.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(
                    value = (dhtSensorData.h ?: 0f),
                    unit = "%",
                    title = "Humedad"
                )
                CircularProgressIndicator(
                    value = (dhtSensorData.t ?: 0f),
                    unit = "ºC",
                    title = "Temp."
                )
            }
        }

    }
}

@Composable
fun CircularProgressIndicator(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    arcColor: Color = MaterialTheme.colorScheme.primary,
    value: Float,
    unit: String,
    title: String
) {
    val progress: Float by animateFloatAsState(
        targetValue = value,
        animationSpec = tween(durationMillis = 1500)
    )
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        androidx.compose.foundation.Canvas(
            modifier = Modifier
                .size(150.dp)
        ) {
            drawArc(
                color = backgroundColor,
                startAngle = 140f,
                sweepAngle = 260f,
                useCenter = false,
                style = Stroke(10.dp.toPx(), cap = StrokeCap.Round),
            )
            drawArc(
                color = arcColor,
                startAngle = 140f,
                sweepAngle = progress.normalize() * 260f,
                useCenter = false,
                style = Stroke(10.dp.toPx(), cap = StrokeCap.Round),
            )
        }
        ProgressStatus(value, unit, title)
    }
}

@Composable
private fun ProgressStatus(
    currentValue: Float,
    unit: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(modifier = modifier, text = buildAnnotatedString {
            val emphasisSpan =
                MaterialTheme.typography.displayLarge.toSpanStyle()
            val defaultSpan =
                MaterialTheme.typography.titleLarge.toSpanStyle()
            append(AnnotatedString("$currentValue", spanStyle = emphasisSpan))
            append(AnnotatedString(text = unit, spanStyle = defaultSpan))
        })
        Text(
            modifier = Modifier.padding(8.dp),
            text = title,
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}



