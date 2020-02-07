package com.techmesystem.intera.productdetail.data

import com.techmesystem.intera.R
import com.techmesystem.intera.productdetail.model.Product

/**
 * Prem's creation, on 2020-02-05
 */
val PRODUCT_1 = Product(
  R.drawable.product_one,
  listOf("Alergias", "Rinite Alergica", "Rinite Alergica"),
  "Protetor Solar Corporal Neutrogena Sun\nFresh FPS90 120ml",
  "Neutrogena",
  "26,71",
  "18,95",
  "26,71",
  "1,89",
  R.string.product_one_description,
  1,
  "50,00"
)
val PRODUCT_2 = Product(
  R.drawable.product_two,
  listOf("Alergias", "Rinite Alergica", "Rinite Alergica"),
  "Allegra Pediátrico 6MG/ML\nCloridrato de fexofenadina",
  "Sanofi",
  "70,00",
  "70,00",
  "70,00",
  "7,00",
  R.string.point_two_description,
  1,
  captionShort = "Allegra Pediátrico 6MG/ML"
)
val PRODUCT_1_EQUIVALENT = Product(
  R.drawable.product_one,
  listOf("Alergias", "Rinite Alergica", "Rinite Alergica"),
  "Protetor Solar Corporal Neutrogena Sun\nFresh FPS90 120ml",
  "Neutrogena",
  "26,71",
  "18,95",
  "26,71",
  "1,89",
  R.string.product_one_description,
  1,
  "50,00",
  isNextDrawAvailable = true
)
val PRODUCT_2_EQUIVALENT = Product(
  R.drawable.product_two,
  listOf("Alergias", "Rinite Alergica", "Rinite Alergica"),
  "Allegra Pediátrico 6MG/ML\nCloridrato de fexofenadina",
  "Sanofi",
  "28,71",
  "20,95",
  "24,71",
  "2,89",
  R.string.point_two_description,
  1,
  captionShort = "Allegra Pediátrico 6MG/ML",
  isInStock = false
)