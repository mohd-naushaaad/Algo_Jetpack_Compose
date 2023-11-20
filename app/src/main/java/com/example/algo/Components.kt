package com.example.algo

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.algo.ui.theme.black1
import com.example.algo.ui.theme.black3
import com.example.algo.ui.theme.black7
import com.example.algo.ui.theme.darkYellow
import com.example.algo.ui.theme.roboto

@Composable
fun SearchEditTextComponent(
    textState: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    icon: Int,
    contentDescription: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
) {

    TextField(
        colors = TextFieldDefaults.colors(
            focusedContainerColor = black3.copy(0.5f),
            unfocusedContainerColor = black3.copy(0.5f),
            disabledContainerColor = black3.copy(0.5f),
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.White.copy(0.5f),
            focusedLeadingIconColor = Color.White.copy(0.5f),
            unfocusedLeadingIconColor = Color.White.copy(0.5f),
            disabledTextColor = Color.White.copy(0.5f),
            unfocusedTextColor = Color.White.copy(0.5f),
            disabledSupportingTextColor = Color.White.copy(0.5f),
            focusedSupportingTextColor = Color.White.copy(0.5f),
            unfocusedSupportingTextColor = Color.White.copy(0.5f),
            focusedTextColor = Color.White.copy(0.5f),
            focusedPlaceholderColor = Color.White.copy(0.5f),
            unfocusedPlaceholderColor = Color.White.copy(0.5f)
        ),
        shape = RoundedCornerShape(100),
        value = textState,
        onValueChange = { onTextChange(it) },
        modifier = modifier,
        placeholder = { Text(text = label) },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        leadingIcon = {
            Icon(
                painterResource(id = icon),
                contentDescription = contentDescription
            )
        },
        textStyle = MaterialTheme.typography.titleSmall,
    )

}

@Composable
fun DashedVerticalLine(modifier: Modifier = Modifier, height: Dp, width: Dp, color: Color) {
    Canvas(
        modifier = modifier
            .width(3.dp)
            .height(height)
    ) {
        val startX = 4f
        val startY = 4f
        val endX = 4f
        val endY = height.toPx()

        drawLine(
            color = color,
            start = Offset(startX, startY),
            end = Offset(endX, endY),
            strokeWidth = width.toPx(),
            pathEffect = PathEffect.dashPathEffect(
                phase = 0f,
                intervals = floatArrayOf(20f, 20f),
            )
        )
    }
}


@Composable
fun CircularImageWithBackground(
    size: Dp = 50.dp,
    icon: Int = R.drawable.ic_upload,
) {

    Box(
        modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(100))
            .border(
                width = 1.dp,
                color = black7,
                shape = RoundedCornerShape(100)
            )
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {

        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = icon),
            contentDescription = "icon",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun IconOutlinedEditTextComponentPreview() {
    SearchEditTextComponent(
        textState = "Hello",
        onTextChange = {},
        label = "",
        icon = R.drawable.ic_search,
        contentDescription = "",
        keyboardOptions = KeyboardOptions()
    )
}

@Composable
fun IconButtonComponent(
    modifier: Modifier = Modifier,
    icon: Int,
    isBadge: Boolean = false,
    size: Dp = 50.dp,
    onClick: () -> Unit,
) {

    IconButton(
        modifier = modifier.size(size),
        onClick = { onClick() },
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Color.White.copy(alpha = 0.1f)
        )
    ) {
        Box {

            Icon(
                painter = painterResource(id = icon),
                contentDescription = "wallet",
                tint = Color.White,
            )

            if (isBadge) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_red_circle),
                    contentDescription = "circle",
                    tint = Color.Red,
                    modifier = Modifier.offset(y = 2.dp, x = 2.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IconButtonComponentPreview() {
    IconButtonComponent(
        icon = R.drawable.ic_bell
    ) {
    }
}


@Composable
fun TextLabelLargeComponent(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.White,
    lineHeight: TextUnit = TextUnit.Unspecified
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.Normal,
        lineHeight = lineHeight,
        color = color
    )
}

@Preview(showBackground = true)
@Composable
fun TextLabelLargeComponentPreview() {
    TextLabelLargeComponent(modifier = Modifier, "Hello")
}

@Composable
fun TextDisplaySmallComponent(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.White,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.displaySmall,
        fontWeight = FontWeight.Normal,
        fontFamily = roboto,
        color = color
    )
}

@Preview(showBackground = true)
@Composable
fun TextDisplaySmallComponentPreview() {
    TextDisplaySmallComponent(modifier = Modifier, "Hello")
}

@Composable
fun TextBodyMediumComponent(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.White,
    fontWeight: FontWeight = FontWeight.Normal,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = fontWeight,
        color = color
    )
}

@Preview(showBackground = true)
@Composable
fun TextBodyMediumComponentPreview() {
    TextBodyMediumComponent(modifier = Modifier, "Hello")
}

@Composable
fun TextTitleLargeComponent(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.White,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Medium,
        color = color
    )
}

@Preview(showBackground = true)
@Composable
fun TextTitleLargeComponentPreview() {
    TextTitleLargeComponent(modifier = Modifier, "Hello")
}

@Composable
fun TextTitleMediumComponent(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.White,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = fontWeight,
        color = color
    )
}

@Preview(showBackground = true)
@Composable
fun TextTitleMediumComponentPreview() {
    TextTitleMediumComponent(modifier = Modifier, "Hello")
}


@Composable
fun TextTitleSmallComponent(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.White,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = fontWeight,
        color = color
    )
}

@Preview(showBackground = true)
@Composable
fun TextTitleSmallComponentPreview() {
    TextTitleSmallComponent(modifier = Modifier, "Hello")
}

@Composable
fun TextHeadingSmallComponent(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.White
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Medium,
        color = color
    )
}

@Preview(showBackground = true)
@Composable
fun TextHeadingSmallComponentPreview() {
    TextHeadingSmallComponent(modifier = Modifier, "Hello")
}

@Composable
fun TextDisplayLargeComponent(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.White
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.displayLarge,
        fontSize = 40.sp,
        fontWeight = FontWeight.Medium,
        color = color
    )
}

@Preview(showBackground = true)
@Composable
fun TextDisplayLargeSmallComponentPreview() {
    TextDisplayLargeComponent(modifier = Modifier, "Hello")
}

@Composable
fun BoxIndicatorComponent(
    modifier: Modifier,
    color: Color
) {
    Box(
        modifier = modifier
            .height(10.dp)
            .width(24.dp)
            .clip(RoundedCornerShape(100))
            .background(color)
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun BoxIndicatorComponentPreview() {
    BoxIndicatorComponent(
        modifier = Modifier,
        color = darkYellow
    )
}