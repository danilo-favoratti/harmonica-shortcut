package com.favoratti.harmonicashortcut.data

class CircularList<T>(private val items: List<T>) : Iterable<T> {
    private var currentIndex = 0

    override fun iterator(): Iterator<T> {
        return object : Iterator<T> {
            private var iterations = 0

            override fun hasNext(): Boolean {
                return iterations < items.size
            }

            override fun next(): T {
                if (items.isEmpty()) throw NoSuchElementException()
                val currentItem = items[currentIndex]
                moveToNext()
                iterations++
                return currentItem
            }
        }
    }

    private fun moveToNext() {
        currentIndex = (currentIndex + 1) % items.size
    }

    private fun moveToPrevious() {
        currentIndex = (currentIndex - 1 + items.size) % items.size
    }

    fun translateForwardFromKey(value: T, quantity: Int = 1): T {
        currentIndex = items.indexOf(value)
        for (i in 1..quantity) {
            moveToNext()
        }
        return items[currentIndex]
    }

    fun translateBackwardFromKey(value: T, quantity: Int = 1): T {
        currentIndex = items.indexOf(value)
        for (i in 1..quantity) {
            moveToPrevious()
        }
        return items[currentIndex]
    }
}