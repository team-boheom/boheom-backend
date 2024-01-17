package com.example.boheom.domain.user.service

import com.example.boheom.domain.user.facade.UserFacade
import com.example.boheom.domain.user.presentation.dto.response.UploadProfileResponse
import com.example.boheom.global.utils.S3Utils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class UploadProfileService(
    private val s3Utils: S3Utils,
    private val userFacade: UserFacade,
) {
    @Transactional
    fun execute(file: MultipartFile): UploadProfileResponse {
        val user = userFacade.getCurrentUser()
        val fileUrl = s3Utils.upload(file)
        user.updateProfile(fileUrl)
        return UploadProfileResponse(fileUrl)
    }
}