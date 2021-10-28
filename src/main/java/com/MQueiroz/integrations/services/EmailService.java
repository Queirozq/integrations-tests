package com.MQueiroz.integrations.services;

import com.MQueiroz.integrations.DTO.EmailDTO;

public interface EmailService {

    void sendEmail(EmailDTO email);
}
