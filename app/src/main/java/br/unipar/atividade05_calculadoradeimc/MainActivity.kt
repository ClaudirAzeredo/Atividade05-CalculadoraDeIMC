package br.unipar.atividade05_calculadoradeimc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextAltura: EditText
    private lateinit var editTextPeso: EditText
    private lateinit var buttonCalcularIMC: Button
    private lateinit var textViewResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextAltura = findViewById(R.id.editTextAltura)
        editTextPeso = findViewById(R.id.editTextPeso)
        buttonCalcularIMC = findViewById(R.id.buttonCalcularIMC)
        textViewResultado = findViewById(R.id.textViewResultado)

        buttonCalcularIMC.setOnClickListener {
            calcularIMC()
        }
    }

    private fun calcularIMC() {
        val alturaStr = editTextAltura.text.toString()
        val pesoStr = editTextPeso.text.toString()

        if (alturaStr.isEmpty() || pesoStr.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show()
            return
        }

        val altura = alturaStr.toDoubleOrNull()
        val peso = pesoStr.toDoubleOrNull()

        if (altura == null || peso == null || altura <= 0 || peso <= 0) {
            Toast.makeText(this, "Por favor, insira valores válidos!", Toast.LENGTH_SHORT).show()
            return
        }

        val imc = peso / (altura * altura)

        if (imc.isNaN() || imc.isInfinite()) {
            textViewResultado.text = "Erro no cálculo do IMC"
            return
        }

        val categoria = when {
            imc < 18.5 -> "Abaixo do peso"
            imc in 18.5..24.9 -> "Peso normal"
            imc in 25.0..29.9 -> "Sobrepeso"
            else -> "Obesidade"
        }

        textViewResultado.text = "IMC: %.2f\nCategoria: %s".format(imc, categoria)
    }
}
