package com.soulinc.spokeFolks.base.lifecycle

import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.soulinc.spokeFolks.LAYOUT_ID

/**
 * paras's creation on 01-05-2021
 */
abstract class BaseActivity(@LayoutRes private var layoutId: Int) : AppCompatActivity() {

  val screenName: String by lazy { this::class.java.name }

  @IdRes
  open fun fragmentContainer(): Int = -1

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    outState.putInt(LAYOUT_ID, layoutId)
  }

  override fun onRestoreInstanceState(savedInstanceState: Bundle) {
    super.onRestoreInstanceState(savedInstanceState)
    layoutId = savedInstanceState.getInt(LAYOUT_ID)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (savedInstanceState?.containsKey("layoutId") == true) {
      layoutId = savedInstanceState.getInt("layoutId")
    }

    if (layoutId != -1) setContentView(layoutId)
  }

  fun <F> showFragment(fragment: Fragment, addToBackStack: Boolean) where F : Fragment {
    if (fragmentContainer() == -1) throw IllegalStateException("Please override fragmentContainerRes() and give a valid layout ID.")
    fragmentContainer().let { containerRes ->
      supportFragmentManager.beginTransaction().replace(containerRes, fragment, fragment.tag).let {
        if (addToBackStack) it.addToBackStack(fragment.tag)
        try {
          it.commit()
        } catch (e: Exception) {
          it.commitAllowingStateLoss()
        } catch (e: Exception) {
          Log.e(screenName, e.toString())
        }
      }
    }
  }

}