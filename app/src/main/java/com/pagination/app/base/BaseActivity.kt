package com.pagination.app.base


import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.text.Spannable
import android.text.Spanned
import android.view.View
import android.view.Window
import android.widget.Space
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.pagination.app.R

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initProgressLoader()
    }

    fun initProgressLoader() {
        progressDialog = Dialog(this)
        val view = View.inflate(this, R.layout.view_loading_circular, null)
        progressDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog!!.setContentView(view)
        progressDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressDialog!!.setCancelable(false)
    }

    private var progressDialog: Dialog? = null


    fun showLoading(message: String? = getString(R.string.loading_please_wait)) {
        if (progressDialog == null) {
            initProgressLoader()
        }
        if (progressDialog?.isShowing == true) {
            progressDialog?.dismiss()
        }
        progressDialog?.show()
    }

    fun hideLoading() {
        progressDialog?.dismiss()
    }

    fun setData(valueKey: String, txt: String): Spanned {
        return HtmlCompat.fromHtml(returnValue(valueKey,txt), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    private fun returnValue(valueKey: String, key: String): String {
        return "<font color='#000000'><b>$key:</b></font> ${valueKey}"
    }

    private var exit: Boolean = false
    fun backAction() {
        if (exit) {
            finishAffinity()
        } else {
            Toast.makeText(this,
                getString(R.string.press_one_more_time_to_push_app_into_background), Toast.LENGTH_SHORT).show()
            exit = true
            Handler().postDelayed({ exit = false }, (2 * 1000).toLong())
        }
    }


}