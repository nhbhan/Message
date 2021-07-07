package vn.hannhb.message.module.message.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.squareup.phrase.Phrase
import kotlinx.android.synthetic.main.detail_message_footer_layout.view.*
import vn.hannhb.message.R

class DetailMessageFooterLayout @JvmOverloads constructor(
    private val viewModel: DetailMessageViewModel,
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        initView()
        initEventListeners()
        setText()
    }

    private fun initView() {
        View.inflate(context, R.layout.detail_message_footer_layout, this)
    }

    private fun setText() {
        commentTextView?.text = Phrase.from(context?.getString(R.string.comment))
            .put("comment", viewModel.getTotalComments().toString()).format().toString()
    }

    private fun initEventListeners() {
        replyButton?.setOnClickListener { }

        forwardButton?.setOnClickListener { }
    }
}
