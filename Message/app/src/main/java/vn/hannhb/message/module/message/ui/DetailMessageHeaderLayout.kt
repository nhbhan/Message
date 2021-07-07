package vn.hannhb.message.module.message.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.squareup.phrase.Phrase
import kotlinx.android.synthetic.main.detail_message_header_layout.view.*
import vn.hannhb.message.R

class DetailMessageHeaderLayout @JvmOverloads constructor(
    private val viewModel: DetailMessageViewModel,
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        initView()
        setData()
    }

    private fun initView() {
        View.inflate(context, R.layout.detail_message_header_layout, this)
    }

    private fun setData() {
        val name = viewModel.getFullName()
        val avatar = viewModel.getAvatarUrl()
        avatarView?.loadImage(
            name,
            avatar,
            ContextCompat.getDrawable(context, R.drawable.ic_default_avatar)
        )
        titleTextView?.text = viewModel.getTitle()
        contentTextView?.text = viewModel.getContent()
        attachmentTextView?.text = Phrase.from(context?.getString(R.string.attachment))
            .put("attachment", viewModel.getNumberOfAttachments().toString()).format().toString()
        timeTextView?.text = viewModel.getTime()
        nameTextView?.text = name
    }
}
