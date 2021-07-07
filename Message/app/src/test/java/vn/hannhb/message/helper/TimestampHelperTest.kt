package vn.hannhb.message.helper

import org.junit.Assert
import org.junit.Test
import vn.hannhb.message.core.helpers.TimestampHelper

class TimestampHelperTest {
    private val dayMonthYearPattern = "dd/MM/yyyy"
    private val dayMonthYearTimePattern = "dd/MM/yyy - hh:mm"
    private val timestamp = 1539748532948
    private val negativeTimestamp = -1861945262080

    @Test
    fun test_getTimeWithTimestampNull_dayMonthYearPattern() {
        val expectedResult = ""
        val actualResult = TimestampHelper.getDateTime(null, dayMonthYearPattern)

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun test_getTimeWithTimestampNull_dayMonthYearTimePattern() {
        val expectedResult = ""
        val actualResult = TimestampHelper.getDateTime(null, dayMonthYearTimePattern)

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun test_getTimeWithTimestampNotNull_dayMonthYearPattern() {
        val expectedResult = "17/10/2018"
        val actualResult = TimestampHelper.getDateTime(timestamp, dayMonthYearPattern)

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun test_getTimeWithTimestampNotNull_dayMonthYearTimePattern() {
        val expectedResult = "17/10/2018 - 10:55"
        val actualResult = TimestampHelper.getDateTime(timestamp, dayMonthYearTimePattern)

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun test_getTimeWithNegativeTimestamp_dayMonthYearPattern() {
        val expectedResult = "01/01/1911"
        val actualResult = TimestampHelper.getDateTime(negativeTimestamp, dayMonthYearPattern)

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun test_getTimeWithNegativeTimestamp_dayMonthYearTimePattern() {
        val expectedResult = "01/01/1911 - 12:05"
        val actualResult = TimestampHelper.getDateTime(negativeTimestamp, dayMonthYearTimePattern)

        Assert.assertEquals(expectedResult, actualResult)
    }
}
