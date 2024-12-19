package com.app.comu_carona.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale.Companion.FillBounds
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.app.comu_carona.R
import com.app.comu_carona.theme.GrayLine
import com.app.comu_carona.theme.SoftBlack

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(vertical = 10.dp)
    ) {
        HomeTopBar()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(GrayLine),
        ) {  }
    }
}

@Composable
fun HomeTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.home_username_title, "Teste"),
            style = MaterialTheme.typography.titleLarge,
            color = SoftBlack,
            fontWeight = Bold,
            textAlign = TextAlign.Center,
        )
        Image(
            painter = rememberAsyncImagePainter(model = "https://firebasestorage.googleapis.com/v0/b/comu-carona.firebasestorage.app/o/uploads%2Fupload2233688854578175299.tmp?alt=media&token=486be14c-5708-4c8d-a1e6-650867480c0a"),
            contentScale = FillBounds,
            contentDescription = null,
            modifier = Modifier
                .size(45.dp)
                .clip(CircleShape)
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}