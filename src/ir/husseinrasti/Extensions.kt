package ir.husseinrasti

fun String.isNumeric(): Boolean = try {
    this.replace(" ", "").trim().toBigInteger()
    true
} catch (e: NumberFormatException) {
    false
}

fun header() {
    println(
        "******************************************\n" +
                "*********Box Packing Application**********\n" +
                "******************************************\n" +
                "**********Author: Hussein Rasti***********\n"
    )
}

fun footer(count: Int) {
    println(
        "******** $count objects can be packed *********\n" +
                "******************************************\n" +
                "************** MIT License ***************\n" +
                "**** Copyright (c) 2021 Hussein Rasti ****\n" +
                "******************************************\n"
    )
}