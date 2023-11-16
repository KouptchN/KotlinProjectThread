package be.heh.kotlinproject2

import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast

public class BackgroundTask {
    private val MESSAGE_PRE_EXECUTE = 1
    private val MESSAGE_PROGRESS_UPDATE = 2
    private val MESSAGE_POST_EXECUTE = 3
    private var pb_main_progressionThHa1: ProgressBar? = null
    private var bt_main_startThHa: Button? = null
    private var vi_main_ui: View? = null
    constructor(v: View, b: Button, p: ProgressBar) {
        pb_main_progressionThHa1 = p
        bt_main_startThHa = b
        vi_main_ui = v
    }
    fun Start(){
        if (!monThread.isAlive()) monThread.start();
    }
    private fun downloadPreExecute() {
        bt_main_startThHa?.setVisibility(View.GONE)
// l'opérateur d'assertion non nul "!!" convertit toute valeur
// en un type non nul et lève une exception si la valeur est null
        pb_main_progressionThHa1!!.visibility = View.VISIBLE
        Toast.makeText(
            vi_main_ui?.getContext(),
            "Le traitement de la tâche de fond est démarré !",
            Toast.LENGTH_SHORT
        ).show()
    }
    private fun downloadProgressUpdate(progress: Int) {
        Log.i("var progress : ", progress.toString())
        pb_main_progressionThHa1!!.progress = progress
        Log.i("pb_main_progression: ", pb_main_progressionThHa1!!.progress.toString())
    }
    private fun downloadPostExecute() {
        Toast.makeText(
            vi_main_ui?.getContext(),
            "Le traitement de la tâche de fond est terminé !!!",
            Toast.LENGTH_SHORT
        ).show()
        bt_main_startThHa?.setVisibility(View.VISIBLE)
        pb_main_progressionThHa1!!.visibility = View.GONE
    }
    private val monHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                MESSAGE_PRE_EXECUTE -> downloadPreExecute()
                MESSAGE_PROGRESS_UPDATE -> downloadProgressUpdate(msg.arg1)
                MESSAGE_POST_EXECUTE -> downloadPostExecute()
                else -> {}
            }
        }
    }
    private var monThread = Thread(){
        try {
            sendPreExecuteMessage()
            for (i in 0..19) {
                sendProgressMessage(i)
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            sendPostExecuteMessage()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    private fun sendPostExecuteMessage() {
        val postExecuteMessage = Message()
        postExecuteMessage.what = MESSAGE_POST_EXECUTE
        monHandler.sendMessage(postExecuteMessage)
    }
    private fun sendPreExecuteMessage() {
        val preExecuteMessage = Message()
        preExecuteMessage.what = MESSAGE_PRE_EXECUTE
        monHandler.sendMessage(preExecuteMessage)
    }
    private fun sendProgressMessage(i: Int) {
        val progressMessage = Message()
        progressMessage.what = MESSAGE_PROGRESS_UPDATE
        progressMessage.arg1 = i * 10
        monHandler.sendMessage(progressMessage)
    }
}