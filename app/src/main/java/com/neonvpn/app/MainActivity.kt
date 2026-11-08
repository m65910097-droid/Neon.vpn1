
package com.neonvpn.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.*

class MainActivity : Activity() {

    private var selectedCountry = "🇩🇪 Германия"
    private var connected = false
    private lateinit var status: TextView
    private lateinit var connectButton: TextView

    private val countries = listOf(
        "🇩🇪 Германия",
        "🇳🇱 Нидерланды",
        "🇺🇸 США",
        "🇫🇷 Франция",
        "🇬🇧 Великобритания",
        "🇯🇵 Япония"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.rgb(10, 15, 29)
        window.navigationBarColor = Color.rgb(10, 15, 29)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(28, 30, 28, 30)
            setBackgroundColor(Color.rgb(10, 15, 29))
        }

        val title = TextView(this).apply {
            text = "✦ NEON VPN"
            textSize = 32f
            setTextColor(Color.rgb(65, 240, 205))
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
        }

        val subtitle = TextView(this).apply {
            text = "YOUR PRIVATE CONNECTION"
            textSize = 12f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
        }

        status = TextView(this).apply {
            text = "● НЕ ПОДКЛЮЧЕНО"
            textSize = 17f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
        }

        connectButton = TextView(this).apply {
            text = "⏻"
            textSize = 65f
            gravity = Gravity.CENTER
            setTextColor(Color.rgb(65, 240, 205))
            setBackgroundColor(Color.rgb(24, 49, 59))
            setPadding(35, 20, 35, 20)
            setOnClickListener {
                Toast.makeText(
                    this@MainActivity,
                    "Сначала настройте VPN-сервер WireGuard",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        val countryTitle = TextView(this).apply {
            text = "ВЫБЕРИ СТРАНУ"
            textSize = 15f
            setTextColor(Color.WHITE)
            typeface = Typeface.DEFAULT_BOLD
        }

        val spinner = Spinner(this)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            countries
        )
        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        spinner.adapter = adapter

        spinner.onItemSelectedListener =
            object : android.widget.AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {}

                override fun onItemSelected(
                    parent: android.widget.AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selectedCountry = countries[position]
                }
            }

        val footer = TextView(this).apply {
            text = "NEON VPN • FREE EDITION"
            textSize = 12f
            setTextColor(Color.GRAY)
            gravity = Gravity.CENTER
        }

        root.addView(title)
        root.addView(subtitle)
        root.addView(space(45))
        root.addView(status)
        root.addView(space(20))
        root.addView(connectButton)
        root.addView(space(45))
        root.addView(countryTitle)
        root.addView(spinner)
        root.addView(space(45))
        root.addView(footer)

        setContentView(root)
    }

    private fun space(height: Int): View {
        return View(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                1,
                height
            )
        }
    }
}
