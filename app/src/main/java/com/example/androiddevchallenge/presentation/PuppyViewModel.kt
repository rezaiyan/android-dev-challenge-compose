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
package com.example.androiddevchallenge.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.data.Api
import com.example.androiddevchallenge.data.PuppyRepositoryImpl
import com.example.androiddevchallenge.domain.Puppy

class PuppyViewModel : ViewModel() {

    private val repository = PuppyRepositoryImpl(Api)

    private val _puppiesLiveData = MutableLiveData<List<Puppy>>()
    val puppiesLiveData: LiveData<List<Puppy>> = _puppiesLiveData

    fun getPuppies() {
        _puppiesLiveData.value = repository.getPuppies()
    }

    fun find(it: String) {
        _puppiesLiveData.value = repository.find(it)
    }
}
