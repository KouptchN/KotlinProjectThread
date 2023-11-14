package be.heh.kotlinproject2

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {
    lateinit var monThread : ThreadTest
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun xmlClicEvent(v:View){
        when(v.id){
            bt_main_txtchange.id->bpModifTexte()
            bt_main_startTH.id->bpThreadGo()
        }
    }
    private fun bpModifTexte(){
        if(tv_main_texte.getText().equals("Hello World !")) tv_main_texte.setText("Hey Android !")
        else tv_main_texte.setText("Hello World !")
    }
    private fun bpThreadGo() {
        if (bt_main_startTH.getText().equals("Thread GO !")) {
            monThread = ThreadTest(pb_main_progressionTH)
            monThread.Go()
            bt_main_startTH.setText("Thread Stop !")
            Toast.makeText(getApplicationContext(), "Activation du Thread", Toast.LENGTH_LONG)
                .show()
        }
        else {
            monThread.Stop()
            Toast.makeText(getApplicationContext(), "Désactivation du Thread", Toast.LENGTH_LONG)
                .show()
            bt_main_startTH.setText("Thread GO !")
        }
    }
}