package com.ozanyazici.cleanmovieapp.presentation.movies

// Kullanıcı etkileşimleriyle ilgili olaylar, UI bileşenlerinde (örneğin, bir düğmeye tıklama, bir metin girişi yapma,
// bir listeden öğe seçme) gerçekleşir.

//sealed classlar, bir türün belirli alt türlerinin sınırlı bir listesini temsil etmek için idealdir.
// Bu, bir türün potansiyel tüm durumlarını tek bir yerde tanımlamanıza ve kontrol altında tutmanıza olanak tanır.
// Yani eventleri tek bir çatı altında topluyoruz.

// prensentation katmanındaki her paket için eğer ihitiyaç varsa bir event bir state bir de viewmodel oluştururuz.
// ihtiyaç olursa dedik çünkü event e her zaman ihtiyaç olmayabilir ama diğerlerine genelde oluyor.

sealed class MoviesEvent {
    data class Search(val searchString: String): MoviesEvent()
}