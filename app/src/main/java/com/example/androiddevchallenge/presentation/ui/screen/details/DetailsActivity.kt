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
package com.example.androiddevchallenge.presentation.ui.screen.details

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.domain.Puppy
import com.example.androiddevchallenge.presentation.ui.screen.details.view.PuppyDetails
import com.example.androiddevchallenge.presentation.ui.theme.MyTheme

class DetailsActivity : AppCompatActivity() {

    private val puppy: Puppy by lazy {
        intent.getParcelableExtra("puppy")!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                ContentView()
            }
        }
    }

    @Composable
    fun ContentView() {
        Surface(color = MaterialTheme.colors.background) {
            PuppyDetails(puppy) {
                Toast.makeText(this, R.string.no_functionality, Toast.LENGTH_SHORT).show()
            }
        }
    }

    @Preview("Light Theme", widthDp = 360, heightDp = 640)
    @Composable
    fun LightPreview() {
        MyTheme {
            ContentView()
        }
    }

    @Preview("Dark Theme", widthDp = 360, heightDp = 640)
    @Composable
    fun DarkPreview() {
        MyTheme(darkTheme = true) {
            ContentView()
        }
    }
}
