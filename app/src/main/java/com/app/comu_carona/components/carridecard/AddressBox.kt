package com.app.comu_carona.components.carridecard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.app.comu_carona.theme.Primary
import com.app.comu_carona.theme.TextFieldColor
import com.app.comu_carona.theme.UrbanistFontFamily

@Composable
fun AddressBox(
    modifier: Modifier = Modifier,
    initHourProp: String,
    finishHourProp: String,
    initAddressProp: String,
    finishAddressProp: String
) {
    var addressHeight by remember { mutableStateOf(0.dp) }

    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {
        val (
            initHour,
            finishHour,
            initPoint,
            finishPoint,
            linePoint,
            initAddress,
            finishAddress
        ) = createRefs()

        Text(
            text = initHourProp,
            style = MaterialTheme.typography.bodySmall,
            color = TextFieldColor,
            fontWeight = Bold,
            modifier = Modifier.constrainAs(initHour) {
                top.linkTo(initPoint.top)
                bottom.linkTo(initPoint.bottom)
                start.linkTo(parent.start)
            }
        )

        Text(
            text = finishHourProp,
            style = MaterialTheme.typography.bodySmall,
            color = TextFieldColor,
            fontWeight = Bold,
            modifier = Modifier.constrainAs(finishHour) {
                bottom.linkTo(parent.bottom)
            }
        )

        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .border(1.dp, Primary, CircleShape)
                .background(White)
                .constrainAs(initPoint) {
                    start.linkTo(initHour.end, margin = 8.dp)
                    bottom.linkTo(initAddress.bottom)
                    top.linkTo(initAddress.top)
                }
        )

        Box(
            modifier = Modifier
                .background(Primary)
                .width(4.dp)
                .height(addressHeight)
                .constrainAs(linePoint) {
                    top.linkTo(initPoint.bottom)
                    start.linkTo(initPoint.start)
                    end.linkTo(initPoint.end)
                    bottom.linkTo(finishPoint.top)
                }
        )

        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .border(1.dp, Primary, CircleShape)
                .background(White)
                .constrainAs(finishPoint) {
                    start.linkTo(finishHour.end, margin = 8.dp)
                    bottom.linkTo(finishHour.bottom)
                    top.linkTo(finishHour.top)
                }
        )

        Text(
            text = initAddressProp,
            style = MaterialTheme.typography.bodySmall,
            fontFamily = UrbanistFontFamily,
            color = TextFieldColor,
            fontWeight = Normal,
            modifier = Modifier
                .constrainAs(initAddress) {
                    top.linkTo(initPoint.top, margin = (-3).dp)
                    start.linkTo(initPoint.end, margin = 8.dp)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .onGloballyPositioned { coordinates ->
                    addressHeight = (coordinates.size.height * 0.4).dp
                }
        )

        Text(
            text = finishAddressProp,
            style = MaterialTheme.typography.bodySmall,
            fontFamily = UrbanistFontFamily,
            color = TextFieldColor,
            fontWeight = Normal,
            modifier = Modifier.constrainAs(finishAddress) {
                top.linkTo(finishPoint.top)
                bottom.linkTo(finishPoint.bottom)
                start.linkTo(finishPoint.end, margin = 8.dp)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AddressBoxPreview() {
    AddressBox(
        initHourProp = "10:00",
        finishHourProp = "12:00",
        initAddressProp = "Rua pandia calógeras - praça da xxxxsadasdasdasdas",
        finishAddressProp = "Rua pandia calógeras - praça"
    )
}