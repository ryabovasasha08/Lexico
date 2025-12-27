package com.oriabova.lexico.newwordscheduler.domain.model

data class TimeOfDay(
    val hour: Int,
    val minute: Int
) {
    init {
        require(hour in 0..23) { "Hour must be between 0 and 23." }
        require(minute in 0..59) { "Minute must be between 0 and 59." }
    }

    companion object {
        val Default = TimeOfDay(hour = 10, minute = 0)
    }
}
