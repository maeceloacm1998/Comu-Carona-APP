package com.app.comu_carona.components.bottomsheet

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

/**
 * A custom bottom sheet component.
 *
 * @param showSheet Whether the bottom sheet is visible.
 * @param onDismissRequest Called when the bottom sheet is dismissed.
 * @param modifier Modifier to be applied to the bottom sheet.
 * @param sheetState State of the bottom sheet.
 * @param shape Shape of the bottom sheet.
 * @param containerColor Background color of the bottom sheet.
 * @param contentColor Content color of the bottom sheet.
 * @param tonalElevation Elevation of the bottom sheet.
 * @param scrimColor Color of the scrim behind the bottom sheet.
 * @param dragHandle Composable to display a drag handle.
 * @param content Content of the bottom sheet.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CCBottomSheet(
    showSheet: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    shape: Shape = BottomSheetDefaults.ExpandedShape,
    containerColor: Color = BottomSheetDefaults.ContainerColor,
    contentColor: Color = contentColorFor(containerColor),
    tonalElevation: Dp = BottomSheetDefaults.Elevation,
    scrimColor: Color = BottomSheetDefaults.ScrimColor,
    dragHandle: @Composable (() -> Unit)? = { BottomSheetDefaults.DragHandle() },
    content: @Composable ColumnScope.() -> Unit,
) {
    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            modifier = modifier,
            sheetState = sheetState,
            shape = shape,
            containerColor = containerColor,
            contentColor = contentColor,
            tonalElevation = tonalElevation,
            scrimColor = scrimColor,
            dragHandle = dragHandle,
            content = content
        )
    }
}