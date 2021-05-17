package com.dee.doodleblue.Utils

import android.app.Activity
import android.app.Application
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.view.Display
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dee.mvvm.koin.MVVM.apiModule
import dee.mvvm.koin.MVVM.repositoryModule
import dee.mvvm.koin.MVVM.retrofitModule
import dee.mvvm.koin.MVVM.viewModelModule
import dee.mvvm.koin.R
import kotlinx.android.synthetic.main.fragment_pricelist.view.*
import kotlinx.android.synthetic.main.loading.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Utils: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@Utils)
            loadKoinModules(listOf(repositoryModule, viewModelModule, retrofitModule, apiModule))
        }
    }
}

private var dialog: Dialog? = null

fun customalert(context: Context?) {
    dialog = Dialog(context!!)
    dialog?.setContentView(R.layout.loading)
    dialog!!.setCancelable(false)
    val activity=context as Activity
    val display: Display = activity!!.windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)
    val width: Int = size.x
    val height: Int = size.y
    val parms = LinearLayout.LayoutParams(width, height)
    dialog!!.loadingcard.layoutParams = parms
    dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog!!.show()
}


fun customalertcancel() {
    if (dialog!!.isShowing) {
        dialog!!.dismiss()
    }
}

fun Toast(context: Context?, s: String) {
    Toast.makeText(context, "$s", Toast.LENGTH_SHORT).show()
}


fun setCurrentFragment(activity: AppCompatActivity, fragment: Fragment) =
    activity.supportFragmentManager.beginTransaction().apply {
        replace(R.id.viewview, fragment)
        commit()
    }




