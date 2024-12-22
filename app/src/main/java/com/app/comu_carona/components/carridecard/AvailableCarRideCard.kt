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
import com.app.comu_carona.theme.GrayLine

@Composable
fun AvailableCarRideCard(
    modifier: Modifier = Modifier,
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
                initHourProp = "10:00",
                finishHourProp = "12:00",
                initAddressProp = "Rua pandia calógeras - praça da xxxxsadasdasdasdas",
                finishAddressProp = "Rua pandia calógeras - praça"
            )
            
            HorizontalLine(
                color = GrayLine,
                thickness = 1f
            )

            UserSelectionBox(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 15.dp),
                userImageUrl = "https://firebasestorage.googleapis.com/v0/b/comu-carona.firebasestorage.app/o/uploads%2Fupload2233688854578175299.tmp?alt=media&token=486be14c-5708-4c8d-a1e6-650867480c0a",
                userName = "João",
                userDescription = "Participa de alvo da mocidade"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AvailableCarRideCardPreview() {
    AvailableCarRideCard()
}