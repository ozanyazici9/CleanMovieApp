package com.ozanyazici.cleanmovieapp.util

object Constants {
    const val API_KEY = "250901b2"
    // url http ile başladığında hata veriyor çünkü güvenli değil. Eğer http kullanmak gerekiyorsa bunun için özel izin almamız gerekiyor.
    const val BASE_URL = "http://www.omdbapi.com"
    // filmi spesifik olarak bu değişkeni kullanarak alıcağız.
    const val IMDB_ID = "imdb_id"
}