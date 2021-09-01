package com.soulinc.spokeFolks.base.lifecycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.soulinc.spokeFolks.LAYOUT_ID

/**
 * paras's creation on 01-05-2021
 */
abstract class BaseFragment(@LayoutRes private var layoutId: Int) : Fragment() {

  val TAG by lazy { this::class.java.name }

  private val parentActivity: BaseActivity
    get() = requireActivity() as BaseActivity

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    if (savedInstanceState?.containsKey(LAYOUT_ID) == true) {
      layoutId = savedInstanceState.getInt(LAYOUT_ID)
    }
    return if (layoutId != 0) inflater.inflate(layoutId, container, false) else null
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    outState.putInt(LAYOUT_ID, layoutId)
  }
}