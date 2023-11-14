package be.heh.kotlinproject2

import android.os.AsyncTask
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast

class AsyncroTask : AsyncTask<String, Int, String> {
    private var pb_main_progressionAS: ProgressBar? = null
    private var bt_main_startAS: Button? = null
    private var vi_main_ui: View? = null
    constructor(v: View?, b: Button?, p: ProgressBar?) {
        pb_main_progressionAS = p
        bt_main_startAS = b
        vi_main_ui = v
    }
    override fun onPreExecute() {
        super.onPreExecute()
// Mise à jour de l'interface
        bt_main_startAS?.setVisibility(View.GONE)
        pb_main_progressionAS!!.visibility = View.VISIBLE
        Toast.makeText(
            vi_main_ui?.getContext(), "Démarrage de la tâche de fond AsyncTask",
            Toast.LENGTH_LONG)
            .show()
    }
    override fun doInBackground(vararg params: String?): String? {
        Log.i("Paramètre : ", params[0].toString() + params[1].toString())
        var result = ""
//remplissage de la barre de progression avec onProgressUpdate() !
        for (i in 0..19 ) {
//pb_main_progressionAS!!.progress=(i*5)
            onProgressUpdate(i * 5)
            result += i.toString()
            SystemClock.sleep(500)
        }
        return result
    }
    fun onProgressUpdate(progress: Int) {
//super.onProgressUpdate(progress)
        pb_main_progressionAS!!.setProgress(progress)
    }
    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        Toast.makeText(
            vi_main_ui?.getContext(), "Fin de l'exécution de la tâche de fond AsyncTask"
                    + result,
            Toast.LENGTH_LONG)
            .show()
// Mise à jour de l'interface
        pb_main_progressionAS!!.visibility = View.GONE
        bt_main_startAS?.setVisibility(View.VISIBLE)
    }
}