package biniam.berhane.kaiserpermanenteexercise.utils

import androidx.fragment.app.FragmentActivity

/**
 * Designed and developed by Biniam Berhane on 15/05/2020.
 */

inline fun <reified T> FragmentActivity.getTopFragment(): T?
        = supportFragmentManager.fragments.firstOrNull()?.let { it as? T }