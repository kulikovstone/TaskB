package com.mkulikov.taskb.base

inline fun <T> createIf(condition: Boolean, block: () -> T): T? {
    return if (condition) block.invoke() else null
}