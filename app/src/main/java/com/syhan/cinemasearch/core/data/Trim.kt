package com.syhan.cinemasearch.core.data

import android.annotation.SuppressLint

@SuppressLint("DefaultLocale")
fun Float.trim() = String.format("%.2f", this)