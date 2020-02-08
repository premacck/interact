package com.techmesystem.intera.base.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.techmesystem.intera.R

/**
 * Prem's creation, on 2020-02-08
 */
abstract class BaseBottomSheet : BottomSheetDialogFragment() {

  abstract val TAG: String

  override fun getTheme(): Int = R.style.BottomSheetDialogTheme

  @LayoutRes abstract fun layoutRes(): Int

  override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, state: Bundle?): View? = inflater.inflate(layoutRes(), parent, false)

  fun show(manager: FragmentManager) = show(manager, TAG)
}