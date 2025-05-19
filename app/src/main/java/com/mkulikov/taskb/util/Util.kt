package com.mkulikov.taskb.util

inline fun <T> createIf(condition: Boolean, block: () -> T): T? {
    return if (condition) block.invoke() else null
}