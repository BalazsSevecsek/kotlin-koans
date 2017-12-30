package iii_conventions

import java.util.*

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate>{
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

operator fun MyDate.rangeTo(other: MyDate):DateRange = DateRange(this,other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}
operator fun DateRange.contains(other: MyDate): Boolean =
        this.start.compareTo(other)<=0 && this.endInclusive.compareTo(other)>=0

class DateRange(val start: MyDate, val endInclusive: MyDate):Iterable<MyDate>{
    var stx : MyDate = MyDate(start.year,start.month,start.dayOfMonth)
    override fun iterator(): Iterator<MyDate> {
        return DateRangeIterator
    }

    companion object DateRangeIterator: Iterator<MyDate>{
        override fun next(): MyDate {
            return MyDate(1990,7,6)
        }

        override fun hasNext(): Boolean {
            return true
        }
    }
}

