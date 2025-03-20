package com.app.comu_carona.components.boxselection

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.comu_carona.R
import com.app.comu_carona.components.horizontalline.HorizontalLine
import com.app.comu_carona.theme.Primary
import com.app.comu_carona.theme.TextFieldColor

@Composable
fun CCBoxSelection(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    iconImageVector: ImageVector? = null,
    iconPainter: Painter? = null,
    iconColor: Color? = null,
    iconSize: Int = 25,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if(iconImageVector != null) {
                Icon(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(iconSize.dp),
                    imageVector = iconImageVector,
                    tint = iconColor ?: TextFieldColor,
                    contentDescription = null,
                )
            }

            if(iconPainter != null) {
                Icon(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(iconSize.dp),
                    painter = iconPainter,
                    tint = iconColor ?: TextFieldColor,
                    contentDescription = null,
                )
            }

            Column {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp),
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Primary,
                    fontWeight = Bold
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = description,
                    style = MaterialTheme.typography.labelSmall,
                    color = TextFieldColor,
                    fontWeight = Bold
                )
            }
        }

        HorizontalLine(modifier = Modifier.padding(top = 10.dp, start = 15.dp, end = 15.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun CCBoxSelectionPreview() {
    CCBoxSelection(
        title = "Title",
        description = "Description",
        iconPainter = painterResource(R.drawable.ic_wpp)
    )
}