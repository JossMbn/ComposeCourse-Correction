package com.formation.composecourse.data.common.serializer

import androidx.datastore.core.Serializer
import com.formation.composecourse.domain.common.model.AppPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class AppPreferencesSerializer @Inject constructor() : Serializer<AppPreferences> {

    override val defaultValue: AppPreferences = AppPreferences()

    override suspend fun readFrom(input: InputStream): AppPreferences {
        return try {
            Json.decodeFromString(
                deserializer = AppPreferences.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: AppPreferences, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = AppPreferences.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }
    }
}
