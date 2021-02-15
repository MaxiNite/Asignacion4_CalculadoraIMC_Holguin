package mx.edu.itson.asignacion4_calculadoraimc_holguin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var imc: Double = 0.0
    var range: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etHeight: EditText = findViewById(R.id.etHeight) as EditText
        val etWeight: EditText = findViewById(R.id.etWeight) as EditText
        val btnCalculate: Button = findViewById(R.id.btnCalculate) as Button
        val tvIMC: TextView = findViewById(R.id.tvIMC) as TextView
        val tvRange: TextView = findViewById(R.id.tvRange) as TextView

        btnCalculate.setOnClickListener {
            if (!etHeight.text.isBlank() && !etWeight.text.isBlank()){

                imc = calculaIMC(etHeight.text.toString().toDouble(), etWeight.text.toString().toDouble())
                tvIMC.setText(imc.toString())

                range = rango(imc)
                tvRange.setText(range.toString())

                when(range){
                    "Bajo de peso" -> tvRange.setBackgroundResource(R.color.colorBronw)
                    "Saludable" -> tvRange.setBackgroundResource(R.color.colorGreen)
                    "Sobrepeso" -> tvRange.setBackgroundResource(R.color.colorGreenish)
                    "Obesidad grado 1" -> tvRange.setBackgroundResource(R.color.colorYellow)
                    "Obesidad grado 2" -> tvRange.setBackgroundResource(R.color.colorOrange)
                    "Obesidad grado 3" -> tvRange.setBackgroundResource(R.color.colorRed)
                }
            }
        }
    }

    fun calculaIMC(estatura: Double, peso: Double): Double{
        val res = peso / (Math.pow(estatura, 2.0))
        return res
    }

    fun rango(imcR: Double): String{
        when{
            imcR < 18.5 -> return "Bajo peso"
            imcR >= 18.5 && imcR <= 24.9 -> return "Saludable"
            imcR >= 25 && imcR <= 29.9 -> return "Sobrepeso"
            imcR >= 30 && imcR <= 34.9 -> return "Obesidad grado 1"
            imcR >= 35 && imcR <= 39.9 -> return "Obesidad grado 2"
            imcR >= 40 -> return "Obesidad grado 3"
        }
        return "Error"
    }
}