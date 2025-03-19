package com.app.comu_carona.components.carridecard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale.Companion.FillBounds
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.comu_carona.R
import com.app.comu_carona.components.shimmerimage.CCShimmerImage
import com.app.comu_carona.theme.BackgroundSkeleton
import com.app.comu_carona.theme.Primary
import com.app.comu_carona.theme.TextFieldLightColor
import com.app.comu_carona.theme.UrbanistFontFamily
import com.valentinilk.shimmer.shimmer

@Composable
fun UserSelectionBox(
    modifier: Modifier = Modifier,
    riderPhotoUrl: String,
    riderUserName: String,
    riderDescription: String,
    showCar: Boolean = true,
    showArrow: Boolean = true,
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = White
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(showCar) {
                Image(
                    modifier = Modifier
                        .size(30.dp)
                        .weight(0.1f)
                        .padding(end = 10.dp),
                    painter = painterResource(R.drawable.ic_car_ride),
                    contentDescription = "car_Ride_Icon",
                )
            }

            Row(
                modifier = Modifier
                    .weight(0.7f)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                CCShimmerImage(
                    imageUrl = riderPhotoUrl,
                    contentScale = FillBounds,
                    imageSize = 35
                )

                Column(
                    modifier = Modifier.padding(start = 10.dp),
                ) {
                    Text(
                        text = riderUserName,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Primary,
                        fontWeight = Bold
                    )

                    Spacer(modifier = Modifier.size(5.dp))

                    Text(
                        text = riderDescription,
                        style = MaterialTheme.typography.bodySmall,
                        color = TextFieldLightColor,
                        fontFamily = UrbanistFontFamily,
                        fontWeight = Bold
                    )
                }
            }

            if(showArrow) {
                Icon(
                    modifier = Modifier
                        .size(30.dp)
                        .padding(start = 10.dp)
                        .weight(0.1f),
                    painter = rememberVectorPainter(image = Icons.Default.ArrowForward),
                    contentDescription = "Arrow_Forward",
                    tint = Primary
                )
            }
        }
    }
}

@Composable
fun UserSelectionBoxShimmer(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .shimmer(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .weight(0.1f)
                    .padding(end = 10.dp)
                    .background(BackgroundSkeleton)
            )

            Row(
                modifier = Modifier
                    .weight(0.7f)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                        .background(BackgroundSkeleton)
                )

                Column(
                    modifier = Modifier.padding(start = 10.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .height(20.dp)
                            .fillMaxWidth(0.5f)
                            .background(BackgroundSkeleton)
                    )

                    Spacer(modifier = Modifier.size(5.dp))

                    Box(
                        modifier = Modifier
                            .height(15.dp)
                            .fillMaxWidth(0.7f)
                            .background(BackgroundSkeleton)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .size(30.dp)
                    .padding(start = 10.dp)
                    .weight(0.1f)
                    .background(BackgroundSkeleton)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserSelectionBoxShimmerPreview() {
    UserSelectionBoxShimmer()
}

@Preview(showBackground = true)
@Composable
fun UserSelectionBoxPreview() {
    UserSelectionBox(
        riderPhotoUrl = "https://i.pin",
        riderUserName = "Teste",
        riderDescription = "Teste"
    )
}