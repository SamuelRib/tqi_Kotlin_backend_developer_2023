package me.tqi.entity

data class FormaPagamento(
    val cartaoCredito: String,
    val debito: String,
    val dinheiroFisico: String,
    val pix: String
)
