package com.techmesystem.intera.search.ui

import android.Manifest.permission.CAMERA
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseActivity
import com.techmesystem.intera.productdetail.data.PRODUCT_1
import com.techmesystem.intera.productdetail.ui.ProductDetailActivity
import com.techmesystem.intera.util.onDebounceClick
import kotlinx.android.synthetic.main.activity_barcode_scanner.*
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity

/**
 * Prem's creation, on 2020-02-14
 */
class BarcodeScannerActivity : BaseActivity(), ZBarScannerView.ResultHandler {

  companion object {
    private const val REQUEST_CAMERA_PERMISSIONS = 0
    fun launch(from: Context?) = from?.run { startActivity<BarcodeScannerActivity>() }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_barcode_scanner)
    toolbar.onBackNavigation { onBackPressed() }
    initListeners()
  }

  private fun initListeners() {
    bar_code_scanner.setAspectTolerance(0.5f)
    tv_enter_barcode.onDebounceClick { EnterBarcodeActivity.launch(this) }
  }

  override fun onResume() {
    super.onResume()
    checkPermissionsAndOpenCamera()
  }

  private fun checkPermissionsAndOpenCamera() {
    if (ContextCompat.checkSelfPermission(this, CAMERA) == PERMISSION_GRANTED) {
      bar_code_scanner.setResultHandler(this)
      bar_code_scanner.startCamera()
    } else ActivityCompat.requestPermissions(this, arrayOf(CAMERA), REQUEST_CAMERA_PERMISSIONS)
  }

  override fun onPause() {
    super.onPause()
    bar_code_scanner.stopCamera()
  }

  override fun handleResult(rawResult: Result?) {
    val resultMessage = if (rawResult?.contents.isNullOrEmpty()) getString(R.string.not_found) else rawResult?.contents.orEmpty()
    alert {
      titleResource = R.string.scan_results
      message = resultMessage
      positiveButton(R.string.okay) {
        ProductDetailActivity.launch(this@BarcodeScannerActivity, PRODUCT_1)
        it.dismiss()
        finish()
      }
      show()
    }
    bar_code_scanner.resumeCameraPreview(this)
  }

  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    checkPermissionsAndOpenCamera()
  }
}