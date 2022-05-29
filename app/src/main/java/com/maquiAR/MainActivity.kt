package com.maquiAR

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.PixelCopy
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.maquiAR.arface.AugmentedFaceFragment
import com.maquiAR.arface.AugmentedFaceListener
import com.maquiAR.arface.AugmentedFaceNode
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), AugmentedFaceListener {

    private var contourChanged = false
    private var contourColor = floatArrayOf(0.7f, 0.5f, 0.38f, 1f)

    private var eyeshadowChanged = false
    private var eyeshadowColor = floatArrayOf(0.7f, 0.5f, 0.38f, 1f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (face_view as AugmentedFaceFragment).setAugmentedFaceListener(this)
        contour_group.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                chip_contour1.id -> changeContour(floatArrayOf(0.7f, 0.5f, 0.38f, 1f))
                chip_contour2.id -> changeContour(floatArrayOf(0f, 0f, 0f, 0f))
            }
        }
        eyes_group.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                chip_eye1.id -> changeEyeshadow(floatArrayOf(0.7686f, 0.5411f, 0.4f, 1f))
                chip_eye2.id -> changeEyeshadow(floatArrayOf(0.7f, 0.38f, 0.32f, 1f))
            }
        }
    }
  
    override fun onFaceAdded(face: AugmentedFaceNode) {
        face.setFaceMeshTexture(arrayListOf("models/contour.png", "models/lipstick.png"))
        face.setContourColor(contourColor)
        face.setEyeshadowColor(eyeshadowColor)
    }

    override fun onFaceUpdate(face: AugmentedFaceNode) {
        if (contourChanged) {
            contourChanged = false
            face.setContourColor(contourColor)
        }
        if (eyeshadowChanged) {
            eyeshadowChanged = false
            face.setEyeshadowColor(eyeshadowColor)
        }
    }

    private fun changeContour(color: FloatArray) {
        contourColor = color
        contourChanged = true
    }

    private fun changeEyeshadow(color: FloatArray) {
        eyeshadowColor = color
        eyeshadowChanged = true
    }

    private fun takePhoto(view: View) {
        view.getDrawingCache()
    }

    private fun getBitmapFromView(view: View, activity: Activity, callback: (Bitmap) -> Unit) {
        activity.window?.let { window ->
            val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            val locationOfViewInWindow = IntArray(2)
            view.getLocationInWindow(locationOfViewInWindow)
            try {
                PixelCopy.request(window, Rect(locationOfViewInWindow[0], locationOfViewInWindow[1], locationOfViewInWindow[0] + view.width, locationOfViewInWindow[1] + view.height), bitmap, { copyResult ->
                    if(copyResult == PixelCopy.SUCCESS) {
                        callback(bitmap)
                    }
                }, Handler(Looper.getMainLooper()))
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            }
        }
    }

}