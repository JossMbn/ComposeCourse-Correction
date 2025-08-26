package com.formation.composecourse.data.utils.extension

import com.formation.composecourse.BuildConfig

fun String?.buildTMDBImagePath(): String = "${BuildConfig.TMDP_IMAGE_BASE_URL}$this"
fun String?.buildTMDBBackdropImagePath(): String? = "${BuildConfig.TMDP_BACKDROP_IMAGE_BASE_URL}$this"
