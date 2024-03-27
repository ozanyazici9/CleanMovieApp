package com.ozanyazici.cleanmovieapp.util
// Kotlin'de "sealed class" (mühürlü sınıf), sınıf hiyerarşisinde kullanılan özel bir yapıdır.
// Sealed class, o sınıfın bir alt sınıflarını sınırlar ve genellikle birbirleriyle ilişkili olan sınıf türlerini temsil etmek
// için kullanılır.

// Şimdi endüstride kullanılan daha temiz bir mimariyi uygulayacağız. Bu mimaride data domain ve presentation paketlerimiz var.
// presentation paketimizde: kullanıcının göreceği etkileşime geçeceği kodları state i ve viewmodelları tutacağız.
// data paketimizde: veri ile ilgili olan kodları burada tutacağız.
// domain paketimizde: business logic i tuttacağız yani repositoryleri use caseleri burada tutacağız.
// Use Case = tek ve en küçük işleri yapan yapılara denir. use caseleri repositoryde kullanacağız.
// Aslında repositoryide use caseleri kullanarak bölmüş olduk. olabildiğince single responsible yapıyoruz.
sealed class Resource<T>(val data: T? = null, val message:String? = null) {
    class Success<T>(data: T) : Resource<T>(data = data)
    class Error<T>(message: String, data:T? = null) : Resource<T>(data = data,message=message)
    class Loading<T>(data:T?= null) : Resource<T>(data=data)
}