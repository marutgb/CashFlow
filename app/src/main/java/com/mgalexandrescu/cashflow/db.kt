package com.mgalexandrescu.cashflow

import com.mgalexandrescu.cashflow.models.Category
import com.mgalexandrescu.cashflow.models.Expense
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

val config = RealmConfiguration.create(schema = setOf(Expense::class, Category::class))
val db: Realm = Realm.open(config)