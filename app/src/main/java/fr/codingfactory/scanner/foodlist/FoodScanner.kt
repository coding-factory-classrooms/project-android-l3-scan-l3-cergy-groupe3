package fr.codingfactory.scanner.foodlist

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import me.dm7.barcodescanner.zxing.ZXingScannerView
import com.google.zxing.Result


class FoodScanner : Activity(), ZXingScannerView.ResultHandler {
    private var mScannerView: ZXingScannerView? = null
    public override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        mScannerView = ZXingScannerView(this)
        setContentView(mScannerView)
    }

    public override fun onResume() {
        super.onResume()
        mScannerView!!.setResultHandler(this)
        mScannerView!!.startCamera()
    }

    public override fun onPause() {
        super.onPause()
        mScannerView!!.stopCamera()
    }

    override fun handleResult(rawResult: Result) {
        Log.v(TAG, rawResult.getText())
        Log.v(TAG, rawResult.getBarcodeFormat().toString()
        )

        mScannerView!!.resumeCameraPreview(this)
    }
}