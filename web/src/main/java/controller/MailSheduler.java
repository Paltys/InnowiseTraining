package controller;

import dao.Dao;
import entity.ContactEntity;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import service.impl.MailSender;

import javax.xml.bind.SchemaOutputResolver;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Configuration
@EnableScheduling
public class MailSheduler {

    @Autowired
    private final Dao<ContactEntity> contactDao;

    @Autowired
    private final MailSender mailSender;

    public MailSheduler(Dao<ContactEntity> contactDao, MailSender mailSender) {
        this.contactDao = contactDao;
        this.mailSender = mailSender;
    }

    @Scheduled(cron = "0 0 14 * * *")
    public void scheduleSendMail() {
        LocalDate date = LocalDate.now();
        int mounth = date.getMonthValue();
        int day = date.getDayOfMonth();
        List<ContactEntity> contactList = contactDao.getAll(20, 0);
        for (ContactEntity contact : contactList) {
            if (contact.getDataBirthday() != null && contact.getEmail() != null) continue;

            int birtdayMounth = LocalDate.ofInstant(contact.getDataBirthday(), ZoneId.systemDefault()).getMonthValue();
            int birthdayDay = LocalDate.ofInstant(contact.getDataBirthday(), ZoneId.systemDefault()).getDayOfMonth();
            if (birtdayMounth == mounth && birthdayDay == day) {
                mailSender.sendMail(contact.getEmail(), "HappyBirthday ", "Happy birthday" + contact.getFirstName() + " "
                        + contact.getLastName());
            }
        }
    }
}
