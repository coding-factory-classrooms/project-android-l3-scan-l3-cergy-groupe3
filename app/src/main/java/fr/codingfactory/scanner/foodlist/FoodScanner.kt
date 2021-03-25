package fr.codingfactory.scanner.foodlist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import fr.codingfactory.scanner.R


class FoodScanner : AppCompatActivity() {
    lateinit var btnBarcode: Button
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)
        title = "Scanner"
        btnBarcode = findViewById(R.id.button)
        textView = findViewById(R.id.txtContent)
        btnBarcode.setOnClickListener {
            val intentIntegrator = IntentIntegrator(this@FoodScanner)
            intentIntegrator.setBeepEnabled(false)
            intentIntegrator.setCameraId(0)
            intentIntegrator.setPrompt("SCAN")
            intentIntegrator.setBarcodeImageEnabled(false)
            intentIntegrator.initiateScan()
            intentIntegrator.setOrientationLocked(false)

        }
    }
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "cancelled", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("FoodScanner", "Scanned")
                Toast.makeText(this, "Scanned -> " + result.contents, Toast.LENGTH_SHORT)
                    .show()
                textView.text = String.format("Scanned Result: %s", result)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}