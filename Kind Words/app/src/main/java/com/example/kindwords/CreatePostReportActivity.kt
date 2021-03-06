package com.example.kindwords

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

/*
 Create a new Report for a post (offensive content)
 */
class CreatePostReportActivity : AppCompatActivity() {
    private lateinit var mReport : Report
    private lateinit var postId: String
    private lateinit var authorId: String
    private lateinit var subjectView: TextView
    private lateinit var messageView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_report)
        actionBar?.hide()
        subjectView = findViewById(R.id.subject)
        messageView = findViewById(R.id.message)
        postId = intent.getStringExtra("postId") as String
        authorId = intent.getStringExtra("authorId") as String
    }

    // ensure all fields of the post are filled
    private fun validateFields(): Boolean {
        if (subjectView.text.toString().replace(" ", "") == "" ||
            messageView.text.toString().replace(" ", "") == ""
        ) { return false; }
        return true
    }

    // submit the report to the database
    fun addReportToDatabase(view: View) {
        if (validateFields()) {
            mReport = Report(subjectView.text.toString() , messageView.text.toString())
            mReport.submitPostReport(postId, authorId)
            Toast.makeText(applicationContext, "Report was successful!", Toast.LENGTH_LONG).show()
            finish()
        } else {
            Toast.makeText(applicationContext, "report was not successful. Make sure the subject" +
                    "and body are not empty!", Toast.LENGTH_LONG).show()
        }

    }

    // return to the home page
    fun goToHome(view: View) {
        val i = Intent(this@CreatePostReportActivity, HomeActivity::class.java )
        startActivity(i)
    }


}