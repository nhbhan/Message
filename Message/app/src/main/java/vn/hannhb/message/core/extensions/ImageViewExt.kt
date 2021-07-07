package vn.hannhb.message.core.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.load(
    model: Any?,
    context: Context,
    options: RequestOptions? = null,
    placeholder: Drawable? = null
) {
    val requestBuilder = Glide.with(context).load(model)
    placeholder?.let { requestBuilder.placeholder(placeholder) }
    options?.let { requestBuilder.apply(it) }
    requestBuilder.into(this)
}
