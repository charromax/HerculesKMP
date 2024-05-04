package org.charr0max.herculeskmp.presentation.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.charr0max.herculeskmp.domain.model.MC38SensorData

@Composable
fun TriggerSensorComponent(
    modifier: Modifier = Modifier,
    data: MC38SensorData
) {
    ElevatedCard(
        modifier = modifier.size(200.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            CardHeader(
                modifier = Modifier.padding(8.dp),
                title = data.model.name,
                id = data.id,
                state = data.state
            )
            Box(
                modifier = Modifier.fillMaxSize().padding(4.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Icon(
                    imageVector = if (data.triggered) Icons.Filled.Lock else Icons.Outlined.Lock,
                    contentDescription = "",
                    modifier = Modifier.size(100.dp),
                    tint = if (data.triggered) Color.Red else Color.Black
                )
            }
        }

    }
}
