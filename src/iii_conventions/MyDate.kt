package iii_conventions

import java.util.*

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return if(this.year==other.year)
                    if (this.month==other.month)
                        if (this.dayOfMonth==other.dayOfMonth)
                            0
                        else
                            this.dayOfMonth-other.dayOfMonth
                    else
                        this.month-other.month
                else
                    this.year-other.year
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = todoTask27()

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}
operator fun DateRange.contains(other: MyDate): Boolean =
        this.start.compareTo(other)<=0 && this.endInclusive.compareTo(other)>=0

class DateRange(val start: MyDate, val endInclusive: MyDate)
