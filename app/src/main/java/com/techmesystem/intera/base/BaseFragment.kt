package com.techmesystem.intera.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 * Prem's creation, on 2020-02-09
 */
abstract class BaseFragment : Fragment() {

  abstract val TAG: String

  @LayoutRes abstract fun layoutRes(): Int

  override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, state: Bundle?): View? = inflater.inflate(layoutRes(), parent, false)
}