package com.makeevrserg.koleso.feature.koleso.participants.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.makeevrserg.koleso.feature.koleso.participants.domain.model.ParticipantWithArc
import com.makeevrserg.koleso.resources.MR
import com.makeevrserg.koleso.service.resources.CatImagesExt.catImages
import io.github.skeptick.libres.compose.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParticipantWidget(
    entry: ParticipantWithArc,
    onDeleteClicked: () -> Unit,
    onEditClicked: () -> Unit
) {
    val arc = entry.arcModel
    val participant = entry.participantModel
    Card(
        modifier = Modifier.height(IntrinsicSize.Min),
        onClick = onEditClicked
    ) {
        Row(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(Modifier.fillMaxHeight().width(8.dp).clip(CircleShape).background(Color(arc.argbColor)))
            Column(verticalArrangement = Arrangement.spacedBy(4.dp), modifier = Modifier.width(IntrinsicSize.Min)) {
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    val catImages = MR.catImages
                    val i = participant.id.hashCode() % catImages.size
                    Icon(
                        painter = catImages[i].painterResource(),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = participant.desc,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Icon(
                            painter = MR.image.img_gold.painterResource(),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "${participant.point}",
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Icon(
                        painter = MR.image.img_lava_bucket.painterResource(),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(24.dp).clip(CircleShape).clickable {
                            onDeleteClicked.invoke()
                        }
                    )
                }
            }
        }
    }
}
