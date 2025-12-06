package com.example.cocinafacil

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.window.OnBackInvokedDispatcher
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private var pantalla=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        pantallaPrincipal()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        onBackPressedDispatcher.addCallback(this){
            if(pantalla!=1){
                pantallaPrincipal()
            }else{
                finish()
            }
        }

    }
    private fun pantallaPrincipal(){
        setContentView(R.layout.activity_main)
        pantalla=1
        val botonInicio=findViewById<Button>(R.id.inicioBoton)
        botonInicio.setOnClickListener {
            pantallaLogin()
        }
        val botonRegistro=findViewById<Button>(R.id.registraseBoton)
        botonRegistro.setOnClickListener {
            pantallaRegistro()
        }
    }
    private fun pantallaLogin(){
        setContentView(R.layout.iniciasesion)
        pantalla=2
    }
    private fun pantallaRegistro(){
        setContentView(R.layout.registro)
        pantalla=3
        val fecha=findViewById<EditText>(R.id.fecha)
        fecha.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dp = DatePickerDialog(this, { _, y, m, d ->
                fecha.setText(String.format("%02d/%02d/%04d", d, m + 1, y))
            }, year, month, day)

            dp.datePicker.maxDate = System.currentTimeMillis()

            dp.show()
        }
    }

}