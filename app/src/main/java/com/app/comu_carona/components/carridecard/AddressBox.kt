package com.app.comu_carona.components.carridecard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileCopy
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.app.comu_carona.commons.usecase.CopyToClipboardUseCase
import com.app.comu_carona.theme.BackgroundSkeleton
import com.app.comu_carona.theme.Primary
import com.app.comu_carona.theme.TextFieldColor
import com.app.comu_carona.theme.UrbanistFontFamily
import com.valentinilk.shimmer.shimmer
import org.koin.compose.koinInject

@Composable
fun AddressBox(
    modifier: Modifier = Modifier,
    waitingHour: String,
    destinationHour: String,
    waitingAddress: String,
    destinationAddress: String,
    showCopyAddress: Boolean = true
) {
    var addressHeight by remember { mutableStateOf(0.dp) }
    var copyToClipboardUseCase = koinInject<CopyToClipboardUseCase>()

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
            finishAddress,
            copyIconInitAddress,
            copyIconFinishAddress
        ) = createRefs()

        Text(
            text = waitingHour,
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
            text = destinationHour,
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
            text = waitingAddress,
            style = MaterialTheme.typography.bodySmall,
            fontFamily = UrbanistFontFamily,
            color = TextFieldColor,
            fontWeight = Normal,
            modifier = Modifier
                .constrainAs(initAddress) {
                    top.linkTo(initPoint.top, margin = (-3).dp)
                    start.linkTo(initPoint.end, margin = 8.dp)
                    end.linkTo(copyIconInitAddress.start)
                    width = Dimension.fillToConstraints
                }
                .onGloballyPositioned { coordinates ->
                    addressHeight = (coordinates.size.height * 0.4).dp
                }
        )

        IconButton(
            onClick = {
                copyToClipboardUseCase(waitingAddress)
            },
            modifier = Modifier
                .constrainAs(copyIconInitAddress) {
                    top.linkTo(initPoint.top, margin = 8.dp)
                    bottom.linkTo(initPoint.bottom, margin = 8.dp)
                    end.linkTo(parent.end, margin = 8.dp)
                }
        ) {
            if (showCopyAddress) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Outlined.FileCopy,
                    contentDescription = null,
                    tint = Primary
                )
            }
        }

        Text(
            text = destinationAddress,
            style = MaterialTheme.typography.bodySmall,
            fontFamily = UrbanistFontFamily,
            color = TextFieldColor,
            fontWeight = Normal,
            modifier = Modifier.constrainAs(finishAddress) {
                top.linkTo(finishPoint.top)
                bottom.linkTo(finishPoint.bottom)
                start.linkTo(finishPoint.end, margin = 8.dp)
                end.linkTo(copyIconFinishAddress.start)
                width = Dimension.fillToConstraints
            }
        )

        IconButton(
            onClick = {
                copyToClipboardUseCase(destinationAddress)
            },
            modifier = Modifier
                .constrainAs(copyIconFinishAddress) {
                    top.linkTo(finishPoint.top, margin = 8.dp)
                    bottom.linkTo(finishPoint.bottom, margin = 8.dp)
                    end.linkTo(parent.end, margin = 8.dp)
                }
        ) {
            if (showCopyAddress) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Outlined.FileCopy,
                    contentDescription = null,
                    tint = Primary
                )
            }
        }
    }
}


@Composable
fun AddressBoxShimmer(
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .shimmer()
    ) {
        val (
            initPoint,
            finishPoint,
            linePoint,
            initAddress,
            finishAddress
        ) = createRefs()

        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(BackgroundSkeleton)
                .constrainAs(initPoint) {
                    start.linkTo(parent.start, margin = 8.dp)
                    top.linkTo(parent.top, margin = 8.dp)
                }
        )

        Box(
            modifier = Modifier
                .background(BackgroundSkeleton)
                .width(4.dp)
                .height(30.dp)
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
                .background(BackgroundSkeleton)
                .constrainAs(finishPoint) {
                    start.linkTo(parent.start, margin = 8.dp)
                    top.linkTo(linePoint.bottom)
                }
        )

        Box(
            modifier = Modifier
                .height(20.dp)
                .fillMaxWidth(0.7f)
                .background(BackgroundSkeleton)
                .constrainAs(initAddress) {
                    top.linkTo(initPoint.top)
                    start.linkTo(initPoint.end, margin = 8.dp)
                }
        )

        Box(
            modifier = Modifier
                .height(20.dp)
                .fillMaxWidth(0.7f)
                .background(BackgroundSkeleton)
                .constrainAs(finishAddress) {
                    top.linkTo(finishPoint.top)
                    start.linkTo(finishPoint.end, margin = 8.dp)
                }
        )
    }
}

@Preview
@Composable
fun AddressBoxShimmerPreview() {
    AddressBoxShimmer()
}

@Preview(showBackground = true)
@Composable
fun AddressBoxPreview() {
    AddressBox(
        waitingHour = "10:00",
        destinationHour = "12:00",
        waitingAddress = "Rua pandia calógeras - praça da xxxxsadasdasdasdas",
        destinationAddress = "Rua pandia calógeras - praça",
        showCopyAddress = true
    )
}