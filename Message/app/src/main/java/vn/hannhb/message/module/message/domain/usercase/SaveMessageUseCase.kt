package vn.hannhb.message.module.message.domain.usercase

import vn.hannhb.message.module.message.data.repository.MessageRepository

class SaveMessageUseCase(private val messageRepository: MessageRepository) {
    suspend operator fun invoke(messageId: Int, favourite: Boolean) = messageRepository.saveMessage(messageId, favourite)
}
