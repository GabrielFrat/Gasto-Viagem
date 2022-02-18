package com.example.gastodeviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.gastodeviagem.databinding.ActivityMainBinding
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalculate.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.buttonCalculate) {
            calculate()
        }
    }

    private fun calculate() {
        if (validationOK()) {
            try {
                val distance = binding.editDistance.text.toString().toFloat()
                val price = binding.editPrice.text.toString().toFloat()
                val autonomy = binding.editAutonomy.text.toString().toFloat()

                val value = (distance * price) / autonomy
                binding.textTotal.text = "R$ ${"%.2f".format(value)}"
            } catch (nfe: NumberFormatException) {
                Toast.makeText(this, getString(R.string.valor_valido), Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, getString(R.string.preencha_todos_campos), Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun validationOK(): Boolean {
        return (binding.editDistance.text.toString() != ""
                && binding.editAutonomy.text.toString() != ""
                && binding.editPrice.text.toString() != ""
                && binding.editAutonomy.text.toString() != "0")

    }
}