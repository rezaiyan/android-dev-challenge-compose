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
package com.example.androiddevchallenge.presentation.ui.screen.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.domain.Puppy
import com.example.androiddevchallenge.presentation.PuppyViewModel
import com.example.androiddevchallenge.presentation.ui.screen.details.DetailsActivity
import com.example.androiddevchallenge.presentation.ui.screen.main.view.Puppies
import com.example.androiddevchallenge.presentation.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    private val viewModel: PuppyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getPuppies()
        setContent {
            MyTheme {
                ContentView(viewModel)
            }
        }
    }

    @Composable
    fun ContentView(viewModel: PuppyViewModel) {
        val puppies = viewModel.puppiesLiveData.observeAsState(emptyList())
        Surface(color = MaterialTheme.colors.background) {
            Puppies(
                puppies = puppies.value,
                {
                    viewModel.find(it)
                },
                navigateToDetails = {
                    goToDetails(it)
                },
            )
        }
    }

    @Preview("Light Theme", widthDp = 360, heightDp = 640)
    @Composable
    fun LightPreview() {
        MyTheme {
            ContentView(viewModel)
        }
    }

    @Preview("Dark Theme", widthDp = 360, heightDp = 640)
    @Composable
    fun DarkPreview() {
        MyTheme(darkTheme = true) {
            ContentView(viewModel)
        }
    }

    private fun goToDetails(puppy: Puppy) {
        startActivity(
            Intent(this, DetailsActivity::class.java).apply {
                putExtra("puppy", puppy)
            }
        )
    }
}
