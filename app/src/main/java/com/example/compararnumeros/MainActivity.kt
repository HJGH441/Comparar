package com.example.compararnumeros

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import com.example.compararnumeros.databinding.ActivityMainBinding

class MainActivity : Activity(), View.OnClickListener {


    private lateinit var J: ActivityMainBinding
    private var bnCompara : Button? = null

    class Comp {
        fun comparar(N1: Int, N2: Int): Int {
            if (N1 > N2) return N1
            return N2
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        J = ActivityMainBinding.inflate(layoutInflater)
        setContentView(J.root)

        iniciarVistas()
        Evento()
    }

    override fun onClick(p0: View?) {
        val num1 = J.spNumUno.selectedItem.toString().toInt()
        val num2 = J.spNumDos.selectedItem.toString().toInt()
        val comp = Comp()

        when(p0?.id) {
            R.id.bnCompara -> {
                val alert = AlertDialog.Builder(this)
                alert.setTitle("---------")
                    .setMessage("¿Desea comparar los valores?")
                    .setCancelable(false)
                    .setPositiveButton("Si",
                        DialogInterface.OnClickListener{ dialogInterface, i ->
                            if (num1 == num2){
                                Toast.makeText(this, "Los números son iguales", Toast.LENGTH_LONG).show()
                            } else {
                                val mayor = comp.comparar(num1, num2)
                                Toast.makeText(this, "Número mayor = $mayor", Toast.LENGTH_LONG).show()
                            }
                        }
                    )
                    .setNegativeButton("No",
                        DialogInterface.OnClickListener{ dialogInterface, i ->
                            
                        }
                    )
                    .show()
            }
        }
    }

    private fun iniciarVistas() {
        bnCompara = J.bnCompara
        // Array Adapter
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.misNumeros,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Relacionar Spinner con nuestro adapter
        J.spNumUno.adapter = adapter
        J.spNumDos.adapter = adapter
    }

    private fun Evento(){
        bnCompara?.setOnClickListener(this)
    }

}