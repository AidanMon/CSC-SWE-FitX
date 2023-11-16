package com.example.fitx.view.home_page_links

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitx.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import android.widget.TextView
import android.widget.LinearLayout
import android.util.Log
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.google.firebase.firestore.DocumentSnapshot
import java.time.temporal.WeekFields
import java.time.ZoneId
import com.google.firebase.Timestamp
import com.google.firebase.firestore.Query
import android.util.TypedValue
import android.graphics.Typeface
import android.graphics.Color

class ProgressFragment : Fragment() {
    private fun fetchInputData() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        userId?.let { uid ->
            FirebaseFirestore.getInstance().collection("users").document(uid)
                .collection("Input Data")
                .orderBy("date", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener { documents ->
                    val workoutsByDate = documents.groupBy { document ->
                        val timestamp = document.getTimestamp("date")
                        val instant = timestamp?.toDate()?.toInstant()
                        instant?.atZone(ZoneId.systemDefault())?.toLocalDate()
                    }


                    // Convert to a list and sort by date in descending order
                    val sortedWorkouts = workoutsByDate.toList().sortedByDescending { it.first }

                    sortedWorkouts.forEach { (date, dailyDocuments) ->
                        if (date != null) {
                            addToLayout("Date: ${formatDate(Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))}\n", isDate = true)

                            dailyDocuments.forEach { document ->
                                displayData(document.id, document.data, isExerciseName = true)

                            }
                        }
                    }



                    // Calculate and display achievements
                    val workouts = documents.map { documentToWorkoutData(it) }
                    val achievements = calculateAchievements(workouts)
                    displayAchievements(achievements)
                }
                .addOnFailureListener { exception ->
                    Log.e("ProgressFragment", "Error fetching data", exception)
                }
        }
    }





    private fun displayData(documentId: String, data: Map<String, Any>, isExerciseName: Boolean = false) {
        if (isExerciseName) {
            val exerciseName = data["exerciseName"] as? String ?: "Unknown Exercise"
            addToLayout("$exerciseName\n", isExerciseName = true)
        }

        val setsText = StringBuilder()
        var i = 1
        while (data.containsKey("Set$i")) {
            val set = data["Set$i"] as? Map<*, *>
            if (set != null) {
                val count = set["Count"]?.toString() ?: "0"
                val weight = set["Weight"]?.toString() ?: "0"
                setsText.append("SET $i: $count reps, $weight lbs\n")
            }
            i++
        }

        if (setsText.isNotEmpty()) {
            addToLayout(setsText.toString())
        }
    }



    private fun formatDate(date: Date): String {
        val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        return formatter.format(date)
    }

    private fun formatSets(data: Map<String, Any>): String {
        val sets = StringBuilder()
        var i = 1
        while (data.containsKey("Set$i")) {
            val set = data["Set$i"] as? Map<*, *>
            if (set != null) {
                val count = set["Count"]?.toString() ?: "0"
                val weight = set["Weight"]?.toString() ?: "0"
                sets.append("SET $i: $count reps, $weight lbs\n")
            } else {
                break // Exit the loop if a set is missing
            }
            i++
        }
        return sets.toString()
    }


    private fun addToLayout(displayText: String, isDate: Boolean = false, isExerciseName: Boolean = false, isAchievement: Boolean = false) {
        val textView = TextView(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            text = displayText

            if (isDate) {
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f) // Example size for dates
            }

            if (isExerciseName) {
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f) // Same size as date
                setTypeface(null, Typeface.BOLD) // Bold text
                setTextColor(Color.RED) // Red text
            }

            if (isAchievement) {
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f) // Example size for achievements
                setTypeface(null, Typeface.BOLD) // Bold text
                setTextColor(Color.parseColor("#FFD700")) // Gold text (hex color code)
            }

            if (!isDate && !isExerciseName && !isAchievement) {
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f) // Size for other texts
            }
        }

        val layout = view?.findViewById<LinearLayout>(R.id.progressLayout)
        layout?.addView(textView)
    }


    private fun documentToWorkoutData(document: DocumentSnapshot): WorkoutData {
        var totalWeight = 0
        var totalReps = 0

        val timestamp = (document.getTimestamp("date") ?: Timestamp.now()).toDate()

        var i = 1
        while (document.contains("Set$i")) {
            val set = document["Set$i"] as? Map<*, *>
            if (set != null) {
                totalWeight += (set["Weight"] as? Number)?.toInt() ?: 0
                totalReps += (set["Count"] as? Number)?.toInt() ?: 0
            }
            i++
        }

        return WorkoutData(timestamp, totalWeight, totalReps)

    }

    private fun calculateAchievements(workouts: List<WorkoutData>): Map<String, Int> {
        val achievements = mutableMapOf<String, Int>()

        // Example structure of WorkoutData class
        // data class WorkoutData(val date: Date, val totalWeight: Int, val totalReps: Int)

        val workoutsByLocalDate = workouts.groupBy {
            it.date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        }


        // Good Work Badge and Committed Badge logic
        workoutsByLocalDate.forEach { (_, dailyWorkouts) ->
            if (dailyWorkouts.size >= 5) {
                achievements.merge("Good Work Badge", 1, Int::plus)
            }
        }

        // Checking for Committed Badge (workouts on 5 different days in a week)
        val weekFields = WeekFields.of(Locale.getDefault())
        val workoutsByWeek = workouts.groupBy {
            it.date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().get(weekFields.weekOfWeekBasedYear())
        }
        workoutsByWeek.forEach { (_, weeklyWorkouts) ->
            if (weeklyWorkouts.size >= 5) {
                achievements.merge("Committed Badge", 1, Int::plus)
            }
        }

        // Super Strength, Super Endurance, and Pretty Strong Badge logic
        workouts.forEach { workout ->
            if (workout.totalWeight > 999) {
                achievements.merge("Super Strength Badge", 1, Int::plus)
            }
            if (workout.totalReps > 149) {
                achievements.merge("Super Endurance Badge", 1, Int::plus)
            }
            if (workout.totalWeight > 599) {
                achievements.merge("Pretty Strong Badge", 1, Int::plus)
            }
        }

        return achievements
    }


    private fun displayAchievements(achievements: Map<String, Int>) {
        // Format and display achievements
        val formattedAchievements = formatAchievements(achievements)
        formattedAchievements.forEach { achievement ->
            addToLayout(achievement, isAchievement = true)

        }
    }

    private fun formatAchievements(achievements: Map<String, Int>): List<String> {
        return achievements.map { (badge, count) ->
            badge + if (count > 1) " x$count" else ""
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("ProgressFragment", "onCreateView called")
        return inflater.inflate(R.layout.fragment_progress, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("ProgressFragment", "onViewCreated called")
        fetchInputData()
    }



}

data class WorkoutData(val date: Date, val totalWeight: Int, val totalReps: Int)
