package education.hry.pkl.cricket11.utility

import android.app.ProgressDialog
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity : AppCompatActivity() {

    var mProgressDialog: ProgressDialog? = null

    override fun onStart() {
        super.onStart()
        initData()
        initListeners()
    }

    abstract fun initData()
    abstract fun initListeners()


    protected fun hideKeyboard() {
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }


    /**
     * ********** Dismiss Progress Dialog **********
     */

    fun progressDialogDismiss() {
        if (mProgressDialog != null) {
            mProgressDialog!!.dismiss()
            mProgressDialog = null
        }
    }

    fun progressDialogShowDisable(message: String) {
        mProgressDialog = ProgressDialog(this)
        mProgressDialog!!.setMessage(message)
        mProgressDialog!!.setCancelable(true)
        mProgressDialog!!.show()

    }

}