package com.example.jogokotlin

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs
import kotlin.random.Random

class JogoActivity : AppCompatActivity() {

    private var tentativas = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogo)

        val nome = intent.getStringExtra("nome") ?: "Jogador"
        val maximo = intent.getIntExtra("maximo", 10)
        val secreto = Random.nextInt(1, maximo + 1)

        val txtmensagem = findViewById<TextView>(R.id.txtmensagem)
        val edtpalpite = findViewById<EditText>(R.id.edtpalpite)
        val btnchutar = findViewById<Button>(R.id.btnchutar)
        val txtdica = findViewById<TextView>(R.id.txtdica)
        val txttentativas = findViewById<TextView>(R.id.txttentativas)
        val btnnovamente = findViewById<Button>(R.id.btnnovamente)

        txtmensagem.text = "$nome, pensei num número de 1 a $maximo!"

        btnchutar.setOnClickListener {
            val texto = edtpalpite.text.toString()

            if (texto.isEmpty()) {
                Toast.makeText(this, "Digite um palpite", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val palpite = texto.toIntOrNull()

            if (palpite == null || palpite < 1 || palpite > maximo) {
                Toast.makeText(this, "Digite um número de 1 a $maximo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            tentativas++
            txttentativas.text = "Tentativas: $tentativas"

            if (palpite == secreto) {
                txtdica.text = "$nome acertou em $tentativas tentativas!"
                btnchutar.isEnabled = false
                edtpalpite.isEnabled = false
                btnnovamente.visibility = View.VISIBLE
            } else {
                val diferenca = abs(palpite - secreto)
                val limitequente = maximo * 0.1
                val temperatura = if (diferenca <= limitequente) "Quente!" else "Frio!"
                val direcao = if (secreto > palpite) "MAIOR" else "MENOR"
                txtdica.text = "$temperatura O número é $direcao."
                edtpalpite.text.clear()
            }
        }

        btnnovamente.setOnClickListener {
            finish()
        }
    }
}
