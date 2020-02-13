package com.techmesystem.intera.base.component

import android.util.Log
import android.widget.EditText
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import org.jetbrains.anko.sdk27.coroutines.textChangedListener
import java.util.concurrent.TimeUnit

/**
 * Prem's creation, on 2020-02-13
 */
object RxSearch {

  fun addTo(
    editText: EditText, delayMillis: Long = 500, subscribe: (searchTerm: String) -> Unit
  ): Disposable =
    from(editText).debounce(delayMillis, TimeUnit.MILLISECONDS).distinctUntilChanged().observeOn(
      AndroidSchedulers.mainThread()
    ).subscribe({ subscribe(it) }, { Log.e("Error", "when searching", it) })

  private fun from(editText: EditText) = PublishSubject.create<String>().apply {
    editText.textChangedListener {
      onTextChanged { charSequence, _, _, _ -> onNext(charSequence?.toString().orEmpty()) }
    }
  }
}