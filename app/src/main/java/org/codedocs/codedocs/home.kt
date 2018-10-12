package org.codedocs.codedocs

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.util.EventLog
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.*
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.nostra13.universalimageloader.core.assist.QueueProcessingType
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder
import com.nostra13.universalimageloader.core.download.BaseImageDownloader
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*
import org.codedocs.codedocs.R.id.cvhe
import java.net.URL
import java.util.*
import kotlin.concurrent.timerTask
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener




// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [home.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [home.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class home : Fragment() {
    var db = FirebaseFirestore.getInstance()
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    var mView: View? = null
    var nname: String? = null
    var hdesc: String? = null
    var hname: String? = null
    var ndesc: String? = null
    var ncount: Int? = null
    var hcount: Int? = null
    var i: Int = 0
    var j: Int = 0
    var nurl:String?=null
    var hurl:String?=null


    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mView=inflater.inflate(R.layout.fragment_home, container, false)
        getnotifc()
        gethelc()
        return mView
    }

    
    fun getnotifc() {

        var notifs = db.collection("count").document("counts")
        notifs.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                ncount = Integer.parseInt(task.result.get("count").toString())
                Log.e("bleh2", "success")
                Log.e("bleh", "" + ncount)
                i = ncount as Int
                getnotis()

            }
        }


    }


    fun getnotis() {
        while (i in 1..2) {
            var notifs = db.collection("notifs").document(i.toString())
            notifs.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    nname=task.result.get("name").toString()
                    ndesc=task.result.get("desc").toString()
                    nurl=task.result.get("url").toString()
                    Log.e("bleh5",nname)
                    Log.e("bleh6",ndesc)
                    tvn(nname!!)

                    tvn(ndesc!!)


                }

            }
            i--
        }


    }
    fun tvn(str:String){
        val tvt=TextView(getContext())
        tvt.setText(str)
        tvt.setHeight(100)
        tvt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25f)
        tvt.setTextColor(Color.parseColor("#817fa0"))
        llno.addView(tvt)

    }
    fun gethelc(){
        var notifs = db.collection("hcount").document("count")
        notifs.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                hcount = Integer.parseInt(task.result.get("count").toString())
                Log.e("bleh2", "success")
                Log.e("bleh", "" + hcount)
                j = hcount as Int
                gethels()


            }

    }
}
    fun gethels() {
        while (j in 1..2) {
            var hl = db.collection("hlights").document(j.toString())
            hl.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    hname=task.result.get("name").toString()
                    hdesc=task.result.get("desc").toString()
                    hurl=task.result.get("url").toString()
                    Log.e("bleh5",hname)
                    Log.e("bleh6",hdesc)
                    tvh(hname!!)
                    tvh(hdesc!!)
                }

            }
            i--
        }


    }
    fun tvh(str:String){
        val tvt=TextView(getContext())
        tvt.setText(str)
        tvt.setHeight(100)
        tvt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25f)
        tvt.setTextColor(Color.parseColor("#817fa0"))
        llhe.addView(tvt)

    }

}
