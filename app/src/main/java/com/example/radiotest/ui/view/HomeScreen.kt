@file:Suppress("UNCHECKED_CAST")

package com.example.radiotest.ui.view

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.radiotest.domain.UiStation


@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {
    val mUiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    when (mUiState) {
        is UiState.Loading -> {
            Radios(true, emptyList())
        }

        is UiState.Success -> {
            Radios(false, (mUiState as UiState.Success).data)
        }

        is UiState.Error -> {
            Toast.makeText(LocalContext.current, "Error", Toast.LENGTH_SHORT).show()
        }
    }

}

@Composable
private fun Radios(
    isLoading: Boolean,
    stations: List<UiStation>
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            RadioStations(stations)
        }
    }
}


@Composable
private fun RadioStations(
    stations: List<UiStation>
) {
    LazyColumn {
        items(stations) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = it.stationName,
                        fontSize = 24.sp,
                        fontFamily = FontFamily.Serif
                    )
                }
            }
        }
    }
}