package com.example.radiotest.domain

import com.example.radiotest.data.model.Available
import com.example.radiotest.data.model.RadioStation

data class Station(val radioStation: RadioStation, val available: Available?)
