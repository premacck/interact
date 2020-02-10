package com.techmesystem.intera.payment.view

import android.content.Context
import android.os.Bundle
import androidx.core.os.postDelayed
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseActivity
import com.techmesystem.intera.base.component.CardTextWatcher
import com.techmesystem.intera.checkout.ui.OrderSummaryActivity
import com.techmesystem.intera.payment.component.ICardDataListener
import com.techmesystem.intera.payment.mockdata.MonthList
import com.techmesystem.intera.payment.mockdata.YearList
import com.techmesystem.intera.payment.model.CardDetail
import com.techmesystem.intera.request.model.ProductRequest
import com.techmesystem.intera.util.onDebounceClick
import com.techmesystem.intera.util.showOrHide
import kotlinx.android.synthetic.main.activity_card_data.*
import org.jetbrains.anko.dip
import org.jetbrains.anko.sdk27.coroutines.textChangedListener
import org.jetbrains.anko.selector
import org.jetbrains.anko.startActivity

/**
 * Prem's creation, on 2020-02-10
 */
class CardDataActivity : BaseActivity(), ICardDataListener {

  private var cardDetail: CardDetail = CardDetail()
  private val cardTextWatcher: CardTextWatcher by lazy { CardTextWatcher(dip(8)) }
  private val years: YearList by lazy { YearList() }
  private val months: MonthList by lazy { MonthList() }
  private var isToSave: Boolean = false

  companion object {
    private const val CARD_DETAIL = "cardDetail"
    private const val TO_SAVE = "toSave"
    fun launch(from: Context?, cardDetail: CardDetail? = null, toSave: Boolean = false) = from?.run {
      startActivity<CardDataActivity>(
        CARD_DETAIL to cardDetail, TO_SAVE to toSave
      )
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    isToSave = intent?.getBooleanExtra(TO_SAVE, false) ?: false
    setContentView(R.layout.activity_card_data)
    toolbar.onBackNavigation { onBackPressed() }
    initViews()
    initListeners()
    initCard()
  }

  private fun initViews() {
    btn_delete_card.showOrHide(!isToSave)
    btn_save.showOrHide(isToSave)
  }

  override fun onDestroy() {
    et_card_number?.removeTextChangedListener(cardTextWatcher)
    super.onDestroy()
  }

  private fun initListeners() {
    et_card_number.addTextChangedListener(cardTextWatcher)
    et_card_number.textChangedListener {
      onTextChanged { s, _, _, _ ->
        cardDetail.cardNumber = s?.toString().orEmpty()
      }
    }
    tv_card_month.onDebounceClick {
      selector(getString(R.string.select_month), months) { dialog, index ->
        cardDetail.cardMonth = months[index]
        tv_card_month.text = cardDetail.cardMonth
        dialog.dismiss()
      }
    }
    tv_card_year.onDebounceClick {
      selector(getString(R.string.select_year), years) { dialog, index ->
        cardDetail.cardYear = years[index]
        tv_card_year.text = cardDetail.cardYear
        dialog.dismiss()
      }
    }
    if (isToSave) {
      btn_save.onDebounceClick { OrderSummaryActivity.launch(this, ProductRequest()) }
    } else {
      btn_delete_card.onDebounceClick { DeleteCardConfirmBottomSheet().show(supportFragmentManager) }
    }
  }

  private fun initCard() {
    if (!cardDetail.isEmpty()) {
      et_card_number.setText(cardDetail.cardNumber)
      et_card_cvv.setText(cardDetail.cardCvv)
      et_name_on_card.setText(cardDetail.nameOnCard)
      tv_card_month.text = cardDetail.cardMonth
      tv_card_year.text = cardDetail.cardYear
    }
  }

  override fun onCardDeleteDone() {
    DeleteCardSuccessBottomSheet().show(supportFragmentManager)
  }

  override fun onCardDeleteFinish() {
    handler.postDelayed(200) { finish() }
  }
}