package com.app.comu_carona.components.carridecard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.comu_carona.components.horizontalline.HorizontalLine
import com.app.comu_carona.components.tag.CCTag
import com.app.comu_carona.feature.home.steps.rideinprogress.data.models.RideInProgressFilterOptions
import com.app.comu_carona.theme.BackgroundSkeleton
import com.app.comu_carona.theme.TextFieldLineColor
import com.valentinilk.shimmer.shimmer

@Composable
fun AvailableCarRideCard(
    modifier: Modifier = Modifier,
    waitingHour: String,
    destinationHour: String,
    waitingAddress: String,
    destinationAddress: String,
    riderPhotoUrl: String,
    riderUserName: String,
    riderDescription: String,
    status: List<RideInProgressFilterOptions> = emptyList(),
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = TextFieldLineColor,
                shape = CardDefaults.shape
            ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = White
        ),
        onClick = onClick,
    ) {
        Column {
            if(status.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .padding(top = 12.dp)
                ) {
                    status.map { items ->
                        CCTag(
                            modifier = Modifier
                                .padding(start = 10.dp),
                            title = items.title,
                            color = items.color
                        )
                    }
                }
            }

            AddressBox(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 15.dp),
                waitingHour = waitingHour,
                destinationHour = destinationHour,
                waitingAddress = waitingAddress,
                destinationAddress = destinationAddress,
                showCopyAddress = false
            )

            HorizontalLine(
                modifier = Modifier.padding(top = 10.dp),
                color = TextFieldLineColor,
                thickness = 1f
            )

            UserSelectionBox(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 15.dp),
                riderPhotoUrl = riderPhotoUrl,
                riderUserName = riderUserName,
                riderDescription = riderDescription
            )
        }
    }
}

@Composable
fun AvailableCarRideCardLoading(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .shimmer()
            .fillMaxWidth()
            .height(130.dp)
            .background(
                BackgroundSkeleton,
                shape = RoundedCornerShape(10.dp)
            )
    )
}


@Preview(showBackground = true)
@Composable
fun AvailableCarRideCardPreview() {
    AvailableCarRideCard(
        waitingAddress = "Rua 1",
        destinationAddress = "Rua 2",
        waitingHour = "18:00",
        destinationHour = "19:00",
        riderPhotoUrl = "https://firebasestorage.googleapis.com/v0/b/comu-carona.firebasestorage.app/o/uploads%2Fupload2233688854578175299.tmp?alt=media&token=486be14c-5708-4c8d-a1e6-650867480c0a",
        riderUserName = "João",
        riderDescription = "Descrição"
    )
}

@Preview(showBackground = true)
@Composable
fun AvailableCarRideCardLoadingPreview() {
    AvailableCarRideCardLoading()
}