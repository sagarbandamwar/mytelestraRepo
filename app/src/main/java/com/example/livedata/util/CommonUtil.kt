package com.example.livedata.util

import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.example.livedata.R

// class for showing dialog and to check if user is connected to internet or not
class CommonUtil {

    companion object {

        fun isOnline(context: Context?): Boolean {   // is online method will return user is connected or not
            val connectivityManager =
                context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }

        fun showDialog(title: String, context: Context) { // this method will show dialog
            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.custom_dialog)
            val body = dialog.findViewById(R.id.tvBody) as TextView
            body.text = title
            val yesBtn = dialog.findViewById(R.id.btn_yes) as Button
            // val noBtn = dialog .findViewById(R.id.noBtn) as TextView
            yesBtn.setOnClickListener {
                dialog.dismiss()
            }
            //  noBtn.setOnClickListener { dialog .dismiss() }
            dialog.show()

        }

    }

}