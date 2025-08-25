package com.formation.composeformation.ui.day_2.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.formation.composeformation.R
import com.formation.composeformation.domain.movie.model.CreditDomain

@Composable
fun PersonLayoutView(
    item: CreditDomain
) {
    Column(
        modifier = Modifier
            .width(120.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .width(120.dp)
                .height(160.dp)
                .clip(RoundedCornerShape(4.dp)),
            model = item.profilePath,
            error = painterResource(R.drawable.placeholder),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Text(
            text = item.name ?: "",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = item.character,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onBackground,
            overflow = TextOverflow.Ellipsis
        )
    }
}
