package com.example.jogokotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtnome = findViewById<EditText>(R.id.edtnome)
        val radiolimite = findViewById<RadioGroup>(R.id.radiolimite)
        val btnjogar = findViewById<Button>(R.id.btnjogar)

        btnjogar.setOnClickListener {
            val nome = edtnome.text.toString().trim()

            if (nome.isEmpty()) {
                Toast.makeText(this, "Digite seu nome", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val maximo = when (radiolimite.checkedRadioButtonId) {
                R.id.radio10 -> 10
                R.id.radio50 -> 50
                R.id.radio100 -> 100
                else -> 0
            }

            if (maximo == 0) {
                Toast.makeText(this, "Escolha um limite", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, JogoActivity::class.java)
            intent.putExtra("nome", nome)
            intent.putExtra("maximo", maximo)
            startActivity(intent)
        }
    }
}
