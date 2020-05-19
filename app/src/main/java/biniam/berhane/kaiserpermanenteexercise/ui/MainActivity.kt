package biniam.berhane.kaiserpermanenteexercise.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import biniam.berhane.kaiserpermanenteexercise.R


class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }

    override fun onResume() {
        super.onResume()
        hostFragmentManager()

    }

    private fun hostFragmentManager() {

        navController = navHostFragment.navController
        navController.navigate(R.id.homeFragment)
        onBackPressed()
    }

    override fun onBackPressed() {
        val currentFragment: Fragment =
            navHostFragment.childFragmentManager.fragments[0]
        val controller = Navigation.findNavController(this, R.id.nav_host_fragment)
        if (currentFragment is OnBackPressedListener) (currentFragment as OnBackPressedListener).onBackPressed() else if (!controller.popBackStack()) finish()
    }
}

interface OnBackPressedListener {
    fun onBackPressed()
}

