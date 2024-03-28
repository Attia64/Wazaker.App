package com.attia.wazaker.ui.fragments.azkaar.specificazkaar

data class SpecficAzkaarType(
    val zekr: String,
    var count: Int,
    val id: Int
) {
    fun decreaseCount(): SpecficAzkaarType {
        return if (count > 0) {
            this.copy(count = count - 1)
        } else this
    }
}