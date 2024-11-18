// ConfiguracaoUtil.kt
package br.com.fiap.appaura.util

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate

object ConfiguracaoUtil {

    private const val PREFS_NAME = "user_config"
    private const val KEY_TEMA_DARK = "tema_dark"
    private const val KEY_FONT_SIZE = "font_size"

    fun saveConfiguracao(context: Context, temaDark: Boolean, fontSize: Int) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putBoolean(KEY_TEMA_DARK, temaDark)
            putInt(KEY_FONT_SIZE, fontSize)
            apply()
        }
    }

    fun applyConfiguracao(context: Context) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val temaDark = prefs.getBoolean(KEY_TEMA_DARK, false)
        val fontSize = prefs.getInt(KEY_FONT_SIZE, 16)

        AppCompatDelegate.setDefaultNightMode(
            if (temaDark) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )

        context.resources.configuration.fontScale = fontSize / 16.0f
        context.resources.updateConfiguration(context.resources.configuration, context.resources.displayMetrics)
    }

    fun clearConfiguracao(context: Context) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(prefs.edit()) {
            clear()
            apply()
        }
    }
}