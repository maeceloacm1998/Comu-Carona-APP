package com.app.comu_carona.components.photoselect

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ContentScale.Companion.FillBounds
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.app.comu_carona.R
import com.app.comu_carona.theme.GrayLight
import com.app.comu_carona.theme.TextFieldColor
import com.app.comu_carona.theme.TextFieldLineColor

@Composable
fun PhotoUriComponent(
    photoUri: Uri,
    onPhotoSelected: (Uri?) -> Unit
) {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            onPhotoSelected(result.data?.data)
        }
    }

    Column(
        modifier = Modifier
            .size(300.dp)
            .background(GrayLight, shape = RoundedCornerShape(500.dp))
            .clickable {
                val intent = Intent(Intent.ACTION_PICK).apply {
                    type = "image/*"
                }
                launcher.launch(intent)
            }
            .border(1.dp, TextFieldLineColor, shape = RoundedCornerShape(500.dp)),
        verticalArrangement = Center,
        horizontalAlignment = CenterHorizontally
    ) {
        AnimatedVisibility(
            photoUri != Uri.EMPTY
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = photoUri),
                contentScale = FillBounds,
                contentDescription = null,
                modifier = Modifier
                    .size(300.dp)
                    .clip(CircleShape)
            )
        }

        AnimatedVisibility(photoUri == Uri.EMPTY) {
            Column(
                verticalArrangement = Center,
                horizontalAlignment = CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.size(70.dp),
                    imageVector = Icons.TwoTone.Person,
                    contentDescription = null,
                    tint = TextFieldColor
                )

                Text(
                    text = stringResource(id = R.string.register_account_stage_of_photo_circular_button_title),
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextFieldColor
                )
            }
        }
    }
}

@Composable
fun PhotoUrlComponent(
    photoUrl: String,
    isLoading: Boolean,
    sizeOfImageBox: Int = 300,
    onPhotoSelected: (Uri?) -> Unit
) {
    val loadingLottieAnimation by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.loading_lottie)
    )


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            onPhotoSelected(result.data?.data)
        }
    }

    Column(
        modifier = Modifier
            .size(sizeOfImageBox.dp)
            .background(GrayLight, shape = RoundedCornerShape(500.dp))
            .clickable {
                val intent = Intent(Intent.ACTION_PICK).apply {
                    type = "image/*"
                }
                launcher.launch(intent)
            }
            .border(1.dp, TextFieldLineColor, shape = RoundedCornerShape(500.dp)),
        verticalArrangement = Center,
        horizontalAlignment = CenterHorizontally
    ) {
        AnimatedVisibility(
            photoUrl.isNotEmpty()
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = photoUrl),
                contentScale = FillBounds,
                contentDescription = null,
                modifier = Modifier
                    .size(300.dp)
                    .clip(CircleShape)
            )
        }

        AnimatedVisibility(photoUrl.isEmpty()) {
            Column(
                verticalArrangement = Center,
                horizontalAlignment = CenterHorizontally
            ) {
                if(isLoading) {
                    LottieAnimation(
                        composition = loadingLottieAnimation,
                        modifier = Modifier
                            .size(100.dp),
                        contentScale = ContentScale.Crop,
                        iterations = LottieConstants.IterateForever
                    )
                } else {
                    Icon(
                        modifier = Modifier.size(70.dp),
                        imageVector = Icons.TwoTone.Person,
                        contentDescription = null,
                        tint = TextFieldColor
                    )

                    Text(
                        text = stringResource(id = R.string.register_account_stage_of_photo_circular_button_title),
                        style = MaterialTheme.typography.bodyLarge,
                        color = TextFieldColor
                    )
                }
            }
        }
    }
}