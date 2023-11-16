package com.example.fitx.view.user_data

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.TypedValue
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.fitx.R
import com.example.fitx.databinding.InputSelectedWorkoutDataFragBinding
import com.example.fitx.model.Exercise
import com.example.fitx.repository.AllExerciseLists
import com.example.fitx.repository.ExerciseRepository
import com.example.fitx.repository.WorkoutDataRepository
import java.lang.Exception

class InputSelectedWorkoutDataFragBinding : Fragment() {

    private var _binding: InputSelectedWorkoutDataFragBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //Creating my different exercise lists as well as their button and text view lists
    private var exercisesList = mutableListOf<Exercise>()
    private var textViewList = mutableListOf<TextView>()
    private var expandButtonList = mutableListOf<Button>()

    private var layoutList = mutableListOf<ConstraintLayout>()
    private var setCountTextList = mutableListOf<TextView>()
    private var setCountInputList = mutableListOf<EditText>()
    //These are lists the size on the number of layouts/setCount with each sublist being associated with one
    private var setHeaderList: MutableList<MutableList<TextView>> = MutableList(AllExerciseLists.currentSelectedWorkout.second.size) { mutableListOf() }
    private var repCountTextList: MutableList<MutableList<TextView>> = MutableList(AllExerciseLists.currentSelectedWorkout.second.size) { mutableListOf() }
    private var repCountInputList: MutableList<MutableList<EditText>> = MutableList(AllExerciseLists.currentSelectedWorkout.second.size) { mutableListOf() }
    private var weightTextList: MutableList<MutableList<TextView>> = MutableList(AllExerciseLists.currentSelectedWorkout.second.size) { mutableListOf() }
    private var weightInputList: MutableList<MutableList<EditText>> = MutableList(AllExerciseLists.currentSelectedWorkout.second.size) { mutableListOf() }


    //Function to truncate Exercise names that are too long
    private fun setStringMaxLength(input: String, maxLength: Int): String {
        return if (input.length <= maxLength) {
            input
        } else {
            input.substring(0, maxLength) + "."
        }
    }

    private val dpTextHeightValue = 64
    private val textHeightValue = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, dpTextHeightValue.toFloat(), Resources.getSystem().displayMetrics
    )
    private val dpTextWidthValue = 380
    private val textWidthValue = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, dpTextWidthValue.toFloat(), Resources.getSystem().displayMetrics
    )
    //Button
    private val dpButtonHeightValue = 40
    private val buttonHeightValue = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, dpButtonHeightValue.toFloat(), Resources.getSystem().displayMetrics
    )
    private val dpButtonWidthValue = 84
    private val buttonWidthValue = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, dpButtonWidthValue.toFloat(), Resources.getSystem().displayMetrics
    )

    //Function to create our views for each muscle group
    private fun createAllViews(exerciseList: MutableList<Exercise>, parentLayout: ConstraintLayout, textViewList: MutableList<TextView>,
                               inputButtonList: MutableList<Button>, layoutList: MutableList<ConstraintLayout>, setCountTextList: MutableList<TextView>,
                               setCountInputList: MutableList<EditText>){

        //Creating the TextViews and all Buttons for the leg exercises
        for(exercise in exerciseList){
            //Creating out TextViews
            val newTextView = TextView(requireContext())
            parentLayout.addView(newTextView)
            newTextView.id = View.generateViewId()
            newTextView.text = setStringMaxLength(exercise.exerciseName,16)// Change the text
            newTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 64f)
            newTextView.setPadding(11,21,0,0)
            newTextView.height = textHeightValue.toInt()
            newTextView.width = textWidthValue.toInt()
            newTextView.setTextColor(Color.BLACK)
            //newTextView.textAlignment = TextView.TEXT_ALIGNMENT_VIEW_START
            newTextView.setBackgroundResource(R.drawable.exercise_border)
            textViewList.add(newTextView)

            //Creating our buttons
            val newButton = Button(requireContext())
            parentLayout.addView(newButton)
            //newButton.tag = (element.exerciseName + "Video")            //Just the tag
            newButton.id = View.generateViewId()
            newButton.text = "Input Data"                                  // Change the text
            newButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, 37f)  //Text size
            newButton.gravity = Gravity.CENTER                          //Text centered
            newButton.height = buttonHeightValue.toInt()                //Text height
            newButton.width = buttonWidthValue.toInt()                  //Text width
            newButton.setTextColor(Color.WHITE)                         //Text color
            newButton.setBackgroundResource(R.drawable.rounded_button)
            inputButtonList.add(newButton)
        }

        //Adding layouts to parent
        for(i in 0 until textViewList.size){
            val newLayout = ConstraintLayout(requireContext())
            parentLayout.addView(newLayout)
            newLayout.id = View.generateViewId()

            newLayout.setBackgroundResource(R.drawable.red_triple_border_top_gone)
            newLayout.visibility = View.GONE
            layoutList.add(newLayout)
        }

        var dpValue = 112
        var widthValue = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dpValue.toFloat(), Resources.getSystem().displayMetrics
        )
        dpValue = 42
        var heightValue = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dpValue.toFloat(), Resources.getSystem().displayMetrics
        )

        //Adding setCountText to our added layouts
        for(layout in layoutList){
            val newSetCountText = TextView(requireContext())
            layout.addView(newSetCountText)
            newSetCountText.id = View.generateViewId()
            newSetCountText.text = "# Of Sets"
            newSetCountText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)
            newSetCountText.setPadding(11,11,0,0)
            newSetCountText.width = widthValue.toInt()
            newSetCountText.height = heightValue.toInt()
            newSetCountText.setTextColor(Color.BLACK)
            newSetCountText.setBackgroundResource(R.drawable.workout_border)
            setCountTextList.add(newSetCountText)
        }

        dpValue = 251
        widthValue = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dpValue.toFloat(), Resources.getSystem().displayMetrics
        )
        dpValue = 42
        heightValue = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dpValue.toFloat(), Resources.getSystem().displayMetrics
        )
        //Adding setCountInput to our added layouts
        for(layout in layoutList){
            val newSetCountInput = EditText(requireContext())
            layout.addView(newSetCountInput)
            newSetCountInput.id = View.generateViewId()
            newSetCountInput.hint = "Enter Number Of Sets"
            newSetCountInput.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)
            newSetCountInput.setPadding(11,11,0,11)
            newSetCountInput.width = 657
            newSetCountInput.height = heightValue.toInt()
            val textColor = ContextCompat.getColor(requireContext(), R.color.black)
            newSetCountInput.setTextColor(textColor)
            newSetCountInput.setBackgroundResource(R.color.white)
            newSetCountInput.inputType = InputType.TYPE_CLASS_NUMBER
            newSetCountInput.maxEms = 1
            setCountInputList.add(newSetCountInput)
        }
    }

    //Setting constraints within our sub layouts
    private fun setConstraintSubLayout(myLayouts: MutableList<ConstraintLayout>, setCountTextList: MutableList<TextView>, setCountInputList: MutableList<EditText>){
        //Beginning of setting constraints
        for(i in 0 until myLayouts.size){
            val constraintSet = ConstraintSet()
            constraintSet.clone(myLayouts[i])

            //Constraint for TextView
            constraintSet.connect(setCountTextList[i].id, ConstraintSet.TOP, myLayouts[i].id, ConstraintSet.TOP)
            constraintSet.connect(setCountTextList[i].id, ConstraintSet.LEFT, myLayouts[i].id,ConstraintSet.LEFT)

            //Constraint for EditText
            constraintSet.connect(setCountInputList[i].id, ConstraintSet.TOP, myLayouts[i].id, ConstraintSet.TOP)
            constraintSet.connect(setCountInputList[i].id, ConstraintSet.LEFT, setCountTextList[i].id,ConstraintSet.RIGHT)

            constraintSet.applyTo(myLayouts[i])
        }
    }

    //Function set the constraints of our added items
    private fun setConstraint(parentLayout: ConstraintLayout, layoutList: MutableList<ConstraintLayout>, textViewList: MutableList<TextView>,
                              inputButtonList: MutableList<Button>){
        //Beginning of setting constraints
        val constraintSet = ConstraintSet()
        constraintSet.clone(parentLayout)

        //Constraint for first TextView
        constraintSet.connect(textViewList[0].id, ConstraintSet.TOP, R.id.inputSelectedWorkoutDataHeader, ConstraintSet.BOTTOM, 64)
        constraintSet.connect(textViewList[0].id, ConstraintSet.LEFT, parentLayout.id,ConstraintSet.LEFT,42)

        //Constraint for first Button
        constraintSet.connect(inputButtonList[0].id, ConstraintSet.TOP, textViewList[0].id, ConstraintSet.TOP, 21)
        constraintSet.connect(inputButtonList[0].id, ConstraintSet.RIGHT, textViewList[0].id, ConstraintSet.RIGHT, 6)

        //Constraint for first Layout
        constraintSet.connect(layoutList[0].id, ConstraintSet.TOP, textViewList[0].id, ConstraintSet.BOTTOM, -16)
        constraintSet.connect(layoutList[0].id, ConstraintSet.LEFT, textViewList[0].id, ConstraintSet.LEFT)

        //Constraints for the remainder of the exercises
        for(i in 1 until textViewList.size){
            //TextView constraints
            constraintSet.connect(textViewList[i].id, ConstraintSet.TOP, layoutList[i-1].id, ConstraintSet.BOTTOM, 21)
            constraintSet.connect(textViewList[i].id, ConstraintSet.LEFT, parentLayout.id,ConstraintSet.LEFT, 42)

            //Button constraints
            constraintSet.connect(inputButtonList[i].id, ConstraintSet.TOP, textViewList[i].id, ConstraintSet.TOP, 21)
            constraintSet.connect(inputButtonList[i].id, ConstraintSet.RIGHT, textViewList[i].id, ConstraintSet.RIGHT, 6)

            //Layout constraints
            constraintSet.connect(layoutList[i].id, ConstraintSet.TOP, textViewList[i].id, ConstraintSet.BOTTOM, -16)
            constraintSet.connect(layoutList[i].id, ConstraintSet.LEFT, textViewList[0].id, ConstraintSet.LEFT)

        }

        constraintSet.applyTo(parentLayout)
    }

    //Function to add all of the button listeners
    private fun addButtonListeners(layoutList: MutableList<ConstraintLayout>, expandButtonList: MutableList<Button>,
                                   textViewList: MutableList<TextView>){
        for(i in 0 until layoutList.size){
            //Setting buttons for drop down menu
            expandButtonList[i].setOnClickListener {
                if (layoutList[i].visibility == View.GONE) {
                    layoutList[i].visibility = View.VISIBLE
                    textViewList[i].setBackgroundResource(R.drawable.red_triple_border_bottom_gone)
                } else {
                    layoutList[i].visibility = View.GONE
                    textViewList[i].setBackgroundResource(R.drawable.exercise_border)
                }
            }
        }
    }

    //Adding a listener for changing number of sets
    private fun addSetChangeListeners(myLayouts: MutableList<ConstraintLayout>, setCountInputList: MutableList<EditText>){
        for((i, editText) in setCountInputList.withIndex()){
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    addViewsBasedOnInput(s.toString(), myLayouts[i], i)
                }
            })
        }
    }

    private fun getOrdinalNumber(n: Int): String {
        val suffix = when {
            n % 100 in 11..13 -> "th"
            n % 10 == 1 -> "st"
            n % 10 == 2 -> "nd"
            n % 10 == 3 -> "rd"
            else -> "th"
        }

        return "$n$suffix"
    }

    //Resetting the layout so we can have the desired number of Sets
    private fun resetOneLayout(index: Int){
        layoutList[index].addView(setCountTextList[index])
        layoutList[index].addView(setCountInputList[index])

        //Zeroing out our list as well
        setHeaderList[index]= mutableListOf()
        repCountTextList[index]= mutableListOf()
        repCountInputList[index]= mutableListOf()
        weightTextList[index]= mutableListOf()
        weightInputList[index]= mutableListOf()
    }

    //Adding the needed Views for data input
    private fun addViewsBasedOnInput(input: String, layout: ConstraintLayout, indexNum: Int){
        layoutList[indexNum].removeAllViewsInLayout()
        resetOneLayout(indexNum)
        if(input != "" && input != "0"){    //Check to see if the EditText is empty
            var inputNum = input.toInt()

            //Setting the max set count to 10
            if(inputNum > 10){
                inputNum = 10
            }

            var dpValue = 112
            var widthValue = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dpValue.toFloat(), Resources.getSystem().displayMetrics
            )
            dpValue = 42
            var heightValue = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dpValue.toFloat(), Resources.getSystem().displayMetrics
            )
            val blackText = ContextCompat.getColor(requireContext(), R.color.black)

            //Adding the views
            for(i in 0 until inputNum){
                //Adding rep count text
                val setCountHeaderText = TextView(requireContext())
                layout.addView(setCountHeaderText)
                setCountHeaderText.id = View.generateViewId()
                val nameHeader = getOrdinalNumber(i+1) + " Set:"
                setCountHeaderText.text = nameHeader
                setCountHeaderText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)
                setCountHeaderText.setPadding(11,11,0,0)
                setCountHeaderText.width = widthValue.toInt()
                setCountHeaderText.height = heightValue.toInt()
                setCountHeaderText.setTextColor(Color.BLACK)
                setHeaderList[indexNum].add(setCountHeaderText)

                //Adding rep count text
                val repCountText = TextView(requireContext())
                layout.addView(repCountText)
                repCountText.id = View.generateViewId()
                repCountText.text = "# Of Reps"
                repCountText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)
                repCountText.setPadding(11,11,0,0)
                repCountText.width = widthValue.toInt()
                repCountText.height = heightValue.toInt()
                repCountText.setTextColor(Color.BLACK)
                repCountText.setBackgroundResource(R.drawable.workout_border)
                repCountTextList[indexNum].add(repCountText)

                //Adding rep count edit
                val repInput = EditText(requireContext())
                layout.addView(repInput)
                repInput.id = View.generateViewId()
                repInput.hint = "Enter Rep Count"
                repInput.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)
                repInput.setPadding(11,11,0,11)
                repInput.width = 646
                repInput.height = heightValue.toInt()
                repInput.setTextColor(blackText)
                repInput.setBackgroundResource(R.color.white)
                repInput.inputType = InputType.TYPE_CLASS_NUMBER
                repInput.maxEms = 1
                repCountInputList[indexNum].add(repInput)

                //Adding weight text
                val weightText = TextView(requireContext())
                layout.addView(weightText)
                weightText.id = View.generateViewId()
                weightText.text = "Weight"
                weightText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)
                weightText.setPadding(11,11,0,0)
                weightText.width = widthValue.toInt()
                weightText.height = heightValue.toInt()
                weightText.setTextColor(Color.BLACK)
                weightText.setBackgroundResource(R.drawable.workout_border)
                weightTextList[indexNum].add(weightText)


                //Adding weight edit
                val weightInput = EditText(requireContext())
                layout.addView(weightInput)
                weightInput.id = View.generateViewId()
                weightInput.hint = "Enter Weight In lbs"
                weightInput.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)
                weightInput.setPadding(11,11,0,11)
                weightInput.width = 646
                weightInput.height = heightValue.toInt()
                weightInput.setTextColor(blackText)
                weightInput.setBackgroundResource(R.color.white)
                weightInput.inputType = InputType.TYPE_CLASS_NUMBER
                weightInput.maxEms = 3
                weightInputList[indexNum].add(weightInput)
            }


            //Beginning of setting constraints
            val constraintSet = ConstraintSet()
            constraintSet.clone(layout)

            //Constraint for first header
            constraintSet.connect(setHeaderList[indexNum][0].id, ConstraintSet.TOP, setCountTextList[indexNum].id, ConstraintSet.BOTTOM, 21)
            constraintSet.connect(setHeaderList[indexNum][0].id, ConstraintSet.LEFT, layout.id,ConstraintSet.LEFT,11)

            //Constraint for first rep count text
            constraintSet.connect(repCountTextList[indexNum][0].id, ConstraintSet.TOP, setHeaderList[indexNum][0].id, ConstraintSet.BOTTOM, 21)
            constraintSet.connect(repCountTextList[indexNum][0].id, ConstraintSet.LEFT, layout.id,ConstraintSet.LEFT,11)

            //Constraint for first rep count input
            constraintSet.connect(repCountInputList[indexNum][0].id, ConstraintSet.TOP, repCountTextList[indexNum][0].id, ConstraintSet.TOP)
            constraintSet.connect(repCountInputList[indexNum][0].id, ConstraintSet.LEFT, repCountTextList[indexNum][0].id,ConstraintSet.RIGHT)

            //Constraint for first weight text
            constraintSet.connect(weightTextList[indexNum][0].id, ConstraintSet.TOP, repCountTextList[indexNum][0].id, ConstraintSet.BOTTOM, 11)
            constraintSet.connect(weightTextList[indexNum][0].id, ConstraintSet.LEFT, layout.id,ConstraintSet.LEFT,11)

            //Constraint for first weight input
            constraintSet.connect(weightInputList[indexNum][0].id, ConstraintSet.TOP, weightTextList[indexNum][0].id, ConstraintSet.TOP)
            constraintSet.connect(weightInputList[indexNum][0].id, ConstraintSet.LEFT, weightTextList[indexNum][0].id,ConstraintSet.RIGHT)

            //Constraints for the remainder of the exercises
            for(i in 1 until setHeaderList[indexNum].size){
                //Constraint for headers
                constraintSet.connect(setHeaderList[indexNum][i].id, ConstraintSet.TOP, weightTextList[indexNum][i-1].id, ConstraintSet.BOTTOM, 21)
                constraintSet.connect(setHeaderList[indexNum][i].id, ConstraintSet.LEFT, layout.id,ConstraintSet.LEFT,11)

                //Constraint for rep count texts
                constraintSet.connect(repCountTextList[indexNum][i].id, ConstraintSet.TOP, setHeaderList[indexNum][i].id, ConstraintSet.BOTTOM, 21)
                constraintSet.connect(repCountTextList[indexNum][i].id, ConstraintSet.LEFT, layout.id,ConstraintSet.LEFT,11)

                //Constraint for rep count inputs
                constraintSet.connect(repCountInputList[indexNum][i].id, ConstraintSet.TOP, repCountTextList[indexNum][i].id, ConstraintSet.TOP)
                constraintSet.connect(repCountInputList[indexNum][i].id, ConstraintSet.LEFT, repCountTextList[indexNum][i].id,ConstraintSet.RIGHT)

                //Constraint for weight texts
                constraintSet.connect(weightTextList[indexNum][i].id, ConstraintSet.TOP, repCountTextList[indexNum][i].id, ConstraintSet.BOTTOM, 11)
                constraintSet.connect(weightTextList[indexNum][i].id, ConstraintSet.LEFT, layout.id,ConstraintSet.LEFT,11)

                //Constraint for weight inputs
                constraintSet.connect(weightInputList[indexNum][i].id, ConstraintSet.TOP, weightTextList[indexNum][i].id, ConstraintSet.TOP)
                constraintSet.connect(weightInputList[indexNum][i].id, ConstraintSet.LEFT, weightTextList[indexNum][i].id,ConstraintSet.RIGHT)
            }
            constraintSet.applyTo(layout)
        }   //Else... EditText Is Empty
    }

    private fun saveWorkoutDataToRepo(){

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = InputSelectedWorkoutDataFragBinding.inflate(inflater, container, false)

        val rootView = inflater.inflate(R.layout.input_selected_workout_data_frag, container, false)
        val parentLayout = rootView.findViewById<ConstraintLayout>(R.id.parentLayout)

        createAllViews(AllExerciseLists.currentSelectedWorkout.second, parentLayout, textViewList, expandButtonList, layoutList, setCountTextList, setCountInputList)
        setConstraintSubLayout(layoutList,setCountTextList,setCountInputList)
        setConstraint(parentLayout,layoutList,textViewList,expandButtonList)

        //Beginning of setting constraints
        val constraintSet = ConstraintSet()
        constraintSet.clone(parentLayout)

        //Creating our bottom padding object
        val paddingBelow = TextView(requireContext())
        parentLayout.addView(paddingBelow)
        paddingBelow.id = View.generateViewId()
        paddingBelow.height = textHeightValue.toInt()
        paddingBelow.width = textWidthValue.toInt()

        //Bottom padding values and constraint setting
        var paddingValue = 600                  //Excess space to leave room for fullscreen container
        val paddingHeightValue = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, paddingValue.toFloat(), Resources.getSystem().displayMetrics
        )
        constraintSet.connect(paddingBelow.id, ConstraintSet.TOP, layoutList[layoutList.size - 1].id, ConstraintSet.BOTTOM, paddingHeightValue.toInt())
        constraintSet.connect(paddingBelow.id, ConstraintSet.LEFT, R.id.parentLayout, ConstraintSet.LEFT)

        // Apply the constraints
        constraintSet.applyTo(parentLayout)

        // Return the rootView of the fragment
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addButtonListeners(layoutList, expandButtonList,textViewList)
        addSetChangeListeners(layoutList, setCountInputList)

        //Show created workout button
        val saveWorkoutDataButton = view.findViewById<AppCompatButton>(R.id.saveWorkoutDataButton)
        saveWorkoutDataButton.setOnClickListener {

            val workoutDataRepo = WorkoutDataRepository()

            //Creating the int lists
            var repCountIntList: MutableList<MutableList<Int>> = MutableList(AllExerciseLists.currentSelectedWorkout.second.size) { mutableListOf() }
            var weightIntList: MutableList<MutableList<Int>> = MutableList(AllExerciseLists.currentSelectedWorkout.second.size) { mutableListOf() }
            for(i in 0 until repCountInputList.size){
                for(setIndex in 0 until repCountInputList[i].size){
                    val countString = repCountInputList[i][setIndex].text.toString()
                    val weightString = weightInputList[i][setIndex].text.toString()
                    var countInt = 0
                    var weightInt = 0

                    //Checking if the EditTexts have been inputted
                    if(countString != ""){
                        countInt = countString.toInt()
                    }
                    if(weightString != ""){
                        weightInt = weightString.toInt()
                    }

                    repCountIntList[i].add(countInt)
                    weightIntList[i].add(weightInt)
                }
            }



            workoutDataRepo.inputWorkoutData(object : WorkoutDataRepository.InputDataCallback {
                override fun onDataInputted() {
                    Toast.makeText(requireActivity(), "Success", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_InputSelectedWorkoutData_to_HomePage)
                }

                override fun onError(myString: String) {
                    Toast.makeText(requireActivity(), "Error, $myString", Toast.LENGTH_LONG).show()
                }
            }, repCountIntList, weightIntList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
