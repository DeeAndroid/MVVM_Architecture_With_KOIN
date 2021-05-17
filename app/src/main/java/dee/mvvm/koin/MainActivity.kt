package dee.mvvm.koin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dee.doodleblue.Utils.Toast
import com.dee.doodleblue.Utils.setCurrentFragment
import dee.mvvm.koin.Fragments.PricelistFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val activity = this@MainActivity as AppCompatActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_navigation.itemIconTintList = null

        val dashboardFragment = PricelistFragment()
        setCurrentFragment(activity,dashboardFragment)
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.dashboard -> setCurrentFragment(activity,dashboardFragment)
                R.id.Favorites -> Toast(applicationContext,"No View Attached")
                R.id.Portfolio -> Toast(applicationContext,"No View Attached")
                R.id.News -> Toast(applicationContext,"No View Attached")
                R.id.Invest -> Toast(applicationContext,"No View Attached")
            }
            true
        }


    }


}