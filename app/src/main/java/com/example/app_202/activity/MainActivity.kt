package com.example.app_202.activity

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.appcompat.widget.*
import androidx.core.content.ContextCompat
import com.example.app_202.R
import com.example.app_202.util.toast
import com.google.android.material.button.MaterialButton
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    private val languages = arrayOf("Java", "Kotlin", "JavaScript", "C++", "C", "Python")

    private var pos = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        val btnOpen: MaterialButton = findViewById(R.id.btnOpen)
        btnOpen.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
        radioGroup()
        autoTextComplete()
        checkBox()
        ratingBar()
        spinner()
        switch()
        progressDialog()
        textSwitcher()
        checkedTextView()
        customCheckBox()
    }

    private fun radioGroup() {
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioRed -> radioGroup.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.red
                    )
                )
                R.id.radioBlue -> radioGroup.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.blue
                    )
                )
                R.id.radioGreen -> radioGroup.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.green
                    )
                )
                R.id.radioYellow -> radioGroup.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.yellow
                    )
                )
            }
        }

    }

    private fun autoTextComplete() {
        val autoCompleteTextView: AppCompatAutoCompleteTextView =
            findViewById(R.id.autoTextComplete)
        val multiAutoCompleteTextView: AppCompatMultiAutoCompleteTextView =
            findViewById(R.id.multiComplete)

        val arrAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, languages)
        autoCompleteTextView.setAdapter(arrAdapter)
        multiAutoCompleteTextView.setAdapter(arrAdapter)
        multiAutoCompleteTextView.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())
    }

    private fun checkBox() {
        val btnClick: MaterialButton = findViewById(R.id.btnClick)
        val checkJava: CheckBox = findViewById(R.id.checkJava)
        val checkKotlin: CheckBox = findViewById(R.id.checkKotlin)
        val checkAndroid: CheckBox = findViewById(R.id.checkAndroid)

        btnClick.setOnClickListener {
            when {
                checkAndroid.isChecked && checkJava.isChecked && checkKotlin.isChecked -> {
                    toast("Java, Kotlin, Android")
                }
                checkAndroid.isChecked && checkKotlin.isChecked -> {
                    toast("Android, Kotlin")
                }
                checkKotlin.isChecked && checkJava.isChecked -> {
                    toast("Kotlin, Java")
                }
                checkAndroid.isChecked && checkJava.isChecked -> {
                    toast("Android, Java")
                }
                checkAndroid.isChecked -> {
                    toast("Android")
                }
                checkJava.isChecked -> {
                    toast("Java")
                }
                checkKotlin.isChecked -> {
                    toast("Kotlin")
                }
                else -> toast("Nothing!")

            }
        }

    }

    private fun ratingBar() {
        val btnClick: MaterialButton = findViewById(R.id.btnRate)
        val ratingBar: AppCompatRatingBar = findViewById(R.id.ratingBar)
        btnClick.setOnClickListener {
            toast("${ratingBar.rating}")
        }
    }

    private fun spinner() {
        val spinner: AppCompatSpinner = findViewById(R.id.spinner)
        val arrAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, languages)
        spinner.adapter = arrAdapter

    }

    private fun switch() {
        val switcher: SwitchCompat = findViewById(R.id.switchBtn)
        switcher.setOnClickListener {
            if (switcher.isChecked)
                toast("On")
            else
                toast("Off")
        }
    }

    private fun progressDialog() {
        val progressDialog = ProgressDialog(this)
        val btnClick: AppCompatButton = findViewById(R.id.btnStart)

        btnClick.setOnClickListener{
            progressDialog.apply {
                setTitle("Progress Dialog")
                setMessage("Loading...")
                show()
            }
        }
    }

    private fun textSwitcher() {
        val textSwitcher: TextSwitcher = findViewById(R.id.textSwitcher)
        val btnNext: MaterialButton = findViewById(R.id.btnNext)
        val btnBack: MaterialButton = findViewById(R.id.btnBack)

        textSwitcher.setFactory {
            val textView = TextView(this@MainActivity)
            textView.textSize = 30f
            textView.gravity = Gravity.CENTER
            textView
        }
        btnBack.setOnClickListener {
            if (pos > 0){
                pos -= 1
                textSwitcher.setText(languages[pos])
            }
        }
        btnNext.setOnClickListener {
            if (pos <languages.size -1) {
                pos += 1
                textSwitcher.setText(languages[pos])
            }
        }
    }

    private fun checkedTextView() {
        val checkedTextView: CheckedTextView = findViewById(R.id.ctv)
        checkedTextView.setOnClickListener {
            checkedTextView.toggle()
        }
    }

    @SuppressLint("WrongViewCast")
    private fun customCheckBox() {
        val chBoxJava: AppCompatCheckBox = findViewById(R.id.checkJava)
        val chBoxKotlin: AppCompatCheckBox = findViewById(R.id.checkKotlin)
        val btnCheckClick: AppCompatButton = findViewById(R.id.checkBtnClick)

        btnCheckClick.setOnClickListener {
            val  sb = StringBuilder()
            if (chBoxJava.isChecked){
                sb.append("Java")
            }
            if (chBoxKotlin.isChecked){
                sb.append("Kotlin")
            }
            if (sb.isNotEmpty()) {
                toast(sb.toString())
            }else {
                toast("Nothing")
            }
        }
    }




}