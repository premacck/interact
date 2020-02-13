package com.techmesystem.intera.filter.mockdata

import com.techmesystem.intera.R
import com.techmesystem.intera.filter.model.FilterValue

/**
 * Prem's creation, on 2020-02-14
 */
val FILTER_LIST = arrayListOf(
  R.string.categories, R.string.brands, R.string.types_of_medicines, R.string.price_range
)

val CATEGORY_LIST = arrayListOf(
  FilterValue(1, "Medicamentos", false),
  FilterValue(2, "Autocuidado", false),
  FilterValue(3, "Higiene e Cuidado Pessoal", false),
  FilterValue(4, "Beleza", false),
  FilterValue(5, "Mãe e Bebê", false)
)

val BRAND_LIST = arrayListOf(
  FilterValue(1, "Sanofi"),
  FilterValue(2, "Neutrogena"),
  FilterValue(3, "Sanofi"),
  FilterValue(4, "Neutrogena"),
  FilterValue(5, "Sanofi"),
  FilterValue(6, "Neutrogena"),
  FilterValue(7, "Sanofi"),
  FilterValue(8, "Neutrogena"),
  FilterValue(9, "Sanofi"),
  FilterValue(10, "Neutrogena")
)

val TYPES_OF_MEDICINES_LIST = arrayListOf(
  FilterValue(1, "Comprimido"), FilterValue(2, "Pomada"), FilterValue(3, "Gel")
)