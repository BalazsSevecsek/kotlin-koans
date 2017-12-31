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
    override fun iterator(): Iterator<MyDate> {
        return DateRangeIterator(this)
    }
}

class DateRangeIterator(val range: DateRange): Iterator<MyDate>
{
    var currentDate: MyDate = range.start
    override fun next(): MyDate {
        val resultDate :MyDate = currentDate
        currentDate = currentDate.addTimeIntervals(TimeInterval.DAY,1)
        return resultDate
    }

    override fun hasNext(): Boolean = currentDate <= range.endInclusive
}

operator fun MyDate.plus(other: TimeInterval) = addTimeIntervals(other,1)

class RepeatedTimeInterval(val interval: TimeInterval, val number: Int)

operator fun MyDate.plus(other: RepeatedTimeInterval)
        = addTimeIntervals(other.interval,other.number)

operator fun TimeInterval.times(other: Int):RepeatedTimeInterval{
    return RepeatedTimeInterval(this,other)
}
