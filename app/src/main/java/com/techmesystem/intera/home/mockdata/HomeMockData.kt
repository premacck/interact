package com.techmesystem.intera.home.mockdata

import com.techmesystem.intera.R
import com.techmesystem.intera.home.model.HomeDataModel
import java.util.*

/**
 * Prem's creation, on 2020-02-09
 */
val categoryALL: ArrayList<HomeDataModel>
  get() {
    val homeDataModels_all = ArrayList<HomeDataModel>()

    val homeDataModel1 = HomeDataModel()
    homeDataModel1.picture = R.drawable.pic1
    homeDataModel1.name = "Medicamentos"
    homeDataModels_all.add(homeDataModel1)

    val homeDataModel2 = HomeDataModel()
    homeDataModel2.picture = R.drawable.pic2
    homeDataModel2.name = "Autocuidado"
    homeDataModels_all.add(homeDataModel2)

    val homeDataModel3 = HomeDataModel()
    homeDataModel3.picture = R.drawable.pic3
    homeDataModel3.name = "Higiene e Cuidado Pessoal"
    homeDataModels_all.add(homeDataModel3)

    val homeDataModel4 = HomeDataModel()
    homeDataModel4.picture = R.drawable.pic4
    homeDataModel4.name = "Beleza"
    homeDataModels_all.add(homeDataModel4)

    val homeDataModel11 = HomeDataModel()
    homeDataModel11.picture = R.drawable.pic1
    homeDataModel11.name = "Medicamentos"
    homeDataModels_all.add(homeDataModel11)

    val homeDataModel22 = HomeDataModel()
    homeDataModel22.picture = R.drawable.pic2
    homeDataModel22.name = "Autocuidado"
    homeDataModels_all.add(homeDataModel22)

    val homeDataModel33 = HomeDataModel()
    homeDataModel33.picture = R.drawable.pic3
    homeDataModel33.name = "Higiene e Cuidado Pessoal"
    homeDataModels_all.add(homeDataModel33)

    val homeDataModel44 = HomeDataModel()
    homeDataModel44.picture = R.drawable.pic4
    homeDataModel44.name = "Beleza"
    homeDataModels_all.add(homeDataModel44)

    return homeDataModels_all
  }

val servicesALL: ArrayList<HomeDataModel>
  get() {
    val homeDataModels_all = ArrayList<HomeDataModel>()

    val homeDataModel1 = HomeDataModel()
    homeDataModel1.picture = R.drawable.alarm
    homeDataModel1.name = "Acompanhar\ntratamento"
    homeDataModels_all.add(homeDataModel1)

    val homeDataModel2 = HomeDataModel()
    homeDataModel2.picture = R.drawable.group_icon
    homeDataModel2.name = "Carteira\nDigital"
    homeDataModels_all.add(homeDataModel2)

    val homeDataModel3 = HomeDataModel()
    homeDataModel3.picture = R.drawable.win
    homeDataModel3.name = "Ganhadores\nSorteios"
    homeDataModels_all.add(homeDataModel3)

    val homeDataModel4 = HomeDataModel()
    homeDataModel4.picture = R.drawable.assignment
    homeDataModel4.name = "Meus\npedidos"
    homeDataModels_all.add(homeDataModel4)

    val homeDataModel11 = HomeDataModel()
    homeDataModel11.picture = R.drawable.alarm
    homeDataModel11.name = "Acompanhar\ntratamento"
    homeDataModels_all.add(homeDataModel11)

    val homeDataModel22 = HomeDataModel()
    homeDataModel22.picture = R.drawable.group_icon
    homeDataModel22.name = "Carteira\nDigital"
    homeDataModels_all.add(homeDataModel22)

    val homeDataModel33 = HomeDataModel()
    homeDataModel33.picture = R.drawable.win
    homeDataModel33.name = "Ganhadores\nSorteios"
    homeDataModels_all.add(homeDataModel33)

    val homeDataModel44 = HomeDataModel()
    homeDataModel44.picture = R.drawable.assignment
    homeDataModel44.name = "Meus\npedidos"
    homeDataModels_all.add(homeDataModel44)

    return homeDataModels_all
  }