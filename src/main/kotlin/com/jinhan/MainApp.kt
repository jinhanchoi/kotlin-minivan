package com.jinhan

fun main(args : Array<String>){
    println("test")
    val add : (Int) -> (Int) -> Int = { a -> { b -> a + b} }
    val mult : (Int) -> Int = { a -> a * 2 }
    val add5 = add(5)
    val add5Mult = myCompose(mult,add5)
    val intToStr : (Int) -> String = { a -> "result = $a" }

    val comp = compose<Int,Int,Int>()(add5)(mult)
    val comp2 = compose<Int,Int,String>()(intToStr)(comp)
    val result2 = comp(10)
    println(result2)
    println(comp2(10))
}

fun myCompose( fn1 : (Int)-> Int, fn2 : (Int)-> Int) : (Int) -> Int
        = { p -> fn1(fn2(p)) }

fun<T,U,V> compose() = {
    f:(U) -> V ->
        { g:(T) -> U ->
            { x: T -> f(g(x))}
        }
}

fun<A,B,C> partialA( a: A, f:(A)-> (B)->C ): (B)->C = f(a)
fun<A,B,C> partialB( b: B, f:(A)-> (B)->C ): (A)->C = {
    a:A ->
    f(a)(b)
}
