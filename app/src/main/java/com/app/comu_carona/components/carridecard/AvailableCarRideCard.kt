package com.app.comu_carona.components.carridecard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.comu_carona.components.horizontalline.HorizontalLine
import com.app.comu_carona.feature.home.data.models.AvailableCarRide
import com.app.comu_carona.theme.GrayLine

@Composable
fun AvailableCarRideCard(
    modifier: Modifier = Modifier,
    availableCarRide: AvailableCarRide
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = White
        ),
        onClick = {},
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            AddressBox(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 15.dp),
                waitingHour = availableCarRide.waitingHour,
                destinationHour = availableCarRide.destinationHour,
                waitingAddress = availableCarRide.waitingAddress,
                destinationAddress = availableCarRide.destinationAddress
            )

            HorizontalLine(
                color = GrayLine,
                thickness = 1f
            )

            UserSelectionBox(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 15.dp),
                riderPhotoUrl = availableCarRide.riderPhotoUrl,
                riderUserName = availableCarRide.riderUserName,
                riderDescription = availableCarRide.riderDescription
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AvailableCarRideCardPreview() {
    AvailableCarRideCard(
        availableCarRide = AvailableCarRide(
            id = "1",
            waitingAddress = "Rua 1",
            destinationAddress = "Rua 2",
            waitingHour = "18:00",
            destinationHour = "19:00",
            riderPhotoUrl = "https://firebasestorage.googleapis.com/v0/b/comu-carona.firebasestorage.app/o/uploads%2Fupload2233688854578175299.tmp?alt=media&token=486be14c-5708-4c8d-a1e6-650867480c0a",
            riderUserName = "João",
            riderDescription = "Descrição"
        )
    )
}