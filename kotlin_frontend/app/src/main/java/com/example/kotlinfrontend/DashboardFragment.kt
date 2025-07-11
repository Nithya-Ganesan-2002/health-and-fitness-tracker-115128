package com.example.kotlinfrontend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import android.graphics.drawable.Drawable
import android.widget.TextView

// PUBLIC_INTERFACE
/**
 * DashboardFragment - Shows progress tracking, stats, reminders, and quick overview.
 * Now with a modern monthly progress calendar for daily tracking.
 */
class DashboardFragment : Fragment() {

    // Sample progress-tracked days for the current month (to be replaced with actual data)
    private fun getSampleTrackedDays(): Set<CalendarDay> {
        val now = java.util.Calendar.getInstance()
        val year = now.get(java.util.Calendar.YEAR)
        val month = now.get(java.util.Calendar.MONTH) // 0-based

        // Example: mark all Mondays and Thursdays as 'progress done'
        val res = mutableSetOf<CalendarDay>()
        val cal = java.util.Calendar.getInstance()
        cal.set(year, month, 1)
        do {
            val dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK)
            if (dayOfWeek == java.util.Calendar.MONDAY || dayOfWeek == java.util.Calendar.THURSDAY) {
                res.add(CalendarDay.from(cal))
            }
            cal.add(java.util.Calendar.DAY_OF_MONTH, 1)
        } while (cal.get(java.util.Calendar.MONTH) == month)
        return res
    }

    // Minimal dot decorator for tracked days (modern style)
    class ProgressDotDecorator(
        private val days: Set<CalendarDay>,
        private val dotColor: Int,
        private val context: android.content.Context
    ) : DayViewDecorator {
        override fun shouldDecorate(day: CalendarDay): Boolean = days.contains(day)

        override fun decorate(view: DayViewFacade) {
            // Draw a little filled circle below the date number, as a progress indicator.
            view.setBackgroundDrawable(getDotDrawable(dotColor))
        }

        // Helper to create a small colored circle
        private fun getDotDrawable(color: Int): Drawable {
            val size = 16 // px, small dot
            val shape = android.graphics.drawable.GradientDrawable()
            shape.shape = android.graphics.drawable.GradientDrawable.OVAL
            shape.setColor(color)
            shape.setSize(size, size)
            return shape
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val calendarView = v.findViewById<MaterialCalendarView>(R.id.monthlyCalendar)
        if (calendarView != null) {
            // Set selection mode to single day
            calendarView.selectionMode = MaterialCalendarView.SELECTION_MODE_SINGLE
            // Ensure we always display the current month
            calendarView.setCurrentDate(CalendarDay.today(), true)

            // Sample: visualize progress with dots (replace with real data connection)
            val trackedDays = getSampleTrackedDays()
            val context = requireContext()
            val dotColor = ContextCompat.getColor(context, R.color.accent)
            calendarView.removeDecorators()
            calendarView.addDecorator(ProgressDotDecorator(trackedDays, dotColor, context))

            // Handle user selection
            calendarView.setOnDateChangedListener { widget, date, selected ->
                if (selected) {
                    val statsText = v.findViewById<TextView>(R.id.todayStats)
                    // For demo, show tracked if the dot is present, else "No progress"
                    if (trackedDays.contains(date)) {
                        statsText?.text = "Tracked • " + "${date.day}/${date.month}/${date.year}"
                    } else {
                        statsText?.text = "No progress for ${date.day}/${date.month}/${date.year}"
                    }
                }
            }
        }

        return v
    }
}
