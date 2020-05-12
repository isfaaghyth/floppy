package app.isfaaghyth.uicomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.isfaaghyth.uicomponent.view.SampleFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, SampleFragment.init(), "FragmentSample")
                .commit()
        }
    }
}
