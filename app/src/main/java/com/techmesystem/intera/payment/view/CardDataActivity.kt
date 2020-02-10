package com.techmesystem.intera.payment.view

import android.content.Context
import android.os.Bundle
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseActivity
import com.techmesystem.intera.base.component.CardTextWatcher
import com.techmesystem.intera.payment.mockdata.MonthList
import com.techmesystem.intera.payment.mockdata.YearList
import com.techmesystem.intera.payment.model.CardDetail
import com.techmesystem.intera.util.onDebounceClick
import kotlinx.android.synthetic.main.activity_card_data.*
import org.jetbrains.anko.dip
import org.jetbrains.anko.selector
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Prem's creation, on 2020-02-10
 */
class CardDataActivity : BaseActivity() {

  private var cardDetail: CardDetail = CardDetail()
  private val cardTextWatcher: CardTextWatcher by lazy { CardTextWatcher(dip(8)) }
  private val years: YearList by lazy { YearList() }
  private val months: MonthList by lazy { MonthList() }

  companion object {
    private const val CARD_DETAIL = "cardDetail"
    fun launch(from: Context?, cardDetail: CardDetail? = null) = from?.run { startActivity<CardDataActivity>(CARD_DETAIL to cardDetail) }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_card_data)
    toolbar.onBackNavigation { onBackPressed() }
    initListeners()
    initCard()
  }

  override fun onDestroy() {
    et_card_number?.removeTextChangedListener(cardTextWatcher)
    super.onDestroy()
  }

  private fun initListeners() {
    et_card_number.addTextChangedListener(cardTextWatcher)
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
    btn_delete_card.onDebounceClick {
      toast(R.string.card_deleted)
      finish()
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
}