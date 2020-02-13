package com.techmesystem.intera.search.mockdata

import com.techmesystem.intera.R
import com.techmesystem.intera.productdetail.data.PRODUCT_1
import com.techmesystem.intera.productdetail.data.PRODUCT_1_EQUIVALENT
import com.techmesystem.intera.productdetail.data.PRODUCT_2
import com.techmesystem.intera.productdetail.data.PRODUCT_2_EQUIVALENT
import com.techmesystem.intera.search.model.PopularSearch
import com.techmesystem.intera.search.model.ProductSearch

/**
 * Prem's creation, on 2020-02-13
 */
const val DORFLEX = "Dorflex"
const val FRALDAS_DESCARTAVEIS = "Fraldas Descartaveis"
const val PROTETOR_SOLAR = "Protetor Solar"
const val LENÇO_UMEDECIDO = "Lenço Umedecido"
const val FRALDAS = "Fraldas"

val POPULAR_SEARCH_LIST = listOf(
  PopularSearch(1, DORFLEX), PopularSearch(2, FRALDAS_DESCARTAVEIS), PopularSearch(3, PROTETOR_SOLAR), PopularSearch(4, LENÇO_UMEDECIDO), PopularSearch(5, FRALDAS)
)

val SEARCH_LIST = mutableListOf(
  ProductSearch(1, PRODUCT_1, "Protetor Solar Corporal Neutrogena SunFresh FPS90 120ml", R.drawable.product_one),
  ProductSearch(2, PRODUCT_2, "Allegra Pediátrico 60MG/ML", R.drawable.product_two),
  ProductSearch(3, PRODUCT_1_EQUIVALENT, "Protetor Solar Corporal Neutrogena SunFresh FPS90 120ml", R.drawable.product_one),
  ProductSearch(4, PRODUCT_2_EQUIVALENT, "Allegra Pediátrico 60MG/ML", R.drawable.product_two),
  ProductSearch(5, PRODUCT_1, "Protetor Solar Corporal Neutrogena SunFresh FPS90 120ml", R.drawable.product_one),
  ProductSearch(6, PRODUCT_2, "Allegra Pediátrico 60MG/ML", R.drawable.product_two),
  ProductSearch(7, PRODUCT_1_EQUIVALENT, "Protetor Solar Corporal Neutrogena SunFresh FPS90 120ml", R.drawable.product_one),
  ProductSearch(8, PRODUCT_2_EQUIVALENT, "Allegra Pediátrico 60MG/ML", R.drawable.product_two),
  ProductSearch(9, PRODUCT_1, "Protetor Solar Corporal Neutrogena SunFresh FPS90 120ml", R.drawable.product_one),
  ProductSearch(10, PRODUCT_2, "Allegra Pediátrico 60MG/ML", R.drawable.product_two),
  ProductSearch(11, PRODUCT_1_EQUIVALENT, "Protetor Solar Corporal Neutrogena SunFresh FPS90 120ml", R.drawable.product_one),
  ProductSearch(12, PRODUCT_2_EQUIVALENT, "Allegra Pediátrico 60MG/ML", R.drawable.product_two),
  ProductSearch(13, PRODUCT_1, "Protetor Solar Corporal Neutrogena SunFresh FPS90 120ml", R.drawable.product_one),
  ProductSearch(14, PRODUCT_2, "Allegra Pediátrico 60MG/ML", R.drawable.product_two),
  ProductSearch(15, PRODUCT_1_EQUIVALENT, "Protetor Solar Corporal Neutrogena SunFresh FPS90 120ml", R.drawable.product_one),
  ProductSearch(16, PRODUCT_2_EQUIVALENT, "Allegra Pediátrico 60MG/ML", R.drawable.product_two),
  ProductSearch(17, PRODUCT_1, "Protetor Solar Corporal Neutrogena SunFresh FPS90 120ml", R.drawable.product_one),
  ProductSearch(18, PRODUCT_2, "Allegra Pediátrico 60MG/ML", R.drawable.product_two),
  ProductSearch(19, PRODUCT_1_EQUIVALENT, "Protetor Solar Corporal Neutrogena SunFresh FPS90 120ml", R.drawable.product_one),
  ProductSearch(20, PRODUCT_2_EQUIVALENT, "Allegra Pediátrico 60MG/ML", R.drawable.product_two)
)