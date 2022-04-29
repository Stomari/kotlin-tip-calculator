package com.example.kotlintipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlintipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        // Get total cost
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null) {
            binding.tipResult.text = ""
            return
        }

        // Get tip percentage
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        // Calculate tip
        var tip = tipPercentage * cost

        // Check if needs to round the value
        if (binding.roundupSwitch.isChecked) {
            tip = ceil(tip)
        }

        // Format tip with the local cash structure
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        // Display the tip
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}