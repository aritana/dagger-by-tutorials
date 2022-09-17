package com.aritana.dagger_by_tutorials_and

interface Named {
    val name: String
}

fun main() {
    abstraction()
    composition()//server depends on what Repository is
    aggregation()//server depends on what Repository Does. Repository can be an abstraction.
}

fun aggregation() {

    data class Data(val value: Int)

    class Repository {
        fun save(data: Data) {
            //save data
        }
    }

    class Server(val repository:Repository) {

        fun receive(data: Data) {
            repository.save(data)
        }
    }
}

fun composition() {
    data class Data(val value: Int)

    class Repository {
        fun save(data: Data) {
            //save data
        }
    }

    class Server {
        private val repository = Repository()

        fun receive(data: Data) {
            repository.save(data)
        }
    }
}

fun abstraction() {

    abstract class Person(override val name: String) : Named {
        fun think() {
            println("$name is thinking...")
        }
    }

    class Student(name: String) : Person(name) {
        fun study(topic: String) {
            println("$name is studying $topic")
        }
    }

    class Musician(name: String) : Person(name) {
        fun play(instrument: String) {
            println("$name is playing $instrument")
        }
    }

    class Cat(override val name: String) : Named {
        fun meow() {
            println("$name is meowing")
        }
    }

    fun printNames(named: List<Named>) = named.forEach { println(it.name) }

    val persons = listOf(
        Student("Topolino"),
        Musician("Bach"),
        Student("Minnie"),
        Musician("Paganini"),
        Cat("Silvestro")
    )
    printNames(persons)
}