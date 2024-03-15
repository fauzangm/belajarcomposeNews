package id.android.belajarcomposenewsapps.presentation.common

import android.app.Activity
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import id.android.belajarcomposenewsapps.R

@Composable
 fun DialogPrimary(
    subTittle: String,
    modifier: Modifier = Modifier
) {
    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest = {

        },
        title = { Text(text = stringResource(R.string.error_messange)) },
        text = { Text(text = subTittle) },
        modifier = modifier,

        confirmButton = {
            TextButton(onClick = {

            }) {
                Text(text = "Ok")
            }
        }
    )
}