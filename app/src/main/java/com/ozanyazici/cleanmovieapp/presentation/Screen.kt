package com.ozanyazici.cleanmovieapp.presentation
// bu sınıfı açtık çünkü mainActivityde navigation da routeları ekranları tasarlarken composableların içinde de yazacağız
// karışıklık olmasın diye böyle yapıyoruz. Constants içerisinde de yapabilirdik ama endüstride genelde böyle kullanılıyor.
sealed class Screen(val route: String) {
    object MovieScreen: Screen("movie_screen")
    object MovieDetailScreen: Screen("movie_detail_screen")
}