package com.techmesystem.intera.compareandprove.data

import androidx.annotation.IntDef

/**
 * Prem's creation, on 2020-02-08
 */
const val COMPARISON_SAO_PAULO = 1
const val COMPARISON_ULTRA_FARMA = 2
const val COMPARISON_ONOFRE = 3
const val COMPARISON_DROGASIL = 4

@Retention @IntDef(COMPARISON_SAO_PAULO, COMPARISON_ULTRA_FARMA, COMPARISON_ONOFRE, COMPARISON_DROGASIL) annotation class ComparisonCompany