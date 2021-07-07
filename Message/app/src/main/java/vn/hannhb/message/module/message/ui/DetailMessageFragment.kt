package vn.hannhb.message.module.message.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder
import com.stfalcon.frescoimageviewer.ImageViewer
import kotlinx.android.synthetic.main.detail_message_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel
import vn.hannhb.Application
import vn.hannhb.message.R
import vn.hannhb.message.core.extensions.load
import vn.hannhb.message.core.ui.navigation.NavigationFragment
import vn.hannhb.message.module.message.domain.model.entities.Attachment

class DetailMessageFragment : NavigationFragment<DetailMessageViewModel>() {
    private var adapter: Adapter? = null

    private val args: DetailMessageFragmentArgs by navArgs()

    override val viewModel: DetailMessageViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_message_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun loadArguments() {
        super.loadArguments()
        viewModel.message = args.message
    }

    private fun init() {
        initToolbar()
        initRecyclerView()
        initAttachmentData()
    }

    private fun initToolbar() {
        toolbar?.title = ""
        setSupportActionBar(toolbar)
    }

    private fun initRecyclerView() {
        adapter = Adapter()
        attachmentRecyclerView?.layoutManager = GridLayoutManager(context, 4)
        attachmentRecyclerView?.adapter = adapter
        val headerLayout = DetailMessageHeaderLayout(context = context, viewModel = viewModel)
        adapter?.addHeaderView(headerLayout)
        val footerView = DetailMessageFooterLayout(context = context, viewModel = viewModel)
        adapter?.addFooterView(footerView)

        adapter?.setOnItemClickListener { _, _, position ->
            onAttachmentItemClicked(position)
        }
    }

    private fun initAttachmentData() {
        adapter?.data = viewModel.getAttachments()
        adapter?.notifyDataSetChanged()
    }

    private fun onAttachmentItemClicked(position: Int) {
        val item = adapter?.getItem(position) ?: return
        val urlImage = item.url ?: ""
        val hierarchyBuilder = GenericDraweeHierarchyBuilder.newInstance(resources)
            .setPlaceholderImage(R.drawable.ic_default_avatar)

        ImageViewer.Builder(
            context,
            ArrayList<String>().apply {
                add(urlImage)
            }
        )
            .setStartPosition(0)
            .setCustomDraweeHierarchyBuilder(hierarchyBuilder)
            .show()
    }

    inner class Adapter : BaseQuickAdapter<Attachment, BaseViewHolder>(R.layout.detail_message_attachment_item_layout, ArrayList()) {
        override fun convert(holder: BaseViewHolder, item: Attachment) {
            val imageView = holder.getView<ImageView>(R.id.attachmentImageView)
            imageView.load(context = Application.mContext, model = item.url)
        }
    }
}
