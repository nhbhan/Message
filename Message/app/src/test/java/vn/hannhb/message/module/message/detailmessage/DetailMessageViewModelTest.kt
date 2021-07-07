package vn.hannhb.message.module.message.detailmessage

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import vn.hannhb.message.core.helpers.TimestampHelper
import vn.hannhb.message.module.message.domain.model.entities.Attachment
import vn.hannhb.message.module.message.domain.model.entities.Message
import vn.hannhb.message.module.message.domain.model.entities.Owner
import vn.hannhb.message.module.message.ui.DetailMessageViewModel

class DetailMessageViewModelTest {
    private var message: Message? = null
    @Before
    fun setUp() {
        val owner = Owner(
            avatar = "http://cdn.home-designing.com/wp-content/uploads/2019/10/wood-decking.jpg",
            fullName = "Nguyen Van A",
            email = "nguyenvana@gmail.com"
        )

        val attachments: MutableList<Attachment> = ArrayList()
        attachments.add(
            Attachment(
                height = 674,
                url = "http://cdn.home-designing.com/wp-content/uploads/2019/10/outdoor-pool.jpg",
                width = 1200
            )
        )
        attachments.add(
            Attachment(
                height = 800,
                url = "http://cdn.home-designing.com/wp-content/uploads/2019/10/sun-deck.jpg",
                width = 1200
            )
        )

        message = Message(id = 123)
        message?.totalComments = 5
        message?.title = "Title"
        message?.content = "Content"
        message?.owner = owner
        message?.attachmentList = attachments
    }

    @Test
    fun test_getAttachments() {
        val expectedResult = message?.attachmentList?.toMutableList()

        val model = DetailMessageViewModel()
        model.message = message
        val actualResult = model.getAttachments()

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun test_getTitle() {
        val expectedResult = message?.title

        val model = DetailMessageViewModel()
        model.message = message
        val actualResult = model.getTitle()

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun test_getContent() {
        val expectedResult = message?.content

        val model = DetailMessageViewModel()
        model.message = message
        val actualResult = model.getContent()

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun test_getTotalComments() {
        val expectedResult = message?.totalComments ?: 0

        val model = DetailMessageViewModel()
        model.message = message
        val actualResult = model.getTotalComments()

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun get_getNumberOfAttachments() {
        val expectedResult = message?.attachmentList?.size ?: 0

        val model = DetailMessageViewModel()
        model.message = message
        val actualResult = model.getNumberOfAttachments()

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun test_getAvatarUrl() {
        val expectedResult = message?.owner?.avatar ?: ""

        val model = DetailMessageViewModel()
        model.message = message
        val actualResult = model.getAvatarUrl()

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun test_getFullName() {
        val expectedResult = message?.owner?.fullName ?: "No Name"

        val model = DetailMessageViewModel()
        model.message = message
        val actualResult = model.getFullName()

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun test_getTime() {
        val expectedResult = TimestampHelper.getDateTime(message?.postTime, "dd/MM/yyyy")

        val model = DetailMessageViewModel()
        model.message = message
        val actualResult = model.getTime()

        Assert.assertEquals(expectedResult, actualResult)
    }
}
