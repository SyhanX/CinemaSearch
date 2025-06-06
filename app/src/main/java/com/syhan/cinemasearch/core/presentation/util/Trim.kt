package com.syhan.cinemasearch.core.presentation.util

import android.annotation.SuppressLint

@SuppressLint("DefaultLocale")
fun Float.trim() = String.format("%.2f", this)