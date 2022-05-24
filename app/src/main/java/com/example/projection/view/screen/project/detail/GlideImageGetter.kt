package com.example.projection.view.screen.project.detail

import kotlin.jvm.JvmOverloads
import android.widget.TextView
import android.text.Html.ImageGetter
import com.bumptech.glide.Glide
import android.graphics.drawable.BitmapDrawable
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.target.SizeReadyCallback
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import java.lang.ref.WeakReference

class GlideImageGetter @JvmOverloads constructor(
    textView: TextView,
    private var matchParentWidth: Boolean = false,
    densityAware: Boolean = false,
    private var imagesHandler: HtmlImagesHandler? = null
) : ImageGetter {

    private val container: WeakReference<TextView>
    private var density = 1.0f

    override fun getDrawable(source: String): Drawable {
        imagesHandler?.addImage(source)
        val drawable = BitmapDrawablePlaceholder()
        container.get()!!.post {
            Glide.with(container.get()!!.context)
                .asBitmap()
                .load(source)
                .into(drawable)
        }
        return drawable
    }

    private inner class BitmapDrawablePlaceholder internal constructor() : BitmapDrawable(
        container.get()!!.resources,
        Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
    ), Target<Bitmap> {

        var drawable: Drawable? = null

        override fun draw(canvas: Canvas) {
            if (drawable != null) {
                drawable!!.draw(canvas)
            }
        }

        private fun applyDrawable(drawable: Drawable) {
            this.drawable = drawable
            val drawableWidth = (drawable.intrinsicWidth * density).toInt()
            val drawableHeight = (drawable.intrinsicHeight * density).toInt()
            val maxWidth = container.get()!!.measuredWidth
            if (drawableWidth > maxWidth || matchParentWidth) {
                val calculatedHeight = maxWidth * drawableHeight / drawableWidth
                drawable.setBounds(0, 0, maxWidth, calculatedHeight)
                setBounds(0, 0, maxWidth, calculatedHeight)
            } else {
                drawable.setBounds(0, 0, drawableWidth, drawableHeight)
                setBounds(0, 0, drawableWidth, drawableHeight)
            }
            container.get()!!.text = container.get()!!.text
        }

        override fun onLoadStarted(placeholderDrawable: Drawable?) {
            placeholderDrawable?.let { applyDrawable(it) }
        }

        override fun onLoadFailed(errorDrawable: Drawable?) {
            errorDrawable?.let { applyDrawable(it) }
        }

        override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
            applyDrawable(BitmapDrawable(container.get()!!.resources, bitmap))
        }

        override fun onLoadCleared(placeholderDrawable: Drawable?) {
            placeholderDrawable?.let { applyDrawable(it) }
        }

        override fun getSize(cb: SizeReadyCallback) {
            cb.onSizeReady(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
        }

        override fun removeCallback(cb: SizeReadyCallback) {}
        override fun setRequest(request: Request?) {}
        override fun getRequest(): Request? {
            return null
        }

        override fun onStart() {}
        override fun onStop() {}
        override fun onDestroy() {}
    }

    interface HtmlImagesHandler {
        fun addImage(uri: String?)
    }

    init {
        container = WeakReference(textView)
        this.matchParentWidth = matchParentWidth
        this.imagesHandler = imagesHandler
        if (densityAware) {
            density = container.get()!!.resources.displayMetrics.density
        }
    }
}
