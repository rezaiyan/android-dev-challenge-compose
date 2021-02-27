/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.presentation.ui.screen.main.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.domain.Puppy
import com.example.androiddevchallenge.presentation.ui.theme.PlaceHolder
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.insets.ExperimentalAnimatedInsets
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import dev.chrisbanes.accompanist.insets.rememberImeNestedScrollConnection

@OptIn(ExperimentalAnimatedInsets::class)
@Composable
fun Puppies(
    puppies: List<Puppy>,
    searchAction: (String) -> Unit,
    navigateToDetails: (Puppy) -> Unit
) {
    ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
        Column(Modifier.fillMaxSize()) {
            val isSearched = remember { mutableStateOf(false) }

            if (puppies.isEmpty() && isSearched.value)
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    Text(text = "Not found :/")
                }
            else
                LazyColumn(
                    reverseLayout = false,
                    modifier = Modifier
                        .weight(1f)
                        .nestedScroll(connection = rememberImeNestedScrollConnection())
                ) {
                    items(items = puppies) { puppy ->
                        PuppyItem(puppy = puppy, navigateToDetails)
                    }
                }

            Surface(elevation = 4.dp) {
                val text = remember { mutableStateOf(TextFieldValue()) }
                OutlinedTextField(
                    value = text.value,
                    onValueChange = {
                        text.value = it
                        searchAction(it.text)
                        isSearched.value = it.text.isNotBlank()
                    },
                    placeholder = { Text(text = "Find your pet") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
fun PuppyItem(puppy: Puppy, navigateToDetails: (Puppy) -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable { navigateToDetails(puppy) } then Modifier
    ) {
        val (
            name, image
        ) = createRefs()

        CoilImage(
            data = puppy.imageUrl,
            contentDescription = "Photo of ${puppy.name}",
            contentScale = ContentScale.Crop,
            loading = { PlaceHolder() },
            error = { PlaceHolder() },
            modifier = Modifier
                .size(56.dp)
                .clip(MaterialTheme.shapes.medium)
                .constrainAs(image) {
                    end.linkTo(parent.end, 16.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )

        Text(
            text = puppy.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .constrainAs(name) {
                    start.linkTo(parent.start, 16.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}
