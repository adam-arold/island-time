package io.islandtime.extensions.threetenabp.zone

import android.app.Application
import android.content.Context
import com.jakewharton.threetenabp.AndroidThreeTen
import io.islandtime.*
import io.islandtime.extensions.threetenabp.*
import io.islandtime.measures.*
import io.islandtime.zone.TimeZoneOffsetTransition
import io.islandtime.zone.TimeZoneRules
import io.islandtime.zone.TimeZoneRulesException
import io.islandtime.zone.TimeZoneRulesProvider
import org.threeten.bp.Instant as JavaInstant
import org.threeten.bp.zone.ZoneOffsetTransition
import org.threeten.bp.zone.ZoneRules
import org.threeten.bp.zone.ZoneRulesException
import org.threeten.bp.zone.ZoneRulesProvider

/**
 * A time zone rules provider that draws from the DB packaged with ThreeTenAbp
 */
class ThreeTenAbp(context: Context, assetPath: String = "") : TimeZoneRulesProvider {

    init {
        if (assetPath.isNotEmpty()) {
            AndroidThreeTen.init(context, assetPath)
        } else {
            AndroidThreeTen.init(context)
        }
    }

    constructor(application: Application) : this(application as Context)

    override val databaseVersion: String
        get() = try {
            ZoneRulesProvider.getVersions("UTC")?.lastEntry()?.key.orEmpty()
        } catch (e: ZoneRulesException) {
            throw TimeZoneRulesException(e.message, e)
        }

    override val availableRegionIds: Set<String> = ZoneRulesProvider.getAvailableZoneIds()

    override fun rulesFor(regionId: String): TimeZoneRules {
        return try {
            JavaTimeZoneRules(ZoneRulesProvider.getRules(regionId, false))
        } catch (e: ZoneRulesException) {
            throw TimeZoneRulesException(e.message, e)
        }
    }
}

private class JavaTimeZoneRules(
    private val javaZoneRules: ZoneRules
) : TimeZoneRules {

    override fun offsetAt(millisecondsSinceUnixEpoch: LongMilliseconds): UtcOffset {
        val offset = javaZoneRules.getOffset(JavaInstant.ofEpochMilli(millisecondsSinceUnixEpoch.value))
        return offset.toIslandUtcOffset()
    }

    override fun offsetAt(secondsSinceUnixEpoch: LongSeconds, nanosecondAdjustment: IntNanoseconds): UtcOffset {
        val offset = javaZoneRules.getOffset(
            JavaInstant.ofEpochSecond(secondsSinceUnixEpoch.value, nanosecondAdjustment.value.toLong())
        )
        return offset.toIslandUtcOffset()
    }

    override fun offsetAt(instant: Instant): UtcOffset {
        val offset = javaZoneRules.getOffset(instant.toJavaInstant())
        return offset.toIslandUtcOffset()
    }

    override fun offsetAt(dateTime: DateTime): UtcOffset {
        val offset = javaZoneRules.getOffset(dateTime.toJavaLocalDateTime())
        return offset.toIslandUtcOffset()
    }

    override fun validOffsetsAt(dateTime: DateTime): List<UtcOffset> {
        val offsets = javaZoneRules.getValidOffsets(dateTime.toJavaLocalDateTime())
        return offsets.map { it.toIslandUtcOffset() }
    }

    override fun transitionAt(dateTime: DateTime): TimeZoneOffsetTransition? {
        return JavaTimeZoneOffsetTransition(javaZoneRules.getTransition(dateTime.toJavaLocalDateTime()))
    }

    override fun isValidOffset(
        dateTime: DateTime,
        offset: UtcOffset
    ): Boolean {
        return javaZoneRules.isValidOffset(dateTime.toJavaLocalDateTime(), offset.toJavaZoneOffset())
    }

    override fun isDaylightSavingsAt(instant: Instant): Boolean {
        return javaZoneRules.isDaylightSavings(instant.toJavaInstant())
    }

    override fun daylightSavingsAt(instant: Instant): IntSeconds {
        return javaZoneRules.getDaylightSavings(instant.toJavaInstant()).seconds.toInt().seconds
    }
}

private class JavaTimeZoneOffsetTransition(
    private val javaZoneOffsetTransition: ZoneOffsetTransition
) : TimeZoneOffsetTransition {

    override val dateTimeBefore: DateTime
        get() = javaZoneOffsetTransition.dateTimeBefore.toIslandDateTime()

    override val dateTimeAfter: DateTime
        get() = javaZoneOffsetTransition.dateTimeAfter.toIslandDateTime()

    override val offsetBefore: UtcOffset
        get() = javaZoneOffsetTransition.offsetBefore.toIslandUtcOffset()

    override val offsetAfter: UtcOffset
        get() = javaZoneOffsetTransition.offsetAfter.toIslandUtcOffset()

    override val duration: IntSeconds
        get() = offsetAfter.totalSeconds - offsetBefore.totalSeconds

    override val isGap: Boolean
        get() = javaZoneOffsetTransition.isGap

    override val isOverlap: Boolean
        get() = javaZoneOffsetTransition.isOverlap
}