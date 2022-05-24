package com.example.projection.view.screen.project.detail

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

/*
 * Based on code by https://github.com/ddekanski and https://gist.github.com/yrajabi
 * See: https://gist.github.com/yrajabi/5776f4ade5695009f87ce7fcbc08078f
 */

class GlideImageGetter(
    textView: TextView,
    densityAware: Boolean = true
) : ImageGetter {

    private val container: WeakReference<TextView>
    private var density = 1.0f

    init {
        container = WeakReference(textView)
        if (densityAware) {
            container.get()?.let {
                density = it.resources.displayMetrics.density
            }
        }
    }

    override fun getDrawable(source: String): Drawable {
        val drawable = BitmapDrawablePlaceholder()
        container.get()?.apply {
            post {
                Glide.with(container.get()!!.context)
                    .asBitmap()
                    .load(source)
                    .into(drawable)
            }
        }
        return drawable
    }

    private inner class BitmapDrawablePlaceholder : BitmapDrawable(
        container.get()?.resources,
        Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
    ), Target<Bitmap> {

        var drawable: Drawable? = null
            set(value) {
                field = value
                value?.let { drawable ->
                    val drawableWidth = drawable.intrinsicWidth
                    val drawableHeight = drawable.intrinsicHeight

                    val maxWidth = container.get()!!.measuredWidth
                    if (drawableWidth > maxWidth) {
                        val calculatedHeight = maxWidth * drawableHeight / drawableWidth
                        drawable.setBounds(0, 0, maxWidth, calculatedHeight)
                        setBounds(0, 0, maxWidth, calculatedHeight)
                    } else {
                        drawable.setBounds(0, 0, drawableWidth, drawableHeight)
                        setBounds(0, 0, drawableWidth, drawableHeight)
                    }

                    container.get()?.let { it.text = it.text }
                }
            }

        override fun draw(canvas: Canvas) {
            drawable?.draw(canvas)
        }

        override fun onLoadStarted(placeholderDrawable: Drawable?) {
            placeholderDrawable?.let { drawable = it }
        }

        override fun onLoadFailed(errorDrawable: Drawable?) {
            errorDrawable?.let { drawable = it }
        }

        override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
            drawable = BitmapDrawable(container.get()!!.resources, bitmap)
        }

        override fun onLoadCleared(placeholderDrawable: Drawable?) {
            placeholderDrawable?.let { drawable = it }
        }

        override fun getSize(cb: SizeReadyCallback) {
            cb.onSizeReady(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
        }

        override fun removeCallback(cb: SizeReadyCallback) {}
        override fun setRequest(request: Request?) {}
        override fun getRequest(): Request? = null

        override fun onStart() {}
        override fun onStop() {}
        override fun onDestroy() {}
    }
}
