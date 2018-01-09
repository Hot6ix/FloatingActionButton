package com.ybh.floatingactionbutton

import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AccelerateDecelerateInterpolator

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var isMenuCollapsed = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val constraintSet = ConstraintSet()
            constraintSet.clone(menu_layout)

            if(isMenuCollapsed) {
                // Set fab1's position to top of fab
                constraintSet.connect(fab1.id, ConstraintSet.BOTTOM, fab.id, ConstraintSet.TOP)
                // Set fab2's position to top of fab1
                constraintSet.connect(fab2.id, ConstraintSet.BOTTOM, fab1.id, ConstraintSet.TOP)
            }
            else {
                // Set fab1's position back to bottom
                constraintSet.connect(fab1.id, ConstraintSet.BOTTOM, fab.id, ConstraintSet.BOTTOM)
                // Set fab2's position back to bottom
                constraintSet.connect(fab2.id, ConstraintSet.BOTTOM, fab.id, ConstraintSet.BOTTOM)
            }
            val transition = AutoTransition()
            transition.duration = 300
            transition.interpolator = AccelerateDecelerateInterpolator()

            TransitionManager.beginDelayedTransition(menu_layout, transition)
            constraintSet.applyTo(menu_layout)

            isMenuCollapsed = !isMenuCollapsed
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
