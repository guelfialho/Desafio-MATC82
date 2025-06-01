package com.example.calculadoraaposentadoria

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextIdade: EditText
    private lateinit var spinnerSexo: Spinner
    private lateinit var btnCalcular: Button
    private lateinit var textResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextIdade = findViewById(R.id.editTextIdade)
        spinnerSexo = findViewById(R.id.spinnerSexo)
        btnCalcular = findViewById(R.id.btnCalcular)
        textResultado = findViewById(R.id.textResultado)

        val opcoesSexo = arrayOf(
            getString(R.string.sexo_masculino),
            getString(R.string.sexo_feminino)
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcoesSexo)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSexo.adapter = adapter

        btnCalcular.setOnClickListener {
            val idadeTexto = editTextIdade.text.toString()
            if (idadeTexto.isEmpty()) {
                Toast.makeText(this, getString(R.string.erro_idade_vazia), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val idade = idadeTexto.toInt()
            val sexo = spinnerSexo.selectedItem.toString()

            val idadeMinima = if (sexo == getString(R.string.sexo_masculino)) 65 else 62
            val anosRestantes = idadeMinima - idade

            val resultado = if (anosRestantes <= 0) {
                getString(R.string.mensagem_apto)
            } else {
                getString(R.string.mensagem_faltam, anosRestantes)
            }

            textResultado.text = resultado
        }
    }
}